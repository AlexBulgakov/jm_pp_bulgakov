package ru.bulgakov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.bulgakov.config.MyConfig;
import ru.bulgakov.model.User;

import java.util.List;

public class App 
{
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);

        communication.setConnection();

        communication.addUser();


        System.out.println(communication.getAllUsers());

//

//        User updateUser = new User(3, "Thomas", "Shelby", (byte)31);
//        newUser.setId(3);
//        communication.updateUser(updateUser);



//        User userByID = communication.getUser(2);
//        System.out.println(userByID);



    }
}
