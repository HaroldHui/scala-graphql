FROM openjdk:8

RUN wget -O- "https://sbt-downloads.cdnedge.bluemix.net/releases/v1.2.8/sbt-1.2.8.tgz" \
    |  tar xzf - -C /usr/local --strip-components=1 \
    && sbt exit

RUN mkdir /app
ADD . /app
WORKDIR /app