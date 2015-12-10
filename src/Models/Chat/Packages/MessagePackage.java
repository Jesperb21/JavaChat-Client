package Models.Chat.Packages;

import java.util.Date;

/**
 * Created by JesperB on 09-12-2015.
 */
public class MessagePackage implements IPackageBase {

    private Date Time;
    private String Author, Msg, RoomName;

    public MessagePackage(Date time, String author, String msg, String RoomName) {
        Time = time;
        Author = author;
        Msg = msg;
        this.RoomName = RoomName;
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
    public String getRoomName(){
        return RoomName;
    }
    public Date getTime(){
        return Time;
    }
}
