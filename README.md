[![Build Status](https://travis-ci.org/tamada/kunai2.svg?branch=master)](https://travis-ci.org/tamada/kunai2)
[![Coverage Status](https://coveralls.io/repos/github/tamada/kunai2/badge.svg?branch=master)](https://coveralls.io/github/tamada/kunai2?branch=master)
[![codebeat badge](https://codebeat.co/badges/2f934b12-6c8e-439a-81e9-694b89789374)](https://codebeat.co/projects/github-com-tamada-kunai2)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat)](https://github.com/tamada/9rules/blob/master/LICENSE)

# kunai2

This tool is to read/store class files from/to directories, jar file, and war files.
[kunai](https://github.com/tamada/kunai) is same tool, however, kunai is for Java 7 or before.
Kunai2 implemented for Java 8 and used streaming API.

## Simply use.

### Reading

```java
Path path = Paths.get("target/source");
DataSource source = new DefaultDataSourceFactory().build(path);
source.stream();
    // some operation for stream.
```

### Storing
