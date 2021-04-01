package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LitCode extends ByteCode{
    int value;
    String id;

    @Override
    public void init(ArrayList<String> args) {
        this.value = Integer.parseInt(args.get(0));
        if(args.size() > 1){
            this.id = args.get(1);
        }

    }

    @Override
    public void execute(VirtualMachine currVirtualMachine) {
        //execute should do all the heavy lifting
        //using the adt wall inside Virtual machine
        currVirtualMachine.pushRunTimeStack(value);
    }

    @Override
    public String toString() {
        String returnString = "LIT " + value;
        if(id != null){
            returnString += (" int " + id);
        }
        return returnString;
    }
}
