package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends ByteCodeUsingLabels{

    @Override
    public void init(ArrayList<String> args) {
        this.labelArg = args.get(1);
    }

    @Override
    public String toString() {
        return "FALSEBRANCH";
    }

    @Override
    public void execute(VirtualMachine currVirtualMachine) {

    }
}
