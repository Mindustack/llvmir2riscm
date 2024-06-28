package kvtodev.mindustack.llvmir2riscm;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.optim.BackEndOptimizer;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.regalloc.RegisterAllocator;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.AsmBuilder;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.AsmPrinter;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.IRBuilder;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.IRPrinter;
import org.antlr.v4.runtime.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class llvmir2riscm {
    public static Logger logger = Logger.getLogger("llvmir2riscm");

    static {
        try {
            FileHandler fileHandler = new FileHandler("log.txt");
            llvmir2riscm.logger.addHandler(fileHandler);
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {

        llvmir2riscm llvmir2riscm = new llvmir2riscm();
        System.out.println(llvmir2riscm.compile(System.in));
    }

    public String compile(InputStream code) {

        IRBuilder irbuilder = new IRBuilder();
        try {

            irbuilder.run(CharStreams.fromStream(code));
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe(Arrays.toString(e.getStackTrace()));
            return "";
        }

        IRPrinter irPrinter = new IRPrinter(irbuilder.irModule);
        logger.info(irPrinter.toString());
        AsmBuilder builder = new AsmBuilder();
        builder.runOnModule(irbuilder.irModule);

        AsmPrinter asmPrinter = new AsmPrinter(builder.module);
        logger.info(asmPrinter.toString());

        new RegisterAllocator().runOnModule(builder.module);
        new BackEndOptimizer().runOnModule(builder.module);

        asmPrinter = new AsmPrinter(builder.module);
        logger.info(asmPrinter.toString());


        return asmPrinter.toString();
    }
}
