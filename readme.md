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
# также для surname указываем внешнюю директорию data, которая будет добавлена в контейнер как /usr/app/data
docker run -d --name surname -v $(pwd)/data:/usr/app/data --net hello-net surname:1.0
docker run -d --name hello -p 8090:8080 --net hello-net hello:1.0

# проверяем, что контейнеры запустились
docker logs surname
docker logs hello

# тыкаем в hello service
curl 'http://localhost:8090/hello?name=Ivan'

# останавливаем контейнеры
docker container stop hello surname

# далее можно добавить новые имена в surnames.json файл

# после запустить контейнеры (без пересоздания) и убедиться, что новые данные подгрузились
docker container start hello surname

# останавливаем и удаляем контейнеры
docker container stop hello surname
docker rm hello surname

# сносим сеть
docker network rm hello-net

# удаляем образы
docker rmi hello:1.0 surname:1.0

```

#### то же самое с docker-compose

```bash
# пересобрать образы
docker-compose build

# создать контейнеры
docker-compose up --no-start

# запустить сервисы
docker-compose start

# либо одной командой 
docker-compose up --build -d

# снова тыкаем в hello service
curl 'http://localhost:8090/hello?name=Ivan'

# остановить сервисы
docker-compose stop

# поменять данные в surnames.json

# снова запустить сервисы и проверить, что данные поменялись
docker-compose start

# остановить сервисы
docker-compose stop

# удалить созданные контейнеры и сети
docker-compose down

# удалить созданные контейнеры, сети и образы
docker-compose down --rmi all

```

##### дополнительно

```bash

# в процессе пересборки образов старые образы не удаляются, у них стирается tag
# удалить все untagged images
docker image prune

```