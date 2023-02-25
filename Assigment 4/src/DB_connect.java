import java.sql.*;
import javax.security.auth.login.LoginException;import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class DB_connect{
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_Pasword = "040205";

    public Connection connect_to_DB() {
        Connection connect = null;

        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_Pasword);
            if (connect != null) {
                assert true;
            } else {
                System.err.println("Connection Failed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return connect;
    }

}