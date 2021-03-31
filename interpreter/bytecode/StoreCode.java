package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode{

    @Override
    public void init(ArrayList<String> args) {

    }

    @Override
    public void execute(VirtualMachine currVirtualMachine) {

    }

    @Override
    public String toString() {
        return "STORE";
    }
}
