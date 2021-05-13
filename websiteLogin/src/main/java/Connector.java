import java.sql.*;

public class Connector {
    private Connection con;

    public Connector() throws SQLException {
        con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/login", "root", "JKL0aqw444MnB1207!");
    }

    public String searchUser(int id) throws SQLException {
        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM user WHERE user_id = " + id + ";");

        String username = "";
        if(rs.next()) {
            username = rs.getString(4);
        }

        return username;

    }
    public String searchPass(String user) throws SQLException {
        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM user WHERE username='"+user+"';");

        rs.next();
        return rs.getString(5);

    }
    public void add(String[] entries, String password) throws  SQLException {
        Statement statement = con.createStatement();

        int id = highestID();


        statement.executeUpdate("INSERT INTO user VALUE(" +
                "'" + id + "'," +
                "'" + entries[0] + "'," +
                "'" + entries[1] + "'," +
                "'" + entries[2] + "'," +
                "'" + password + "'," +
                "'" + entries[3] + "'," +
                "'" + entries[5] + "'," +
                "'" + entries[4] + "');");
    }

    public int highestID() throws SQLException {
        int id = 0;
        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM user;");
        while (rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }
}
