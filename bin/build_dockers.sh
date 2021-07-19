#! /bin/sh

VERSION="2.3.1"

for i in $(find dockers -type d -depth 1)
do
  name=$(basename $i)
  docker build --force-rm=true --rm=true --tag=ghcr.io/tamada/$name:$VERSION $i
  docker tag ghcr.io/tamada/$name:$VERSION ghcr.io/tamada/$name:latest
done
