import kvtodev.mindustack.minterpreter.Minterpreter;

public class MinterpreterTest {
    public static void main(String[] args) {
        Minterpreter m = new Minterpreter()
                .parse("write 6 at zero 0")
                .parse("read at zero 0");
        m.run();
        m.dump();

    }
}
