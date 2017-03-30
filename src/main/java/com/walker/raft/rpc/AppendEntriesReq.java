package com.walker.raft.rpc;

import com.walker.raft.log.Entry;

/**
 * @author walkerwei
 * @version 2017/3/29
 */
public class AppendEntriesReq {
    /**
     * leader's term
     */
    private int term;
    /**
     * so follower can redirect clients
     */
    private int leaderId;
    /**
     * index of log entry immediately preceding new ones
     */
    private int prevLogIndex;
    /**
     * term of prevLogIndex entry
     */
    private int prevLogTerm;
    /**
     * log entries to store(empty for heartbeat)
     */
    private Entry[] entries;
    /**
     * last entry known to be committed
     */
    private int commitIndex;

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    public int getPrevLogIndex() {
        return prevLogIndex;
    }

    public void setPrevLogIndex(int prevLogIndex) {
        this.prevLogIndex = prevLogIndex;
    }

    public int getPrevLogTerm() {
        return prevLogTerm;
    }

    public void setPrevLogTerm(int prevLogTerm) {
        this.prevLogTerm = prevLogTerm;
    }

    public Entry[] getEntries() {
        return entries;
    }

    public void setEntries(Entry[] entries) {
        this.entries = entries;
    }

    public int getCommitIndex() {
        return commitIndex;
    }

    public void setCommitIndex(int commitIndex) {
        this.commitIndex = commitIndex;
    }
}
