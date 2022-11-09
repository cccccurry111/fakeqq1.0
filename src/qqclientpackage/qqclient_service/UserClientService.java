package qqclientpackage.qqclient_service;
/*
 * @author Curry
 * @version 1.0
 *
 * 专用于验证登录的服务，获取输入的用户名和密码发送到服务端验证，获取回复以判断是否登录成功
 */

import qqcommon.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class UserClientService {
    //该类持有Socket和User对象,即将这两个对象作为该类的属性，因为以后别处会用到，方便管理
    private static Socket socket = null;
    private static User user = new User();

    public static boolean checkUserLogin(String userId, String password){
        boolean b = false;
        //使用输入的用户号和密码赋给一个新的User对象
        user.setUserid(userId);
        user.setPassword(password);
        //和服务端建立链接，获取socket
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"),9999);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //发送User对象给服务器
        try {
            //获取Object输出流
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //将User对象通过socket传输给服务端
            oos.writeObject(user);
            //获取服务端回复
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //服务端回复的是Message类对象
            Message msg = (Message) ois.readObject();
            System.out.println("msg信息已接收，msg登录状态为"+msg.getMessageType());
            try {
                if (msg.getMessageType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)) {
                    System.out.println("登录已成功");
                    //登录成功，创建一个线程和服务端保持链接
                    ClientConnectServerThread ccsThread = new ClientConnectServerThread(socket);
                    System.out.println("线程已建立");
                    //将创建出来的线程添加到线程管理集合中
                    ClientConnectServerManager.addClientConnectServerThread(user.getUserid(),ccsThread);
                    System.out.println("线程已加入集合");
                    //启动线程
                    ccsThread.start();
                    System.out.println("线程已启动");
                    //将提示登录成功与否的boolean置为true
                    b = true;
                    System.out.println("登录状态已置为true");
                }else{
                    //因为登录失败，将数据传输通道关闭
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("用户登录验证完毕");
        return b;
    }
}
