[![Build Status](https://travis-ci.org/tamada/pochi.svg?branch=master)](https://travis-ci.org/tamada/pochi)
[![Coverage Status](https://coveralls.io/repos/github/tamada/pochi/badge.svg?branch=master)](https://coveralls.io/github/tamada/pochi?branch=master)
[![codebeat badge](https://codebeat.co/badges/7d4be5b9-c604-4bf9-b67b-d6d20f703ab9)](https://codebeat.co/projects/github-com-tamada-pochi)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=flat)](https://github.com/tamada/pochi/blob/master/LICENSE)
[![Version](https://img.shields.io/badge/Version-2.1.0-yellowgreen.svg)](https://github.com/tamada/pochi/releases/tag/v2.1.0)

[![Gitter](https://img.shields.io/badge/chat-on%20gitter-green?logo=gitter)](https://gitter.im/pochi-birthmark/)
[![Javadoc](https://img.shields.io/static/v1?label=javadoc&message=v2.1.0&color=blue&logo=java)](https://tamada.github.io/pochi/apidocs)
[![Docker](https://img.shields.io/badge/docker-tamada%2Fpochi%3A2.0.0-blue?logo=docker&style=social)](https://hub.docker.com/r/tamada/pochi)

# :dog: pochi

Detecting the software theft, the birthmark toolkit for the JVM platform.

**pochi** is the birthmarking toolkit for the JVM platform. The birthmarks are the native characteristics extracted from executable programs. Then, we compare them and computes the similarities. The resultant similarities shows the copy relation possibilities between two programs.

## :bookmark: Table of Contents

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


## Discussion

[![Gitter](https://img.shields.io/badge/chat-on%20gitter-green?logo=gitter)](https://gitter.im/pochi-birthmark/)

Join [our Gitter channel](https://gitter.im/pochi-birthmark/) if you have any problems or suggestions on pochi.

## Modules

**pochi** provides the following modules, and the depenent modules are shown.

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

Also, **pochi** runs on Groovy environment, and the Groovy depends on `java.scripting`, `java.desktop`, `java.sql`, and `java.xml` modules.



![Module graph](https://github.com/tamada/pochi/raw/master/site/static/images/module-graph.svg)
