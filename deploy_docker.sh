echo "========= start application building ========="
./gradlew clean build buildDocker

echo "======= start nginx build ============"
cd nginx_docker
docker build -t jwp/nginx .

echo "======= stop docker ps ============"
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)

echo "======= start docker ps ============"
docker-compose up -d
