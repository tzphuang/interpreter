package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class CallCode extends CodeJump {

    String functionName;
    int numArg;
    String funcArgsForDumping = "";

    @Override
    public void init(ArrayList<String> args) {
        this.labelArg = args.get(0);

        String[] stringParts = labelArg.split("<<");

        functionName = stringParts[0];
        if(1 < stringParts.length) {
            stringParts[1] = stringParts[1].replace(">>", "");
            numArg = Integer.parseInt(stringParts[1]);
        }
    }

    @Override
    public String toString() {
        String returnString = "";

        returnString += "CALL " + labelArg + "     " + functionName + "(" + funcArgsForDumping + ")";

        returnString = returnString.replace(",)", ")");

        return returnString;
    }

    @Override
    public void execute(VirtualMachine currVirtualMachine) {

        //set up so toString can dump properly***
        ArrayList<Integer> callArgs = new ArrayList<>();
        int currTopStack;

        // if top of the stack is: 5 > 4 > 3
        // store ints in callArgs: 3 > 4 > 5
        for(int count = 0; count < numArg; count++){
            currTopStack = currVirtualMachine.popRunTimeStack();
            callArgs.add(0, currTopStack);
        }

        for(Integer currCallArg: callArgs){
            funcArgsForDumping += (currCallArg + ",");
            currVirtualMachine.pushRunTimeStack((int) currCallArg);
        }
        //***done with set up


        //storing return address on returnAddressStack
        currVirtualMachine.pushReturnAddress(currVirtualMachine.getProgramCounter());

        //setting program counter to jump address
        currVirtualMachine.setProgramCounter(resolvedInt);


    }
}
