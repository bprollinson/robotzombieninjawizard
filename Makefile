full:
	make clean
	make test
test:
	javac ./*.java
clean:
	find . -name "*.class" | xargs --no-run-if-empty rm
	find . -name "*.java~" | xargs --no-run-if-empty rm
