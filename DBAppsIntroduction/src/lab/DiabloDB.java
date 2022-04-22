package lab;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class DiabloDB {
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

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement statement = connection.prepareStatement
                ("SELECT first_name, last_name, count(g.id) AS 'count_games'\n" +
                        "FROM users AS u\n" +
                        "JOIN users_games AS ug\n" +
                        "ON ug.user_id = u.id\n" +
                        "JOIN games AS g\n" +
                        "ON g.id = ug.game_id\n" +
                        "WHERE u.user_name = ?");

        String username = scanner.nextLine();
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            if (resultSet.getInt("count_games") != 0) {
                System.out.println("User: " + username);
                System.out.printf("%s %s has played %d games",
                        resultSet.getString("first_name"), resultSet.getString("last_name"),
                        resultSet.getInt("count_games"));
            } else {
                System.out.println("No such user exists");
            }
        }
    }
}
