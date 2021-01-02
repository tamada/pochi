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
