run: 
	mvn compile exec:java -Dexec.mainClass="com.project.app.App"

test:
	mvn test;

package:
	mvn package;
	java -cp target/my-app-1.0-SNAPSHOT.jar com.project.app.App