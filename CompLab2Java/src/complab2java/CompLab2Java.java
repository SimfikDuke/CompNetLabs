package complab2java;

import java.util.Date;

public class CompLab2Java {
    public static long t1time;
    public static long t3time;
    public static int tCount = 0;
    public static int t1Count = 0;
    public static int t2Count = 0;
    public static int t3Count = 0;

    public static void workWithOneThrow() throws InterruptedException {
        Thread t = new Thread(new Runnable(){
        public void run(){ tCount = doCalc(1000000,4000000,"ПОТОК");}});Date d11 = new Date();
        t1time = 0;
        t.start();
        t.join();
        Date d12 = new Date();
        t1time = d12.getTime()-d11.getTime();
    }
    public static void workWithThreeThrow() throws InterruptedException {
        
        Thread t1 = new Thread(new Runnable(){
        public void run(){ t1Count = doCalc(1000000,2000000,"ПОТОК1");}});
        Thread t2 = new Thread(new Runnable(){
        public void run(){ t2Count = doCalc(2000000,3000000,"ПОТОК2");}});
        Thread t3 = new Thread(new Runnable(){
        public void run(){ t3Count = doCalc(3000000,4000000,"ПОТОК3");}});
        Date d31 = new Date();
        t3time = 0;
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        Date d32 = new Date();
        t3time = d32.getTime()-d31.getTime();
    }
    
    public static int doCalc(int a, int b, String name){
        int counter = 0;
        int j = 0;
        int[] q = new int[12];
        for (int i = a; i < b; i++){
            if (i % 11 == 0 && i % 13 == 0 && i % 17 == 0){
                counter++;
                q[j] = i;
                //System.out.println(name + " нашел число " + i);
                if(j==11){
                    j = 0;
                    String out = name + ": ";
                    for(int k = 0; k<12; k++){
                        out += q[k]+", ";
                    }
                    System.out.println(out);
                }
                else j++;
            }
        }
        return counter;
    }
    public static void showStats(){
        System.out.println("Нулевой поток нашел: " + tCount + " чисел за " + t1time + " миллисекунд");
        System.out.println("Три потока нашли: " + (t1Count+t2Count+t3Count) + " чисел за " + t3time + " миллисекунд");
    }
    public static void main(String[] args) throws InterruptedException {
        workWithOneThrow();
        workWithThreeThrow();
        showStats();
    }   
}
