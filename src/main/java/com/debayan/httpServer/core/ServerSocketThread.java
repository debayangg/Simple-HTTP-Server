package com.debayan.httpServer.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketThread extends Thread{
    ServerSocket serverSocket;

    public ServerSocketThread(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }
    @Override
    public void run() {
        while(serverSocket.isBound() && !serverSocket.isClosed()) {
            try {
                Socket socket = serverSocket.accept();
                new Thread(new SocketThread(socket)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
