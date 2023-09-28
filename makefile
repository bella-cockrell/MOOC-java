run: 
	./gradlew run

test:
	java -jar junit-platform-console-standalone-1.9.3.jar --class-path target  --scan-class-path

build:
	./gradlew build