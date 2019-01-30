#### how to build:

```bash

# собираем проект
mvn clean verify

# создаём образы
docker build hello-app --tag hello:1.0
docker build surname-app --tag surname:1.0

# чтобы было можно обращаться к другому контейнеру по имени вместо IP
docker network create hello-net

# при создании контейнеров указываем сеть, в которой они будут находиться
docker run --rm --name surname -p 8081:8080 --net hello-net surname:1.0
# передаём через env переменную путь к surname service (порт 8080!)
docker run --rm --name hello -p 8082:8080 -e 'SURNAME_SERVICE_URI=http://surname:8080' --net hello-net hello:1.0

# проверяем, что surname работает
curl 'http://localhost:8081/surname?name=Alina'
# тыкаем в hello service
curl 'http://localhost:8082/hello?name=Alina'

# то же самое с docker-compose (см docker-compose.yml)
docker-compose up
# потом дропнуть созданные контейнеры
docker-compose rm

```