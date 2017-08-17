echo "========= start application building ========="
./gradlew clean build buildDocker --stacktrace

echo "======= start nginx build ============"
cd nginx_docker
docker build -t jwp/nginx .

echo "======= stop docker ps ============"
docker-compose -p jwptrello down

echo "======= start docker ps ============"
docker-compose -p jwptrello up -d
