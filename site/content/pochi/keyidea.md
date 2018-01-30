---
title: "Key Idea（キーアイデア）"
date: 2017-08-29T09:00:00+09:00
slug: keyidea
weight: 2
categories: [ "Pochi", "Document" ]
draft: false
---

# Key Idea

Almost traditional birthmark systems are usually simple input and output.
For example, an user specifies the type of target birthmark, $p$ and $q$ as input, and obtains result as output.
The process of such system is hard to use.
Because, when the user would perform some process (ex. filtering results), it requires the update of birthmark system.

In general, the process of birthmarking is composed of extraction phase, and comparison phase.
Also, the both phases are performed to huge amount of programs.

Therefore, the birthmark system accept the script file as input, and the user describes extraction and comparison of birthmarks in the script file.
If the user would perform filtering process, it is easy to perform the process.

# キーアイデア

旧来のバースマークシステムは，単純なシステムで，
コマンドラインやGUIなどのインターフェースでバースマークのタイプ，$p$, $q$を指定して結果を出力するという
非常に汎用性が低い形式である．
すなわち，途中で何か処理（例えば結果のフィルタリングなど）を入れたい場合であっても，ツールの改良が必要となる．

バースマーク処理は一般に，抽出処理，比較処理の２つの処理が行われる．
また，これら２つの処理を大量のプログラムに対して実施する．
このような状況の中，何度もプログラムを実行するのは効率的ではない．

そこで，スクリプト言語で処理を書いておき，そのスクリプトに従って処理できるようにすると上記の問題は解決できる．
途中でフィルタリングを行う場合であっても，スクリプト言語であれば，ユーザ毎に定義できるようになる．


