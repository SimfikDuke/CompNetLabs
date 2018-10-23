package complab3java;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompLab3Java {
    private static ServerSocket server;
    private static int iterator;
    private static int interval;
    private static String addrString = "localhost";
    private static int serverPort = 8888;

    public static void main(String[] args) {
        if(args.length > 0){
            addrString = args[0];
            if(args.length > 1) serverPort = Integer.valueOf(args[1]);
        }
        try {
            try {
                iterator = 0;
                interval = 1000000;
                server = new ServerSocket(serverPort,1000,InetAddress.getByName(addrString));
                //System.out.println("Сервер ("+ server.toString() + ") запущен!");
                System.out.println("Сервер "+addrString+":"+serverPort+" запущен!");
                System.out.println("Сервер ожидает подключения клиента к порту "+serverPort);
                while(true){
                    Socket newClient = server.accept();
                    connectedClient c = new connectedClient(newClient, ++iterator, interval);
                    c.start();
                }
            }  finally {
                server.close();
            }
        }   catch (IOException ex) {
            System.err.println("Ошибка"+ex);
            Logger.getLogger(CompLab3Java.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
