package interpreter.bytecode;

import java.util.ArrayList;

public class DumpCode extends ByteCode{

    @Override
    public void init(ArrayList<String> args) {

    }

    @Override
    public String toString() {
        return "DUMP";
    }
}
