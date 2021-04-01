package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class GotoCode extends CodeJump {
    @Override
    public void init(ArrayList<String> args) {
        this.labelArg = args.get(0);
    }

    @Override
    public String toString() {
        return "GOTO " + resolvedInt;
    }

    @Override
    public void execute(VirtualMachine currVirtualMachine) {
        currVirtualMachine.setProgramCounter(resolvedInt);
    }

}

