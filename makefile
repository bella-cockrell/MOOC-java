run: 
	java -cp target/my-app-1.0-SNAPSHOT.jar com.mycompany.app.App

test:
	mvn test;

build:
	mvn package