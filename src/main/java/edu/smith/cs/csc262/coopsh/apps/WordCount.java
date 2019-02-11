package edu.smith.cs.csc262.coopsh.apps;

import edu.smith.cs.csc262.coopsh.InputLine;
import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

public class WordCount extends Task {
	int wordCount = 0;

	public WordCount(ShellEnvironment env, String[] args) {
		super(env, args);
	}

	@Override
	protected void update() {
		InputLine line = this.input.poll();
		//debugging statement
		//System.out.println(line+String.valueOf(this.input)+args[0]);
		if (line == null) {
			// still waiting for more...
			//this may be wrong
			System.err.println("The file is empty or does not exist");
			this.closeOutput();
			this.exit(0);
			//
			return;
		}

		// only output and print when we've seen the whole file!
		if (line.isEndOfFile()) {
			this.println(wordCount);
			this.closeOutput();
			this.exit(0);
			return;
		}

		//Otherwise, increment this count!
		if (args.length == 0) {
			// we count the number of words
			wordCount += line.get().split("\\s+").length;
		}else if(args[0].equals("-l")){
			// we count the number of lines
			wordCount++;
		}else if(args[0].equals("-c")){
			//count the numver of characters
			wordCount += line.get().toCharArray().length;
		}
	}
}
