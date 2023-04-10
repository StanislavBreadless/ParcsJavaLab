all: run

clean:
	rm -f out/Bluck.jar out/Solver.jar

out/Bluck.jar: out/parcs.jar src/Bluck.java src/Node.java
	@javac -cp out/parcs.jar src/Bluck.java src/Node.java
	@jar cf out/Bluck.jar -C src Bluck.class -C src Node.class
	@rm -f src/Bluck.class src/Node.class

out/Solver.jar: out/parcs.jar src/Solver.java src/Node.java
	@javac -cp out/parcs.jar src/Solver.java src/Node.java
	@jar cf out/Solver.jar -C src Solver.class -C src Node.class
	@rm -f src/Solver.class src/Node.class

build: out/Bluck.jar out/Solver.jar

run: out/Bluck.jar out/Solver.jar
	@cd out && java -cp 'parcs.jar:Bluck.jar' Bluck
