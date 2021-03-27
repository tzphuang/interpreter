package interpreter.bytecode;

import java.util.ArrayList;

public class CallCode extends ByteCodeUsingLabels{
    @Override
    public void init(ArrayList<String> args) {
        this.labelArg = args.get(1);
    }

    @Override
    public String toString() {
        return "CALL";
    }
}
