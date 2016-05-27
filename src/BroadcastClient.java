import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.MulticastSocket;

/**
 * Created by idalov on 25.05.16.
 */
public class BroadcastClient {
    private InetAddress address;
    public byte[] buffer;
    public DatagramPacket packet;
    // public DatagramSocket socket;
    public MulticastSocket socket;

    public BroadcastClient() throws Exception{
      /*  socket = new DatagramSocket(4567);

        socket.setBroadcast(true);*/
        socket = new MulticastSocket(4567);
        socket.setInterface(InetAddress.getByName("192.168.0.51"));
        socket.setBroadcast(true);
        transmit();


    }

    private void transmit() {
        int i = 0;
        double Time;
        long LTime = System.currentTimeMillis();
        try {
            buffer = new byte[1481];
            packet = new DatagramPacket(buffer,buffer.length, InetAddress.getByName("0.0.0.0"),4567);
            while(true) {
                socket.receive(packet);

                if(i++%100000 == 0) {
                    Time=(System.currentTimeMillis()-LTime)/1000.;
                    LTime = System.currentTimeMillis();
                    System.out.println("Total reseive: " + i + "speed: " + 0.00138 * 100000 / Time + " Mb/s");

                }
                }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            socket.close();
            System.out.println("Total: " + i + "packets");}

    }

}



