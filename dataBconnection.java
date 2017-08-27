/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//this helps to connect to the database
package chapelseminarproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PHILLIP OLALERE
 */
public class dataBconnection {
    public Connection connect() {

        try {
            //String url = "jdbc:mysql://192.168.43.235:3306/pass_online";
            String url = "jdbc:mysql://localhost:3306/chapel";
            //jdbc:mysql://localhost:3306/pass_online?zeroDateTimeBehavior=convertToNull [root on Default schema]
            String user = "root";
            String password = "";

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dataBconnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(dataBconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
