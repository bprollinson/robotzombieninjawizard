full:
	make clean
	make game
game:
	javac ./*.java
clean:
	find . -name "*.class" -exec rm {} \;
	find . -name "*.java~" -exec rm {} \;
run:
	java RobotZombieNinjaWizard
