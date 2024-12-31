import kvtodev.mindustack.llvmir2riscm.share.CodePreProcessor;
import kvtodev.mindustack.llvmir2riscm.vmConfig.VmCodeGen;
import kvtodev.mindustack.minterpreter.Minterpreter;

public class MindustackTest {
    public static void main(String[] args) {
//        StringBuilder stringBuilder=new StringBuilder();
//        stringBuilder.append("""
//                        read pc bank1 7
//                        op shr memId pc 9
//                        getlink mem memId
//                        op mod localInstId pc 0x200
//                        read inst mem localInstId
//
//                        op shr instId inst 42
//                        op shr Reg0id inst 33
//                        op mod Reg0id Reg0id 0x200
//                        op shr Reg1id inst 24
//                        op mod Reg1id Reg1id 0x200
//                        op mod uimm inst 0x4000
//                        set @counter instId
//
//                        step:
//                        op add pc pc 1
//                        direct:
//                        write pc bank1 7
//                        set pTick @tick
//                        check:
//                        jump check equal pTick @tick
//                        """);
//        stringBuilder.append("""
//                        setRR:
//                        read v0 bank1 Reg1id
//                        write v0 bank1 Reg0id
//                        jump step always
//                        """);
//        stringBuilder.append("""
//                        setRII:
//                        op mod imm inst 0x100000000
//                        jump set-immInt lessThanEq imm 0x8fffffff
//                        op sub imm imm 0x100000000
//                        set-immInt:
//                        write imm bank1 Reg0id
//                        jump step always
//                        """);
//        stringBuilder.append("""
//                        setRIF:
//                        op mod imm inst 0x100000000
//                        op shr v1 imm 23
//                        op mod v1 v1 0x100
//                        op sub v1 v1 0x96
//                        op pow v1 2 v1
//                        op mod v2 imm 0x800000
//                        op add v2 v2 0x800000
//                        op mul v0 v2 v1
//                        jump set-immFloat lessThanEq imm 0x80000000
//                        op mul v0 v0 -1
//                        set-immFloat:
//                        write v0 bank1 Reg0id
//                        jump step always
//                        """);
//        stringBuilder.append("""
//                        load:
//                        read addr bank1 Reg1id
//                        op add addr addr uimm
//                        op shr memId addr 9
//                        getlink mem memId
//                        op mod localInstId addr 512
//
//                        read v0 mem localInstId
//                        write v0 bank1 Reg0id
//                        jump step always
//                        """);
//        stringBuilder.append("""
//                        store:
//                        read addr bank1 Reg1id
//                        op add addr addr uimm
//                        op shr memId addr 9
//                        getlink mem memId
//                        op mod localInstId addr 512
//
//                        read v0 bank1 Reg0id
//                        write v0 mem localInstId
//                        jump step always
//                        """);
//        stringBuilder.append("""
//                        jump-true-return:
//                        op mod pc inst 0x800000
//                        jump direct always
//                        # always
//                        jump-always:
//                        JUMP_SHARE
//                        jump jump-true-return always
//                        # equal
//                        jump-equal:
//                        JUMP_SHARE
//                        jump jump-true-return equal v0 v1
//                        jump step always
//                        # not equal
//                        jump-notEqual:
//                        JUMP_SHARE
//                        jump jump-true-return notEqual v0 v1
//                        jump step always
//                        # less than
//                        jump-lessThan:
//                        JUMP_SHARE
//                        jump jump-true-return lessThan v0 v1
//                        jump step always
//                        # less than or equal
//                        jump-lessThanEq:
//                        JUMP_SHARE
//                        jump jump-true-return lessThanEq v0 v1
//                        jump step always
//                        # greater than
//                        jump-greaterThan:
//                        JUMP_SHARE
//                        jump jump-true-return greaterThan v0 v1
//                        jump step always
//                        # greater than or equal
//                        jump-greaterThanEq:
//                        JUMP_SHARE
//                        jump jump-true-return greaterThanEq v0 v1
//                        jump step always
//                        # and
//                        jump-and:
//                        JUMP_SHARE
//                        jump jump-true-return and v0 v1
//                        jump step always
//                        # or
//                        jump-or:
//                        JUMP_SHARE
//                        jump jump-true-return or v0 v1
//                        jump step always
//                        # xor
//                        jump-xor:
//                        JUMP_SHARE
//                        jump jump-true-return xor v0 v1
//                        jump step always
//                        """);
//        stringBuilder.append("""
//                        op-return:
//                        write v0 bank1 Reg0id
//                        jump step always
//                        # add
//                        op-add:
//                        OP_SHARE
//                        op add v0 v1 v2
//                        jump op-return always
//                        # sub
//                        op-sub:
//                        OP_SHARE
//                        op sub v0 v1 v2
//                        jump op-return always
//                        # mul
//                        op-mul:
//                        OP_SHARE
//                        op mul v0 v1 v2
//                        jump op-return always
//                        # div
//                        op-div:
//                        OP_SHARE
//                        op div v0 v1 v2
//                        jump op-return always
//                        # mod
//                        op-mod:
//                        OP_SHARE
//                        op mod v0 v1 v2
//                        jump op-return always
//                        # eq
//                        op-equal:
//                        OP_SHARE
//                        op equal v0 v1 v2
//                        jump op-return always
//                        # ne
//                        op-notEqual:
//                        OP_SHARE
//                        op notEqual v0 v1 v2
//                        jump op-return always
//                        # lt
//                        op-lessThan:
//                        OP_SHARE
//                        op lessThan v0 v1 v2
//                        jump op-return always
//                        # le
//                        op-lessThanEq:
//                        OP_SHARE
//                        op lessThanEq v0 v1 v2
//                        jump op-return always
//                        # gt
//                        op-greaterThan:
//                        OP_SHARE
//                        op greaterThan v0 v1 v2
//                        jump op-return always
//                        # ge
//                        op-greaterThanEq:
//                        OP_SHARE
//                        op greaterThanEq v0 v1 v2
//                        jump op-return always
//                        # and
//                        op-and:
//                        OP_SHARE
//                        op and v0 v1 v2
//                        jump op-return always
//                        # or
//                        op-or:
//                        OP_SHARE
//                        op or v0 v1 v2
//                        jump op-return always
//                        # xor
//                        op-xor:
//                        OP_SHARE
//                        op xor v0 v1 v2
//                        jump op-return always
//                        # shl
//                        op-shl:
//                        OP_SHARE
//                        op shl v0 v1 v2
//                        jump op-return always
//                        # shr
//                        op-shr:
//                        OP_SHARE
//                        op shr v0 v1 v2
//                        jump op-return always
//                        """);
//
//        CodePreProcessor codePreProcessor = new CodePreProcessor(stringBuilder.toString());
//        codePreProcessor.applyMacro("OP_SHARE", """
//                                read v1 bank1 Reg1id
//                                op shr Reg2id inst 15
//                                op mod Reg2id Reg2id 0x200
//                                read v2 bank1 Reg2id""");
//        codePreProcessor.applyMacro("JUMP_SHARE",
//                                """
//                                        read v0 bank1 Reg0id
//                                        read v1 bank1 Reg1id
//                                        """);
//
//        codePreProcessor.process();
        CodePreProcessor codePreProcessor = VmCodeGen.codePreProcessor;
        Minterpreter minterpreter = new Minterpreter("@counter");
        minterpreter.parse(codePreProcessor.get());
        minterpreter
                .setMem(7, 1030)
                .setMem(49, 1)
                .setMem(50, 2)
                .setMem(1024,
                        (codePreProcessor.getFlagCounter("setRR") << 42) +
                                (48L << 33) +
                                (49L << 24))

                .setMem(1025,
                        (codePreProcessor.getFlagCounter("setRII") << 42) +
                                (47L << 33) +
                                (0xffffffffL)
                )
                .setMem(1026,
                        (codePreProcessor.getFlagCounter("setRIF") << 42) +
                                (46L << 33) +
                                (0xc0000400L)
                )
                .setMem(44, 48)
                .setMem(1028,
                        (codePreProcessor.getFlagCounter("store") << 42) +
                                (45L << 33) +
                                (44L << 24) +
                                1
                )
                .setMem(1029, (codePreProcessor.getFlagCounter("jump-and") << 42)
                        + (49L << 33)
                        + (50L << 24) +
                        1027)
                .setMem(1030, (codePreProcessor.getFlagCounter("op-add") << 42)
                        + (40L << 33)
                        + (50L << 24)
                        + (49L << 15))
        ;

        minterpreter.run(25);
        minterpreter.dump();
    }
}
