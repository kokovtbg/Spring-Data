package exercise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AddMinion {
    public static void main(String[] args) throws SQLException {
        Engine engine = new Engine();
        engine.run();

        Scanner scanner = new Scanner(System.in);
        String[] inputDataMinion = scanner.nextLine().split("\\s+");
        String[] inputDataVillain = scanner.nextLine().split("\\s+");
        String nameMinion = inputDataMinion[1];
        int ageMinion = Integer.parseInt(inputDataMinion[2]);
        String townMinion = inputDataMinion[3];
        String nameVillain = inputDataVillain[1];

        PreparedStatement statementToCheckTownName = Engine.connection.prepareStatement
                ("SELECT t.`name` FROM towns AS t\n" +
                        "WHERE t.`name` = ?");
        statementToCheckTownName.setString(1, townMinion);
        ResultSet resultSetToCheckTownName = statementToCheckTownName.executeQuery();
        int count = 0;
        while (resultSetToCheckTownName.next()) {
            count++;
        }
        if (count == 0) {
            PreparedStatement statementToAddTown = Engine.connection.prepareStatement
                    ("INSERT INTO towns(`name`)\n" +
                            "VALUES(?)");
            statementToAddTown.setString(1, townMinion);
            statementToAddTown.execute();
            System.out.printf("Town %s was added to the database.%n", townMinion);
        }

        PreparedStatement statementToCheckVillainName = Engine.connection.prepareStatement
                ("SELECT v.`name` FROM villains AS v\n" +
                        "WHERE v.`name` = ?");
        statementToCheckVillainName.setString(1, nameVillain);
        ResultSet resultSetToCheckVillainName = statementToCheckVillainName.executeQuery();
        count = 0;
        while (resultSetToCheckVillainName.next()) {
            count++;
        }
        if (count == 0) {
            PreparedStatement statementToAddVillain = Engine.connection.prepareStatement
                    ("INSERT INTO villains(`name`, evilness_factor)\n" +
                            "VALUES(?, 'evil')");
            statementToAddVillain.setString(1, nameVillain);
            statementToAddVillain.execute();
            System.out.printf("Villain %s was added to the database.%n", nameVillain);
        }

        PreparedStatement statementToCheckTownID = Engine.connection.prepareStatement
                ("SELECT id FROM towns\n" +
                        "WHERE `name` = ?");
        statementToCheckTownID.setString(1, townMinion);
        ResultSet resultSetToCheckTownID = statementToCheckTownID.executeQuery();
        int townID = 0;
        while (resultSetToCheckTownID.next()) {
            townID = resultSetToCheckTownID.getInt(1);
        }

        PreparedStatement statementToAddMinion = Engine.connection.prepareStatement
                ("INSERT INTO minions(`name`, age, town_id)\n" +
                        "VALUES(?, ?, ?)");
        statementToAddMinion.setString(1, nameMinion);
        statementToAddMinion.setInt(2, ageMinion);
        statementToAddMinion.setInt(3, townID);
        statementToAddMinion.execute();

        PreparedStatement statementToGetMinionID = Engine.connection.prepareStatement
                ("SELECT id FROM minions\n" +
                        "WHERE `name` = ?");
        statementToGetMinionID.setString(1, nameMinion);
        int minionID = 0;
        ResultSet resultSetToGetMinionID = statementToGetMinionID.executeQuery();
        while (resultSetToGetMinionID.next()) {
            minionID = resultSetToGetMinionID.getInt(1);
        }

        PreparedStatement statementToGetVillainID = Engine.connection.prepareStatement
                ("SELECT id FROM villains\n" +
                        "WHERE `name` = ?");
        statementToGetVillainID.setString(1, nameVillain);
        int villainID = 0;
        ResultSet resultSetToGetVillainID = statementToGetVillainID.executeQuery();
        while (resultSetToGetVillainID.next()) {
            villainID = resultSetToGetVillainID.getInt(1);
        }

        PreparedStatement statementToAddRelationship = Engine.connection.prepareStatement
                ("INSERT INTO minions_villains(minion_id, villain_id)\n" +
                        "VALUES(?, ?)");
        statementToAddRelationship.setInt(1, minionID);
        statementToAddRelationship.setInt(2, villainID);
        statementToAddRelationship.execute();

        System.out.printf("Successfully added %s to be minion of %s", nameMinion, nameVillain);
    }
}
