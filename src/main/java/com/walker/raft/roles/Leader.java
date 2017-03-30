package com.walker.raft.roles;

/**
 * @author walkerwei
 * @version 2017/3/27
 1. Initialize nextIndex for each to last log index + 1;
 2. Send inital empty AppendEntries RPCs(heartbeat) to each follower; repeat during idle periods to prevent election timeout;
 3. Accept commands from clients, append new entries to local log;
 4. Whenever last log index >= nextIndex for a follower, send AppendEntries RPC with log entries starting at nextIndex, update nextIndex if successful;
 5. If AppendEntries fails because of log inconsistency, decrement nextIndex and retry;
 6. Mark log entries committed if stored on a majority of servers and at least one entry from current term is stored on a majority of servers;
 7. Step down if currentTerm changes;
 */
public class Leader {
}
