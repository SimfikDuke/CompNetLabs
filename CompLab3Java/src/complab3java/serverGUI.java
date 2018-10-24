package complab3java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;
import javax.swing.JTextPane;


/**
 *
 * @author duke
 */
public class serverGUI extends Thread{
    Socket sock;
    private static BufferedReader in;
    private static BufferedWriter out;
    private JTextPane tPane;
    
    int id;
    int interval;
    Date tDate;
    public serverGUI(Socket socket, int id_i, int interval_i, JTextPane jTextPane){
        this.sock = socket;
        this.id = id_i;
        this.interval = interval_i;
        this.tPane = jTextPane;
    }
    
    public void tAdd(String s){
        tPane.setText(tPane.getText()+s+"\n");
    }
    
    @Override
    public void run() {
        System.out.println("Клиент"+this.id+" подключен ");
        tAdd("Клиент"+this.id+" подключен ");
        System.out.println("Клиент"+this.id+" получил интервал от "+
                (this.id*this.interval+1)+" до "+((this.id+1)*this.interval));
        tAdd("Клиент"+this.id+" получил интервал от "+
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
                tAdd("Клиент"+this.id+" нашел "+count+
                        " значений за "+time+" милисекунд");
            }
            finally {
                this.in.close();
                this.out.close();
                this.sock.close();
                System.out.println("Клиент"+this.id+" успешно отключился.");
                tAdd("Клиент"+this.id+" успешно отключился.");
            }
        } catch(IOException exeption){
            System.err.println("Ошибка: " + exeption);
        }
    }
    
    
}
