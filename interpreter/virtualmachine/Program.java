package interpreter.virtualmachine;

import interpreter.bytecode.ByteCode;
import interpreter.bytecode.ByteCodeUsingLabels;

import java.util.ArrayList;
import java.util.HashMap;

public class Program {

    private ArrayList<ByteCode> program;

    public Program() {
        program = new ArrayList<>();
    }

    protected ByteCode getCode(int programCounter) {
        return this.program.get(programCounter);
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter
     * HINT: make note what type of data-structure ByteCodes are stored in.
     */
    public void resolveAddress() {

        HashMap<String, Integer> labelTable = new HashMap<>();
        Integer count;
        Integer resolvedInt;
        String currValue; //example "9. LABEL continue<<6>>" currValue would be "continue<<6>>"
        Integer currIndex; //example "9. LABEL continue<<6>>" currIndex would be "9"
        String resolvedString;


        //the 2 loops below are used to resolve all ByteCodeUsingLabels
        // 1st pass through ArrayList keeping track of all label codes and their labels
        count = 0;
        for (ByteCode currByteCode : program) {
            currValue = currByteCode.toString();
            if (currValue.equals("LABEL")) {
                currIndex = count;
                labelTable.put(currByteCode.getLabelArg(), count);
            }
            count++;
        }

        // 2nd pass through ArrayList look for "CALL", "GOTO", "FALSEBRANCH" codes and do the following:
        // look at stored label codes and find the one that has the matching label
        for (ByteCode currByteCode : program) {
            currValue = currByteCode.toString();
            if (currValue.equals("CALL") || currValue.equals("GOTO") || currValue.equals("FALSEBRANCH")) {
                resolvedInt = labelTable.get(currByteCode.getLabelArg());
                currByteCode.setResolvedInt(resolvedInt);
            }
        }


    }

    // adds "addThisBC" as a byteCode to the private ArrayList inside Program
    // the reason why this function is here is because, to not break encapsulation as the "ArrayList<ByteCode> program"
    // is private, we will use a setter function
    public void addByteCode(ByteCode addThisBC) {
        program.add(addThisBC);
    }


}
