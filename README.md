# :dog: pochi

[![build](https://github.com/tamada/pochi/workflows/build/badge.svg)](https://github.com/tamada/pochi/actions?query=workflow%3Abuild)
[![Coverage Status](https://coveralls.io/repos/github/tamada/pochi/badge.svg?branch=main)](https://coveralls.io/github/tamada/pochi?branch=main)
[![codebeat badge](https://codebeat.co/badges/8e8c5e70-cb07-4f58-941c-3ddb64f3c059)](https://codebeat.co/projects/github-com-tamada-pochi-main)

[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg)](https://github.com/tamada/pochi/blob/master/LICENSE)
[![Version](https://img.shields.io/badge/Version-2.4.6-green.svg)](https://github.com/tamada/pochi/releases/tag/v2.4.6)
[![DOI](https://img.shields.io/badge/DOI-10.5281/zenodo.4271132-green.svg)](https://zenodo.org/badge/latestdoi/82773287)

[![Javadoc](https://img.shields.io/badge/Javadoc-v2.4.6-blue?logo=java)](https://tamada.github.io/pochi/apidocs)
[![Docker](https://img.shields.io/badge/Docker-ghcr.io%2Ftamada%2Fpochi%3A2.4.6-blue?logo=docker)](https://github.com/users/tamada/packages/container/package/pochi)
[![GitHub Discussion](https://img.shields.io/badge/GitHub-Discussions-blue?logo=github)](https://github.com/tamada/pochi/discussions)

Detecting the software theft, the birthmark toolkit for the JVM platform.

**pochi** is the birthmarking toolkit for the JVM platform.
The birthmarks are the native characteristics extracted from executable programs.
Then, we compare them and computes the similarities.
The resultant similarities shows the copy relation possibilities between two programs.

## :bookmark: Table of Contents in the Web page of pochi

* [:books: Birthmarks](https://tamada.github.io/pochi/birthmarks)
  - [:green_book: Definition of Birthmarks](https://tamada.github.io/pochi/birthmarks#-definition-of-birthmarks)
  - [:blue_book: Types of Birthmarks](https://tamada.github.io/pochi/birthmarks#-types-of-birthmarks)
  - [:orange_book: Similarities](https://tamada.github.io/pochi/birthmarks#-similarities)
  - [:closed_book: Theft detection process by birthmarks](https://tamada.github.io/pochi/birthmarks#-theft-detection-process-by-birthmarks)
* :newspaper: What is **pochi**
  - [:key: Key idea](https://tamada.github.io/pochi/description#-key-idea)
  - :fork_and_knife: Usage
    - [:runner: CLI Interface](https://tamada.github.io/pochi/description#-cli-interface)
    - [:whale: Docker](https://tamada.github.io/pochi/description#-docker)
  - [:swimmer: The birthmarking flow](https://tamada.github.io/pochi/description#-the-birthmarking-flow)
* :anchor: Install
  - [:beer: Homebrew](https://tamada.github.io/pochi/install#-homebrew)
  - [:muscle: Compiling pochi yourself](https://tamada.github.io/pochi/install#-compiling-pochi-yourself)
  - [:package: Maven repository](https://tamada.github.io/pochi/install#-maven-repository)
  - :briefcase: Requirements
    - [:pouch: Modules](https://tamada.github.io/pochi/install#-modules)
* :ant: Examples
  - [:one: `printing_args.groovy`](https://tamada.github.io/pochi/examples#1-printing_argsgroovy)
  - [:two: `printing_pochi_info.groovy`](https://tamada.github.io/pochi/examples#2-printing_pochi_infogroovy)
  - [:three: `extracting_birthmarks.groovy`](https://tamada.github.io/pochi/examples#3-extracting_birthmarksgroovy)
  - [:four: `filtering_source.groovy`](https://tamada.github.io/pochi/examples#4-filtering_sourcegroovy)
  - [:five: `comparing_birthmarks.groovy`](https://tamada.github.io/pochi/examples#5-comparing_birthmarksgroovy)
  - [:six: `registering_new_extractor.groovy`](https://tamada.github.io/pochi/examples#6-registering_new_extractorgroovy)
* :smile: About
  - [:scroll: License](https://tamada.github.io/pochi/about#-license)
  - [:man_office_worker: Developers :woman_office_worker:](https://tamada.github.io/pochi/about#-developers-)
  - [:question: Icon of pochi](https://tamada.github.io/pochi/about#-icon-of-pochi)
  - :surfer: References
    - [:basketball: Surveys](https://tamada.github.io/pochi/about#-surveys)
    - [:soccer: Articles of supported birthmark types](https://tamada.github.io/pochi/about#-articles-of-supported-birthmark-types)
    - [:tennis: Articles by H. Tamada](https://tamada.github.io/pochi/about#-articles-by-h-tamada)
* [:smile_cat: API document](https://tamada.github.io/pochi/apidocs)

### :runner: CLI Interface

```sh
pochi [OPTIONS] [SCRIPT_FILE [ARGV...]]
OPTIONS
    -c, --classpath <CLASSPATH>      specifies the classpaths for Groovy (JVM) separated with colon (:).
    -C, --config <CONFIG_FILE>       specifies the configuration file.
    -e, --expression <EXPRESSION>    specifies one line script.
    -w, --working-dir <DIR>          specifies the working directory.
    -v, --verbose                    sets as verbose mode.

    -h, --help                       prints this message.
SCRIPT_FILE [ARGV...]
    Groovy script file name and its arguments.
    If no script files and no expression were given, pochi runs on interactive mode.
```

#### Script file

The script files are parsed by the Groovy.
For more detail, see [:ant: Examples](https://tamada.github.io/pochi/examples).

### :whale: Docker

Container images of **pochi** for Docker are:

* [`ghcr.io/tamada/pochi`](https://github.com/users/tamada/packages/container/package/pochi)
  * `2.4.6`, `latest`
  * `2.4.5`
  * `2.4.4`
  * `2.4.3`
  * `2.4.2`
  * `2.4.1`
  * `2.4.0`
  * `2.3.24`
  * `2.3.24`
  * `2.3.24`
  * `2.3.24`
  * `2.3.21`
  * `2.3.21`
  * `2.3.19`
  * `2.3.18`
  * `2.3.17`
  * `2.3.16`
  * `2.3.2`
  * `2.3.1`
  * `2.3.0`
  * `2.2.0`
  * `2.1.0`
  * `2.0.0`
    * accept only `.groovy` script files.
  * `1.0.0`
    * accept only `.js` script files.

[![Docker](https://img.shields.io/badge/Docker-ghcir.io%2Ftamada%2Fpochi%3A2.4.6-blue?logo=docker)](https://github.com/users/tamada/packages/container/package/pochi)

To run **pochi** on Docker container OS, type the following commands.

```sh
$ docker run --rm -it -v "$PWD":/home/pochi ghcr.io/tamada/pochi:latest [OPTIONS] [SCRIPT [ARGV...]]
```

* `OPTIONS`: the options for **pochi**.
* `[SCRIPT [ARGV...]]`: script file for **pochi**.
* `--rm`: remove the container after running.
* `-it`: interactive and tty mode.
* `-v "$PWD":/home/pochi`: share volume `$PWD` in host OS to `/home/pochi` in the container OS.
  * `$PWD` must be the absolute path.

#### Environments in the docker container

* `USER`: `pochi`
* `WORKDIR`: `/home/pochi`
* `JAVA_HOME`: `/opt/java` (symbolic link from `/opt/openjdk-11-minimal`)
  * This Java runtime environment do not include unnecessary modules.
* `GROOVY_HOME`: `/opt/groovy` (symbolic link from `/opt/groovy-3.0.8`)
* `POCHI_HOME`: `/opt/pochi` (symbolic link from `/opt/pochi-2.0.0`)

## Discussion

[![GitHub Discussion](https://img.shields.io/badge/GitHub-Discussions-blue?logo=github)](https://github.com/tamada/pochi/discussions)

If you have any problems or suggestions on pochi, please post the messages to the [GitHub Discussions](https://github.com/tamada/pochi/discussions).

## Maven repository

Copy and paste the following snippet into your `pom.xml`.

```xml
<repositories>
  <repository>
    <id>tamada_github</id>
    <name>Apache Maven Packages of tamada</name>
    <url>https://tamada.github.io/maven</url>
  </repository>
</repositories>
```

Then, add the dependencies of your `pom.xml`.

| groupId            | artifactId   | version |
|--------------------|--------------|---------|
|`jp.cafebabe.pochi` | `kunai2`     | `2.4.6` |
|`jp.cafebabe.pochi` | `pochi-core` | `2.4.6` |
|`jp.cafebabe.pochi` | `pochi-api`  | `2.4.6` |
|`jp.cafebabe.pochi` | `pochi-cmd`  | `2.4.6` |

## Modules

**pochi** provides the following modules, and the dependant modules are shown below.

* `jp.cafebabe.kunai`
    * `org.objectweb.asm`
    * `jdk.zipfs`
* `jp.cafebabe.birthmarks`
    * `java.logging`
    * `io.vavr`
    * `com.fasterxml.jackson.databind`
    * `jp.cafebabe.kunai`
* `jp.cafebabe.pochi`
    * `java.logging`
    * `jp.cafebabe.birthmarks`
* `jp.cafebabe.pochicmd`
    * `info.picocli`
    * `java.scripting`
  
![Module graph](https://github.com/tamada/pochi/raw/main/site/static/images/module-graph.svg)
