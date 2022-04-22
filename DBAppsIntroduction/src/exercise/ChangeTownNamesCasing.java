package exercise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNamesCasing {
    public static void main(String[] args) throws SQLException {
        Engine engine = new Engine();
        engine.run();

        Scanner scanner = new Scanner(System.in);
        String country = scanner.nextLine();

        PreparedStatement statementToCheckCountry = Engine.connection.prepareStatement
                ("SELECT `name` FROM towns\n" +
                        "WHERE country = ?");
        statementToCheckCountry.setString(1, country);
        ResultSet resultSetToCheckCountry = statementToCheckCountry.executeQuery();
        List<String> listCountries = new ArrayList<>();
        while (resultSetToCheckCountry.next()) {
            listCountries.add(resultSetToCheckCountry.getString(1).toUpperCase());
        }
        if (listCountries.size() > 0) {
            PreparedStatement statement = Engine.connection.prepareStatement
                    ("UPDATE towns\n" +
                            "SET `name` = upper(`name`)\n" +
                            "WHERE country = ?");
            statement.setString(1, country);
            statement.execute();
            System.out.printf("%d town names were affected.%n", listCountries.size());
            System.out.println(listCountries);
        } else {
            System.out.println("No town names were affected.");
        }
    }
}
