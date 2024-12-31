package kvtodev.mindustack.llvmir2riscm.console.error;

public class UnknownArgument extends ConsoleError {

    public UnknownArgument(String argument) {
        super("unknown argument \"" + argument + "\"");
    }

}
