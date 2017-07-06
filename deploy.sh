./gradlew clean build
echo "build successful"

cd build/libs
kill -9 -f jwp-trello
java -Dserver.port=8000 -jar project1-0.0.1-SNAPSHOT.jar &
echo "started server"