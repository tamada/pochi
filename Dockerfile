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

ARG version="2.1.0"

LABEL maintainer="Haruaki Tamada" \
      pochi-version="${version}" \
      description="Java birthmarking toolkit."

COPY --from=base  /opt/openjdk-11-minimal /opt/openjdk-11-minimal

RUN    apk update \
    && apk --no-cache add --update --virtual .builddeps curl unzip bash libstdc++ tar \
    && cd /opt    \
    && ln -s /opt/openjdk-11-minimal /opt/java \
# install pochi from release file in the GitHub.
    && curl -L https://github.com/tamada/pochi/releases/download/v${version}/pochi-${version}_linux_amd64.tar.gz -o /tmp/pochi.tar.gz \
    && tar xvfz /tmp/pochi.tar.gz \
    && ln -s /opt/pochi-${version} /opt/pochi \
# install groovy (ref. http://konstructcomputers.blogspot.com/2017/02/install-groovy-in-alpine-based-docker.html)
    && curl -L https://dl.bintray.com/groovy/maven/apache-groovy-binary-3.0.5.zip -o /tmp/groovy.zip \
    && unzip /tmp/groovy.zip \
    && ln -s /opt/groovy-3.0.5 /opt/groovy \
# add user
    && adduser -D pochi \
# remove installed package
    && rm /tmp/pochi.tar.gz /tmp/groovy.zip \
    && apk del --purge .builddeps


ENV POCHI_HOME="/opt/pochi"
ENV JAVA_HOME="/opt/java"
ENV GROOVY_HOME="/opt/groovy"
ENV PATH="$PATH:$JAVA_HOME/bin:$POCHI_HOME/bin:$GROOVY_HOME/bin"
ENV HOME="/home/pochi"

WORKDIR /home/pochi
USER    pochi

ENTRYPOINT [ "pochi" ]
