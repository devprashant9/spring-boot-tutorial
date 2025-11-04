package com.loose.coupling;

public class LooseCouplingMain {
    public static void main(String[] args) {
        UserDataProvider databaseProvider = new UserDatabaseProvider();
        UserManager userManagerWithDB = new UserManager(databaseProvider);
        System.out.println(userManagerWithDB.getUserInfo());

        UserDataProvider newService = new NewService();
        UserManager newServiceWithDB = new UserManager(newService);
        System.out.println(newServiceWithDB.getUserInfo());

        // Loose Coupling
        // Future Integration Requires Minimal or No Code Changes

        // Inversion of Control
        // Object is Created and Managed by Developer. Frameworks needs to manage it

        // Dependency Injection
        // UserManager needs an instance of UserDataProvider

        // Beans
        // Objects Managed by Framework From Config. Files. Eg, Line 5 and Line 6

        // Spring Container
        // Place Where all Objects to be Used at Run-Time Resides

        // Config File
        // A File Used to Instruct Spring Container Which Object to be Used When


    }
}
