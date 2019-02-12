package edu.smith.cs.csc262.coopsh.apps;

import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

public class SetVar extends Task {
    public SetVar(ShellEnvironment env, String[] args){
        super(env, args);
    }

    // update should only execute once, since it is such a small task
    @Override
    protected void update() {
        if(args.length != 2){
            this.println("Error: SetVar takes two arguments"); //i should do an error output but idk how to handle that
        }else {
            env.setVariable(args[0], args[1]);
        }

        this.closeOutput();
        this.exit(0);
        return;

    }
}
