java -classpath target/classes:$HOME/.m2/repository/org/antlr/antlr4-runtime/4.11.1/antlr4-runtime-4.11.1.jar kvtodev.mindustack.llvmir2riscm.llvmir2riscm < testcases/basic/Arithmetic_operation.ll >out.txt

java -classpath target/classes kvtodev.mindustack.minterpreter.Minterpreter <out.txt >result.txt