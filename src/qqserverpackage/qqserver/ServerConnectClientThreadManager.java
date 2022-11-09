package qqserverpackage.qqserver;
/*
 * @author Curry
 * @version 1.0
 *
 * 用于集中管理线程
 */

import java.util.HashMap;

public class ServerConnectClientThreadManager {
    private final static HashMap<String, ServerConnectClientThread> Threads = new HashMap<>();

    public static void addSccThread(String userId, ServerConnectClientThread sccThread) {
        Threads.put(userId, sccThread);
    }

    public static ServerConnectClientThread getSccThread(String userID) {
        return Threads.get(userID);
    }
}
