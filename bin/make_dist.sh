#! /bin/sh

VERSION="2.4.5"

function mkdirIfNeeded () {
  if [ ! -d $1 ] ; then
    mkdir -p $1
  fi
}

function copyImpl () {
  cp {pochi-cmd,pochi-api,pochi-core,kunai2}/target/*.jar "$1/lib"
  cp -r README.md LICENSE Dockerfile examples "$1"
  mkdir -p "$1/docs"
  cp -r site/public/* "$1/docs"
  cp bin/pochi "$1/bin"
  cp data/* "$1/data"
  cp pochi-cmd/target/bash-completions-pochi.sh "$1/completions/bash/pochi"
  cp pochi-cmd/target/bash-completions-pochi.sh "$1/completions/zsh/pochi"
  find "$1" -name '.git*' | xargs rm -f
  find "$1" -name .DS_Store | xargs rm -f
}

function makeWithoutJvm() {
  NAME=pochi-${VERSION}
  mkdirIfNeeded "dist/${NAME}"
  mkdirIfNeeded "dist/${NAME}/bin"
  mkdirIfNeeded "dist/${NAME}/lib"
  mkdirIfNeeded "dist/${NAME}/data"
  mkdirIfNeeded "dist/${NAME}/completions/bash"
  mkdirIfNeeded "dist/${NAME}/completions/zsh"
  copyImpl "dist/$NAME"

  (cd dist ; zip -9 -q "${NAME}.zip" -r "${NAME}")
}

function makeWithJvm() {
  NAME=pochi-withjvm-${VERSION}
  jlink --module-path lib --compress=2 --no-header-files --no-man-pages \
        --add-modules java.base,java.scripting,java.logging,java.desktop,java.sql,java.xml,jdk.zipfs,jp.cafebabe.pochi,jp.cafebabe.birthmarks,jp.cafebabe.kunai,jp.cafebabe.pochicmd,info.picocli,org.objectweb.asm,com.fasterxml.jackson.databind,org.codehaus.groovy.jsr223,org.codehaus.groovy \
        --launcher pochi=jp.cafebabe.pochicmd/jp.cafebabe.pochicmd.Main \
        --output dist/${NAME}
  copyImpl dist/$NAME

  (cd dist ; zip "${NAME}.zip" -r "${NAME}")
}

# ./bin/build_dockers.sh

echo "create distribution file pochi-${VERSION}"

# makeWithJvm
makeWithoutJvm
