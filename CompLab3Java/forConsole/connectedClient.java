/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complab3java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author duke
 */
public class connectedClient extends Thread{
    Socket sock;
    private static BufferedReader in;
    private static BufferedWriter out;
    int id;
    int interval;
    Date tDate;
    public connectedClient(Socket socket, int id_i, int interval_i){
        this.sock = socket;
        this.id = id_i;
        this.interval = interval_i;
    }
    
    @Override
    public void run() {
        System.out.println("Клиент"+this.id+" подключен ");
        System.out.println("Клиент"+this.id+" получил интервал от "+
                (this.id*this.interval+1)+" до "+((this.id+1)*this.interval));
        try {
            try{
                in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
                out.write(this.id+"\n");
                out.write(this.interval+"\n");
                out.flush();
                String count, time;
                count = in.readLine();
                time = in.readLine();
                System.out.println("Клиент"+this.id+" нашел "+count+
                        " значений за "+time+" милисекунд");
            }
            finally {
                this.in.close();
                this.out.close();
                this.sock.close();
                System.out.println("Клиент"+this.id+" успешно отключился.");
            }
        } catch(IOException exeption){
            System.err.println("Ошибка: " + exeption);
        }
    }
    
    
}
