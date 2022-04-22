package exercise;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {
    public static void main(String[] args) throws SQLException {
        Engine engine = new Engine();
        engine.run();

        Scanner scanner = new Scanner(System.in);
        int minionID = Integer.parseInt(scanner.nextLine());
        CallableStatement statement = Engine.connection.prepareCall
                ("CALL usp_get_older(?)");
        statement.setInt(1, minionID);
        statement.execute();

        PreparedStatement statementToDisplay = Engine.connection.prepareStatement
                ("SELECT `name`, age FROM minions\n" +
                        "WHERE id = ?");
        statementToDisplay.setInt(1, minionID);
        ResultSet resultSet = statementToDisplay.executeQuery();
        while (resultSet.next()) {
            System.out.printf("%s %d", resultSet.getString(1), resultSet.getInt(2));
        }
    }
}
