full:
	make clean
	make game
	make jar
	make clean
game:
	javac -cp ./src/main -Xlint:unchecked ./*.java
clean:
	find . -name "*.class" -exec rm {} \;
	find . -name "*.java~" -exec rm {} \;
jar:
	rm -f ./RobotZombieNinjaWizard.jar
	jar cvfm RobotZombieNinjaWizard.jar Manifest.txt RobotZombieNinjaWizard.java RobotZombieNinjaWizard.class -C ./src/main rznw
run:
	java -jar RobotZombieNinjaWizard.jar
