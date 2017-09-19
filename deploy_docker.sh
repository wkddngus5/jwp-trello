docker-compose down

git pull

echo "========= start application building ========="
./gradlew clean build buildDocker --stacktrace -x test

echo "======= start nginx build ============"
cd nginx_docker
docker build -t jwp/nginx .

echo "======= stop docker ps ============"
docker-compose -p jwptrello down

echo "======= start docker ps ============"
docker-compose -p jwptrello up -d
