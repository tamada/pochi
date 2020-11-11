---
title: ":house: Home"
date: 2020-10-08
draft: false
---

[![Build Status](https://travis-ci.org/tamada/pochi.svg?branch=master)](https://travis-ci.org/tamada/pochi)
[![Coverage Status](https://coveralls.io/repos/github/tamada/pochi/badge.svg?branch=master)](https://coveralls.io/github/tamada/pochi?branch=master)
[![codebeat badge](https://codebeat.co/badges/7d4be5b9-c604-4bf9-b67b-d6d20f703ab9)](https://codebeat.co/projects/github-com-tamada-pochi)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=flat)](https://github.com/tamada/pochi/blob/master/LICENSE)
[![Version](https://img.shields.io/badge/Version-2.1.0-yellowgreen.svg)](https://github.com/tamada/pochi/releases/tag/v2.1.0)

[![Gitter](https://img.shields.io/badge/chat-on%20gitter-green?logo=gitter)](https://gitter.im/pochi-birthmark/)
[![Javadoc](https://img.shields.io/static/v1?label=javadoc&message=v2.1.0&color=blue&logo=java)](https://tamada.github.io/pochi/apidocs)
[![Docker](https://img.shields.io/badge/docker-tamada%2Fpochi%3A2.0.0-blue?logo=docker&style=social)](https://hub.docker.com/r/tamada/pochi)

**pochi** is the birthmarking toolkit for the JVM platform.
The birthmarks are the native characteristics extracted from executable programs.
Then, **pochi** compares them and computes the similarities.
The resultant similarities shows the copy relation possibilities between two program.

## :bookmark: Table of Contents

* [:books: Birthmarks](birthmarks)
    * [:green_book: Definition of Birthmarks](birthmarks#-definition-of-birthmarks)
    * [:blue_book: Types of Birthmarks](birthmarks#-types-of-birthmarks)
    * [:orange_book: Similarities](birthmarks#-similarities)
    * [:closed_book: Theft detection process by birthmarks](birthmarks#-theft-detection-process-by-birthmarks)
* [:newspaper: What is **pochi**](description)
    * [:key: Key idea](description#-key-idea)
    * [:fork_and_knife: Usage](description#-usage)
        * [:runner: CLI Interface](description#-cli-interface)
        * [:whale: Docker](description#-docker)
    * [:swimmer: The birthmarking flow](description#-the-birthmarking-flow)
* [:anchor: Install](install)
    * [:beer: Homebrew](install#-homebrew)
    * [:muscle: Compiling pochi yourself](install#-compiling-pochi-yourself)
    * [:briefcase: Requirements](install#-requirements)
        * [:pouch: Modules](install#-modules)
* [:ant: Examples](examples)
    * [:one: `printing_args.groovy`](examples#1-printing_argsgroovy)
    * [:two: `printing_pochi_info.groovy`](examples#2-printing_pochi_infogroovy)
    * [:three: `extracting_birthmarks.groovy`](examples#3-extracting_birthmarksgroovy)
    * [:four: `filtering_source.groovy`](examples#4-filtering_sourcegroovy)
    * [:five: `comparing_birthmarks.groovy`](examples#5-comparing_birthmarksgroovy)
    * [:six: `registering_new_extractor.groovy`](examples#6-registering_new_extractorgroovy)
* [:smile: About](about)
    * [:scroll: License](about#-license)
    * [:man_office_worker: Developers :woman_office_worker:](about#-developers-)
    * [:question: Icon of pochi](about#-icon-of-pochi)
    * [:surfer: References](about#-references)
        * [:basketball: Surveys](about#-surveys)
        * [:soccer: Articles of supported birthmark types](about#-articles-of-supported-birthmark-types)
        * [:tennis: Articles by H. Tamada](about#-articles-by-h-tamada)
* [:smile_cat: API document](apidocs)
