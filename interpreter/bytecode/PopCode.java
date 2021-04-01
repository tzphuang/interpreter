package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class PopCode extends ByteCode{

    int numPops;

    @Override
    public void init(ArrayList<String> args) {
        this.numPops = Integer.parseInt(args.get(0));
    }

    @Override
    public void execute(VirtualMachine currVirtualMachine) {
        int sizeFrame = currVirtualMachine.peekRTFramePtr() - currVirtualMachine.sizeRTStack();

        if(sizeFrame < numPops){
            numPops = sizeFrame;
        }

        for(int count = 0; count < numPops; count++){
            currVirtualMachine.popRunTimeStack();
        }

    }

    @Override
    public String toString() {
        return "POP" + numPops;
    }
}
