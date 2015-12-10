package Services;

import Models.Chat.Packages.IPackageBase;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSocketService implements ISocketService{
    private Socket socket;

    //region constructor
    public ClientSocketService(Socket socket) {
        this.socket = socket;
    }
    //endregion

    //region socket commands available
    public void SendMsg(String Author, String Msg){
        //MessagePackage msgPckg = new MessagePackage(Date.from(Instant.now()), Author, Msg);
        //TransferPckg(msgPckg);
    }
    //endregion

    /**
     * transfer the package over the socket connection
     * @param pckg specifies what package to send
     */
    @Override
    public void TransferPckg(IPackageBase pckg) {
        try {
            //get output stream
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            //parse to json
            Gson g = new Gson();
            String parsedPckg = g.toJson(pckg);

            //write to output stream
            out.writeUTF(parsedPckg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
