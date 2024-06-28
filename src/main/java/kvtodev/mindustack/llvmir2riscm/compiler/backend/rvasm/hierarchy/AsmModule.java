package kvtodev.mindustack.llvmir2riscm.compiler.backend.rvasm.hierarchy;

import kvtodev.mindustack.llvmir2riscm.compiler.share.pass.Pointer;

import java.util.ArrayList;
import java.util.List;

public class AsmModule {

    public ArrayList<AsmFunction> functions = new ArrayList<>();



    public Pointer<Integer> heapUse=new Pointer<>(0);

    public List<AsmFunction> externFunctions=new ArrayList<>();
}
