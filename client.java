/////client

import java.io.*;
import java.net.*;
import java.util.Date;

public class client
{
    public static void main(String[] args) throws IOException {

        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress host = InetAddress.getLocalHost();
        int serverport = Integer.parseInt(args[0]);

        Date end = null;
        StringBuilder merge = new StringBuilder();
        int exec_count = Integer.parseInt(args[1]);
        int limit = 2 + exec_count;
        for (int i = 1;i<=limit;i++)
        {
            merge.append(args[i]);
            if(i!=limit){
            merge.append(",");
        }}

        //sending Data
        byte[] sendData = merge.toString().trim().getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, host, serverport);
        Date start = new Date();
        System.out.println("request sent at"+start.getTime());
        clientSocket.send(sendPacket);



        //receiving Data
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);

        clientSocket.receive(receivePacket);
        String fromserver = new String(receivePacket.getData());
        System.out.println("From Server" + "\n" + fromserver);

        end = new Date();
        System.out.println("request received"+end.getTime());

        long difference = end.getTime() - start.getTime();
        System.out.println("This whole process took:(RTT)"+difference+"ms");
    }}
