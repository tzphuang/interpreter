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
    private boolean isDump = false;

    public VirtualMachine(Program program) {
        this.program = program;
    }

    public void executeProgram(){
        programCounter = 0;
        runTimeStack = new RunTimeStack();
        returnAddress = new Stack<Integer>();
        isRunning = true;

        //resolves all jumpCode before we go and execute
        program.resolveAddress();


        while(isRunning){
            ByteCode currCode = program.getCode(programCounter);

            if(currCode instanceof HaltCode){
                isRunning = false;
                programCounter++;
                break;
            }

            if(currCode instanceof DumpCode){
                isDump = ((DumpCode) currCode).dumpOn();
            }

            // > bytecode >  Interepreter > vm (blackbox) > program > arraylist(bytecode) > bytecode
            currCode.execute(this);

            if(isDump){
                System.out.println(currCode); //prints each current byteCode's to String
                runTimeStack.dump();
            }
            programCounter++;
        }

    }

    public void pushRunTimeStack(int value) {
        runTimeStack.push(value);
    }

    public int popRunTimeStack() {
        return runTimeStack.pop();
    }

    public int peekRunTimeStack(){
        return runTimeStack.peek();
    }

    public int sizeRTStack() {
        return runTimeStack.sizeStack();
    }

    public int peekRTFramePtr(){
        return runTimeStack.peekTopFramePtr();
    }

    public void setProgramCounter(int newCounter){
        this.programCounter = newCounter;
    }

    public void storeRunTimeStack(int currOffSet){
        runTimeStack.store(currOffSet);
    }

    public void loadRunTimeStack(int offSet) {
        runTimeStack.load(offSet);
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void newFramePtr(int offset) {
        runTimeStack.newFrameAt(offset);
    }


    public void pushReturnAddress(int programCounter) {
        returnAddress.push(programCounter);
    }
}
