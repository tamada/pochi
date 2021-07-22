# building minimal jdk
FROM alpine:3.10.1 AS base

RUN    apk --no-cache add openjdk11=11.0.4_p4-r1 \
    && rm -rf /var/cache/apk/* \
# create minimal jdk
    && /usr/lib/jvm/java-11-openjdk/bin/jlink \
       --module-path /usr/lib/jvm/java-11-openjdk/jmods \
       --compress=2 \
      --add-modules java.base,java.scripting,java.logging,java.desktop,java.sql,java.xml,jdk.zipfs \
      --no-header-files \
      --no-man-pages \
      --output /opt/openjdk-11-minimal

# building pochi
FROM alpine:3.10.1

ARG PochiVersion="2.3.24"
ARG GroovyVersion="3.0.8"

LABEL maintainer="Haruaki Tamada" \
      pochi-version="${PochiVersion}" \
      description="Java birthmarking toolkit."

COPY --from=base  /opt/openjdk-11-minimal /opt/openjdk-11-minimal

RUN    apk update \
    && apk --no-cache add --update --virtual .builddeps curl unzip bash libstdc++ \
    && ln -s /opt/openjdk-11-minimal /opt/java \
# install pochi from release file in the GitHub.
    && curl -L https://github.com/tamada/pochi/releases/download/v${PochiVersion}/pochi-${PochiVersion}.zip -o /tmp/pochi.zip \
    && unzip -q /tmp/pochi.zip -d /opt \
    && ln -s /opt/pochi-${PochiVersion} /opt/pochi \
    && rm -rf /opt/pochi/docs /opt/pochi/examples /opt/pochi/completions \ 
# install groovy (ref. http://konstructcomputers.blogspot.com/2017/02/install-groovy-in-alpine-based-docker.html)
    && curl -sL https://groovy.jfrog.io/artifactory/dist-release-local/groovy-zips/apache-groovy-binary-${GroovyVersion}.zip -o /tmp/groovy.zip \
    && unzip -q /tmp/groovy.zip -d /opt \
    && ln -s /opt/groovy-${GroovyVersion} /opt/groovy \
# add user
    && adduser -D pochi \
# remove installed package
    && rm /tmp/pochi.zip /tmp/groovy.zip \
    && apk del --purge .builddeps

ENV POCHI_HOME="/opt/pochi"
ENV JAVA_HOME="/opt/java"
ENV GROOVY_HOME="/opt/groovy"
ENV PATH="$PATH:$JAVA_HOME/bin:$POCHI_HOME/bin:$GROOVY_HOME/bin"
ENV HOME="/home/pochi"

WORKDIR /home/pochi
USER    pochi

ENTRYPOINT [ "pochi" ]
