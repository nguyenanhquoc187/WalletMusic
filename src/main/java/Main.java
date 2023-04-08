import com.walletmusic.dao.impl.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        Connection connection = null;
        String sql = "SELECT * FROM songs WHERE id = 12";
        ResultSet resultSet = null;
        try {
            connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            String title = null;
            while (resultSet.next()) {
                title = resultSet.getString("title");
            }
            System.out.println(connection);
            System.out.println(title);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
