package kvtodev.mindustack.llvmir2riscm.compiler.backend.riscm.operand;

import kvtodev.mindustack.llvmir2riscm.share.Lang;

public class VirtualReg extends Register {

    public static int virtualRegNum = 0;
    public final int num, size;

    public VirtualReg() {
        super(Lang.VirtualRegPrefix + virtualRegNum);
        this.num = virtualRegNum;
        this.size = 4;
        virtualRegNum++;
    }

    public static void regNumReset() {
        virtualRegNum = 0;
    }
}
