package interpreter.bytecode;

import java.util.ArrayList;

public abstract class CodeJump extends ByteCode{
    // this might need to be in a byteCode abstract class extending the byteCode class
    // that "CALL", "GOTO", "FALSEBRANCH" extends so not all ByteCode classes have this method as not all need it
    protected Integer resolvedInt;

    //this variable is specific to "CALL", "GOTO", "FALSEBRANCH" ByteCode
    //this should be initialized to the 1st argument in ArrayList<String> args
    //for example "LABEL continue<<9>>", labelArg will hold "continue<<9>>"
    protected String labelArg;


    //getter function for "labelArg", used to not break encapsulation
    public String getLabelArg(){
        return this.labelArg;
    }

    //setter function for "resolvedInt", used to not break encapsulation
    public void setResolvedInt(Integer currIntRes){
        resolvedInt = currIntRes;
    }
}
