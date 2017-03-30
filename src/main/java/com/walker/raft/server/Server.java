package com.walker.raft.server;

import com.walker.raft.log.Entry;
import com.walker.raft.roles.Role;
import com.walker.raft.rpc.AppendEntriesReq;
import com.walker.raft.rpc.AppendEntriesRsp;
import com.walker.raft.rpc.RequestVoteReq;
import com.walker.raft.rpc.RequestVoteRsp;
import com.walker.raft.state.PersistentState;

import java.util.Random;

/**
 * @author walkerwei
 * @version 2017/3/27
 */

public class Server {

    private PersistentState persistentState;
    private Role role = Role.FOLLOWER; //initial to follower;
    private Entry lastEntry;
    private double electionTimeout;
    private static final Random random = new Random();
    private static final long T = 100;  //100ms
    private void resetElectionTimeout(){
        electionTimeout = T + random.nextDouble() * T;
    }

    /**
     * Invoked by candidates to gather votes
     * @param candidate
     * @return
     */
    public RequestVoteRsp RequestVote(RequestVoteReq candidate) {
        boolean voted = false;
        //if term > currentTerm
        if (candidate.getTerm() > this.persistentState.getCurrentTerm()) {
            // currentTerm = term;
            this.persistentState.setCurrentTerm(candidate.getTerm());
            //step down if leader or candidate
            if (this.role != Role.FOLLOWER) {
                this.role = Role.FOLLOWER;
            }
        }
        //if term = currentTerm, voteFor is null or candidateId, and candidate's log is at least as complete as local log
        if (candidate.getTerm() == this.persistentState.getCurrentTerm() && (this.persistentState.getVotedFor() == 0 || this.persistentState.getVotedFor() == candidate.getCandidateId())
                && candidate.getLastLogIndex() >= this.lastEntry.getIndex()) {
            //grant vote
            voted = true;
            //reset election timeout
            resetElectionTimeout();
        }
        return new RequestVoteRsp(this.persistentState.getCurrentTerm(), voted);
    }

    /**
     * Invoked by leader to replicate log entries and discover inconsistencies; also used as heartbeat.
     * @param leader
     * @return
     */
    public AppendEntriesRsp AppendEntries(AppendEntriesReq leader) {
        // return if term < currentTerm
        if (leader.getTerm() < this.persistentState.getCurrentTerm()) {
            return new AppendEntriesRsp(this.persistentState.getCurrentTerm(), false);
        }
        //if term > currentTerm
        if (leader.getTerm() > this.persistentState.getCurrentTerm()) {
            //update currentTerm
            this.persistentState.setCurrentTerm(leader.getTerm());
            //step down if leader or candidate
            if (this.role != Role.FOLLOWER) {
                this.role = Role.FOLLOWER;
            }
            //reset election timeout
            resetElectionTimeout();
        }
        //return failure if log doesn't contain an entry at prevLogIndex whose term matches prevLogTerm
        if (this.persistentState.getEntries().size() <leader.getPrevLogIndex() || leader.getPrevLogTerm() == this.persistentState.getEntries().get(leader.getPrevLogIndex()).getTerm()) {
            return new AppendEntriesRsp(this.persistentState.getCurrentTerm(), false);
        }
        boolean conflictDeleted = false;
        for (int i = 0; i < leader.getEntries().length; i++) {
            Entry newEntry = leader.getEntries()[i];
            int indexOnServer = leader.getPrevLogIndex() + 1 + i;
            //if existing entries conflict with new entries
            if (!conflictDeleted && this.persistentState.getEntries().size() >= indexOnServer && !this.persistentState.getEntries().get(indexOnServer).equals(newEntry)) {
                //delete all existing entries starting with first conflicting entry
                for(int j=indexOnServer;j<this.persistentState.getEntries().size();j++) {
                    this.persistentState.getEntries().remove(j);
                }
                conflictDeleted = true;
            }
            //append any new entries not already in the log
            this.persistentState.getEntries().add(newEntry);
        }

        //advance state machine with newly committed entries
        // TODO: 2017/3/30

        return new AppendEntriesRsp(this.persistentState.getCurrentTerm(), true);

    }




    public PersistentState getPersistentState() {
        return persistentState;
    }

    public void setPersistentState(PersistentState persistentState) {
        this.persistentState = persistentState;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
