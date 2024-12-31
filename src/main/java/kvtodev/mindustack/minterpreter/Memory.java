package kvtodev.mindustack.minterpreter;

import java.util.HashMap;
import java.util.Map;

public class Memory {
    Map<Long, Map<Long, Double>> mem = new HashMap<>();

    protected Memory() {
        mem.put(0L, new HashMap<>());
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Long, Map<Long, Double>> entry : mem.entrySet()) {
            sb.append("Page: ").append(entry.getKey()).append("\n");
            for (Map.Entry<Long, Double> entry1 : entry.getValue().entrySet()) {
                sb.append("Index: ").append(entry1.getKey()).append(" Value: ").append(entry1.getValue()).append("\n");
            }
        }
        return sb.toString();
    }

    public void read(Variable ret, Variable page, Variable index, int imm) {
        long i = page.asInteger();
        if (!mem.containsKey(i)) {
            mem.put(i, new HashMap<>(512));
        }
        ret.value = mem.get(i).getOrDefault(index.asInteger() + imm, 0.0);
    }

    public void write(Variable source, Variable page, Variable index, int imm) {
        long i = page.asInteger();
        if (!mem.containsKey(i)) {
            mem.put(i, new HashMap<>(512));
        }
        mem.get(i).put(index.asInteger() + imm, source.value);
    }
}
