package com.walker.raft.rpc;

import com.walker.raft.log.Entry;

/**
 * @author walkerwei
 * @version 2017/3/29
 */
public class AppendEntriesRsp {
    /**
     * currentTerm, for leader to update itself
     */
    private int term;
    /**
     * true if follower contained entry matching prevLogindex and prevLogTerm
     */
    private boolean success;

    public AppendEntriesRsp(int term, boolean success) {
        this.term = term;
        this.success = success;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
