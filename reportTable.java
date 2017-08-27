/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chapelseminarproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author PHILLIP OLALERE
 */
public class reportTable {
    private final StringProperty id;
   private final StringProperty matric_number;
    private final StringProperty attendance;
    private final StringProperty department;
    private final StringProperty email;
    private final StringProperty grade;
    private final StringProperty score;
    private final StringProperty level;
   
    public reportTable(String id, String matric_number, String attendance,String level, String department, String grade, String score, String email) {
      this.id = new SimpleStringProperty(id);
         this.matric_number = new SimpleStringProperty(matric_number);
          this.attendance = new SimpleStringProperty(attendance);
          this.level = new SimpleStringProperty(level);
          this.department = new SimpleStringProperty(department);
           this.grade = new SimpleStringProperty(grade);
             this.score = new SimpleStringProperty(score);
              this.email = new SimpleStringProperty(email);
           
       
      }


    public String getId() {
        return id.get();
    }
   public String getMatric_number() {
        return matric_number.get();
    }
    public String getAttendance() {
        return attendance.get();
    }
    public String getEmail() {
        return email.get();
    }
    public String getGrade() {
        return grade.get();
    }
    public String getScore() {
        return score.get();
    }
      public String getDepartment() {
        return department.get();
    }
    
        public String getLevel() {
        return level.get();
    }
    
    

  
   
    public void setId(String value) {
        id.set(value);
    }
        public void setMatric_number(String value) {
        matric_number.set(value);
    }
            public void setAttendance(String value) {
        attendance.set(value);
    }
                public void setEmail(String value) {
        email.set(value);
    }
                    public void setGrade(String value) {
        grade.set(value);
    }
                        public void setScore(String value) {
        score.set(value);
    }
                         public void setDepartment(String value) {
        department.set(value);
    }
        public void setLevel(String value) {
        level.set(value);
    }
    
   
    
    public StringProperty idProperty() {
        return id;
    }

  
 public StringProperty matric_numberProperty() {
        return matric_number;
    }
  public StringProperty attendanceProperty() {
        return attendance;
    }
   public StringProperty emailProperty() {
        return email;
    }
    public StringProperty gradeProperty() {
        return grade;
    }
     public StringProperty scoreProperty() {
        return score;
    }
     public StringProperty departmentProperty() {
        return department;
    }
        public StringProperty levelProperty() {
        return level;
    }
    
    
}
