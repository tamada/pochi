# building minimal jdk
FROM alpine:3.10.1 AS base

RUN    apk --no-cache add openjdk11=11.0.4_p4-r1 \
    && rm -rf /var/cache/apk/* \
    && /usr/lib/jvm/java-11-openjdk/bin/jlink \
       --module-path /usr/lib/jvm/java-11-openjdk/jmods \
       --compress=2 \
      --add-modules java.base,java.scripting,java.logging,java.xml,jdk.zipfs,jdk.scripting.nashorn,jdk.scripting.nashorn.shell \
      --no-header-files \
      --no-man-pages \
      --output /opt/openjdk-11-minimal

# building pochi
FROM maven:3.6.3-jdk-11 AS maven

ARG pochi_version="1.0.0"

RUN    git clone https://github.com/tamada/pochi.git /usr/src/mymaven/pochi \
    && cd /usr/src/mymaven/pochi \
    && git checkout -b v${pochi_version} refs/tags/v${pochi_version} \
    && mvn package \
    && unzip distribution/target/pochi-${pochi_version}-dist.zip -d /usr/src/mymaven

# building distributed docker image.
FROM alpine:3.10.1

## Is ARG binded to FROM?
ARG pochi_version="1.0.0"

LABEL maintainer="Haruaki Tamada" \
      pochi-version="${pochi_version}" \
      description="Java birthmarking toolkit."

COPY --from=base  /opt/openjdk-11-minimal                 /opt/openjdk-11-minimal
COPY --from=maven /usr/src/mymaven/pochi-${pochi_version} /opt/pochi-${pochi_version}

RUN    adduser -D pochi \
    && ln -s /opt/pochi-${pochi_version} pochi \
    && chmod 755 /opt/pochi/bin/pochi.sh

ENV POCHI_HOME="/opt/pochi-${pochi_version}"
ENV JAVA_HOME="/opt/openjdk-11-minimal"
ENV PATH="$PATH:$JAVA_HOME/bin:/opt/pochi-${pochi_version}/bin"
ENV HOME="/home/pochi"

WORKDIR /home/pochi
USER    pochi

ENTRYPOINT [ "pochi.sh" ]
