#! /bin/sh

VERSION=1.0.0
if [ -z "$POCHI_HOME" ]; then
    POCHI_HOME=$(cd $(dirname $0)/..; pwd)
fi

java -jar $POCHI_HOME/lib/pochi-cli-${VERSION}.jar $@
