package interpreter.bytecode;

import java.util.ArrayList;

public class LoadCode extends ByteCode{

    @Override
    public void init(ArrayList<String> args) {

    }

    @Override
    public String toString() {
        return "LOAD";
    }
}
