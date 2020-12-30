#! /bin/sh

VERSION="2.2.0"

function mkdirIfNeeded () {
  if [ ! -d $1 ] ; then
    mkdir -p $1
  fi
}

function copyImpl () {
  cp {pochi-cmd,pochi-api,pochi-core,kunai2}/target/*.jar $1/lib
  cp -r README.md LICENSE Dockerfile completions examples $1
  cp -r site/public $1/docs
  cp bin/pochi $1/bin
}

function makeWithoutJvm() {
  NAME=pochi-${VERSION}
  mkdirIfNeeded dist/${NAME}
  mkdirIfNeeded dist/${NAME}/lib
  mkdirIfNeeded dist/${NAME}/bin
  copyImpl dist/$NAME

  (cd dist ; zip ${NAME}.zip -r ${NAME})
}

function makeWithJvm() {
  NAME=pochi--withjvm-${VERSION}
  jlink --module-path ${JAVA_HOME}/jmods --compress=2 --no-header-files --no-man-pages \
        --add-modules java.base,java.scripting,java.logging,java.desktop,java.sql,java.xml,jdk.zipfs \
        --output dist/${NAME}
  copyImpl dist/$NAME

  (cd dist ; zip ${NAME}.zip -r ${NAME})
}

# makeWithJvm
makeWithoutJvm
