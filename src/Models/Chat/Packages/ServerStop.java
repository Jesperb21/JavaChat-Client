package Models.Chat.Packages;

/**
 * Created by JesperB on 09-12-2015.
 */
public class ServerStop implements IPackageBase{
    @Override
    public PackageType getPackageType() {
        return PackageType.ServerStop;
    }
}
