package kvtodev.mindustack.llvmir2riscm.share;

import java.util.HashMap;
import java.util.Map;

public class CodePreProcessor {

    private String code;

  public  Map<String, Integer> labels = new HashMap<>(64);

    public CodePreProcessor(String raw) {
        this.code = raw;
    }

    public CodePreProcessor applyMacro(String mark, String def) {
        this.code = this.code.replaceAll(mark, def);
        return this;
    }

    public CodePreProcessor process() {
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 0;
        String[] lines = this.code.split("\n");
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("#") || line.startsWith("//")) {
                continue;
            } else if (line.endsWith(":")) {
                stringBuilder.append(line).append('\n');
                labels.put(line.replaceAll("[:]", ""), counter);
            } else if (line.equals("")) {
                continue;
            } else {
                counter++;
                stringBuilder.append(line).append('\n');
            }
        }
        this.code=stringBuilder.toString();
        return this;
    }
    public long getFlagCounter(String flag){
        return labels.get(flag);
    }
    public String get(){
        return  this.code;
    }
}
