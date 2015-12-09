package Models.Chat.Packages;

import java.util.Date;

/**
 * Created by JesperB on 09-12-2015.
 */
public class LoginPackage implements IPackageBase {
    private String Name;

    public LoginPackage(String name) {
        Name = name;
    }

    @Override
    public PackageType getPackageType() {
        return PackageType.LoginPackage;
    }
    public String getName(){
        return Name;
    }
}
