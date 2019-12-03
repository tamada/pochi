---
title: "Description"
date: 2019-12-02
draft: false
weight: 50
---

## Key idea

Almost traditional birthmark systems are usually simple input and output.
For example, an user specifies the type of target birthmark, $p$ and $q$ as input, and obtains result as output.
The process of such system is hard to use.
Because, when the user would perform some process (ex. filtering results), it requires the update of birthmark system.

In general, the process of birthmarking is composed of extraction phase, and comparison phase.
Also, the both phases are performed to huge amount of programs.

Therefore, the birthmark system accept the script file as input, and the user describes extraction and comparison of birthmarks in the script file.
If the user would perform filtering process, it is easy to perform the process.

## Examples of script files

### Extracting birthmarks

```javascript:extract.js
extractor = engine.extractor("uc");
source = engine.source("target-test-classes/resources");
birthmarks = extractor.extract(source);
birthmarks.forEach(function(b){
   // If the filtering is required, the filtering routine locates here!
   print(b);
});

// to compute the execution time, use the commented script.
//   extractor = engine.extractor("uc");
//   source = engine.source("target/test-classes/resources");
//   result = {}
//   result.time = sys.measure(function(){
//       result.birthmarks = extractor.extract(source);
//   })
//   result.birthmarks.forEach(function(birthmark){
//       print(birthmark);
//   });
//   print(result.time + " ns");
```

