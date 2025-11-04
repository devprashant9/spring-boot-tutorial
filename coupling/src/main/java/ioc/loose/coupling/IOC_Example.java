package ioc.loose.coupling;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOC_Example {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationIoCLooseCoupling.xml");

        UserManager userManagerWithDB = (UserManager) context.getBean("userManagerWithDataProvider");
        System.out.println(userManagerWithDB.getUserInfo());

        UserManager newServiceWithDB = (UserManager) context.getBean("userManagerWithNewService");
        System.out.println(newServiceWithDB.getUserInfo());
    }
}
