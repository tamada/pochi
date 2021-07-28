#! /bin/sh

if [ ! -d data ]; then
    mkdir data
fi

if [ ! -f data/en-lemmatizer.dict ]; then
  curl -L -o data/en-lemmatizer.dict https://raw.githubusercontent.com/richardwilly98/elasticsearch-opennlp-auto-tagging/master/src/main/resources/models/en-lemmatizer.dict
fi

if [ ! -f data/en-pos-maxent.bin ]; then
  curl -L -o data/en-pos-maxent.bin http://opennlp.sourceforge.net/models-1.5/en-pos-maxent.bin
fi