/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neslerp;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author DS
 */
public class AdminController implements Initializable {
    @FXML
    private Label admin_portal;
     @FXML
    private TextField username_textfield_admin;

    @FXML
    private PasswordField password_field_admin;

    @FXML
    private ComboBox<String> admin_box;

    @FXML
    private Button login_btn_admin;
    
    private double x=0;
    private double y=0;

     public void selectUser(){
        List<String> listU = new ArrayList<>();
        for(String data: Users.users){
            listU.add(data);
        }
        
        ObservableList listData = FXCollections.observableArrayList(listU);
        admin_box.setItems(listData);
    }
    
     private Connection connect;
     private PreparedStatement prepare;
     private ResultSet result;
     
     private Alert alert;
     
     private void errorMessage(String message){
             
         alert = new Alert(AlertType.ERROR);
         alert.setTitle("Error Message");
         alert.setHeaderText(null);
         alert.setContentText(message);
         alert.showAndWait();
     }
     
     public void successMessage(String message){
         alert = new Alert(AlertType.INFORMATION);
         alert.setTitle("Information message");
         alert.setHeaderText(null);
         alert.setContentText(message);
         alert.showAndWait();
     }
     
     public void loginAccount(){
         
         if(username_textfield_admin.getText().isEmpty() || password_field_admin.getText().isEmpty()){
             errorMessage("Please fill all blank fields");
         }else{
                String selectData = "SELECT * FROM ADMIN_USER WHERE USERNAME = ? AND PASSWORD = ?";
         
                connect = Database.connectDb();
         
            try{
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1,username_textfield_admin.getText());
                prepare.setString(2,password_field_admin.getText());
                
                result = prepare.executeQuery();
                
                if(result.next()){
                    successMessage("Login Successful!");
                    
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Dashboard");
                    stage.setScene(new Scene(root));
        
                     root.setOnMousePressed((MouseEvent event) ->{
                     x = event.getSceneX();
                     y = event.getSceneY();
                    });
        
                    root.setOnMouseDragged((MouseEvent event) ->{
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                    });
                
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.show();
                }else{errorMessage("Incorrect Username/Password!");}
                
            }catch(Exception e){e.printStackTrace();}
         }  
     }
    
    public void switchForm(){
        try{
            Parent root = null;
            if(admin_box.getSelectionModel().getSelectedItem().equals("User1 Portal")){
                root = FXMLLoader.load(getClass().getResource("User1Login.fxml"));
            }else if(admin_box.getSelectionModel().getSelectedItem().equals("User2 Portal")){
                root = FXMLLoader.load(getClass().getResource("User2Login.fxml"));
            }else if(admin_box.getSelectionModel().getSelectedItem().equals("User3 Portal")){
                root = FXMLLoader.load(getClass().getResource("User3Login.fxml"));
            }else if(admin_box.getSelectionModel().getSelectedItem().equals("User4 Portal")){
                root = FXMLLoader.load(getClass().getResource("User4Login.fxml"));
            }else if(admin_box.getSelectionModel().getSelectedItem().equals("User5 Portal")){
                root = FXMLLoader.load(getClass().getResource("User5Login.fxml"));
            }else{
                root = FXMLLoader.load(getClass().getResource("AdminUserLogin.fxml"));
            }
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
            admin_box.getScene().getWindow().hide();
        }catch(Exception e){e.printStackTrace();}
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       selectUser();
    }
}
