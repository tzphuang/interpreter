package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class DumpCode extends ByteCode{

    boolean dump;

    @Override
    public void init(ArrayList<String> args) {
        if(args.get(0).equals("ON")){
            dump = true;
        }else if(args.get(0).equals("OFF")) {
            dump = false;
        }

    }

    public boolean dumpOn(){
        return dump;
    }

    @Override
    public void execute(VirtualMachine currVirtualMachine) {
    }


    @Override
    public String toString() {
        return "DUMP";
    }
}
