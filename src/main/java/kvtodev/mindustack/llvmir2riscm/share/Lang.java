package kvtodev.mindustack.llvmir2riscm.share;

import java.util.*;

public class Lang {

    public static Set<String> ignoredLLVMFunctions =new HashSet<>(List.of("@llvm.lifetime.start.p0","@llvm.lifetime.end.p0"));
    // --- Register Related ---

    public static final String FuncArgRegPrefix = "a";
    public static final String TempRegPrefix = "t";
    public static final String SavedRegPrefix = "s";

    public static final List<String> RegCalleeSaved = new ArrayList<String>(List.of(

            "s0", "s1", "s2", "s3", "s4", "s5",
            "s6", "s7", "s8"

    ));

    public static final List<String> FuncArgReg = new ArrayList<String>(List.of(
            "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7","a8","a9","a10"

    ));

    public static final List<String> RegCallerSaved = new ArrayList<String>(List.of(

            "t0", "t1",
            "t2", "t3", "t4", "t5", "t6"
    )) {
        {
            addAll(FuncArgReg);
        }
    };
    public static final List<String> Reg = new ArrayList<String>(Arrays.asList(
            "zero",
            "ra"// return address
            , "sp" // top of stack
            , "gp1", "gp2", "gp3"
            , "tp"// treads
            , "pc"
    )) {
        {
            addAll(RegCallerSaved);
            addAll(RegCalleeSaved);
        }
    };


    public static final String AddOperation = "add";
    public static final String SubOperation = "sub";
    public static final String MulOperation = "mul";
    public static final String DivOperation = "div";
    public static final String OrOperation = "or";
    public static final String XorOperation = "xor";
    public static final String ShiftLeftOperation = "shl";
    public static final String ShiftRightOperation = "shr";
    public static final String ModOpertion = "mod";
    public static final String LessThanOperation = "lessThan";
    public static final String GreaterThanOperation = "greaterThan";
    public static final String LessThanEqOperation = "lessThanEq";
    public static final String GreaterThanEqOperation = "greaterThanEq";
    public static final String EqualOperation = "equal";
    public static final String NotEqualOperation = "notEqual";
    public static final String GPRegPrefix = "gp";

    public static String VirtualRegPrefix = "virtualReg";

    public static  final  int defaultMemory=512*16;
    public static final int reservedMemory=512;

}
