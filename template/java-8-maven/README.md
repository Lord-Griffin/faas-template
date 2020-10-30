## Template: java-8-maven

The Java 8 template uses maven as a build system.

Maven version: 3.6.3

Java version: Oracle OpenJDK 8 update 272

### Structure

There are three projects which make up a single build:

- model - (Library) classes for parsing request/response
- function - (Library) your function code as a developer, you will only ever see this folder
- entrypoint - (App) HTTP server for re-using the JVM between requests

### Handler

The handler is written in the `./src/main/java/me/mith/openfaas/function/Handler.java` folder

Tests are supported with junit via files in `./src/test`

### External dependencies

External dependencies can be specified in ./pom.xml in the normal way using maven central or some other remote repository.

