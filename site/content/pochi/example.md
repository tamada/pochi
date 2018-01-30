---
title: "Example scripts（スクリプト例）"
date: 2017-08-29T17:00:00+09:00
slug: example
weight: 6
categories: [ "Pochi", "Document" ]
draft: false
---

# Extract birthmarks（抽出）

```javascript:extract.js
extractor = engine.extractor("uc");
source = engine.source("target-test-classes/resources");
birthmarks = extractor.extract(source);
birthmarks.forEach(function(b){
   // If the filtering is required, the filtering routine locates here!
   print(b);
});

// 実行時間を計測したければ，次の通り．
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
