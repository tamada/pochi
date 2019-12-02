# Scripts

JavaScript，Groovyスクリプトを処理できることを考えています．

## 用意する予定の標準関数

* ```BirthmarkName[] birthmarks()```
    * 有効なバースマーク名の一覧を配列形式で返します．
* ```DataSource load(Path)```
    * jarファイルやクラスファイルを指定して，```DataSource```を返します．
* ```Birthmarks extract(birthmark_name, DataSource)```
    * バースマーク名と ```DataSource``` を受け取り，バースマークを抽出します．
* ```void store(Birthmarks, Path)```
* ```Pairs pair(Birthmarks)```
* ```Pairs pair(Birthmarks, Birthmarks)```
* ```Pairs pair(Birthmarks, PairList)```
* ```Similarity compare(Birthmark, Birthmark)```
* ```Similarity compare(Pair)```
* ```Similarities compare(Birthmarks)```
* ```Similarities compare(Birthmarks, Birthmarks)```
* ```ComparatorName[] comparators()```
    * 比較器名の一覧を配列形式で返します．
* ```Comparator comparator(comparator_name)```
    * 比較器名を受け取り，比較器の一覧を返します．
