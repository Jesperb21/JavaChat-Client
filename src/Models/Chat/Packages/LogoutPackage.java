package Models.Chat.Packages;

/**
 * Created by JesperB on 09-12-2015.
 */
public class LogoutPackage implements IPackageBase{
    private String name;

    public LogoutPackage(String name) {
        this.name = name;
    }

    @Override
    public PackageType getPackageType() {
        return PackageType.LogoutPackage;
    }

    public String getName(){
        return name;
    }

}
