import kvtodev.mindustack.llvmir2riscm.llvmir2riscm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MultiTest {
    public static void main(String[] args) {
          try {
            llvmir2riscm llvmir2riscm = new llvmir2riscm();
            llvmir2riscm.compile(new FileInputStream("k.ll"));
            llvmir2riscm.compile(new FileInputStream("k2.ll"));


            llvmir2riscm.assemble();
            llvmir2riscm.printBin();
            llvmir2riscm.printLiter();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
