package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode{
    String identifier;
    int returnValue;
    String baseId;

    @Override
    public void init(ArrayList<String> args) {

        if(args.size() > 0){
            this.identifier = args.get(0);
            String[] splitString = identifier.split("<<");
            baseId = splitString[0];
        }


    }

    @Override
    public void execute(VirtualMachine currVirtualMachine) {
        //pop the top of the stack that holds the return value
        returnValue = currVirtualMachine.popReturnAddress();
        //pops the whole frame for the run time stack including the framePtr
        currVirtualMachine.popFrameRTStack();
        currVirtualMachine.setProgramCounter(returnValue);
    }

    @Override
    public String toString() {
        String returnString = "RETURN";

        if(identifier != null){
            returnString +=  " <" + identifier + ">";
        }

        returnString += "   EXIT ";

        if(baseId != null){
            returnString += baseId;
        }
        
        returnString += ": " + returnValue;

        return returnString;
    }
}
