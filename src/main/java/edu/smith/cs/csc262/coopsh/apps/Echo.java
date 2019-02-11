package edu.smith.cs.csc262.coopsh.apps;

import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

import java.io.IOException;

public class Echo extends Task {

    private int counter = 0;
    /**
     * Create a new echo object
     * @param env the shell environment
     * @param args the stuff we want to echo
     */
    public Echo(ShellEnvironment env, String[] args) {
        super(env, args);
    }

    /**
     * Let's do a bit-sized chunk
     */
    @Override
    protected void update() {
        //case 1 -we're done
        if (counter >= args.length) {
            //this.println();
            this.closeOutput();
            this.exit(0);
            return;
        }else {
            //case 2 we still have args to read and print
            this.println(args[counter] + " ");
            counter++;
        }
    }
}
