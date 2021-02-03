# Maven

Maven is a dependency management tool as well as a build automation tool.

On the dependency management side, Maven has a series of cloud repositories that houses many different public libraries. This makes easy for Maven to automatically download and include dependencies for your project and organize them according to their versions.

On the build automation side, Maven provides a CLI, with the `mvn` command, that allows to perform actions in regards to the project's build lifecycle. For example, `mvn compile`, `mvn test`, `mvn package`, `mvn deploy`, etc.

You have a local maven repository, that is located in your user profile home folder (e.g `C:/Users/Username`), called `.m2/repository`. This local repository houses the variety of different dependencies that you have needed across multiple projects working with Maven. Note that this can get pretty big.

There are also cloud/remote repositories that Maven can download the dependencies from. The default remote repository is `mvnrepository.com`.

Maven organizes/represents projects as a Project Object Model.

## Maven Lifecycle

Maven has 3 build lifecycles: default, clean, site. The clean lifecycle has to do with deleting any old compiled artifacts (such as the content of the `target/` or `bin/` directories). The site lifecycle has to do with generating your site documentation for the project.

The most important is the default lifecycle.

- Validate
- Compile
- Test
- Package
- Verify
- Install
- Deploy