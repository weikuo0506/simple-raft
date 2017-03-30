package com.walker.raft.roles;

/**
 * @author walkerwei
 * @version 2017/3/27
 1. Increment currentTerm, vote for self
 2. Reset eleciton timeout
 3. Send RequestVoteReq RPCs to all other servers, wait for either:
    a.Votes received from majority of servers: become leader
    b. AppendEntries RPC received from new leader: step down
    c. Election timeout elaspse without election resolution: Increment term, start new election
    d. Discover higher term: step down
 */
public class Candidate {


}
