package exercise;

import java.sql.*;

public class GetVillainsNames {
    public static void main(String[] args) throws SQLException {
        Engine engine = new Engine();
        engine.run();

        PreparedStatement statement = Engine.connection.prepareStatement
                ("SELECT v.`name`, count(DISTINCT mv.minion_id) AS 'count_minions' \n" +
                        "FROM villains AS v\n" +
                        "JOIN minions_villains AS mv\n" +
                        "ON mv.villain_id = v.id\n" +
                        "GROUP BY v.id\n" +
                        "HAVING count_minions > 15\n" +
                        "ORDER BY count_minions DESC");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d", resultSet.getString(1), resultSet.getInt(2));
        }
    }
}
