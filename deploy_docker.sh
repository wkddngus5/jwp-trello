echo "========= start application building ========="
./gradlew clean build buildDocker

echo "======= start nginx build ============"
cd nginx_docker
docker build -t jwp/nginx .

echo "======= stop docker ps ============"
docker-compose stop all

echo "======= start docker ps ============"
docker-compose up --build -d