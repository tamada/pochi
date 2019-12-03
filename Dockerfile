FROM maven:3.6.3-jdk-11 AS maven

RUN    git clone https://github.com/tamada/pochi.git \
    && cd pochi
    && mvn package
    && unzip distribution/target/pochi-1.0.0-dist.zip -d ../
    && /usr/local/openjdk-11/bin/jlink \
       --module-path /usr/local/openjdk-11/jmods \
       --compress=2 \
      --add-modules java.base,java.scripting.java.logging,java.xml,jdk.zipfs,jdk.scripting.nashorn,jdk.scripting.nashorn.shell \
      --no-header-files \
      --no-man-pages \
      --output /usr/src/mymaven/openjdk-11-minimal

FROM alpine:3.10.1
LABEL maintainer="Haruaki Tamada" \
      pochi-version="1.0.0" \
      description="Java birthmarking toolkit."

COPY --from=maven /usr/src/mymaven/openjdk-11-minimal /opt/openjdk-11-minimal
COPY --from=maven /usr/src/mymaven/pochi-1.0.0        /home/pochi

RUN    adduser -D pochi \
    && chmod 755 /home/pochi/bin/pochi

ENV JAVA_HOME="/opt/openjdk-11-minimal"
ENV PATH="$PATH:$JAVA_HOME/bin"
ENV HOME="/home/pochi"

WORKDIR /home/pochi
USER    pochi

ENTRYPOINT [ "9rules.sh" ]
