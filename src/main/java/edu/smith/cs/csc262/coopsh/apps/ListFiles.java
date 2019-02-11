//Elizabeth Freeman
// referenced stack overflow
//https://stackoverflow.com/questions/15482423/how-to-list-the-files-in-current-directory


package edu.smith.cs.csc262.coopsh.apps;

import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

import java.io.File;
/**
 * ListFiles lists all files in the current directory
 */
public class ListFiles extends Task {
    //directory
    File dir;
    //list of files in directory
    File[] files;
    //keeps track of the next file to be printed
    int fileCounter = 0;

    /**
     * constructor for ListFiles
     * @param env
     * @param args
     */
    public ListFiles(ShellEnvironment env, String[] args){
        super(env, args);
        dir = env.currentDirectory;
        files = dir.listFiles();
    }

    // does a small bite of the work, prints out one file at a time,
    // exits when all files printed
    protected void update(){
        if(files==null || fileCounter >= files.length){
            //we're done
            this.closeOutput();
            this.exit(0);
            return;
        }else{
            this.println(files[fileCounter].getName());
            fileCounter++;
        }
    }
}
