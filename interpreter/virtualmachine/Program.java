package interpreter.virtualmachine;

import interpreter.bytecode.ByteCode;
import interpreter.bytecode.CodeJump;
import interpreter.bytecode.LabelCode;

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
        Integer count; //used to keep index of label in arrayList. example "9. LABEL continue<<6>>" count would be "9"
        Integer resolvedInt;
        String currValue; //example "9. LABEL continue<<6>>" currValue would be "continue<<6>>"

        // 1st pass through ArrayList keeping track of all label codes and their labels
        count = 0;
        for (ByteCode currByteCode : program) {

            if (currByteCode instanceof LabelCode) {
                labelTable.put( ((LabelCode) currByteCode).getLabel(), count);
            }
            count++;
        }

        // 2nd pass through ArrayList look for "CALL", "GOTO", "FALSEBRANCH" codes and do the following:
        // look at stored label codes and find the one that has the matching label
        for (ByteCode currByteCode : program) {
            //checks the type of currByteCode to see if its a CodeJump class or a subclass of CodeJump
            if (currByteCode instanceof CodeJump) {
                resolvedInt = labelTable.get(((CodeJump) currByteCode).getLabelArg());
                ((CodeJump) currByteCode).setResolvedInt(resolvedInt);
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
