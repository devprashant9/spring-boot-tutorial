package ioc.loose.coupling;

public class NewService implements UserDataProvider {

    @Override
    public String getUserDetails() {
        return "This is From Newly Added Service";
    }
}
