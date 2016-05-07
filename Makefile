full:
	make clean
	make test
test:
	javac ./*.java
clean:
	find . -name "*.class" -exec rm {} \;
	find . -name "*.java~" -exec rm {} \;
