FROM busybox:1.31.0 AS base

ARG JAR_FILE
ADD ${JAR_FILE} /app.jar
RUN unzip app.jar

FROM openjdk:11.0.5-jre-slim

RUN useradd supply
USER supply

VOLUME /tmp

COPY --from=base --chown=supply /BOOT-INF/lib     /app/lib
COPY --from=base --chown=supply /META-INF         /app/META-INF
COPY --from=base --chown=supply /BOOT-INF/classes /app

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-cp", "app:app/lib/*", "org.tcc.gestao.normas.core.GestaoNormasApplication"]