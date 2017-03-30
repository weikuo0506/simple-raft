package com.walker.raft.log;

import com.walker.raft.command.Command;

/**
 * @author walkerwei
 * @version 2017/3/27
 1. term: term when entry was received by leader
 2. index: position of entry in the log
 3. command: command for state machine
 */
public class Entry {
    /**
     * term when entry was received by leader
     */
    private int term;
    /**
     * position of entry in the log
     */
    private int index;
    /**
     * command for state machine
     */
    private Command command;

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
