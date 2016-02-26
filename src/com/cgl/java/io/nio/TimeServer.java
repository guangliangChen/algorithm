package com.cgl.java.io.nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chenguangliang on 8/20/15.
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8081;
        if(args != null && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }

        MultiplexerTimeServer multiplexerTimeServer = new MultiplexerTimeServer(port);
        new Thread(multiplexerTimeServer, "NIO-MultiplexerTimeServer-001").start();
//        ServerSocket serverSocket = null;
//        try {
//            serverSocket = new ServerSocket(port);
//            System.out.println("Time server has started in port:" + port);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Socket socket = null;
//        try {
//            while (true) {
//                socket = serverSocket.accept();
//                new Thread(new MultiplexerTimeServer(socket)).start();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if(serverSocket != null) {
//                try {
//                    serverSocket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                serverSocket = null;
//            }
//            //is socket close neccesary?
//        }
    }
}
