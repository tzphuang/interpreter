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
        int result = 1;

        if("+".equals(operator)){
            result = firstArg + secondArg;
        }else if("-".equals(operator)){
            result = firstArg - secondArg;
        }else if("/".equals(operator)){
            result = firstArg / secondArg;
        }else if("*".equals(operator)){
            result = firstArg * secondArg;
        }else if("==".equals(operator)){
            if(firstArg != secondArg){
                result = 0;
            }
        }else if("!=".equals(operator)){
            if(firstArg == secondArg){
                result = 0;
            }
        }else if("<=".equals(operator)){
            if(firstArg > secondArg){
                result = 0;
            }

        }else if(">".equals(operator)){
            if(firstArg <= secondArg){
                result = 0;
            }

        }else if(">=".equals(operator)){
            if(firstArg < secondArg){
                result = 0;
            }

        }else if("<".equals(operator)){
            if(firstArg >= secondArg){
                result = 0;
            }

        }else if("|".equals(operator)){

        }else if("&".equals(operator)){

        }else{
            System.out.println("I have no idea how you got here but you dont have a valid operator");
        }

        currVirtualMachine.pushRunTimeStack(result);
    }

    @Override
    public String toString() {
        return ("BOP "+ operator);
    }
}
