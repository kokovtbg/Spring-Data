package exercise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveVillain {
    public static void main(String[] args) throws SQLException {
        Engine engine = new Engine();
        engine.run();

        Scanner scanner = new Scanner(System.in);
        int villainID = Integer.parseInt(scanner.nextLine());

        PreparedStatement statementToCheckVillain = Engine.connection.prepareStatement
                ("SELECT v.`name`, count(mv.minion_id) FROM villains AS v\n" +
                        "LEFT JOIN minions_villains AS mv\n" +
                        "ON mv.villain_id = v.id\n" +
                        "WHERE v.id = ?\n" +
                        "GROUP BY v.id");
        statementToCheckVillain.setInt(1, villainID);
        ResultSet resultSetToCheckVillain = statementToCheckVillain.executeQuery();
        int count = 0;
        int countMinions = 0;
        while (resultSetToCheckVillain.next()) {
            countMinions += resultSetToCheckVillain.getInt(2);
            count++;
        }
        if (count > 0) {
            PreparedStatement statementToGetVillainName = Engine.connection.prepareStatement
                    ("SELECT `name` FROM villains\n" +
                            "WHERE id = ?");
            statementToGetVillainName.setInt(1, villainID);
            ResultSet resultSetToGetVillainName = statementToGetVillainName.executeQuery();
            String villainName = null;
            while (resultSetToGetVillainName.next()) {
                villainName = resultSetToGetVillainName.getString(1);
            }

            PreparedStatement statementToDeleteRelationship = Engine.connection.prepareStatement
                    ("DELETE mv \n" +
                            "FROM minions_villains AS mv\n" +
                            "JOIN villains AS v\n" +
                            "ON mv.villain_id = v.id\n" +
                            "WHERE v.id = ?");
            statementToDeleteRelationship.setInt(1, villainID);
            statementToDeleteRelationship.execute();

            PreparedStatement statementToDeleteVillain = Engine.connection.prepareStatement
                    ("DELETE FROM villains\n" +
                            "WHERE id = ?");
            statementToDeleteVillain.setInt(1, villainID);
            statementToDeleteVillain.execute();
            System.out.printf("%s was deleted%n", villainName);
            System.out.printf("%d minions released%n", countMinions);
        } else {
            System.out.printf("No such villain was found%n");
        }
    }
}
