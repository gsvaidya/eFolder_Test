import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class server
{
    public static void main(String[] args) throws IOException, InterruptedException {
        for(int a=0;a<args.length;a++){

            if (args[a].equals("help"))
            {
                System.out.println("The order of arguments for TCP programs is\n"+
                        "port_Num, num_documents, string, paths to docs");
                System.exit(0);
            }
        }

        int clientport = Integer.parseInt(args[0]);
        DatagramSocket serversocket = new DatagramSocket(clientport);
        InetAddress add = InetAddress.getLocalHost();
        System.out.println("server is on with IP address:" + add.getHostAddress());
        byte[] receiveData = new byte[1024];
        String send = "";

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String s = "";

        while(true) { //data reception
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serversocket.receive(receivePacket);
            String datareceived = new String(receivePacket.getData());
            System.out.println(datareceived);
            System.out.println("received from Client");
            String[] parts = datareceived.split(",");

            //System.out.println(parts[0]);

            int executioncount = Integer.parseInt(parts[0]);
            String match = parts[1];
            List<String> pathList = new ArrayList<String>();
            boolean w;
            List<String> resultList = new ArrayList<String>();
            for(int j=0;j<executioncount;j++) {

               if((parts[j+2])!="") pathList.add(j,parts[j+2].trim());

                w = checkString.searchUsingBufferedReader(match,pathList.get(j));
                System.out.println(w+" for "+pathList.get(j));
                resultList.add(j,String.valueOf(w));
            }
            //System.out.println("delay time is " +delaytime);
            InetAddress clientaddress = receivePacket.getAddress();
            String clientAdd = clientaddress.toString().replaceAll("\\/","");
            InetAddress clientaddress_mod = InetAddress.getByName(clientAdd);
            System.out.println("Client address is: " + clientaddress_mod);

            System.out.println("executing and sending to client");

//            for (int j = 0; j < executioncount; j++) {
//                Date timestamp = new Date();
//                Process p = Runtime.getRuntime().exec(parts[2]);
//                BufferedReader brInput = new BufferedReader
//                        (new InputStreamReader(p.getInputStream()));
//
//                FileOutputStream file = new FileOutputStream("out.txt");
//                while ((send = brInput.readLine()) != null) {
//
//
//                    file.write(send.getBytes());
//                    file.write("\n".getBytes());
//
//                }
//                file.close();
//                //add error
//
//                //datasend = send.getBytes();
//
//
//                Path path = Paths.get("", "out.txt");
//                send = new String(Files.readAllBytes(path));

                Date timestamp = new Date();
                send = send.concat("current time:" + df.format(timestamp).trim());
                byte[] data = send.trim().getBytes();
                DatagramPacket sendPacket = new DatagramPacket(data, data.length, clientaddress_mod, receivePacket.getPort());
                //System.out.println(clientaddress+"check client");
                serversocket.send(sendPacket);

                //Files.delete(path)
            System.out.println("status - closed");

        }}}
/////////server