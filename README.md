# llvmir2riscm

llvmir2riscm is a backend for clang ,designed for compiling llvmir to asm in `Mindustry`.
llvmir2riscm is part of project `Mindustack`, serving as a compiler to make it possible to execute c/cpp in the game

## basic use

```bash
# install the compiler using maven (jdk21 is wanted)
mvn install
# write your c code ,testcases/basic/Arithmetic_operation.c for example
# take note that func print is to test and func main is marked as not to be optimized
vim testcases/basic/Arithmetic_operation.c
#compile it using clang (v17 is ok ,too old will produce unsupported llvmir code)
clang -S -O3 -emit-llvm  -target mips testcases/basic/Arithmetic_operation.c
# run and output to out.txt
java -classpath target/classes:$HOME/.m2/repository/org/antlr/antlr4-runtime/4.11.1/antlr4-runtime-4.11.1.jar kvtodev.mindustack.llvmir2riscm.llvmir2riscm < testcases/basic/Arithmetic_operation.ll >out.txt
# minterpreter is a interpreter to test the code ,print will be redirected to file result.txt 
java -classpath target/classes kvtodev.mindustack.minterpreter.Minterpreter <out.txt >result.txt
```