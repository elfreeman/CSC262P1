// Elizabeth Freeman
// referenced this page for help on Collections.sort syntax: http://www.java67.com/2016/02/how-to-sort-linkedlist-in-java-example.html


package edu.smith.cs.csc262.coopsh.apps;

import edu.smith.cs.csc262.coopsh.InputLine;
import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

import java.util.Collections;
import java.util.LinkedList;

public class Sort extends Task {
    LinkedList<String> lines = new LinkedList<String>(); // keep track of all the lines
    boolean reading = true; // keeps track of if we're reading input or writing output
    int lineToBeWritten = 0;

    public Sort(ShellEnvironment env, String[] args){
        super(env, args);
    }

    @Override
    protected void update() {
        if(reading){
            InputLine line = this.input.poll();
            if (line == null) {
                // still waiting for more...
                return;
            }
            if (line.isEndOfFile()) {
                Collections.sort(lines);
                //lines.sort();
                reading = false;
            }else{
                lines.add(line.get());
            }
        }else{
            //if we're here we've read and sorted, we just need to output, then exit
            if(lineToBeWritten < lines.size()){
                this.println(lines.get(lineToBeWritten));
                lineToBeWritten++;
            }else{
                //we're done
                this.closeOutput();
                this.exit(0);
                return;
            }
        }
    }
}
