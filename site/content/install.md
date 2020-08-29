---
title: ":anchor: Install"
date: 2019-11-29
draft: false
---

## :beer: Homebrew

For macOS user, **pochi** supports homebrew installation.

```sh
$ brew install tamada/brew/pochi
```

{{< gototop >}}

## :muscle: Compiling **pochi** yourself

For building yourself, clone the source code from GitHub, and build it with [Maven](https://maven.apache.org/).

```sh
$ git clone https://github.com/tamada/pochi.git
$ cd pochi
$ make
```

Then, maven packages **pochi** and generates `pochi-${VERSION}-fat.jar`, and `pochi-${VERSION}-all.jar` onto `distribution/target` directory.

{{< gototop >}}

## :briefcase: Requirements

* [Jackson](https://github.com/FasterXML/jackson) 2.10.0
* [ASM](https://asm.ow2.io/) 7.2
* [Groovy](https://groovy-lang.org) 3.0.5

### :pouch: Modules

* [java.base](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/module-summary.html)
* [java.logging](https://docs.oracle.com/en/java/javase/11/docs/api/java.logging/module-summary.html)
* [java.scripting](https://docs.oracle.com/en/java/javase/11/docs/api/java.scripting/module-summary.html)
* [java.desktop](https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/module-summary.html)
* [java.sql](https://docs.oracle.com/en/java/javase/11/docs/api/java.sql/module-summary.html)
* [java.xml](https://docs.oracle.com/en/java/javase/11/docs/api/java.xml/module-summary.html)
* [jdk.zipfs](https://docs.oracle.com/en/java/javase/11/docs/api/jdk.zipfs/module-summary.html)

{{< gototop >}}
