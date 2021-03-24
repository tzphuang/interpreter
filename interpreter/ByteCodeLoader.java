
package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.virtualmachine.Program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;

    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN loadCodes.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }

    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     * Tokenize string to break it into parts. Can also use the split function in the String class.
     * Grab THE correct class name for the given ByteCode from CodeTable
     * Create an instance of the ByteCode class name returned from code table.
     * Parse any additional arguments for the given ByteCode and send them to
     * the newly created ByteCode instance via the init function.
     */
    public Program loadCodes() {
        String currLine;
        String[] items;
        ArrayList<String> args = new ArrayList<>();
        String byteCodeName; //ByteCode name from .x.cod file
        String className; //class name after its mapped from name in source code to class name
        Class classBlueprint;
        Program program = new Program();
        ByteCode currByteCode;
        try {
            while (this.byteSource.ready()) {
                // tokenize read line
                currLine = this.byteSource.readLine();
                // grab first token of line
                items = currLine.split("\\s+"); // "\\s+" represents 1 or more spaces
                // grab class name from token
                byteCodeName = items[0];
                // load class bluePrint from classname
                className = CodeTable.getClassName(byteCodeName);
                // get declared constructor (should be no-arg constructor)
                classBlueprint = Class.forName("interpreter.bytecode." + className);
                // create a new instance of bytecode using constructor
                currByteCode = (ByteCode) classBlueprint.getDeclaredConstructor().newInstance();

                // grabs all arguments in items and stores in args arraylist
                for (String currArg : items) {
                    args.add(currArg);
                }
                //deletes the first item in the array list, which is "byteCodeName" as its not needed
                //as we are only passing in the args which is after the "byteCodeName"
                //for example "FALSEBRANCH continue<<6>>" will be tokenized [FALSEBRANCH][continue<<6>>]
                //but since we dont need the first element it gets deleted with the remove below
                args.remove(0);

                // pass args to bytecode init function
                currByteCode.init(args);

                // add bytecode to program
                program.addByteCode(currByteCode);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
            System.exit(255);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }


        program.resolveAddress();
        return program;
    }
}
