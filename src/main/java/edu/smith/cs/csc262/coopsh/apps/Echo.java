// I only changed one line from the code given to me (line 34)

package edu.smith.cs.csc262.coopsh.apps;

import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

/**
 * This is a full implementation of Echo.
 * @author jfoley
 *
 */
public class Echo extends Task {

    /**
     * Store the lines present in the user input.
     */
    final String[] lines;
    /**
     * Store the current index into the list for printing.
     */
    int i = 0;

    /**
     * Construct an Echo Task.
     * @param env - the shell environment.
     * @param args - the arguments to echo.
     */
    public Echo(ShellEnvironment env, String[] args) {
        super(env, args);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<args.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(args[i]);
        }
        this.lines = sb.toString().split("\n");
    }

    /**
     * Update is like the body of a loop.
     */
    @Override
    protected void update() {
        if (i < this.lines.length) {
            this.println(this.lines[i]);
            i++;
        } else {
            this.closeOutput();
            this.exit(0);
        }
    }
}