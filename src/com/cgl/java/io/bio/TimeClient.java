package com.cgl.java.io.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by chenguangliang on 8/20/15.
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8081;
        if(args != null && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            socket = new Socket("127.0.0.1", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("QUERY TIME ORDER");
            System.out.println("Send query order to server succeed.");

            String res = in.readLine();
            System.out.println("Response from time server is:" + res);
        } catch (Exception e) {

        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (Exception inE) {
                    inE.printStackTrace();
                }
            }
            if(out != null) {
                try {
                    out.close();
                } catch (Exception outE) {
                    outE.printStackTrace();
                }
            }
            if(socket != null) {
                try {
                    socket.close();
                } catch (Exception socketE) {
                    socketE.printStackTrace();
                }
            }
            socket = null;// is necessary?
        }
    }
}
