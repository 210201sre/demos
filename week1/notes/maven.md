# Maven

Maven is a dependency management tool as well as a build automation tool.

On the dependency management side, Maven has a series of cloud repositories that houses many different public libraries. This makes easy for Maven to automatically download and include dependencies for your project and organize them according to their versions.

On the build automation side, Maven provides a CLI, with the `mvn` command, that allows to perform actions in regards to the project's build lifecycle. For example, `mvn compile`, `mvn test`, `mvn package`, `mvn deploy`, etc.

You have a local maven repository, that is located in your user profile home folder (e.g `C:/Users/Username`), called `.m2/repository`. This local repository houses the variety of different dependencies that you have needed across multiple projects working with Maven. Note that this can get pretty big.

There are also cloud/remote repositories that Maven can download the dependencies from. The default remote repository is `mvnrepository.com`.

Maven organizes/represents projects as a Project Object Model. This is configured through an xml configuration file called `pom.xml`. That has all the key-value pairs that constitute the configuration for the project.

## Maven Lifecycle

Maven has 3 build lifecycles: default, clean, site. The clean lifecycle has to do with deleting any old compiled artifacts (such as the content of the `target/` or `bin/` directories). The site lifecycle has to do with generating your site documentation for the project.

The most important is the default lifecycle.

- Validate
    - Validate the project is correct and all necessary information is available
    - Validate the contents of `pom.xml` and verify that the configuration is correct and all dependencies are available
- Compile
    - Compile the source code of the project
- Test
    - Test the compiled source code using a suitable unit testing framework
    - These tests should not require the code be packaged or deployed
    - In particular, we are using JUnit
    - Unit tests should be completely independent, and only test a single unit
- Package
    - Take the compiled code and package it in its distributable format, such as a JAR or WAR
    - JAR = Java Archive
    - WAR = Web Archive
- Verify
    - Run any checks on results of Integration Tests to ensure quality criteria are met
    - Integration Tests are bigger than Unit Tests and integrate multiple components together
- Install
    -  Install the package into the local repository, for use as a dependency in other projects locally
    - Put in the `~/.m2/repository`
    - `~` refers to your user profile home folder
- Deploy
    - Done in the build environment, copies the final package to the remote repository for sharing with other developers and projects
    - Such as to `mvnrepository.com`