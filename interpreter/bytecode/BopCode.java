package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class BopCode extends ByteCode{
    String operator;

    @Override
    public void init(ArrayList<String> args) {
        operator= args.get(0);
    }

    @Override
    public void execute(VirtualMachine currVirtualMachine) {
        int secondArg = currVirtualMachine.popRunTimeStack();
        int firstArg = currVirtualMachine.popRunTimeStack();
        int result = 0;

        if("+".equals(operator)){
            result = firstArg + secondArg;
        }else if("-".equals(operator)){
            result = firstArg - secondArg;
        }else if("/".equals(operator)){
            result = firstArg / secondArg;
        }else if("*".equals(operator)){
            result = firstArg * secondArg;
        }else if("==".equals(operator)){ //wait what? what do i do here?

        }else if("!=".equals(operator)){

        }else if("<=".equals(operator)){

        }else if(">".equals(operator)){

        }else if(">=".equals(operator)){

        }else if("<".equals(operator)){

        }else if("|".equals(operator)){

        }else if("&".equals(operator)){

        }else{

        }

        currVirtualMachine.pushRunTimeStack(result);
    }

    @Override
    public String toString() {
        return ("BOP "+ operator);
    }
}
