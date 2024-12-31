package kvtodev.mindustack.llvmir2riscm.compiler.middleend.analyzer;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.Value;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy.IRBlock;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy.IRFunction;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst.IRBaseInst;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst.IRBrInst;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst.IRMoveInst;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst.IRRetInst;
import kvtodev.mindustack.llvmir2riscm.share.pass.IRFuncPass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * MiddleendAnalyzer
 */
public class MiddleendAnalyzer implements IRFuncPass {

    static final int visited = 8;

    private List<IRBlock> oriList = new ArrayList<>();
    private List<IRBlock> objList = new ArrayList<>();
    private List<IRBlock> desList = new ArrayList<>();

    LinkedList<IRBlock> history = new LinkedList<>();

    public void redirect() {
        for (int i = 0; i < objList.size(); i++) {
            objList.get(i).redirect(oriList.get(i), desList.get(i));
        }
        objList.clear();
        oriList.clear();
        desList.clear();
    }

    public void addRedirect(IRBlock obj, IRBlock origin, IRBlock dest) {

        objList.add(obj);

        oriList.add(origin);
        desList.add(dest);
    }

    @Override
    public void runOnFunc(IRFunction function) {
        redirect();
        function.entryBlock = function.blocks.getFirst();
        resolveBlockgraph(function);
        visit(function.entryBlock);

    }

    void visit(IRBlock block) {
        if (block.visited == visited) {
            Iterator<IRBlock> it = history.descendingIterator();
            IRBlock cur;
            while (it.hasNext()) {
                cur = it.next();
                if (cur == block) {
                    it = history.descendingIterator();
                    while (it.hasNext()) {
                        cur = it.next();
                        cur.loopDepth += 1;
                        if (cur == block)
                            break;
                    }
                    break;
                }

            }
            return;
        }
        block.visited = visited;
        block.nexts.forEach(this::visit);
    }

    void resolveBlockgraph(IRFunction func) {
        var blocks = func.blocks;
        List<IRBlock> rets = new ArrayList<>();

//        if (func.blocks.size() == 1) {
//
//
//            IRBlock irBlock = new IRBlock(func.name + "_ret");
//
//            irBlock.instructions.add(func.entryBlock.terminator());
//            func.entryBlock.instructions.removeLast();
//            func.entryBlock.addInst(new IRBrInst(irBlock));
//
//            func.addBlock(irBlock);
//        }

        blocks.forEach(b -> {
            IRBaseInst inst = b.instructions.getLast();
            if (inst instanceof IRBrInst) {
                if (((IRBrInst) inst).isJump()) {
                    IRBlock v;
                    v = ((IRBrInst) inst).destBlock();
                    b.nexts.add(v);
                    v.prevs.add(b);
                } else {
                    IRBlock v1, v2;
                    v1 = ((IRBrInst) inst).ifTrueBlock();
                    b.nexts.add(v1);
                    v1.prevs.add(b);
                    v2 = ((IRBrInst) inst).ifFalseBlock();
                    b.nexts.add(v2);
                    v2.prevs.add(b);
                }
            } else {
                // func.exitBlock=((IRRetInst)inst).retVal();
                rets.add(b);
            }
        });

        if (rets.size() == 1) {
            func.exitBlock = rets.getFirst();
        } else {
            func.exitBlock = new IRBlock(func.name + "_ret");
            func.addBlock(func.exitBlock);
            Value finalRet = new Value(func.name + "_result", func.type);
            for (IRBlock b : rets) {
                Value retVal = ((IRRetInst) b.instructions.getLast()).retVal();
                b.instructions.removeLast();
                finalRet.type = retVal.type;
                b.addInst(new IRMoveInst(finalRet, retVal));

                b.addInst(new IRBrInst(func.exitBlock));
                b.nexts.add(func.exitBlock);
                func.exitBlock.prevs.add(b);
            }
        }
    }

}
