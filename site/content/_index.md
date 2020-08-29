---
title: ":house: Home"
date: 2020-08-27
draft: false
---

[![Build Status](https://travis-ci.org/tamada/pochi.svg?branch=master)](https://travis-ci.org/tamada/pochi)
[![Coverage Status](https://coveralls.io/repos/github/tamada/pochi/badge.svg?branch=master)](https://coveralls.io/github/tamada/pochi?branch=master)
[![codebeat badge](https://codebeat.co/badges/7d4be5b9-c604-4bf9-b67b-d6d20f703ab9)](https://codebeat.co/projects/github-com-tamada-pochi)
[![Gitter](http://badges.gitter.im/owner/repo.png)](https://gitter.im/pochi-birthmark/)
[![Javadoc](https://img.shields.io/static/v1?label=javadoc&message=v2.0.0&color=blue&logo=java)](https://tamada.github.io/pochi/apidocs)

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
* [:ant: Samples](samples)
    * [:one: `printing_args.groovy`](samples#1-printing_argsgroovy)
    * [:two: `printing_pochi_info.groovy`](samples#2-printing_pochi_infogroovy)
    * [:three: `extracting_birthmarks.groovy`](samples#3-extracting_birthmarksgroovy)
    * [:four: `filtering_source.groovy`](samples#4-filtering_sourcegroovy)
    * [:five: `comparing_birthmarks.groovy`](samples#5-comparing_birthmarksgroovy)
    * [:six: `registering_new_extractor.groovy`](samples#6-registering_new_extractorgroovy)
* [:smile: About](about)
    * [:scroll: License](about#-license)
    * [:man_office_worker: Developers :woman_office_worker:](about#-developers-)
    * [:question: Icon of pochi](about#-icon-of-pochi)
    * [:surfer: References](about#-references)
        * [:basketball: Surveys](about#-surveys)
        * [:soccer: Articles of supported birthmark types](about#-articles-of-supported-birthmark-types)
        * [:tennis: Articles by H. Tamada](about#-articles-by-h-tamada)
* [:smile_cat: API document](apidocs)
