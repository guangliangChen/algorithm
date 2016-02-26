package com.cgl.java.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by chenguangliang on 8/20/15.
 */
public class MultiplexerTimeServer implements Runnable{

//    private int _port;
    private Selector _selector;
    private ServerSocketChannel _serverSocketChannel;

    private volatile boolean _stop;//is volatile neccessary?

    public MultiplexerTimeServer(int port) {
//        this._port = port;
        try {
            this._selector = Selector.open();
            this._serverSocketChannel = ServerSocketChannel.open();
            this._serverSocketChannel.configureBlocking(false);
            this._serverSocketChannel.bind(new InetSocketAddress(port), 1024);
            this._serverSocketChannel.register(this._selector, SelectionKey.OP_ACCEPT);
            System.out.println("Time server has started in port:" + port);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!this._stop) {
            Set<SelectionKey> selectionKeys = this._selector.selectedKeys();
            Iterator iterator = selectionKeys.iterator();
            SelectionKey selectionKey = null;
            try {
                this._selector.select(1000);
                while (iterator.hasNext()) {
                    selectionKey = (SelectionKey)iterator.next();
                    iterator.remove();
                    handleInput(selectionKey);
                }
            } catch (Exception e) {
                if(selectionKey != null) {
                    selectionKey.cancel();//to cancel the channel which register to the selector by this selectionKey
                }

            }


        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if(!key.isValid()) {
            System.out.println("The selectionKey is not valid,return.");
            return;
        }
        if(key.isAcceptable()) {//the key's channel is ready to accept a new socket connection
            //return value is serverSocketChannel?
            //what diff between ServerSocketChannel to SocketChannel?
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();//accept() will block until a new connection is available
                socketChannel.configureBlocking(false);
                socketChannel.register(this._selector, SelectionKey.OP_READ);
            } catch (Exception e) {
                try {
                    serverSocketChannel.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } else if(key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel)key.channel();
            socketChannel.configureBlocking(false);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int readLength = socketChannel.read(byteBuffer);
            byteBuffer.flip();
            if(readLength > 0) {
                byte[] bytes = new byte[byteBuffer.remaining()];
                byteBuffer.put(bytes);//why this is neccesary?

                String request = new String(bytes, "UTF-8");
                System.out.println("Multiplexer time server received order:" + request);

                String currentTime = "QUERY TIME ORDER".equals(request) ? new Date(System.currentTimeMillis()).toString() : "UNKOWN ORDER";
                doWrite(socketChannel, currentTime);
            } else if(readLength < 0){
                key.cancel();
                socketChannel.close();
            }

        }
    }

    private void doWrite(SocketChannel socketChannel, String response) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(response.getBytes());
        byteBuffer.flip();
        try {
            socketChannel.write(byteBuffer);
        } catch (Exception e) {
            if (socketChannel != null) {
                try{
                    socketChannel.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
