package exercise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {
        Engine engine = new Engine();
        engine.run();

        PreparedStatement statement = Engine.connection.prepareStatement
                ("SELECT m.`name` FROM minions AS m");
        ResultSet resultSet = statement.executeQuery();

        List<String> listMinions = new ArrayList<>();
        while (resultSet.next()) {
            listMinions.add(resultSet.getString(1));
        }
        List<String> sortedMinions = new ArrayList<>();
        for (int i = 0; i < listMinions.size() / 2; i++) {
            for (int j = listMinions.size() - 1; j >= listMinions.size() / 2; j--) {
                sortedMinions.add(listMinions.get(i));
                sortedMinions.add(listMinions.get(j));
                i++;
            }
        }
        if (listMinions.size() % 2 != 0) {
            sortedMinions.remove(sortedMinions.size() - 1);
        }
        sortedMinions.forEach(System.out::println);
    }
}
