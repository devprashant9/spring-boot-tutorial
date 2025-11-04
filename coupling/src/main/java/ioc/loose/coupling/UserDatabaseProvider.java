package ioc.loose.coupling;

public class UserDatabaseProvider implements UserDataProvider {
    // A - MySQL, PostgreSQL
    // B - MongoDB
    @Override
    public String getUserDetails() {
        return "User Details From Database";
    }
}
