# Plan of ebis

## 実行状態のイメージ

```javascript
$ java -jar target/ebis-1.0.jar
Ebis version 1.0
ebisjs> birthmarks()
[ "cvfv", "k-gram", "smc", "uc" ]
ebisjs> source = open("hoge.jar")
source("hoge.jar")
ebisjs> extract_rs = extract("cvfv", source)
extract_result_set("cvfv", source("hoge.jar"))
ebisjs> print_func = function(item){
    csv.print(item)
}
ebisjs> extract_result_set.foreach(print_func)
```

上記のように，JavaScript のインタラクティブシェルのように動作することを目指します．
また，Groovy のスクリプトとしても実行できることも目指します．

### 用意する予定の標準関数

* ```birthmarks()```
    * 有効なバースマーク名の一覧を配列形式で返します．
* ```open(path)```
    * jarファイルやクラスファイルを指定して，```DataSource```を返します．
* ```extract(birthmark_name, source)```
    * バースマーク名と ```DataSource``` を受け取り，バースマークを抽出します．
      返り値の方は ```ExtractResultSet``` です．
* ```comparators()```
    * 比較器名の一覧を配列形式で返します．
* ```comparator(comparator_name)```
    * 比較器名を受け取り，比較器の一覧を返します．
    
