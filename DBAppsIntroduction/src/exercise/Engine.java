package exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Engine implements Runnable {
    static Connection connection;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username default (root): ");
        String user = scanner.nextLine();
        user = user.equals("") ? "root" : user;

        System.out.print("Enter password default (empty):");
        String password = scanner.nextLine().trim();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
