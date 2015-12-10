package Services;

import Models.Chat.Packages.IPackageBase;

/**
 * Created by JesperB on 09-12-2015.
 */
public interface ISocketService {
    void SendMsg(String Author, String Msg);
    void TransferPckg(IPackageBase pckg);
}