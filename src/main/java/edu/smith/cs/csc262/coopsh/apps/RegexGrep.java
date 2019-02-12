//Elizabeth Freeman
// I referenced this page for info on regex, Pattern and Matcher : https://alvinalexander.com/blog/post/java/how-find-string-simple-regex-pattern-matcher


package edu.smith.cs.csc262.coopsh.apps;

import edu.smith.cs.csc262.coopsh.InputLine;
import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexGrep extends Task {
    boolean reading = true;
    LinkedList<String> lines = new LinkedList<String>();
    int line = 0; //this will point to the line we're currently looking at
    Pattern regex;

    public RegexGrep(ShellEnvironment env, String[] args){
        super(env, args);
        regex = Pattern.compile(args[0]);
    }
    //do a little step, here we will go over 1 line at a time, first reading, then checking
    // for the regex in question
    @Override
    protected void update() {
        if(reading){
            InputLine line = this.input.poll();
            if (line == null) {
                // still waiting for more...
                return;
            }
            if (line.isEndOfFile()) {
                //we're done reading
                reading = false;
            }else{
                lines.add(line.get());
            }
        }else {
            //if we're here we've read all the lines, we need to check for our string and output
            if (line < lines.size()) {
                Matcher matcher = regex.matcher(lines.get(line));
                if (matcher.find()) {
                    this.println(lines.get(line));
                }
                line++;
            } else {
                //we're done
                this.closeOutput();
                this.exit(0);
                return;
            }
        }
    }
}
