# Spring Boot Jpa Demo

This project is a [Spring boot](https://spring.io/projects/spring-boot) (Java) project, used for build backend service quickly.


* Support table generate automated if table not created
* Support database password encryption by [Jasypt](http://www.jasypt.org/)
* Support scheduled task
* Support manual trigger task
* Support unified interface pre-processing
* Support docker build and run
* Support access permission set by jwt
 

## Getting Started

These instructions will guide you how to run the service.

### Prerequisites

What things you need to install the software and how to install them

* docker

### Installing

install docker: https://docs.docker.com/engine/install/


### Create base build && run image 

```
make build-image # build maven-jdk8 image 
```

### Build target jar

#### Local build
```
mvn clean package # build target jar
```
#### Docker build
```
make build # build target jar
```

### Run backend service

```
make run # run backend service by docker
```

## Coding style

We use "Google Java Style Guide" as coding style guide:

* Official: https://google.github.io/styleguide/javaguide.html
* Chinese version: https://jervyshi.gitbooks.io/google-java-styleguide-zh/content/

### Installing the coding style settings in Intellij

1. Download the [intellij-java-google-style.xml](https://raw.githubusercontent.com/google/styleguide/gh-pages/intellij-java-google-style.xml) file from the http://code.google.com/p/google-styleguide/ repo.
2. go into Preferences -> Editor -> Code Style. Click on Manage and import the downloaded Style Setting file. Select GoogleStyle as new coding style.

### Installing the coding style settings in Eclipse

1. Download the [eclipse-java-google-style.xml](https://raw.githubusercontent.com/google/styleguide/gh-pages/eclipse-java-google-style.xml) file from the http://code.google.com/p/google-styleguide/ repo. 
2. Under Window/Preferences select Java/Code Style/Formatter. Import the settings file by selecting Import.

## Contributing

Please read [使用git进行代码提交、审查、合并流程](https://github.com/agis/git-style-guide) for details on our code of conduct, and the process for submitting pull requests to us.

## Workflow

* [GitHub Flow](https://help.github.com/en/articles/github-flow)

## License

This project is copyright by Rivtower Co., Ltd. All rights reserved.

## Acknowledgments

* You should know how to use Maven.
* You should know how to use Java.
* You should know how to use Mysql.
