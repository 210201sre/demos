# Jenkins

Jenkins in an Open-Source Automation Server. It helps automate the building, testing, and deployment of an application for continuous integration and delivery.

It supports the development of CI/CD or DevOps "Pipelines".

We describe them as pipelines, because our application must go through every step of the pipeline in order to be successfully deployed.

Common steps might be: Compile, test, sonarcloud quality gates, perhaps more tests (like integration tests), build docker image, and deploy docker image.

## Jenkins Jobs

A Jenkins "Job" represents a single "Pipeline". It is used for one project, to build/test/deploy etc, generally linked with only 1 GitHub repo.

## Jenkins Agents

An Agent is something that will perform an action on behalf of someone/something else. The details depends on what it is an agent _for_. Jenkins is an Automation _Server_. Which means Jenkins Agents can be _other_ servers. They can perform similar tasks, but they will all be managed by the primary Jenkins Server.

In other contexts, for example fluentd, the agents are not entire servers, they are simply individual containers.

In our case with Jenkins, we could horizontally scale Jenkins' functionality by configuring additional servers as Jenkins agents. It is also possible to have short-lived Pods in a Kubernetes cluster that can act as Jenkins agents, before being removed.

Each agent (along with the primary jenkins server) will perform the actions configured in different Jenkins Jobs. The primary Jenkins server decides which agent (if any) will be responsible for which currently running jobs. This can change over time, a single job is never automatically associated with a Jenkins agent.

These agents are used to delegate certain tasks when needed. It all comes down to the usefulness of horizontal scaling.

### Misc

The initial plan is to execute shell commands in order to perform our pipeline.

```bash
chmod +x mvnw
./mvnw clean package
./mvnw spring-boot:build-image
docker tag log-aggregation-demo:0.0.1-SNAPSHOT ikenoxamos/project1:latest
```

The above script starts off by setting permissions on the maven wrapper executable, so that jenkins can execute it.

It follows up by packaging the application. The `package` phase will also result in our unit tests being executed.

And finally, we build a docker image and tag it.

Note that unless Jenkins is part of the docker group, the mvn build image command will fail and so will the `docker tag` command.

## Scripted Pipelines

Jenkins supports creating `Jenkinsfile`s. These are files that use `Groovy` syntax to define how the pipeline will operate. Instead of configuring the Jenkins Job through the UI, we instead configure it through the use of this file. This has benefits in that we can track changes to the pipeline configuration with Git.

This follows a common/popular tenant/practice of "Configuration as Code". This means, we represent the configuration of a software system as part of its codebase. This allows us to audit the configuration in the same way we can audit the rest of our sourcecode.

Jenkinsfiles are how Jenkins supports the idea of Configuration as Code.

`Groovy` is a language that has many similarities with Java.
It also supports a JSON like structure to specify scopes. It however is not exactly JSON.

We often begin our Jenkinsfiles by specifying which agent we will use. In our case, we will use `any` to indicate to the Jenkins master to use whatever is available.

All of the Jenkinsfile configuration for the pipeline is underneat a `pipeline` scope.

We then specify multiple `stages` as different scopes. Each stage represents a corresponding step of the entire pipeline. This is useful, because each stage can have its own configuration.

```groovy
pipeline {
    agent any

    stages {
        stage('Build') {
            // The syntax within is not exactly bash commands
            // But it has many instructions that translate to bash commands

            // echo instruction - The same as the echo command in bash
            echo 'Building application'

            // If you do want to run a generic bash command, there is the 'sh' instruction
            sh 'mvn clean package'
        }

        stage('Test') {
            // The available instructions can be extended through plugins
            // For example, we can add a docker plugin
            // After, we can have syntax such as
            script {
                app = docker.build('some-image-name')
            }
            
            // We can refer to this 'app' variable in later stages
        }
        // Etc etc
        // Note, that Groovy has the same comment syntax as Java

        stage('Deploy') {
            // I forget the exact scenarios that require a script scope
            // I think the docker comamnds require it
            script {
                docker.withRegistry('some registry url', 'credential name') {
                    app.push('some-tag')
                }
            }
        }
    }
}
```