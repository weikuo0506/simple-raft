package com.walker.raft.rpc;

/**
 * @author walkerwei
 * @version 2017/3/27
 requestVote response :
 1. term : currentTerm, for candidate to update itself
 2. voteGranted: true means candidate received vote
 */
public class RequestVoteRsp {
    /**
     * currentTerm, for candidate to update itself
     */
    private int term;
    /**
     * true means candidate received vote
     */
    private boolean voteGranted;

    public RequestVoteRsp(int term, boolean voteGranted) {
        this.term = term;
        this.voteGranted = voteGranted;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public boolean isVoteGranted() {
        return voteGranted;
    }

    public void setVoteGranted(boolean voteGranted) {
        this.voteGranted = voteGranted;
    }
}
