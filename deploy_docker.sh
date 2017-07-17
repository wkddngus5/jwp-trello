docker stop jwp-trello
docker rm jwp-trello
docker rmi jwp-trello

docker stop jwp-nginx
docker rm jwp-nginx
docker rmi jwp/nginx

./gradlew clean build buildDocker
docker run -d —name jwp-trello jwp-trello

cd nginx_docker
docker build -t jwp/nginx .
docker run --name jwp-trello -d -p 80:80 --link jwp-trello:jwp_trello jwp/nginx
