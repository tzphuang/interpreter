package interpreter.virtualmachine;

import interpreter.bytecode.ByteCode;
import interpreter.bytecode.DumpCode;
import interpreter.bytecode.HaltCode;

import java.util.Stack;

public class VirtualMachine {

    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning;

    public VirtualMachine(Program program) {
        this.program = program;
    }

    public void executeProgram(){
        programCounter = 0;
        runTimeStack = new RunTimeStack();
        returnAddress = new Stack<Integer>();
        isRunning = true;
        boolean dumpPlz = false;

        while(isRunning){
            ByteCode currCode = program.getCode(programCounter);

            if(currCode instanceof HaltCode){
                isRunning = false;
            }

            if(currCode instanceof DumpCode){
                dumpPlz = ((DumpCode) currCode).dumpOn();
            }

            // > bytecode >  Interepreter > vm (blackbox) > program > arraylist(bytecode) > bytecode
            currCode.execute(this);

            if(dumpPlz){
                System.out.println(currCode);
            }
            programCounter++;
        }

    }

}
