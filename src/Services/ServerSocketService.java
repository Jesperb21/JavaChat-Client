package Services;

import javax.activation.FileDataSource;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Models.Chat.Packages.IPackageBase;
import Models.Chat.Packages.MessagePackage;
import Models.Chat.Packages.ServerStop;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Created by jan on 09-12-2015.
 */
public class ServerSocketService implements ISocketService{
    private ServerSocket serverSocket;
    private ArrayList<Socket> connections = new ArrayList<>();

    public ServerSocketService(int Port) throws IOException {
        serverSocket = new ServerSocket(Port);
        serverSocket.setSoTimeout(0);

    }

    private void run () {
        while (true) try {
            System.out.println("Waiting for client...");
            Socket conn = serverSocket.accept();
            System.out.println("Client has connected: " + conn.getRemoteSocketAddress());
            connections.add(conn);

        } catch (SocketTimeoutException s) {
            System.out.println("Socket timed out..");
            break;
        } catch (IOException e) {
            e.printStackTrace();
            break;
        }
    }
    private void handleStream(FileDataSource server) throws IOException {
        JSONParser parser = new JSONParser();

        DataInputStream in = new DataInputStream(server.getInputStream());
//        System.out.println(in.readUTF());
        try {
            IPackageBase pckg =  (IPackageBase) parser.parse(in.readUTF());
            //todo add client package service that handles all the basic client functionality
            /*switch (pckg.getPackageType()){
                case LoginPackage:
                    break;
                case LogoutPackage:
                    break;
                case MessagePackage:
                    break;
                case RequestUserList:
                    break;
            }*/
            repeatPackage(pckg);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    //todo do package transfer for server socket
    //region package transfer


    private void repeatPackage(IPackageBase pckg) {
        TransferPckg(pckg);
    }

    public void SendMsg(String Msg){
        SendMsg("Server", Msg);
    }
    public void SendMsg(String Author, String Msg){
        MessagePackage msgPckg = new MessagePackage(Author, Date.from(Instant.now()), Msg);
        TransferPckg(msgPckg);
    }
    public void StopServer(){
        ServerStop stopPckg = new ServerStop();
        TransferPckg(stopPckg);
        //todo stop server here
    }


    /**
     * transfer a package to all currently established connections
     * @param pckg specifies what to transfer
     */
    @Override
    public void TransferPckg(IPackageBase pckg) {
        try {
            //parse to json
            JSONParser parser = new JSONParser();
            String parsedPckg = (String) parser.parse(pckg.toString());

            //send to all connections currently established
            for (Socket s : connections) {
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                out.writeUTF(parsedPckg);
                out.flush();
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }

    public void StartListening() {
        Thread t = new Thread(this::run);
        t.start();
    }

    //endregion
}
