package Models.Chat.Packages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JesperB on 09-12-2015.
 */
public class RequestUserList implements IPackageBase {
    private ArrayList<String> Users;
    private String RoomName;

    public RequestUserList(ArrayList<String> users) {
        Users = users;
    }

    @Override
    public PackageType getPackageType() {
        return PackageType.RequestUserList;
    }
    public ArrayList<String> getUsers(){
        return Users;
    }
    public String RoomName(){ return RoomName;}
}
