full:
	make clean
	make game
	make jar
	make clean
game:
	javac -Xlint:unchecked ./*.java
clean:
	find . -name "*.class" -exec rm {} \;
	find . -name "*.java~" -exec rm {} \;
jar:
	rm -f ./RobotZombieNinjaWizard.jar
	jar cvfm RobotZombieNinjaWizard.jar Manifest.txt RobotZombieNinjaWizard.java RobotZombieNinjaWizard.class rznw
run:
	java -jar RobotZombieNinjaWizard.jar
