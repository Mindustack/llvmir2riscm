package kvtodev.mindustack.minterpreter;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class Minterpreter {
    public static Logger logger = Logger.getLogger("Minterpreter");
    public List<Instruction> instructions = new ArrayList<>();
    public Map<String, Instruction> labels = new HashMap<>();
    public VariableFactory varFctr;
    public MemoryFactory memFctr;
    Variable counter;

    public static void main(String[] args) {
        Minterpreter minterpreter = new Minterpreter();
        try {
            minterpreter.parse(new String(System.in.readAllBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        minterpreter.run();
    }
    public Minterpreter() {
        this.memFctr = new MemoryFactory();
        this.varFctr = new VariableFactory();

        logger.info("minterpreter started");


    }

    /**
     * @param source mindustry logic plain code
     * @return itself for continue use
     */
    public Minterpreter parse(String source) {
        String[] splitLines = source.split("\n");
        String[] split;
        List<String> labels = new ArrayList<>();
        int count = 0;
        for (String line : splitLines) {
            line = line.trim();
            if (line.startsWith("#") || line.startsWith("//")) {
                continue;
            } else if (line.endsWith(":")) {
                labels.add(line.replaceAll("[:]", ""));

            } else if (line.equals("")) {

                continue;
            } else {
                Instruction instruction = null;
                split = line.split(" ");

                switch (split[0]) {

                    case "op" -> {
                        instruction = new ALUinst(count, split[1], this.varFctr.getVar(split[2]), this.varFctr.getVar(split.length < 4 ? null : split[3]), this.varFctr.getVar(split.length < 5 ? null : split[4]));
                    }
                    case "set" -> {
                        instruction = new SetInst(count, this.varFctr.getVar(split[1]), this.varFctr.getVar(split[2]));
                    }
                    case "stop" -> {
                        instruction = new StopInst(count);
                    }
                    case "jump" -> {

                        if (split[1].matches("[0-9]*"))
                            logger.warning("minterpreter does not support number jmp ,are you using number as label? that's not nice");
                        instruction = new JmpInst(count, split[1], split[2], this.varFctr.getVar(split.length < 4 ? null : split[3]), this.varFctr.getVar(split.length < 5 ? null : split[4]));
                    }
                    case "write" -> {
                        instruction = new WriteInst(count, this.varFctr.getVar(split[1]), this.varFctr.getVar(split[2]), this.varFctr.getVar(split[3]),

                                split.length < 5 ? 0 : Integer.parseInt(split[4]));
                    }
                    case "read" -> {
                        instruction = new ReadInst(count, this.varFctr.getVar(split[1]), this.varFctr.getVar(split[2]), this.varFctr.getVar(split[3]), split.length < 5 ? 0 : Integer.parseInt(split[4]));

                    }
                    case "call" -> {
                        instruction = new CallInst(count, split[1]);
                    }
                    case "print" -> {
                        instruction = new PrintInst(count, this.varFctr.getVar(split[1]));
                    }
                    default -> {
                        logger.severe("can't parse: " + line);
                        throw new RuntimeException("invalid inst:" + Arrays.toString(split));
                    }

                }

                if (!labels.isEmpty()) {
                    for (String l : labels) {
                        this.labels.put(l, instruction);
                    }
                    labels.clear();
                }
                instructions.add(instruction);
                count++;
                logger.info("parsed: " + line + " as: " + instruction.toString());
            }
        }

        return this;
    }

    /**
     * run logic code for limited steps default 1024
     *
     * @return
     */
    public Minterpreter run() {
        return run(1024);
    }

    /**
     * run logic code for limited steps default 1024
     *
     * @param stepLimit
     * @return
     */
    public Minterpreter run(int stepLimit) {
        Instruction inst;
        StringBuilder sb = new StringBuilder();
        counter = this.varFctr.getVar("pc");
        int step = 0;
        while (step < stepLimit) {

            inst = instructions.get((int) counter.asInteger());
            inst.execute(this);
            sb.append(inst).append("\n");
            step++;
            if (counter.value >= instructions.size() || counter.value < 0) break;
        }
        logger.info(sb.toString());
        return this;

    }

    /**
     * @return return the value in 'ret' variable to vertify
     */
    public double getRet() {
        return this.varFctr.getVar("a0").value;
    }
}
