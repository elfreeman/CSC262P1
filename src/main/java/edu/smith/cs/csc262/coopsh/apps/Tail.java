package edu.smith.cs.csc262.coopsh.apps;

import edu.smith.cs.csc262.coopsh.InputLine;
import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

import java.util.LinkedList;

public class Tail extends Task {
    int numLines; //lines to be printed
    LinkedList<InputLine>  lines = new LinkedList<InputLine>(); //since we don't know how many lines there will be
                                    // store all the lines, only print the last numLines

    public Tail(ShellEnvironment env, String[] args){
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
        if (line.isEndOfFile()) {
            //we've stored all lines, now print last numLines
            int start = Math.max(lines.size()-numLines, 0);
            //is this chunk too big?
            //I could introduce a boolean for reading input or writing output and write one line at a time instead
            for(int i = start; i< lines.size(); i++){
                this.println(lines.get(i).get());
            }
            this.closeOutput();
            this.exit(0);
            return;
        }else {
            //if we're not done add the line to our list
            lines.add(line);
        }

    }
}
