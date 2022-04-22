package exercise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetMinionNames {
    public static void main(String[] args) throws SQLException {
        Engine engine = new Engine();
        engine.run();

        Scanner scanner = new Scanner(System.in);
        int villainID = Integer.parseInt(scanner.nextLine());

        PreparedStatement statementToCheckCountVillains = Engine.connection.prepareStatement
                ("SELECT count(id) FROM villains");
        ResultSet resultSetCountVillains = statementToCheckCountVillains.executeQuery();

        int countVillains = 0;
        while (resultSetCountVillains.next()) {
            countVillains = resultSetCountVillains.getInt(1);
        }
        if (villainID <= countVillains && villainID != 0) {
            PreparedStatement statementToGetVillainName = Engine.connection.prepareStatement
                    ("SELECT `name` FROM villains\n" +
                            "WHERE id = ?");
            statementToGetVillainName.setInt(1, villainID);
            ResultSet resultSetVillainName = statementToGetVillainName.executeQuery();
            while (resultSetVillainName.next()) {
                System.out.printf("Villain: %s%n", resultSetVillainName.getString(1));
            }
            PreparedStatement statement = Engine.connection.prepareStatement
                    ("SELECT v.`name`, m.`name`, m.age FROM villains AS v\n" +
                            "JOIN minions_villains AS mv\n" +
                            "ON mv.villain_id = v.id\n" +
                            "JOIN minions AS m\n" +
                            "ON m.id = mv.minion_id\n" +
                            "WHERE v.id = ?");
            statement.setInt(1, villainID);
            ResultSet resultSet = statement.executeQuery();
            int count = 1;
            while (resultSet.next()) {
                System.out.printf("%d. %s %d%n", count, resultSet.getString(2), resultSet.getInt(3));
                count++;
            }
        } else {
            System.out.printf("No villain with ID %d exists in the database.", villainID);
        }

    }
}
