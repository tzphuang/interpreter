package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode{
    int offSet;
    String identifier;
    int topOfRTStack;

    @Override
    public void init(ArrayList<String> args) {
        this.offSet = Integer.parseInt(args.get(0));
        if(args.size() > 1){
            this.identifier = args.get(1);
        }
    }

    @Override
    public void execute(VirtualMachine currVirtualMachine) {
        //must make sure that store does not operate across frame boundaries
        int sizeFrame = currVirtualMachine.peekRTFramePtr() - currVirtualMachine.sizeRTStack();
        if(sizeFrame < offSet){
            offSet = sizeFrame;
        }

        currVirtualMachine.storeRunTimeStack(offSet);
        topOfRTStack = currVirtualMachine.peekRunTimeStack();
    }

    @Override
    public String toString() {
        return "STORE " + offSet + " " + identifier + "        " + identifier + "=" + topOfRTStack;
    }
}
