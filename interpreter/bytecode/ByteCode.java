package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public abstract class ByteCode {

    public abstract void init(ArrayList<String> args);

    //we pass in the whole virtual machine to give the current byteCode the access it needs to perform its actions
    public abstract void execute(VirtualMachine currVirtualMachine);

    //toString method will be used for dumping in Virtual machine
}
