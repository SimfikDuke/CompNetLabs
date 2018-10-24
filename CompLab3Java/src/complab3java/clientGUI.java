package complab3java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;
import javax.swing.JTextPane;

public class clientGUI extends Thread{
    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private String addrString;
    private int serverPort;
    private JTextPane tPane;
    
    private int doCalc(int a, int b){
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
    
    public void tAdd(String s){
        tPane.setText(tPane.getText()+s+"\n");
    }
    
    public clientGUI(String serverAddress, int serverPort, JTextPane tPane){
        this.addrString = serverAddress;
        this.serverPort = serverPort;
        this.tPane = tPane;
    }
    
    @Override
    public void run() {
        tAdd("Клиент запущен.");
        
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
                
                 tAdd("Клиент подключен к серверу. Идентификатор: "+id);
                 tAdd("Получен интервал от "+(id*interval+1)+
                         " до "+((1+id)*interval));
                
                int numbersCount = doCalc(id*interval+1, (1+id)*interval);
                Date date2 = new Date();
                out.write(String.valueOf(numbersCount)+"\n");
                long calcTime = date2.getTime() - date1.getTime();
                out.write(String.valueOf(calcTime)+"\n");
                out.flush();
                 tAdd("Найдено "+numbersCount+" значений. Данные отправленны на сервер.");
                 tAdd("Общее время работы: "+
                         calcTime+" милисекунд.");
                
                // end of work with server
                } finally {
                    tAdd("Клиент закрыт");
                    clientSocket.close();
                    in.close();
                }
            }   catch(java.lang.NullPointerException e){
                System.err.println("Не удалось подключиться к серверу.\nПерезапустите клиент!");
                tAdd("Не удалось подключиться к серверу.\nПерезапустите клиент!");
            }   catch (IOException ex) {
                System.err.println("Ошибка: "+ex);
        }
        
    }
}
