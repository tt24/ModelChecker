Readme for the Model Checker project
University of St Andrews
School of Computer Science


The aim of this project is to implement a simple model checker - specifically to implement
a check method which takes a state, a fairness constraint formula and a query formula, and
returns a boolean, and builds a trace in case the query fails.

Classes are provided which implement models and formulas, and some support for reading
their definitions from json files. There are also some unit tests which illustrate how the
classes can be used.

The code is built using gradle which acts as a tool for getting dependencies, compiling
and running tests.

To build the groject using gradle, the syntax is as follow:
```
	./gradlew clean build test coverage

```
clean is a gradle task to clean the build directory
build is a gradle task to build the project (e.g compileJava)
test is a gradle task to run the unit testing
coverage is a gradle task to generate the test coverage report

Gradle downloads jars it needs and stores them in a ~/.gradle folder.
You can import the code into Eclipse if you wish.


The asCTL parser we use in this model is implemented using Antlr 3.X, 
There are two different parsers available to use for this project based on its grammar 
(i.e. Formula.g). Detail of how the asCTL is being parsed is as below:

```
	> AaFb ( p && q ); 
	  a = ["act1", "act2"];
	  b = ["act3", "act4"]
		quantifier = "AF"
		ap = {p,q}
		operator = "&&"
		actions[0] = ["act1", "act2"]
		actions[1] = ["act3", "act4"]

	> A ( p U AG (q) )
		quantifier = "A"
		ap = {p}
		nestedCTL = {AG (q)}
```
