package kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.inst;

import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.Value;
import kvtodev.mindustack.llvmir2riscm.compiler.middleend.llvmir.type.VoidType;
import kvtodev.mindustack.llvmir2riscm.compiler.share.pass.IRInstPass;

/*
 It is a fake instruction which can not be recognized by llc
 It is value itself is meaningless (void inst)
 created by SSADestructor
*/

public class IRMoveInst extends IRBaseInst {

  public IRMoveInst(Value dest, Value source) {
    super("move", new VoidType());
    this.addOperand(dest);
    this.addOperand(source);
    this.instName= "move";

  }

  public Value dest() {
    return this.getOperand(0);
  }

  public Value source() {
    return this.getOperand(1);
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
