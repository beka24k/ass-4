import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.out;

public class Autentification {
    private String login;
    private String password;
    private String iin;

    Autentification(String login, String password, String iin) {
        this.login = login;
        this.password = password;
        this.iin=iin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String hash(String object) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = md5.digest(object.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02X", b));
        }
        String hash1 = builder.toString();
        hash1 = hash1.toLowerCase();
        return hash1;
    }

    public boolean authenticate() {
        Logger logger = Logger.getLogger(Bot.class.getName());
        Handler filehandler = null;
        try {
            filehandler = new FileHandler("%h/yJava.log");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.setLevel(Level.INFO);
        logger.setUseParentHandlers(false);
        logger.addHandler(filehandler);
        DB_connect db = new DB_connect();
        try (Connection connect = db.connect_to_DB();
             Statement statement = connect.createStatement()) {
            String queryString = "SELECT login,pasword,iin FROM autotentificasi";
            ResultSet results = statement.executeQuery(queryString);
            logger.info("Attempting to authenticate user {} with password {}");
            while (results.next()) {
                String staffname = results.getString("login");
                String password = results.getString("pasword");
                String iin = results.getString("iin");
                if (this.login.equals(staffname) && this.password.equals(password)) {
                    logger.info("User {} authenticated successfully");
                    return true;
                }else if (this.iin.equals(iin) && this.password.equals(password)){
                    return true;
                }
            }
            logger.info("Authentication failed for user {}");
            return false;
        } catch (SQLException e) {
            logger.info("An SQL exception occurred while authenticating user {}");
            return false;
        } catch (Exception e) {
            logger.info("An exception occurred while authenticating user {}");
            return false;
        }
    }
}
