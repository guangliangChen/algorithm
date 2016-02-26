package com.cgl.java.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Created by chenguangliang on 8/20/15.
 */
public class TimeServerHandler implements Runnable{

    private Socket _socket;

    public TimeServerHandler(Socket socket) {
        this._socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this._socket.getInputStream()));
            out = new PrintWriter(this._socket.getOutputStream(), true);

            String body = null;
            String currentTime = null;

            while (true) {
                body = in.readLine();
                if(body == null) {
                    break;
                }
                System.out.println("Time server received order:" + body);
                currentTime = "QUERY TIME ORDER".equals(body) ? new Date(System.currentTimeMillis()).toString() : "UNKOWN ORDER";
                out.println(currentTime);
            }
        } catch (IOException e) {
            e.printStackTrace();

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
            if(this._socket != null) {
                try {
                    this._socket.close();
                } catch (Exception socketE) {
                    socketE.printStackTrace();
                }
            }
            this._socket = null;// is necessary?
        }

    }
}
