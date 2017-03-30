package com.walker.raft.state;

import com.walker.raft.log.Entry;

import java.util.List;

/**
 * @author walkerwei
 * @version 2017/3/27
 Each server persists the following to stable storeage synchronously before responding to RPCs:
 1. currentTerm: lastest term server has seen (initialized to 0 on first boot)
 2. votedFor: candidateId that received vote in current term (or null if none)
 3. log[]: log entries
 */
public class PersistentState {
    /**
     * lastest term server has seen (initialized to 0 on first boot)
     */
    private int currentTerm;
    /**
     * candidateId that received vote in current term (or null if none)
     */
    private int votedFor;
    /**
     * log entries
     */
    private List<Entry> entries;

    public int getCurrentTerm() {
        return currentTerm;
    }

    public void setCurrentTerm(int currentTerm) {
        this.currentTerm = currentTerm;
    }

    public int getVotedFor() {
        return votedFor;
    }

    public void setVotedFor(int votedFor) {
        this.votedFor = votedFor;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
