package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm;

import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmFunction;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.hierarchy.AsmModule;
import kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand.RawMemOffset;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.constant.BaseConst;
import kvtodev.mindustack.llvmir2riscm.llvmir2riscm;
import kvtodev.mindustack.llvmir2riscm.share.Lang;
import kvtodev.mindustack.llvmir2riscm.share.pass.AsmBlockPass;
import kvtodev.mindustack.llvmir2riscm.share.pass.AsmFuncPass;
import kvtodev.mindustack.llvmir2riscm.share.pass.AsmModulePass;

import java.util.HashMap;
import java.util.List;

/**
 * link and build asm
 */
public class Assembler implements AsmModulePass, AsmFuncPass, AsmBlockPass {


    private long cur = Lang.reservedMemory;

    private long top = Lang.defaultMemory - 1;
    private StringBuilder literContent = new StringBuilder();


    private final HashMap<String, AsmFunction> functionLinks = new HashMap<>();
    private final HashMap<String, RawMemOffset> exposedGlobalVars = new HashMap<>();

    public Assembler(List<AsmModule> asmModules) {
        for (AsmModule asmModule : asmModules) {
            for (AsmFunction function : asmModule.functions) {
                if (function.isGlobal) functionLinks.put(function.identifier, function);
                function.entryAddress = cur;
                for (AsmBlock block : function.blocks) {
                    block.asmAddress.pointed = cur;
                    cur += block.instructions.size();
                }
            }
            exposedGlobalVars.putAll(asmModule.exposedMemOffsets);
        }
        for (AsmModule asmModule : asmModules) {
            for (RawMemOffset rawMemOffset : asmModule.rawMemOffsets) {
                rawMemOffset.setValue(cur);
                cur += rawMemOffset.size;
            }
        }
        for (AsmModule asmModule : asmModules) {
            asmModule.externMemOffsets.forEach((k, v) -> {
                v.setValue(exposedGlobalVars.get(k).getValue());
            });

        }
        for (AsmModule asmModule : asmModules) {
            for (AsmFunction externFunction : asmModule.externFunctions) {

                try {
                    externFunction.entryAddress = functionLinks.get(externFunction.identifier).entryAddress;
                } catch (Exception e) {
                    llvmir2riscm.logger.severe("not found implement for " + externFunction.identifier);
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
        asmModules.forEach(this::runOnModule);
        for (AsmModule asmModule : asmModules) {
            for (kvtodev.mindustack.llvmir2riscm.share.Pair<RawMemOffset, BaseConst> Pair : asmModule.dataSection) {
                literContent.append(Pair.first.getValue()).append(":\n").append(Pair.second.toLiteral()).append('\n');
                binContent.append(Pair.first.getValue()).append(":\n").append(Pair.second.toBin()).append('\n');
            }
        }
    }

    private StringBuilder binContent = new StringBuilder();

    @Override
    public void runOnBlock(AsmBlock block) {


        literContent.append(block.identifier).append(":\n");
        block.instructions.forEach(inst -> {
            try {
                String binaryString = Long.toBinaryString(inst.genBin());
                binContent.append("0b");
                for (int i = 0; i < 64 - binaryString.length(); i++) {
                    binContent.append(0);
                }
                binContent.append(binaryString).append('\n');
                literContent.append(inst.genLiter()).append('\n');
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void runOnFunc(AsmFunction function) {


        binContent.append(function.entryAddress).append(":\n");
        function.blocks.forEach(this::runOnBlock);
    }

    @Override
    public void runOnModule(AsmModule module) {
        module.functions.forEach(this::runOnFunc);

    }

    public String assembleBin() {

        return binContent.toString();
    }

    public String assembleLiter() {
        return literContent.toString();
    }
}
