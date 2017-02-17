[![Build Status](https://travis-ci.org/tamada/kunai2.svg?branch=master)](https://travis-ci.org/tamada/kunai2)
[![Coverage Status](https://coveralls.io/repos/github/tamada/kunai2/badge.svg?branch=master)](https://coveralls.io/github/tamada/kunai2?branch=master)
[![codebeat badge](https://codebeat.co/badges/2f934b12-6c8e-439a-81e9-694b89789374)](https://codebeat.co/projects/github-com-tamada-kunai2)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat)](https://github.com/tamada/9rules/blob/master/LICENSE)

# kunai2

This tool is to read/store class files from/to directories, jar file, and war files.
[kunai](https://github.com/tamada/kunai) is same tool, however, kunai is for Java 7 or before.
Kunai2 implemented for Java 8 and used streaming API.

## Simple use case.

```java
// Reading
Path sourcePath = Paths.get("path/of/source/file/or/directory");
DataSourceFactory sourceFactory = new DefaultDataSourceFactory();
try(DataSource source = sourceFactory.build(sourcePath)){
    // Storing
    Path outputPath = Paths.get("path/of/output/file.jar");
    DataSinkFactory sinkFactory = new DefaultDataSinkFactory();
    try(DataSink sink = sinkFactory.build(outputPath)){
        sink.consume(source);
        //     above lines means following code.
        // source.forEach(entry -> {
        //     try{ sink.consume(entry); }
        //     catch(Exception e){ } 
        // });
    }
}
```

