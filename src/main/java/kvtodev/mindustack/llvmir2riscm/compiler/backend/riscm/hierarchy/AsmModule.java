package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.RawMemOffset;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.constant.BaseConst;
import kvtodev.mindustack.llvmir2riscm.share.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsmModule {

    public List<Pair<RawMemOffset, BaseConst>> dataSection=new ArrayList<>();
    public List<AsmFunction> functions = new ArrayList<>();
//    public Pointer<Integer> heapUse=new Pointer<>(0);
    public List<AsmFunction> externFunctions=new ArrayList<>();
    public Map<String,RawMemOffset> exposedMemOffsets =new HashMap<>();
    public Map<String, RawMemOffset> externMemOffsets=new HashMap<>();
    public List<RawMemOffset> rawMemOffsets=new ArrayList<>();
}
