package com.Internet.TCP.Assignment_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class myServer {
    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(9999);
        String studentId = "8819479";
        InetAddress addr = InetAddress.getLocalHost();

        ArrayList<String[]> arraylistB = new ArrayList<>();
        String[] store = new String[2];

        ArrayList<String> arraylistC = new ArrayList<>();


        Socket client = null;
        boolean f = true;
        while(f){
            client = server.accept();

            try{
                //send to client
                PrintStream out = new PrintStream(client.getOutputStream());
                //receive from client
                BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
                boolean flag =true;
                while(flag){
                    String str =  buf.readLine();
                    if(str.equals("connection")){
                        out.println("GREETING");
                    }
                    String[] arr = str.split(" ");
                    if(arr == null || "".equals(arr)){
                        flag = false;
                    }else{
                        if(arr[0].equals("typeD") && arr[1].equals(studentId)){
                            out.println("EXIT");
                            flag = false;
                        }
                        if(arr[0].equals("typeA") && arr[1].equals(studentId)){
                            out.println(addr.getHostAddress());
                        }
                        if(arr[0].equals("typeB") && arr[1].equals(studentId)){
                            if(!arr[2].equals("")){
                                String n = arr[2].toUpperCase();
                                store[0] = arr[0];
                                store[1] = arr[1];
                                arraylistB.add(store);
                                System.out.println(store[0]+ " " + store[1]);
                                out.println(n);
                            }
                        }
                        if(arr[0].equals("typeC") && arr[1].equals(studentId)){
                            arraylistC.add(arr[2]);
                            System.out.println(arr[2]);
                            out.println("ACKN");
                        }
                    }
                }
                out.close();
                client.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        server.close();
    }
}
