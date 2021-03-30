package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LabelCode extends ByteCode{
    String currLabel;

    @Override
    public void init(ArrayList<String> args) {

    }

    //Execute() for labelCode is never called due to program architecture
    //also its not used anyways
    @Override
    public void execute(VirtualMachine currVirtualMachine) { }

    @Override
    //I can use this for something else now since I can use instanceOf operation to check the classes
    public String toString() {
        return "";
    }

    public String getLabel(){
        return currLabel;
    }


}
