package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class WriteCode extends ByteCode{

    @Override
    public void init(ArrayList<String> args) {

    }

    @Override
    public void execute(VirtualMachine currVirtualMachine) {
        System.out.println(currVirtualMachine.peekRunTimeStack());
    }

    @Override
    public String toString() {
        return "WRITE";
    }
}
