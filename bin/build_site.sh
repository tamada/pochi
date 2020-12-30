#! /bin/sh

VERSION="2.2.0"

function build_apidocs() {
  mkdir -p site/msp
  ln -s ${PWD}/kunai2/src/main/java site/msp/jp.cafebabe.kunai
  ln -s ${PWD}/pochi-api/src/main/java site/msp/jp.cafebabe.birthmarks
  ln -s ${PWD}/pochi-core/src/main/java site/msp/jp.cafebabe.pochi
  ln -s ${PWD}/pochi-cmd/src/main/java site/msp/jp.cafebabe.pochicmd

  find site/msp/jp.cafebabe.kunai site/msp/jp.cafebabe.birthmarks site/msp/jp.cafebabe.pochi site/msp/jp.cafebabe.pochicmd \
      -name '*.java' | grep -v src/test | grep -v target > site/resources/javadoc/targets

  sed -e "s!#{pochi.version}!${VERSION}!g" -e "s!#{PROJECT_DIR}!${PWD}!g" \
      site/resources/javadoc/options > site/resources/javadoc/options_abs

  javadoc -d site/static/apidocs @site/resources/javadoc/options_abs @resources/javadoc/targets
  rm -rf site/resources/javadoc/options_abs site/resources/javadoc/targets site/msp
}

if [ ! -d site/public ]; then
  git worktree add site/public gh-pages
fi

if [ ! -d site/static/v1.0.0/apidocs ]; then
  unzip -d site/static/v1.0.0 site/resources/apidocs_v1.0.0.zip
fi

if [ ! -d site/static/apidocs ] ; then
  build_apidocs
fi

(cd site; hugo)
rm -f site/public/apple-touch-icon.png site/public/favicon*
