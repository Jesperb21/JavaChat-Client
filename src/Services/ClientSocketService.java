package Services;

import Models.Chat.Packages.IPackageBase;
import Models.Chat.Packages.MessagePackage;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.SecureDirectoryStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientSocketService implements ISocketService{
    private Socket socket;

    //region constructor
    public ClientSocketService(Socket socket) {
        this.socket = socket;
    }
    //endregion

    //region socket commands available
    public void SendMsg(String Author, String Msg){
        MessagePackage msgPckg = new MessagePackage(Author, Date.from(Instant.now()), Msg);
        TransferPckg(msgPckg);
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
            JSONParser parser = new JSONParser();
            String parsedPckg = (String) parser.parse(pckg.toString());

            //write to output stream
            out.writeUTF(parsedPckg);
            out.flush();

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

}
