package qqclientpackage.qqclient_service;
/*
 * @author Curry
 * @version 1.0
 */


import qqcommon.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread{
    private Socket socket = null;
    private static ObjectInputStream ois;

    //构造器，输入一个socket使之成为这个对象的一个属性，方便后续使用
    public ClientConnectServerThread(Socket socket){
        this.socket = socket;
        //获取数据接收通道
    }

    //取得这个线程所持有的socket
    public Socket getSocket(){
        return socket;
    }

    //使用线程的目的是因为线程在后台需要持续的接收服务端发送过来的数据
    @Override
    public void run() {
        //使用while true循环接收服务端发过来的数据
        while(true){
            try {
                //如果暂时没有接收到服务端发送过来的数据，线程将阻塞在这个read语句处
                ois = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message) ois.readObject();
                //msg将留作他用
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
