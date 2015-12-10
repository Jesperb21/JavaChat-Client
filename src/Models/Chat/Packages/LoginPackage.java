package Models.Chat.Packages;

import java.util.Date;

/**
 * Created by JesperB on 09-12-2015.
 */
public class LoginPackage implements IPackageBase {
    private String Name;
    private String RoomName;

    public LoginPackage(String name, String RoomName) {
        Name = name;
        this.RoomName = RoomName;
    }

    @Override
    public PackageType getPackageType() {
        return PackageType.LoginPackage;
    }
    public String getName(){
        return Name;
    }
    public String getRoomName() { return RoomName;}
}
