package com.walker.raft.rpc;

/**
 * @author walkerwei
 * @version 2017/3/27
 RequestVote Req arguments:
 1. candidateId: candidate requesting vote
 2. term: candidate's term
 3. lastLogIndex: index of candidate's last log entry
 3. lastLogTerm: term of candidate's last log entry
 */
public class RequestVoteReq {
    /**
     * candidate requesting vote
     */
    private int candidateId;
    /**
     * candidate's term
     */
    private int term;
    /**
     * index of candidate's last log entry
     */
    private int lastLogIndex;
    /**
     * term of candidate's last log entry
     */
    private int lastLogTerm;

    public RequestVoteReq(int candidateId, int term, int lastLogIndex, int lastLogTerm) {
        this.candidateId = candidateId;
        this.term = term;
        this.lastLogIndex = lastLogIndex;
        this.lastLogTerm = lastLogTerm;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getLastLogIndex() {
        return lastLogIndex;
    }

    public void setLastLogIndex(int lastLogIndex) {
        this.lastLogIndex = lastLogIndex;
    }

    public int getLastLogTerm() {
        return lastLogTerm;
    }

    public void setLastLogTerm(int lastLogTerm) {
        this.lastLogTerm = lastLogTerm;
    }
}
