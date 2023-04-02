package com.example.egguser;

/**
 * @author 叶鸣镝
 * <p>
 * description:
 */
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable{
   @FXML
   private ImageView LOGOimageView;
   @FXML
   private Button closeButton;
   @FXML
   private Label registrationMessageLabel1;
   @FXML
   private TextField setPasswordField;
   @FXML
   private TextField confirmPasswordField;
   @FXML
   private Label confirmPasswordLabel;
   @FXML
   private TextField firstnameTextField;
   @FXML
   private TextField lastnameTextField;
   @FXML
   private TextField usernameTextField;


   @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File LOGOFile =new File("image/LOGO.png");
        Image LOGOImage=new Image(LOGOFile.toURI().toString());
       LOGOimageView.setImage(LOGOImage);

    }

    public void reCancelButtonOnAction(ActionEvent event){
        Stage stage=(Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();//关闭所有的，包括之前那界面
    }

    public void registerButtonOnAction(ActionEvent event){
       registrationMessageLabel1.setText("User has registered successfully!");
        if(setPasswordField.getText().equals(confirmPasswordField.getText())){
            registerUser();
            registrationMessageLabel1.setText("User has registered successfully!");
        }else {
            confirmPasswordLabel.setText("Password does not match");
        }


        }

    public void registerUser() {
       DatabaseConnection connectionNow =new DatabaseConnection();
       Connection connectionDB = connectionNow.getConnection();

       String firstname=firstnameTextField.getText();
       String lastname=lastnameTextField.getText();
       String username=usernameTextField.getText();
       String password=setPasswordField.getText();

       //这些字符串加起来就是一个完整的插入语句
       String insertFields="Insert Into user_account(lastname,firstname,username,password) values('";
       String insertValues=firstname+ "','"+lastname+ "','"+username+ "','"+password+"')";
       String insertToRegister=insertFields+insertValues;

       try{
           Statement statement=connectionDB.createStatement();
           statement.executeUpdate(insertToRegister);
           registrationMessageLabel1.setText("You has been registered successfully!");


       }catch (Exception e){
           e.printStackTrace();
           e.getCause();
       }


   }


}
