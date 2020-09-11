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
$ make build-all
```

Then, `make` creates `pochi-${VERSION}` directory contains the interpreter (`bin/pochi`), and dependent jar files (`lib`) and misc files (`README.md`, `LICENSE`, `completions`, `samples`, and `Dockerfile`).

{{< gototop >}}

## :briefcase: Requirements

* [Jackson](https://github.com/FasterXML/jackson) 2.10.0
* [Vavr](https://www.vavr.io/) 0.10.3
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


### :steam_locomotive: Module Graph

{{< mermaid >}}
graph TB;
nasubi(jp.cafebabe.nasubi)
izumo(jp.cafebabe.izumo)
kunai(jp.cafebabe.kunai)
api(jp.cafebabe.birthmarks)
pochi(jp.cafebabe.pochi)
vavr(io.vavr)
asm(org.objectweb.asm)
logging(java.logging)
jackson(com.fasterxml.jackson.databind)
zipfs(jdk.zipfs)

api -- transitive --> kunai
kunai -- transitive --> asm
kunai --> zipfs
api -- transitive --> vavr
izumo --> api
api --> jackson
pochi --> izumo
pochi --> api
api --> logging
pochi --> logging
{{< /mermaid >}}

Groovy depends on the modules of `java.scripting`, `java.desktop`, `java.sql` and `java.xml`.

{{< gototop >}}
