---
title: ":newspaper: What is pochi"
date: 2019-12-02
draft: false
weight: 50
---

## :key: Key idea

Almost traditional birthmark systems are usually simple input and output.
For example, an user specifies the type of target birthmark, $p$ and $q$ as input, and obtains result as output.
The process of such system is hard to use.
Because, when the user would perform some process (ex. filtering results), it requires the update of birthmark system.

In general, the process of birthmarking is composed of extraction phase, and comparison phase.
Also, the both phases are performed to huge amount of programs.

Therefore, the birthmark system accept the script file as input, and the user describes extraction and comparison of birthmarks in the script file.
If the user would perform filtering process, it is easy to perform the process.

{{< gototop >}}

## :fork_and_knife: Usage

### :runner: CLI Interface

```sh
pochi [OPTIONS] [SCRIPT_FILE [ARGV...]]
OPTIONS
    -c, --classpath <CLASSPATH>      specifies classpaths for Groovy (JVM) searated with colon (:).
    -C, --config <CONFIG_FILE>       specifies configuration file.
    -e, --expression <EXPRESSION>    specifies command line script.

    -h, --help                       prints this message.
SCRIPT_FILE [ARGV...]
    Groovy script file name and its arguments.
    If no script files and no expression were given, pochi runs on interactive mode.
```

#### Script file

The script files are parsed by the Groovy.
For more detail, see [:ant: Samples](../samples).

### :whale: Docker

Container images of **pochi** for Docker are:

* `tamada/pochi`
    * `2.0.0`, `latest`
    * `1.0.0`


To run **pochi** on Docker container OS'

```sh
$ docker run --rm -v "$PWD":/home/pochi tamada/pochi [OPTIONS] [SCRIPT [ARGV...]]
```


* `OPTIONS`: the options for **pochi**.
* `[SCRIPT [ARGV...]]`: script file for **pochi**.
* `--rm`: remove the container after running.
* `-v "$PWD":/home/pochi`: share volume `$PWD` in host OS to `/home/pochi` in the container OS.
    * `$PWD` must be the absolute path.

{{< gototop >}}

## :swimmer: The birthmarking flow

The birthmarking process in the pochi flows like below.

{{< mermaid >}}
graph LR;
CLASSES("Classes")
BIRTHMARKS(Birthmarks)
PAIRS(Birthmark pair)
COMPARISONS(Comparisons)
CSV(CSV file)
CLASSES -- Extractor --> BIRTHMARKS
CSV -- Parser --> BIRTHMARKS
BIRTHMARKS -- PairMatcher --> PAIRS
PAIRS -- Comparator --> COMPARISONS
{{< /mermaid >}}

The followings are the description of the nodes and edges in the flowchart.

* `Classes`
    * is the Java class files, almost included in the jar files, and the directory.
      **pochi** treats them as the object of [`DataSource`](../apidocs/jp/cafebabe/pochi/kunai/source/DataSource.html)
* `Birthmarks`
    * shows the extracted characteristics from Java class files by certain method.
      In the **pochi**, [`Birthmarks`](../apidocs/jp/cafebabe/pochi/birthmarks/entities/Birthmarks.html) and [`Birthmark`](../apidocs/jp/cafebabe/pochi/birthmarks/entities/Birthmark.html) show the extracted birthmarks.
      The string representation of `Birthmark` (the return value of `toString` method) is CSV format, therefore, we can store them into some csv file.
* `Pairs`
    * shows the pair list of extracted birthmarks.
      **pochi** shows these objects as [`Pair<Birthmark>`](../apidocs/jp/cafebabe/pochi/birthmarks/pairs/Pair.html).
* `Comparisons`
    * represents the comparison results of birthmarks by the certain similarity calculation algorithm.
      **pochi** shows these objects as [`Comparisons`](../apidocs/jp/cafebabe/pochi/birthmarks/comparators/Comparisons.html) and [`Comparison`](../apidocs/jp/cafebabe/pochi/birthmarks/comparators/Comparison.html).
* `Extractor`
    * extracts birthmarks from given class files by the certain algorithm.
      In the script file, `pochi.extractor("ALGORITHM_NAME")` obtains the instance of [`Extractor`](../apidocs/jp/cafebabe/pochi/birthmarks/extractors/Extractor.html).
* `Parser`
    * parses the given csv file and build `Birthmarks` object.
      To get the instance of [`BirthmarkParser`](../apidocs/jp/cafebabe/pochi/birthmarks/BirthmarkParser.html), call `pochi.parser()` method in the script file.
* `PairMatcher`
    * matches the pair of birthmarks by some algorithm.
      `pochi.matcher("ALGORITHM_NAME")` returns the instance of [`PairMatcher`](../apidocs/jp/cafebabe/pochi/birthmarks/pairs/PairMatcher.html)
* `Comparator`
    * calculates similarity between two given birthmaks by the certain algorithm.
      In the script file, `pochi.comparator("ALGORITHM_NAME")` gets the instance of [`Comparator`](../apidocs/jp/cafebabe/pochi/birthmarks/comparators/Comparator.html)
