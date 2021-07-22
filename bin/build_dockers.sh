#! /bin/sh

VERSION="2.3.17"

echo "build docker image for pochi-${VERSION}"

for i in dockers/*; do
  name="$(basename $i)"
  docker build --force-rm=true --rm=true --no-cache=true --tag=ghcr.io/tamada/"$name":$VERSION $i
  docker tag ghcr.io/tamada/"$name":$VERSION ghcr.io/tamada/"$name":latest
done
