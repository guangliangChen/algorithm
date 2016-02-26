package com.cgl.java.io.bio;

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

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Time server has started in port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Socket socket = null;
        try {
            TimeServerHandlerExecutePool timeServerHandlerExecutePool = new TimeServerHandlerExecutePool(30, 500);
            while (true) {
                socket = serverSocket.accept();
                //single thread version(blocking IO)
//                new Thread(new MultiplexerTimeServer(socket)).start();
                //thread poll(pseudo-asynchronous IO)
                timeServerHandlerExecutePool.execute(new TimeServerHandler(socket));

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                serverSocket = null;
            }
            //is socket close neccesary?
        }
    }
}
