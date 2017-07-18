echo "=======docker build============"
docker stop jwp-trello
docker rm jwp-trello
docker rmi jwp-trello

docker stop jwp-nginx
docker rm jwp-nginx
docker rmi jwp/nginx

echo "========= start building ========="
./gradlew clean build buildDocker
docker run -d --name jwp-trello jwp-trello
echo "========= server is running ========="

cd nginx_docker
docker build -t jwp/nginx .
docker run --name jwp-nginx -d -p 80:80 --link jwp-trello:jwp_trello jwp/nginx
echo "========= nginx is running ========="
