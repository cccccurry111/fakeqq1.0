package qqclientpackage.qqclient_view;
/*
 * @author Curry
 * @version 1.0
 *
 * 客户端登录界面
 */

import qqclientpackage.qqclient_service.UserClientService;
import qqclientpackage.utils.Utils;
import qqcommon.*;

import java.util.Scanner;

public class QQView {
    //控制是否显示主菜单，如果客户输入9则将loop更改为false
    private static boolean loginLoop = true;
    private static boolean enterLoop = true;
    private static boolean menuLoop = true;
    private static boolean choseLoop = true;
    private static int logCount;
    private static int choseCount;
    private Message msg;

    private final static Scanner ms = new Scanner(System.in);
    private static String call;

    public static void main(String[] args) {
        mainMenu();
        System.out.println("客户端已退出系统");
    }

    /**
     * 主菜单逻辑：
     * 1.提示输入指令登录或退出系统
     * 1.1退出系统直接退出循环
     * 1.2输入错误指令循环提示3次，3次后退出系统
     * 1.3选择登录则进入下一级，提示输入账号密码
     */

    //显示主菜单
    private static void mainMenu() {
        while (loginLoop) {

            logCount = 3;
            choseCount = 3;
            choseLoop = true;
            enterLoop = true;
            System.out.println("=========网络通讯系统=========");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
            System.out.print("请输入指令：");

            //如果输入9则不显示主菜单并退出系统
            while (choseLoop) {
                if (choseCount == 0) {//
                    System.out.println("输入错误3次，返回主菜单");
                    choseCount = 3;
                    break;
                }
                call = Utils.readString(1);

                if (!("9".equals(call)) && !("1".equals(call))) {//输入错误指令
                    System.out.print("输入有误，请重新输入：");
                    choseCount--;
                } else if ("9".equals(call)) {//选择退出系统,直接退出当前循环
                    loginLoop = false;
                    System.out.println("系统已退出");
                    break;
                } else {//选择登录
                    while (enterLoop) {
                        //提示输入用户号和密码
                        if (logCount == 0) {
                            System.out.println("用户号/密码输入错误3次，返回主菜单");
                            logCount = 3;
                            choseLoop = false;
                            break;
                        }
                        System.out.print("请输入用户号：");
                        String userId = Utils.readLine();
                        System.out.print("请输入密  码：");
                        String password = Utils.readLine();

                        //输入用户号和密码以后，需要以此new一个User对象
                        boolean login = UserClientService.checkUserLogin(userId, password);
                        //将user发送到服务器进行验证，由类ClientConnectServerThread完成
                        System.out.println("登录状态：" + login);

                        //如果用户号和密码正确则登入，否则提示错误回到一级菜单
                        if (login) {
                            System.out.println("=========欢迎用户" + userId + "========");

                            //如果登入则显示菜单
                            while (menuLoop) {
                                System.out.println(
                                        "=========网络通讯系统二级菜单（" + userId + "）========");
                                System.out.println("1 显示在线用户列表");
                                System.out.println("2 群发消息");
                                System.out.println("3 私聊消息");
                                System.out.println("4 发送文件");
                                System.out.println("9 退出登录");
                                System.out.print("请输入你的选择：");
                                call = Utils.readString(1);

                                if ("9".equals(call)) {
                                    enterLoop = false;
                                    choseLoop = false;
                                    break;
                                }

                                //根据指令显示下级菜单
                                switch (call) {
                                    case "1":
                                        System.out.println("显示在线用户列表");
                                        break;
                                    case "2":
                                        System.out.println("群发消息");
                                        break;
                                    case "3":
                                        System.out.println("私聊消息");
                                        break;
                                    case "4":
                                        System.out.println("发送文件");
                                        break;

                                }
                            }
                        } else {
                            System.out.println("用户号或密码出错，请重新输入");
                            logCount--;
                        }
                    }
                }
            }
        }
    }
}
