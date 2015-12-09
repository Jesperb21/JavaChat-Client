package Models.Chat.Packages;

import java.util.Date;

/**
 * Created by JesperB on 09-12-2015.
 */
public class MessagePackage implements IPackageBase {

    private String Author;
    private Date Time;
    private String Msg;

    public MessagePackage(String author, Date time, String msg) {
        Author = author;
        Time = time;
        Msg = msg;
    }

    @Override
    public PackageType getPackageType() {
        return PackageType.MessagePackage;
    }
    public String getAuthor(){
        return Author;
    }



    public String getMessage(){
        return Msg;
    }
    public Date getTime(){
        return Time;
    }
}
