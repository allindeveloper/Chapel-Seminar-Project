/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//this is the admin page of the software
package chapelseminarproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Spark
 */
public class adminFXMLController implements Initializable {
  
    Connection conn = null;
    Statement stmt = null;
     static final String DB_URL = "jdbc:mysql://localhost/chapel";
     static final String USER = "root";
    static final String PASS = "";
    @FXML
    private TextField txt_username;
    
    @FXML
    private TextField txt_password;
    
    @FXML
    private Button login;
    
    private String com_id;
    private String com_username;
    private String com_password;
       Preferences prefsRoot = Preferences.userRoot();
        Preferences myPrefs = prefsRoot.node("com.hospitalstuff.preference.staticPreferenceLoader");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
     @FXML
    private void handleLoginAction(ActionEvent event) {
     
        try{
      Class.forName("com.mysql.jdbc.Driver");

      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      
      stmt = conn.createStatement();

      String sql = "SELECT id, username, password FROM admin";
      ResultSet rs = stmt.executeQuery(sql);
      
      while(rs.next()){
          com_id  = rs.getString("id");
          com_username = rs.getString("username");
          com_password = rs.getString("password");
      } 
      if(txt_username.getText().equals(com_username) && txt_password.getText().equals(com_password)){
       
   try{
             myPrefs.put("username", com_username);
             myPrefs.put("password", com_username);
             myPrefs.put("id", com_id);
             FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Parent root1 = (Parent) fXMLLoader.load();
            Stage stage = new Stage();
            
            stage.setTitle("Home Page");
            
            stage.setScene(new Scene(root1));
     //       stage.setAlwaysOnTop(true);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
       //     Stage st = (Stage) login.getScene().getWindow();
        //    st.hide();
            
        }catch (Exception e){
            e.printStackTrace();
        }

rs.close();
      }else{
          Alert alert2 = new Alert(Alert.AlertType.ERROR);
        alert2.setTitle("Error Dialog");
        alert2.setHeaderText(null);
        alert2.setContentText("Login Error");
alert2.showAndWait();
      }
    
    
   }
        catch(SQLException se){
      //Handle errors for JDBC
      Alert alert2 = new Alert(Alert.AlertType.ERROR);
        alert2.setTitle("Error Dialog");
        alert2.setHeaderText(null);
        alert2.setContentText("SQL ERROR");
alert2.showAndWait();
           
   }catch(Exception e){
      //Handle errors for Class.forName
      Alert alert2 = new Alert(Alert.AlertType.ERROR);
        alert2.setTitle("Error Dialog");
        alert2.setHeaderText(null);
        alert2.setContentText("Connection Error");
alert2.showAndWait();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try

    }
    
    
    
    
    
}
