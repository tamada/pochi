---
title: ":anchor: Install"
date: 2020-10-08
draft: false
---

## :beer: Homebrew

For macOS user, **pochi** supports homebrew installation.

```sh
$ brew tap tamada/brew
$ brew install pochi
```

{{< gototop >}}

## :muscle: Compiling **pochi** yourself

For building yourself, clone the source code from GitHub, and build it with `make` and [Maven](https://maven.apache.org/).

```sh
$ git clone https://github.com/tamada/pochi.git
$ cd pochi
$ make build-all
```

Then, `make` creates `pochi-${VERSION}` directory contains the interpreter (`bin/pochi`), and dependent jar files (`lib`), documents (`docs`) and misc files (`README.md`, `LICENSE`, `completions`, `examples`, and `Dockerfile`).

{{< gototop >}}

## :briefcase: Requirements

* [Jackson](https://github.com/FasterXML/jackson) 2.10.0
* [Vavr](https://www.vavr.io/) 0.10.3
* [ASM](https://asm.ow2.io/) 8.0.1
* [Groovy](https://groovy-lang.org) 3.0.5


### :pouch: Modules

* [`jp.cafebabe.pochi`](https://tamada.github.io/pochi/apidocs/jp.cafebabe.pochi/module-summary.html)
    * [`jp.cafebabe.birthmarks`](https://tamada.github.io/pochi/apidocs/jp.cafebabe.birthmarks/module-summary.html)
        * [`com.fasterxml.jackson.databind`](https://github.com/FasterXML/jackson-databind)
        * [`io.vavr`](https://www.vavr.io/)
        * [`jp.cafebabe.kunai`](https://tamada.github.io/pochi/apidocs/jp.cafebabe.kunai/module-summary.html)
            * [`org.objectweb.asm`](https://asm.ow2.io/)
            * [`jdk.zipfs`](https://docs.oracle.com/en/java/javase/11/docs/api/jdk.zipfs/module-summary.html)
    * [`java.logging`](https://docs.oracle.com/en/java/javase/11/docs/api/java.logging/module-summary.html)
* [`org.codehaus.groovy`](https://groovy-lang.org/)
    * [`java.scripting`](https://docs.oracle.com/en/java/javase/11/docs/api/java.scripting/module-summary.html)
    * [`java.desktop`](https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/module-summary.html)
    * [`java.sql`](https://docs.oracle.com/en/java/javase/11/docs/api/java.sql/module-summary.html)
    * [`java.xml`](https://docs.oracle.com/en/java/javase/11/docs/api/java.xml/module-summary.html)


### :steam_locomotive: Module Graph

{{< image src="images/module-graph.svg" caption="Figure 1. :steam_locomotive: Module Graph" >}}

Groovy depends on the modules of `java.scripting`, `java.desktop`, `java.sql` and `java.xml`.


{{< gototop >}}
