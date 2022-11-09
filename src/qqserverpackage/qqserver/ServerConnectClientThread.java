package qqserverpackage.qqserver;
/*
 * @author Curry
 * @version 1.0
 *
 * 用于保持socket链接的线程
 */

import qqcommon.*;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerConnectClientThread extends Thread{
    private Socket socket;
    private String userId;
    private ObjectInputStream ois;

    public ServerConnectClientThread(String userId, Socket socket){
        this.userId = userId;
        this.socket = socket;
    }

    @Override
    public void run() {
        while(true){
            try {
                ois = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message)ois.readObject();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Socket getSocket(){
        return socket;
    }

    public String getUserId(){
        return userId;
    }
}
