---
title: "Usage"
date: 2019-11-29"
draft: false
---

## CLI Interface

```sh
$ pochi [OPTIONS] [SCRIPT [ARGV...]]

OPTIONS:
    -e <EXPRESSION>     one line of script.
    -config <CONFIG>    specify the configuration file.

    -help               print this message and exit.
    -version            print the version.
    -license            print the license.

SCRIPT [ARGV...]
    Specify the script file for executing.
    Suitable script engine is parsed from extension of the script file.
    ARGV is arguments list for the script.

NO SCRIPT FILE, and NO ONE LINER
    If no script file and no one liner were specified, pochi runs on interactive mode.
```

### Script file

The script files are parsed by the Nashorn (JavaScript engine for Java).
In the future version of **pochi**, other script languages will be supported, because [Nashorn was deprecated on Java SE 11](http://openjdk.java.net/jeps/335).

## Docker

Container images of **pochi** for Docker are:

* `tamada/pochi`
    * `1.0.0`, `latest`


To run **pochi** on Docker container OS'

```sh
$ docker run --rm -v "$PWD":/home/pochi tamada/pochi [OPTIONS] [SCRIPT [ARGV...]]
```


* `OPTIONS`: the options for **pochi**.
* `[SCRIPT [ARGV...]]`: script file for **pochi**.
* `--rm`: remove the container after running.
* `-v "$PWD":/home/pochi`: share volume `$PWD` in host OS to `/home/pochi` in the container OS.


