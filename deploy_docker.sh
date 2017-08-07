echo "=======docker build============"
docker stop jwp-trello
docker rm jwp-trello
docker rmi jwp-trello

docker stop jwp-nginx
docker rm jwp-nginx
docker rmi jwp/nginx

echo "========= start building ========="
./gradlew clean build buildDocker

cd nginx_docker
docker build -t jwp/nginx .
