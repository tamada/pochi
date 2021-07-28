#! /bin/sh

VERSION=$1

if [ "$VERSION" == "" ]; then
  echo "no version specified"
  exit 1
fi

result=0
grep "$VERSION" ./bin/make_dist.sh 2>&1 > /dev/null || result=$?

if [ "$result" == "0" ]; then
  echo "already updated"
  exit 1
fi

for i in README.md site/content/_index.md site/content/description.md ; do
  sed -e "s!Version-[0-9.]*-green!Version-${VERSION}-green!g" \
      -e "s!tag/v[0-9.]*!tag/v${VERSION}!g" \
      -e "s!Javadoc-v[0-9.]*-blue!Javadoc-v${VERSION}-blue!g" \
      -e "s!pochi%3A[0-9.]*!pochi%3A${VERSION}!g" \
      $i > a ; mv a $i;
done

for i in README.md site/content/description.md ; do
  sed "s/  \* \`\([0-9.]*\)\`, \`latest\`/  * \`${VERSION}\`, \`latest\`\n  * \`\1\`/g" $i > a ; mv a "$i"
done

for i in README.md site/content/install.md ; do
  sed "s/| \`\([0-9.]*\)\` |/| \`${VERSION}\` |/g" "$i" > a ; mv a "$i"
done

for i in $(find dockers -name Dockerfile) ; do
  sed "s/ARG PochiVersion=\"[0-9.]*\"/ARG PochiVersion=\"${VERSION}\"/g" "$i" > a ; mv a "$i"
done

for i in bin/make_dist.sh bin/build_site.sh ; do
    sed "s/VERSION=\"[0-9.]*\"/VERSION=\"${VERSION}\"/g" "$i" > a ; mv a "$i"
    chmod 755 $i
done

target=pochi-core/src/main/java/jp/cafebabe/pochi/Pochi.java
sed "s/public static final String VERSION = \"[0-9.]*\"/public static final String VERSION = \"${VERSION}\"/g" "$target" > a ; mv a "$target"

mvn versions:set -DnewVersion="${VERSION}"
mvn versions:commit

mvn clean package

mkdir lib
rm -f lib/*.jar
cp pochi-cmd/target/*.jar lib

echo "version up to ${VERSION} done"