#! /bin/sh

VERSION=$1

if [ "$VERSION" == "" ]; then
  echo "no version specified"
  exit
fi

for i in README.md site/content/_index.md ; do
  sed -e "s!Version-[0-9.]*-green!Version-${VERSION}-green!g" \
      -e "s!tag/v[0-9.]*!tag/v${VERSION}!g" \
      -e "s!javadoc-v[0-9.]*!javadoc-v${VERSION}!g" \
      -e "s!pochi%3A[0-9.]*!pochi%3A${VERSION}!g" \
      $i > a ; mv a $i;
done

sed "s/ARG version=\"[0-9.]*\"/ARG version=\"${VERSION}\"/g" Dockerfile > a ; mv a Dockerfile

for i in bin/make_dist.sh bin/build_site.sh ; do
    sed "s/VERSION=\"[0-9.]*\"/VERSION=\"${VERSION}\"/g" $i > a ; mv a $i
    chmod 755 $i
done

target=pochi-cmd/src/main/java/jp/cafebabe/pochicmd/Main.java
sed "s/public static final String VERSION = \"[0-9.]*\"/public static final String VERSION = \"${VERSION}\"/g" $target > a ; mv a $target

mvn versions:set -DnewVersion=${VERSION}
mvn versions:commit
