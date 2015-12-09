package Services;

import Models.Chat.Packages.IPackageBase;
import Models.Chat.Packages.MessagePackage;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

/**
 * Created by JesperB on 09-12-2015.
 */
public interface ISocketService {
    void SendMsg(String Author, String Msg);
    void TransferPckg(IPackageBase pckg);
}