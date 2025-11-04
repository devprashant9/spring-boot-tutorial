package com.loose.coupling;

public class LooseCouplingMain {
    public static void main(String[] args) {
        UserDataProvider databaseProvider = new UserDatabaseProvider();
        UserManager userManagerWithDB = new UserManager(databaseProvider);
        System.out.println(userManagerWithDB.getUserInfo());

        UserDataProvider newService = new NewService();
        UserManager newServiceWithDB = new UserManager(newService);
        System.out.println(newServiceWithDB.getUserInfo());
    }
}
