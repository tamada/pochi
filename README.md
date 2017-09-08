[![Build Status](https://travis-ci.org/tamada/pochi.svg?branch=master)](https://travis-ci.org/tamada/pochi)
[![Coverage Status](https://coveralls.io/repos/github/tamada/pochi/badge.svg?branch=master)](https://coveralls.io/github/tamada/pochi?branch=master)
[![codebeat badge](https://codebeat.co/badges/7d4be5b9-c604-4bf9-b67b-d6d20f703ab9)](https://codebeat.co/projects/github-com-tamada-pochi)
[![Quality Gate](https://sonarqube.com/api/badges/gate?key=com.github:pochi)](https://sonarqube.com/dashboard/index/com.github:pochi)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat)](https://github.com/tamada/pochi/blob/master/LICENSE)
[![Gitter](http://badges.gitter.im/owner/repo.png)](https://gitter.im/pochi-birthmark/)

# pochi

ここ掘れワンワン．

Pochi enabled for extracting, comparing, and filtering birthmarks by the JavaScript language.
there are the sample scripts in the ```src/sample/js``` directory.

## config.json

Default configuration json script is as follows.

```JavaScript
{
    "properties": {
//      "key1": "value1",
//      "initialize.script": "/path/of/initialize_script.js",
    },
    "rules": {
        "rules": [
            { "type": "PREFIX", "pattern": "java." },
            { "type": "PREFIX", "pattern": "javax." },
            { "type": "PREFIX", "pattern": "org.omg." },
            { "type": "PREFIX", "pattern": "org.ietf." },
            { "type": "PREFIX", "pattern": "org.w3c." },
            { "type": "PREFIX", "pattern": "org.xml.sax." },
            { "type": "PREFIX", "pattern": "org.apache." }
        ]
    }
}
```
