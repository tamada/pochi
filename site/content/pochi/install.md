---
title: "Install（インストール）"
date: 2017-08-29T16:00:00+09:00
weight: 4
slug: install
categories: [ "Pochi", "Document" ]
draft: false
---

# Install

## Getting project code

```sh
$ git clone git@github.com:/tamada/pochi.git # <- cloning project from GitHub.
```

## Compile project

```sh
$ cd pochi 
$ mvn package # <- compile, unit test, archive.
$ ls distribution/target/pochi-1.0-*.jar
distribution/target/pochi-1.0-all.jar
distribution/target/pochi-1.0-fatjar.jar
```

Resultant ```pochi-1.0-all.jar``` and ```pochi-1.0-fat.jar``` are executable jar files.
```pochi-1.0-fat.jar``` is the FAT jar file includes all of dependencies of pochi platform.
```pochi-1.0-all.jar``` is jar file of the project, includes all of class files of the project, and not includes other dependencies.

