package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.Stack;

class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer>     framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.push(0);
    }


    public void dump ()
    {/*
     * Used for dumping the current state of the runTimeStack.
     * * It will print portions of the stack based on respective * frame markers.
     * Example [1,2,3] [4,5,6] [7,8]
     * Frame pointers would be 0,3,6
     */
        ArrayList<String> dumpString = new ArrayList<>();
        Stack<Integer> currStack = new Stack<>();
        Integer curInt = -1;
        String printString = "";

        //this is so the currStack doesnt empty out early so I can
        //loop through the currStack entirely
        currStack.push(curInt);

        //reverse order of stack and store in currStack
        while(!(framePointer.empty())){
            curInt= framePointer.pop();
            currStack.push(curInt);
        }

        Integer currFramePointer;
        Integer count = 0;
        Integer countPlusOne = 1;

        //keep looping through currStack until empty
        while (!currStack.empty()) {
            currFramePointer = currStack.pop();

            //if the currentFramePointer is 0,
            //ignore it move onto the next frame pointer
            if(currFramePointer.equals(0)){
                framePointer.push(currFramePointer);

                //if the current frame stack is now empty since 0
                //was the only frame pointer, set currFramePointer to -1
                if(!(currStack.empty())){
                    currFramePointer = currStack.pop();
                }
                else{
                    currFramePointer = -1;
                }

            }

            //System.out.print("[");
            dumpString.add("[");

            for(; count < runTimeStack.size(); count++, countPlusOne++){
                if(countPlusOne.equals(currFramePointer)){
                    //System.out.print(runTimeStack.get(count));
                    dumpString.add(runTimeStack.get(count) + "");
                    count++;
                    countPlusOne++;
                    break;
                }
                else{
                    //System.out.print(runTimeStack.get(count) + ",");
                    dumpString.add(runTimeStack.get(count) + ",");
                }
                /*if(!countPlusOne.equals(currFramePointer)){
                    System.out.print(",");
                }*/

            }
            //System.out.print("]");
            dumpString.add("]");

            framePointer.push(currFramePointer); //push back currFramePointer onto
        }

        for(String currString: dumpString){
            printString += currString;
        }
        printString = printString.replace(",]", "]");
        System.out.println(printString);

        //pops the Integer -1 off the frame pointer stack that was used
        //for printing purposes
        framePointer.pop();
        //System.out.println();

    }


    public int peek ()
    {/*
     * returns the top of the runtime stack, but does not remove
     * @return copy of the top of the stack.
     */
        int returnInt = runTimeStack.get(runTimeStack.size()-1);
        return returnInt;
    }


    public int push(int i)
    {/*
     * push the value i to the top of the stack.
     * @param i value to be pushed.
     * @return value pushed
     */
        Integer pushInt = i;
        runTimeStack.add(pushInt);
        return this.peek();
    }


    public int pop()
    {/*
     * removes to the top of the runtime stack.
     * @return the value popped.
     */
        int returnInt = runTimeStack.get(runTimeStack.size()-1);
        runTimeStack.remove(runTimeStack.size()-1);
        return returnInt;
    }

    public int store(int offset)
    {/*
     * Takes the top item of the run Time stack, and stores
     * it into a offset starting from the current frame.
     * @param offset number of slots above current frame marker
     * @return the item just stored
     */
        Integer currFrame = framePointer.peek();
        Integer lastItem = runTimeStack.get(runTimeStack.size()-1);
        currFrame = currFrame + offset;
        runTimeStack.add(currFrame, lastItem);
        return lastItem;
    }

    public int load(int offset)
    {/*
    * Takes a value from the run time stack that is at offset
    * from the current frame marker and pushes it onto the top of the stack.
    * @param offset number of slots above current frame marker
    * @return item just loaded into the offset
    */
        Integer currFrame = framePointer.peek();
        currFrame = currFrame + offset;
        Integer loadedItem = runTimeStack.get( (int) currFrame );
        runTimeStack.add(loadedItem);
        return (int) loadedItem;
    }

    public void newFrameAt(int offset) //this is different because offset is from the top of RunTimeStack
    {/*
     * create a new frame pointer at the index offset slots down
     * from the top of the runtime stack.
     * @param offset slots down from the top of the runtime stack
     * */
        Integer newFramePtr = runTimeStack.size() - offset;
        framePointer.push(newFramePtr);
    }

    public void popFrame()
    {/*
    * pop the current frame off the runtime stack. Also removes
    * the frame pointer value from the FramePointer Stack.
    */
        //inside runTimeStack keep deleting elements at index[framePointer stack]
        //until the size of the runTimeStack is equivalent to the framePointer at the top of the FramePointer stack
        Integer peekedInt = framePointer.peek();

        while( !( ( (Integer) runTimeStack.size() ).equals( peekedInt ) ) ){
            runTimeStack.remove( (int) peekedInt );
        }
        framePointer.pop();
    }

    /*
    public static void main(String[] args){
        RunTimeStack currRTStack = new RunTimeStack();

        currRTStack.push(1);
        currRTStack.push(2);
        //currRTStack.push(3);
        currRTStack.push(4);
        currRTStack.push(5);
        currRTStack.push(6);
        currRTStack.push(7);
        currRTStack.push(8);

        //checking if the peek() inside push() works and it does
        System.out.println(currRTStack.push(3));

        currRTStack.runTimeStack.forEach( val -> System.out.println(val)); //enhanced for each loop

        for(int val: currRTStack.runTimeStack){
            System.out.println(val);
        }

        //Example [1,2,3] [4,5,6] [7,8]
        //Frame pointers would be 0,3,6


        currRTStack.dump();
        System.out.println("peeking " + currRTStack.peek());
        System.out.println("storing " + currRTStack.store(2));
        currRTStack.dump();

        currRTStack.pop();
        currRTStack.dump();

        currRTStack.newFrameAt(5);
        currRTStack.dump();

        currRTStack.newFrameAt(2);
        currRTStack.dump();

        currRTStack.load(0);
        currRTStack.load(1);
        currRTStack.newFrameAt(2);
        currRTStack.dump();

        currRTStack.popFrame();
        currRTStack.dump();

        currRTStack.popFrame();
        currRTStack.dump();

        currRTStack.popFrame();
        currRTStack.dump();

        currRTStack.popFrame();
        currRTStack.dump();

    }*/


}
