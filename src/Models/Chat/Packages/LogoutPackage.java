package Models.Chat.Packages;

/**
 * Created by JesperB on 09-12-2015.
 */
public class LogoutPackage implements IPackageBase{
    private String name;
    private String RoomName;

    public LogoutPackage(String name, String RoomName) {
        this.name = name;
        this.RoomName = RoomName;
    }

    @Override
    public PackageType getPackageType() {
        return PackageType.LogoutPackage;
    }

    public String getName(){
        return name;
    }
    public String getRoomName(){
        return RoomName;
    }
}
