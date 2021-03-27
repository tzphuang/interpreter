package interpreter.bytecode;

import java.util.ArrayList;

public abstract class ByteCode {

    public abstract void init(ArrayList<String> args);

    //defined inside the ByteCodeUsingLabels class
    public abstract String getLabelArg();
    //defined inside the ByteCodeUsingLabels class
    public abstract void setResolvedInt(Integer resolvedInt);


}
