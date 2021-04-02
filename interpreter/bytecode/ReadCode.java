package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadCode extends ByteCode{

    int readInt;

    @Override
    public void init(ArrayList<String> args) {

    }

    @Override
    public void execute(VirtualMachine currVirtualMachine) {
        boolean notAnInt = true;

        do{
            System.out.println("Please enter an integer : ");
            Scanner scan = new Scanner(System.in);
            String scannedLine = scan.nextLine();

            try{
                readInt = Integer.parseInt(scannedLine);
                notAnInt = false;

            }catch(NumberFormatException e){
                System.out.println("INVALID INPUT! PLEASE TRY AGAIN");
            }

        }while(notAnInt);

    }

    @Override
    public String toString() {
        return "READ";
    }
}
