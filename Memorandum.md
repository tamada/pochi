# Memorandum

## version up

update each `pom.xml`, and `bin/pochi`

we can update to development (`X.Y.Z**-SNAPSHOT**`) version, run `versions:set`, and `versions:commit` goals of maven like the following.
If we want to rollback after `versions:set`, we can revert with using `versions:revert` goal.

```sh
mvn versions:set -DnewVersion=1.1.0-SNAPSHOT
mvn versions:commit
```

## debugging docker

```sh
docker run -it --rm --entrypoint="/bin/ash" tamada/pochi:1.0.0
```
