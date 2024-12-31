package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.Value;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.hierarchy.IRFunction;
import kvtodev.mindustack.llvmir2riscm.share.pass.IRInstPass;

import java.util.ArrayList;
import java.util.List;

public class IRCallInst extends IRBaseInst {

  public boolean isTailCall = false;
//TODO
  public List<Value> mutableArgs = new ArrayList<>();

  public IRCallInst(IRFunction callFunc, ArrayList<Value> callArgs) {
    super("call" ,
        callFunc.type);
    this.addOperand(callFunc);
    for (int i = 0; i < callArgs.size(); i++)
      this.addOperand(callArgs.get(i));
    this.instName= "call";
    // for (int i = callFunc.operands.size(); i < callArgs.size(); i++)
    // this.addOperand(callArgs.get(i));
  }



  public IRFunction getCallFunc() {
    return (IRFunction) this.getOperand(0);
  }

  public Value getArg(int index) {
    return this.getOperand(index + 1);
  }

    @Override
    public boolean isTerminator() {
        return false;
    }

  @Override
  public IRBaseInst copy() {
    return null;
  }

  @Override
  public void accept(IRInstPass visitor) {
    visitor.visit(this);
  }
}
