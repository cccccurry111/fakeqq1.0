package qqserverpackage.qqserver;
/*
 * @author Curry
 * @version 1.0
 *
 * 服务端程序，用作循环监听端口
 */

import qqcommon.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class QQServer {
    private static ServerSocket serverSocket;
    private static Socket socket;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(9999);
            while (true) {
                try {
                    System.out.println("9999端口开始监听");
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    //接收以获取socket对象
//                Socket socket = serverSocket.accept();
                    System.out.println("获取socket成功");
                    //获取数据传输通道
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    //接收数据
                    User user = (User) ois.readObject();
                    System.out.println("用户名密码读取成功");
                    //创建Message对象以便后面返回对象
                    Message msg = new Message();
                    if ("100".equals(user.getUserid()) && "123456".equals(user.getPassword())) {
                        //将msg中的属性MessageType置为SUCCEED,为登录成功
                        msg.setMessageType(MessageType.MESSAGE_LOGIN_SUCCEED);
                        //创建一个线程持有该socket
                        ServerConnectClientThread sccThread =
                                new ServerConnectClientThread(user.getUserid(), socket);
                        System.out.println("获取到"+user.getUserid()+"的socket，链接已建立");
                        //将该线程加入集合中管理
                        ServerConnectClientThreadManager.addSccThread(user.getUserid(), sccThread);
                        //启动线程
                        sccThread.start();
                        //发送该msg对象给客户端
                        oos.writeObject(msg);
                        System.out.println(user.getUserid()+"登录状态"+msg.getMessageType());
                        System.out.println("发送登录成功信息回客户端");
                    } else {
                        //将msg中的属性MessageType置为FAIL,为登录失败
                        System.out.println("用户名/密码错误，登录失败");
                        msg.setMessageType(MessageType.MESSAGE_LOGIN_FAIL);
                        //发送该msg对象给客户端
                        oos.writeObject(msg);
                        //关闭资源
                        socket.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //服务器循环监听，接收到数据创建socket对象以后再次回来监听

    }
}
