package interpreter.bytecode;

import java.util.ArrayList;

public abstract class ByteCode {

    /*
    // Probably wont  need the following but we will see
    private ArrayList<String> byteCodeArgs;

    // this might need to be in a byteCode abstract class extending the byteCode class
    // that call, go to, false branch extends so not all ByteCode classes have this method as not all need it
    public void changeByteCodeArgs(String newArg){
        byteCodeArgs.set(0, newArg);
    }*/


    //this String will be initialized to its corresponding byteCode by the init function
    //such as if the ByteCode is "HALT", nameByteCode references "HALT"
    private String nameByteCode;

    // this might need to be in a byteCode abstract class extending the byteCode class
    // that "CALL", "GOTO", "FALSEBRANCH" extends so not all ByteCode classes have this method as not all need it
    private Integer resolvedInt;

    //this variable is specific to "CALL", "GOTO", "FALSEBRANCH" ByteCode
    //this should be initialized to the 1st argument in ArrayList<String> args
    //for example "LABEL continue<<9>>", labelArg will hold "continue<<9>>"
    private String labelArg;


    public abstract void init(ArrayList<String> args);

    //getter function for "nameByteCode", used to not break encapsulation
    public String getNameByteCode(){
        return this.nameByteCode;
    }

    //setter function for "resolvedInt", used to not break encapsulation
    public void setResolvedInt(Integer currIntRes){
        resolvedInt = currIntRes;
    }

    //getter function for "labelArg", used to not break encapsulation
    public String getLabelArg(){
        return this.labelArg;
    }
}
