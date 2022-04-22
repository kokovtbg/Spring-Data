package lab;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class SoftUniDBSalary {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username default (root): ");
        String user = scanner.nextLine();
        user = user.equals("") ? "root" : user;

        System.out.print("Enter password default (empty):");
        String password = scanner.nextLine().trim();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", props);

        PreparedStatement stmt = connection.prepareStatement
                ("SELECT * FROM employees WHERE salary > ?");


        String salary = scanner.nextLine();
        stmt.setDouble(1, Double.parseDouble(salary));
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.printf("%s %s%n",
                    rs.getString("first_name"), rs.getString("last_name"));
        }
    }
}
