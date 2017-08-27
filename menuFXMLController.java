/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//this shows the main menu of the software
package chapelseminarproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Spark
 */
public class menuFXMLController implements Initializable {
  
    Statement stmt = null;
     static final String DB_URL = "jdbc:mysql://localhost/chapel";
     static final String USER = "root";
    static final String PASS = "";
    private ObservableList<reportTable> data1;
    
    @FXML
    private TableView<reportTable> DisplayTablereport;
    @FXML
    private TableColumn<reportTable, String> columnId;
  
    @FXML
    private TableColumn<reportTable, String> columnScore;
    @FXML
    private TableColumn<reportTable, String> columnMatric_number;
    @FXML
    private TableColumn<reportTable, String> columnAttendance;
    @FXML
    private TableColumn<reportTable, String> columnDepartment1;
    @FXML
    private TableColumn<reportTable, String> columnEmail;
    @FXML
    private TableColumn<reportTable, String> columnGrade;
   @FXML
    private TableColumn<reportTable, String> columnLevel;    
     private dataBconnection dc;
    @FXML
    private TextField txt_matric_num;
      @FXML
    private ComboBox  d =  new ComboBox();
    @FXML
    private TextField txt_level;
    
    @FXML
    private TextField txt_depart;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_attend;
    
    
    @FXML
    private Button calculate;
    @FXML
    private Button refresh;
    @FXML
    private Button sort;
     @FXML
    private Button done;
         @FXML
    private Label grade_val;
         @FXML
    private Label score_val;
       Connection conn = null;
        PreparedStatement pstmt = null;
    private String com_id;
    private String com_username;
    private String com_password;
       Preferences prefsRoot = Preferences.userRoot();
        Preferences myPrefs = prefsRoot.node("com.chapelseminarproject.preference.staticPreferenceLoader");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList<String> ds = 
        FXCollections.observableArrayList(
                 "Computer Science",
                "Accounting",
                "Computer Technology ",
                "Computer Information System",
                "History",
                "Medicine"
                
    );
          d.setItems(ds);
        // TODO
        displayTable();
    }    
    
    @FXML
    private void handleSortAction(ActionEvent event) {
        // sort
        String d1 = d.getSelectionModel().getSelectedItem().toString();
               try {
                Connection conn2 = dc.connect();
            data1 = FXCollections.observableArrayList();

            ResultSet rs = conn2.createStatement().executeQuery("select student_id, matric_number,level,attendance, department, score, grade, email from student where department =  '"+d1+"' ");  
            
            while (rs.next()) {
                data1.add(new reportTable(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            }
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
         columnMatric_number.setCellValueFactory(new PropertyValueFactory<>("matric_number"));
          columnAttendance.setCellValueFactory(new PropertyValueFactory<>("attendance"));
         columnDepartment1.setCellValueFactory(new PropertyValueFactory<>("department"));
             columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            columnGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
             columnScore.setCellValueFactory(new PropertyValueFactory<>("score"));
          columnLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
         
        DisplayTablereport.setItems(data1);
 
    
        
        
    }
    
    
    
    
     @FXML
    private void handleCalculateAction(ActionEvent event) {
        // calculate grade and score
       String g = null;
       int s = 0;
        
        String value = txt_attend.getText();
    
       if(value.contains("0") || value.contains("1")|| value.contains("2") || value.contains("3")|| value.contains("4")|| value.contains("5")|| value.contains("6")|| value.contains("7")|| value.contains("8") || value.contains("9")){
       //A
        if(value.equals("10")){
            g = "A";
            s = 83;
        }if(value.equals("11")){
            g = "A";
            s = 92;
        }if(value.equals("12")){
            g = "A";
            s = 100;
        }
        //B
         if(value.equals("9")){
            g = "B";
            s = 75;
        }if(value.equals("8")){
            g = "B";
            s = 67;
        }
        //C
        if(value.equals("7")){
            g = "C";
            s = 58;
        }if(value.equals("6")){
            g = "C";
            s = 50;
        }
        //E
         if(value.equals("5")){
            g = "E";
            s = 42;
        }
         
         //F
         if(value.equals("4")){
            g = "F";
            s = 33;
        }
          if(value.equals("3")){
            g = "F";
            s = 25;
        }
           if(value.equals("2")){
            g = "F";
            s = 17;
        }
            if(value.equals("1")){
            g = "F";
            s = 8;
        }
             if(value.equals("0")){
            g = "F";
            s = 0;
        }
              score_val.setText(Integer.toString(s));
        
        grade_val.setText(g);
       }else{
           
              Alert alert2 = new Alert(Alert.AlertType.ERROR);
        alert2.setTitle("Error Dialog");
        alert2.setHeaderText(null);
        alert2.setContentText(" Invalid Attendance Filling");
alert2.showAndWait();
       }
      
        
       
        
        
        
        
    }
    
    
     public static Connection getConnection() throws Exception {
    String driver = "com.mysql.jdbc.Driver"; 
    String url = "jdbc:mysql://localhost/chapel";
    String username = "root";
    String password = "";
    Class.forName(driver);
    Connection conn = DriverManager.getConnection(url, username, password);
    return conn;
  }
    
     @FXML
    private void handleRefreshAction(ActionEvent event) throws SQLException {
     // insert values
       DisplayTablereport.getItems().clear();
        displayTable();
    }
     
     
     
    @FXML
    private void handleDoneAction(ActionEvent event) throws SQLException {
     // insert values
        
              try {
      conn = getConnection();
      String query = "insert into student( matric_number, level, attendance, department, score, grade, email) values(?, ?, ?, ?, ?, ?, ?)";
       
      pstmt = conn.prepareStatement(query); // create a statement
      pstmt.setString(1, txt_matric_num.getText()); // set input parameter 2
      pstmt.setString(2, txt_level.getText()); // set input parameter 3
       pstmt.setString(3, txt_attend.getText()); // set input parameter 3
      pstmt.setString(4, txt_depart.getText());
      pstmt.setString(5, score_val.getText());
      pstmt.setString(6, grade_val.getText());
      pstmt.setString(7, txt_email.getText());
     
    
      pstmt.executeUpdate(); // execute insert statement
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Student Data Inserted Successfully");
        alert.showAndWait();
        
        
        
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      pstmt.close();
      conn.close();
    }
  
    }
    
    
    
        public void displayTable(){
             dc = new dataBconnection();
          try {
                Connection conn2 = dc.connect();
            data1 = FXCollections.observableArrayList();

            ResultSet rs = conn2.createStatement().executeQuery("select student_id, matric_number,level,attendance, department, score, grade, email from student ");  
            
            while (rs.next()) {
                data1.add(new reportTable(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            }
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
         columnMatric_number.setCellValueFactory(new PropertyValueFactory<>("matric_number"));
          columnAttendance.setCellValueFactory(new PropertyValueFactory<>("attendance"));
         columnDepartment1.setCellValueFactory(new PropertyValueFactory<>("department"));
             columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            columnGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
             columnScore.setCellValueFactory(new PropertyValueFactory<>("score"));
          columnLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
         
        DisplayTablereport.setItems(data1);
 
      
    }
    
    
    
}
