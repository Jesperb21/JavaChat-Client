package Services;

import Models.Chat.Packages.IPackageBase;
import Models.Chat.Packages.MessagePackage;
import Models.Chat.Packages.ServerStop;

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

import Controllers.ControllerMediator;
import Models.Chat.Packages.*;
import com.google.gson.Gson;


/**
 * Created by jan on 09-12-2015.
 */
public class ServerSocketService implements ISocketService{
    private ServerSocket serverSocket;
    private ArrayList<Socket> connections = new ArrayList<>();


    // set server socket with port and no timeout
    public ServerSocketService(int Port) throws IOException {
        ControllerMediator.getInstance().SSS = this;
        serverSocket = new ServerSocket(Port);
        serverSocket.setSoTimeout(0);

    }

    // run socket in new thread and wait for clients to connect
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
        Gson g = new Gson();

        DataInputStream in = new DataInputStream(server.getInputStream());
//        System.out.println(in.readUTF());
        Object obj = g.fromJson(in.readUTF(), IPackageBase.class);
        IPackageBase pckg =  (IPackageBase) obj;
        handleReceivedPackage(pckg);
    }


    //todo do package transfer for server socket
    //region package transfer

    private void handleReceivedPackage(IPackageBase pckg){
        ControllerMediator mediator = ControllerMediator.getInstance();

        switch (pckg.getPackageType()){
            case LoginPackage:
                LoginPackage li_pckg = (LoginPackage)pckg;
                mediator.channels.get(li_pckg.getRoomName()).Users.add(li_pckg.getName());
                SendMsg("Notification",li_pckg.getName()
                        .concat(" has joined ")
                        .concat(li_pckg.getRoomName()));
                repeatPackage(li_pckg);
                break;
            case LogoutPackage:
                LogoutPackage lo_pckg = (LogoutPackage)pckg;
                mediator.channels.get(lo_pckg.getRoomName()).Users.add(lo_pckg.getName());
                SendMsg("Notification",lo_pckg.getName()
                        .concat(" has left ")
                        .concat(lo_pckg.getRoomName()));
                break;
            case MessagePackage:
                repeatPackage(pckg);
                break;
            case RequestUserList:
                break;
        }
        repeatPackage(pckg);

    }

    private void repeatPackage(IPackageBase pckg) {
        TransferPckg(pckg);
    }

    public void SendMsg(String Msg){
        SendMsg("Server", Msg);
    }
    public void SendMsg(String Author, String Msg){
        String room = ControllerMediator.getInstance().chatPanelController.getSelectedChannel();
        SendMsg(Author, Msg, room);
    }
    public void SendMsg(String Author, String Msg, String room){
        MessagePackage msgPckg = new MessagePackage(Date.from(Instant.now()), Author, Msg, room);
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
            Gson g = new Gson();
            String parsedPckg = g.toJson(pckg);

            //send to all connections currently established
            for (Socket s : connections) {
                DataOutputStream out = null;
                out = new DataOutputStream(s.getOutputStream());
                out.writeUTF(parsedPckg);
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void StartListening() {
        Thread t = new Thread(this::run);
        t.start();
    }

    //endregion
}
