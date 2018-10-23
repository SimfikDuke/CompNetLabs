package complab3java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

public class client {
    private static Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static String addrString = "localhost";
    private static int serverPort = 8888;
    
    public static int doCalc(int a, int b){
        int counter = 0;
        int j = 0;
        int[] q = new int[12];
        for (int i = a; i < b; i++){
            if (i % 11 == 0 && i % 13 == 0 && i % 17 == 0){
                counter++;
            }
        }
        return counter;
    }
    
    public static void main(String[] args) {
        System.out.println("Клиент успешно запущен.");
        if(args.length > 0){
            addrString = args[0];
            if(args.length > 1) serverPort = Integer.valueOf(args[1]);
        }
        try {
             try {
                 // init components
                clientSocket = new Socket(addrString, serverPort);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                
                // work with server
                Date date1 = new Date();
                int id = Integer.valueOf(in.readLine());
                int interval = Integer.valueOf(in.readLine());
                 System.out.println("Клиент подключен к серверу. Идентификатор: "+id);
                 System.out.println("Получен интервал от "+(id*interval+1)+
                         " до "+((1+id)*interval));
                int numbersCount = doCalc(id*interval+1, (1+id)*interval);
                Date date2 = new Date();
                out.write(String.valueOf(numbersCount)+"\n");
                long calcTime = date2.getTime() - date1.getTime();
                out.write(String.valueOf(calcTime)+"\n");
                out.flush();
                 System.out.println("Найдено "+numbersCount+" значений. Данные отправленны на сервер.");
                 System.out.println("Общее время работы: "+
                         calcTime+" милисекунд.");
                
                // end of work with server
                } finally {
                    System.out.println("Клиент закрыт");
                    clientSocket.close();
                    in.close();
                }
            }   catch(java.lang.NullPointerException e){
                System.err.println("Не удалось подключиться к серверу.\nПерезапустите клиент!");
            }   catch (IOException ex) {
                System.err.println("Ошибка: "+ex);
        }
        
    }
}
