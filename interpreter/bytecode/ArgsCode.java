package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode{
    int numArgs;

    @Override
    public void init(ArrayList<String> args) {
        this.numArgs = Integer.parseInt(args.get(0));
    }

    @Override
    public void execute(VirtualMachine currVirtualMachine) {
        currVirtualMachine.newFramePtr(numArgs);
    }

    @Override
    public String toString() {
        return "ARGS " + numArgs;
    }
}