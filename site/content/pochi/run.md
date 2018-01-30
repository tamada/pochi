---
title: "Running pochi（Pochiの実行）"
date: 2017-08-29T17:00:00+09:00
weight: 5
slug: run
categories: [ "Pochi", "Document" ]
draft: false
---

# Running pochi

After installing pochi, ```pochi-runner-1.0.jar``` was built on ```pochi-runner/target``` directory.
To run pochi, execute ```java``` command with the jar file to ```-jar``` option.

```sh
$ java -jar pochi-runner/target/pochi-runner-1.0.jar
```

## Help message

```sh
$ java -jar pochi-runner/target/pochi-runner-1.0.jar -h
java -jar pochi-runner-1.0-SNAPSHOT.jar [OPTIONS] [SCRIPT [ARGV...]]
OPTIONS:
    -e <EXPRESSION>:     one line of script.
    -config <CONFIG>:    specify the configuration file.

    -help:               print this message and exit.
    -version:            print the version.
    -license:            print the license.
SCRIPT [ARGV...]
    Specify the script file for executing.
    Suitable script engine is parsed from extension of the script file.
    ARGV is arguments list for the script.
NO SCRIPT FILE, NO ONE LINER
    If no script file and no one liner were specified, the pochi-runner runs on interactive mode.
```

## Sample script

Sample scripts are available on ```pochi-runner/src/sample/js``` directory.
