package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public abstract class ByteCode {
    
    private static boolean DumpOn = false;

    public void setDumpOn(){
        this.DumpOn = true;
    }

    public void setDumpOff(){
        this.DumpOn = false;
    }

    public abstract void init(ArrayList<String> args);

    //defined inside the ByteCodeUsingLabels class
    public abstract String getLabelArg();

    //defined inside the ByteCodeUsingLabels class
    public abstract void setResolvedInt(Integer resolvedInt);

    //we pass in the whole virtual machine to give the current byteCode the access it needs to perform its actions
    public abstract void execute(VirtualMachine currVirtualMachine);


}
