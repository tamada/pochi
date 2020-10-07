---
title: ":ant: Examples"
date: 2020-08-27
---

## :mega: Overview

The `examples` directory in the `pochi` directory contains the following example programs.
To write groovy script for the pochi, see the [API document of pochi](../apidocs/),
especially, [BirthmarkSystemHelper](../apidocs/jp/cafebabe/pochi/birthmarks/BirthmarkSystemHelper.html).

The groovy script of pochi defines the two variables as follows.

* the variable `pochi` as the type of `BirthmarkSystemHelper` for the start points of processing, and
* the variable `args` as the type of `String` array for reflecting the command line arguments.

Also, we should know [the birthmarking flow](../description#) in the pochi.

### Example programs

* [:one: `printing_args.groovy`](#1-printing_argsgroovy)
* [:two: `printing_pochi_info.groovy`](#2-printing_pochi_infogroovy)
* [:three: `extracting_birthmarks.groovy`](#3-extracting_birthmarksgroovy)
* [:four: `filtering_source.groovy`](#4-filtering_sourcegroovy)
* [:five: `comparing_birthmarks.groovy`](#5-comparing_birthmarksgroovy)
* [:six: `registering_new_extractor.groovy`](#6-registering_new_extractorgroovy)

## :one: `printing_args.groovy`

Printing `args` to the `stdout`.

### :grapes: Source file

```groovy
args.each(arg -> println(arg))
```

### :wine_glass: Output

```sh
$ pochi examples/printing_args.groovy a b c d e f
a
b
c
d
e
f
```

{{< gototop >}}

## :two: `printing_pochi_info.groovy`

printing information of `pochi`.

### :grapes: Source file

```groovy
println("========== birthmark extractor names ==========")
println(pochi.extractorNames())

println("========== birthmark comparator names ==========")
println(pochi.comparatorNames())

println("========== pair matcher names ==========")
println(pochi.matcherNames())

config = pochi.config()
println("========== system library rules ==========")
println(config.rules())

println("========== properties ==========")
config.propertyStream()
     .each(item -> println(item))
```

### :wine_glass: Output

```sh
$ pochi examples/printing_pochi_info.groovy
========== birthmark extractor names ==========
[6-gram, 4-gram, 2-gram, 5-gram, uc, 1-gram, 3-gram]
========== birthmark comparator names ==========
[DiceIndex, EditDistance, SimpsonIndex, JaccardIndex]
========== pair matcher names ==========
[RoundRobin, Guessed, RoundRobinWithSamePair]
========== system library rules ==========
{PREFIX,java.,PREFIX,javax.,PREFIX,org.omg.,PREFIX,org.ietf.,PREFIX,org.w3c.,PREFIX,org.xml.sax.,PREFIX,org.apache.}
========== properties ==========
$ pochi examples/printing_pochi_info.groovy -C examples/sample_config.json
========== birthmark extractor names ==========
[6-gram, 4-gram, 2-gram, 5-gram, uc, 1-gram, 3-gram]
========== birthmark comparator names ==========
[DiceIndex, EditDistance, SimpsonIndex, JaccardIndex]
========== pair matcher names ==========
[RoundRobin, Guessed, RoundRobinWithSamePair]
========== system library rules ==========
{PREFIX,java.,PREFIX,javax.,PREFIX,org.omg.,PREFIX,org.ietf.,PREFIX,org.w3c.,PREFIX,org.xml.sax.,PREFIX,org.apache.}
========== properties ==========
key1: value1 # <--- sample_config.json adds this property key, and value pair.
```

{{< gototop >}}

## :three: `extracting_birthmarks.groovy`

Extracting specified type of birthmarks from given jar files.

### :grapes: Source file

```groovy
// extract birthmarks by given extractor from given file path.
def extract(path, extractor) {
    source = pochi.source(path)
    return extractor.extract(source)
}

// prints each Birthmark object.
def printBirthmarks(birthmarks) {
    birthmarks.each(b -> println(b))
}

// gets UC birthmark extractor.
extractor = pochi.extractor("uc") // specifies birthmark type.

Arrays.stream(args)
    .map(file -> extract(file, extractor)) // converts to Birthmarks
    .each(birthmarks -> printBirthmarks(birthmarks)) // print given birthmarks.
```

### :wine_glass: Output

```sh
$ pochi examples/extracting_birthmarks.groovy lib/vavr-match-0.10.3.jar
extract uc birthmark from lib/vavr-match-0.10.3.jar
io.vavr.match.annotation.Unapply,jar:file:///POCHI_HOME/lib/vavr-match-0.10.3.jar!/io/vavr/match/annotation/Unapply.class,uc,java.lang.Object,java.lang.annotation.Annotation
io.vavr.match.annotation.Patterns,jar:file:///POCHI_HOME/lib/vavr-match-0.10.3.jar!/io/vavr/match/annotation/Patterns.class,uc,java.lang.Object,java.lang.annotation.Annotation
```

{{< gototop >}}

## :four: `filtering_source.groovy`

`pochi` reads files as `DataSource` type variable.
Then, we can apply the filter on `DataSource` type variable for ignoring the classes with the certain name.

### :grapes: Source file

```groovy
def contains(entry) {
    return entry.isClass()
        && !entry.className().contains("pochi")
}

def filter(source) {
    // filtering DataSource entry by contains method.
    return source.filter(entry -> contains(entry))
}

def printSource(source) {
    source.stream()
        .each(entry -> println(entry.loadFrom()))
}

Arrays.stream(args)
    .map(arg -> pochi.source(arg))
    .map(source -> filter(source))
    .each(source -> printSource(source))
```

### :wine_glass: Output

```sh
$ pochi examples/filtering_source.groovy lib/pochi-api-2.0.0.jar
# no output was given, because, all of classes contains `pochi` keyword in the package name.
$ pochi examples/filtering_source.groovy lib/vavr-match-0.10.3.jar # prints all classes included in the given jar.
jar:file:///POCHI_HOME/lib/vavr-match-0.10.3.jar!/io/vavr/match/annotation/Unapply.class
jar:file:///POCHI_HOME/lib/vavr-match-0.10.3.jar!/io/vavr/match/annotation/Patterns.class
```

{{< gototop >}}

## :five: `comparing_birthmarks.groovy`

This script calculates similarities among extracted birthmarks.
Also, the script can filter by the similarity value.

### :grapes: Source file

```groovy
import jp.cafebabe.pochi.birthmarks.comparators.Threshold;
import jp.cafebabe.pochi.birthmarks.entities.Birthmarks;

// extract birthmarks by given extractor from given file path.
def extract(path, extractor) {
    source = pochi.source(path)
    return extractor.extract(source)
}

// gets UC birthmark extractor.
extractor = pochi.extractor("uc")

birthmarks = Arrays.stream(args)
    .map(file -> extract(file, extractor))              // converts to Birthmarks
    .reduce(new Birthmarks(), (b1, b2) -> b1.merge(b2)) // merges to one Birthmarks object
// above script is the same as [:three: extracting_birthmarks.groovy](#3-extracting_birthmarksgroovy).

comparator = pochi.comparator("JaccardIndex")
matcher = pochi.matcher("RoundRobin")

// default threshold (0.75)
threshold = Threshold.DEFAULT

matcher.match(birthmarks)
    .map(pair -> comparator.compare(pair))
    .filter(either -> either.isRight()) // ignoring the error.
    .map(either -> either.get())
    .filter(comparison -> comparison.isStolen(threshold)) // similarity > 0.75
    .forEach(comparison -> println(comparison))
```

### :wine_glass: Output

```sh
$ pochi examples/comparing_birthmarks.groovy distribution/target/lib/kunai2-1.0.0.jar
jp.cafebabe.pochi.kunai.sink.WarFileDataSink,jp.cafebabe.pochi.kunai.source.WarFileDataSource,1.0
jp.cafebabe.pochi.kunai.sink.WarFileDataSink,jp.cafebabe.pochi.kunai.source.factories.WarFileDataSourceFactory,0.75
jp.cafebabe.pochi.kunai.source.PlainFileDataSource,jp.cafebabe.pochi.kunai.source.ClassFileDataSource,1.0
jp.cafebabe.pochi.kunai.source.WarFileDataSource,jp.cafebabe.pochi.kunai.source.factories.WarFileDataSourceFactory,0.75
jp.cafebabe.pochi.kunai.source.factories.ClassFileDataSourceFactory,jp.cafebabe.pochi.kunai.source.factories.WarFileDataSourceFactory,0.8
jp.cafebabe.pochi.kunai.source.factories.ClassFileDataSourceFactory,jp.cafebabe.pochi.kunai.source.factories.PlainFileDataSourceFactory,0.8
jp.cafebabe.pochi.kunai.source.factories.ClassFileDataSourceFactory,jp.cafebabe.pochi.kunai.source.factories.DirectoryDataSourceFactory,0.8333333333333334
jp.cafebabe.pochi.kunai.source.factories.UnsupportedDataSourceException,jp.cafebabe.pochi.kunai.entries.ClassName,1.0
jp.cafebabe.pochi.kunai.entries.Entry,jp.cafebabe.pochi.kunai.entries.PathEntry,1.0
```

{{< gototop >}}

## :six: `registering_new_extractor.groovy`

This sample registers the new birthmark extractor (`7-gram` based birthmark extractor).

### :grapes: Source file

```groovy
import jp.cafebabe.pochi.birthmarks.kgram.KGramBasedExtractorBuilder

println("========= extractor ==========")
println(pochi.extractorNames())

// create 7-gram birthmark extractor.
sevenGram = new KGramBasedExtractorBuilder(7);
pochi.register(sevenGram)

println(pochi.extractorNames())
```

### :wine_glass: Output

```sh
========= extractor ==========
[6-gram, 4-gram, 2-gram, 5-gram, uc, 1-gram, 3-gram]         # <- before registration
[6-gram, 4-gram, 2-gram, 5-gram, uc, 1-gram, 7-gram, 3-gram] # <- after registration
```
