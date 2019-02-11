package edu.smith.cs.csc262.coopsh.apps;

import edu.smith.cs.csc262.coopsh.InputLine;
import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

/**
 * Head prints out first args[0] lines of the file
 * pipe from cat to put input into head
 */
public class Head extends Task {

    int numLines;


    /**
     * Constructor for Head task
     * @param env
     * @param args
     */
    public Head(ShellEnvironment env, String[] args){
        super(env, args);
        numLines = 10; //if user doesn't specify number of lines, we'll print 10
        if(args.length != 0){
            //we should check if args[0] is an int
            // for now, i'm implementing this assuming the user is perfect
            // then I'll add more defensive code if I have time
            numLines = Integer.valueOf(args[0]);
        }

    }
    protected void update(){
        InputLine line = this.input.poll();
        if (line == null) {
            // still waiting for more...
            return;
        }

        // we're done after we've printed the specified number of lines or we reach the end of the file
        if (line.isEndOfFile() || numLines <= 0) {
            this.closeOutput();
            this.exit(0);
            return;
        }else {
            //if we're not done, print the line and decrement our counter
            this.println(line.get());
            numLines--;
        }


    }
}
