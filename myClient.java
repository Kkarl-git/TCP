package com.Internet.TCP.Assignment_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
public class myClient {
    public static void main(String[] args) throws IOException {
        String a = args[0];
        int b = Integer.parseInt(args[1]);

        InetAddress serverIP = InetAddress.getByName(a);
        int port = b;
        Socket client = new Socket(serverIP,b);
        //establish connection
        client.setSoTimeout(10000);
        //got cmd input
        BufferedReader bufSend = new BufferedReader(new InputStreamReader(System.in));
        //send to server
        PrintStream out = new PrintStream(client.getOutputStream());
        //receive from server
        BufferedReader bufRev =  new BufferedReader(new InputStreamReader(client.getInputStream()));
        boolean flag = true;
        out.println("connection");
        System.out.println("Session has been established.");
        String res = bufRev.readLine();
        System.out.println("Server: " + res);
        while(flag){
            String str = bufSend.readLine();
            out.println(str);
            try{
                res = bufRev.readLine();
                System.out.println("Server: " + res);
            }catch(SocketTimeoutException e){
                System.out.println("Time out, No response");
            }
            if(res.equals("EXIT")){
                System.out.println("Session is terminated.");
                flag = false;
            }
        }
        bufSend.close();
        if(client != null){
            client.close();
        }
    }
}


