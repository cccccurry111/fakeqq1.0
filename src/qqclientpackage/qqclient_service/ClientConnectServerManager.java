package qqclientpackage.qqclient_service;
/*
 * @author Curry
 * @version 1.0
 */

import java.util.HashMap;

public class ClientConnectServerManager {
    private final static HashMap<String, ClientConnectServerThread> Threads = new HashMap<>();

    //添加线程到集合中的方法
    public static void addClientConnectServerThread(String userId, ClientConnectServerThread ccsThread) {
        Threads.put(userId, ccsThread);
    }

    //从集合中取出线程的方法
    public static ClientConnectServerThread getCcsThread(String userID) {
        return Threads.get(userID);
    }
}
