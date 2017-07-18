docker-compose down

echo "=======docker build============"
docker rmi jwp-trello

docker rmi jwp/nginx

echo "========= start building ========="
./gradlew clean build buildDocker

docker-compose up -d