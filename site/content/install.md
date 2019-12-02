---
title: "Install"
date: 2019-11-29"
draft: false
---

## Homebrew

For macOS user, **pochi** supports homebrew installation.

```sh
$ brew tap tamada/brew
$ brew install pochi
```

## Compiling **pochi** yourself

For building yourself, clone the source code from GitHub, and build it with [Maven](https://maven.apache.org/).

```sh
$ git clone https://github.com/tamada/pochi.git
$ cd pochi
$ mvn package
```

Then, `pochi-1.0.0-fat.jar` was created on `distribution/target` directory.

## Requirements

* [Jackson](https://github.com/FasterXML/jackson) 2.10.0
* [Args4j](https://github.com/kohsuke/args4j) 2.33
* [JLine](https://mvnrepository.com/artifact/org.jline/jline/3.13.2) 3.13.2
* [ASM](https://asm.ow2.io/) 5.1

### Modules

* java.base
* java.xml
* java.scripting
