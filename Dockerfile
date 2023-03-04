FROM openjdk:17
#Записываем в переменную путь до варника
ARG jarFile=target/SafeSummit-0.0.1-SNAPSHOT.jar
#Куда переместить варник внутри контейнера
WORKDIR /opt/app
#копируем наш джарник в новый внутри контейнера
COPY ${jarFile} SafeSummit.jar
#Открываем порт
EXPOSE 4747
#Команда для запуска
ENTRYPOINT ["java", "-jar", "SafeSummit.jar"]