package exercise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {
        Engine engine = new Engine();
        engine.run();

        Scanner scanner = new Scanner(System.in);
        int[] minionsAgesArr = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < minionsAgesArr.length; i++) {
            PreparedStatement statementToIncreaseAge = Engine.connection.prepareStatement
                    ("UPDATE minions \n" +
                            "SET `name` = lower(`name`),\n" +
                            "age = age + 1\n" +
                            "WHERE id = ?");
            statementToIncreaseAge.setInt(1, minionsAgesArr[i]);
            statementToIncreaseAge.execute();
        }
        PreparedStatement statementToPrintMinions = Engine.connection.prepareStatement
                ("SELECT `name`, age FROM minions");
        ResultSet resultSetToPrintMinions = statementToPrintMinions.executeQuery();
        while (resultSetToPrintMinions.next()) {
            System.out.printf("%s %d%n",
                    resultSetToPrintMinions.getString(1), resultSetToPrintMinions.getInt(2));
        }
    }
}
