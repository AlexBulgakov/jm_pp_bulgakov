package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Вася", "Пупкин", (byte) 26);
        userService.saveUser("Маша", "Афанасьева", (byte) 19);
        userService.saveUser("Джон", "Уейн", (byte) 37);
        userService.saveUser("Томас", "Рэйн", (byte) 32);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
