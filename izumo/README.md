# izumo

Matching pair utilities.



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

