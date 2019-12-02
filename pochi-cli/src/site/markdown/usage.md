# Usage

## Help message

```
java -jar ebis-1.x.jar [OPTIONS]
OPTIONS:
    -f, --file <FILE>: スクリプトファイルを指定する．
    -e <COMMAND>:      1行でコマンドを指定する．
    -v, --verbose:     冗長モード．
    -h, --help:        このメッセージを表示する．
    -i, --interactive: インタラクティブモード（対話モード）．デフォルト．
```

## 実行状態のイメージ（予定）

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

