test:
	javac ./*.java
clean:
	find . -name "*.class" | xargs rm
	find . -name "*.java~" | xargs rm
