package com.debayan.httpServer.core;

import com.debayan.http.HttpParser;
import com.debayan.http.HttpParsingException;
import com.debayan.http.HttpRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketThread extends Thread {
    Socket socket;

    SocketThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        InputStream inputStream=null;
        OutputStream outputStream=null;
        HttpParser parser = null;
        try{
            parser = new HttpParser();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            inputStream = socket.getInputStream();
            HttpRequest httpRequest = parser.parseHttpRequest(inputStream);
            outputStream = socket.getOutputStream();
            String html = "<html><body><h1>Hello World</h1></body></html>";
            String CRLF = "\r\n";

            String response = "HTTP/1.1 200 OK" + CRLF
                    + "Content-Length: " + html.length() + CRLF
                    + CRLF
                    + html + CRLF
                    + CRLF;

            outputStream.write(response.getBytes());
        } catch (IOException e) {
            System.out.println("IOException");
        }
        catch (HttpParsingException e)
        {
            System.out.println("HttpParsingException");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try {
        if(socket!=null)
        socket.close();

        if(inputStream!=null)
        inputStream.close();

        if(outputStream!=null)
        outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
