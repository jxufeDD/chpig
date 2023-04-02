package com.example.egguser;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.TextStyle;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField enterPasswordField;



    //用于初始化图像的函数，如果不用就无法显示图像
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile =new File("image/login.png");
        Image brandingImage=new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File  lockFile=new File("image/LOGO.png");
        Image lockImage=new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);

    }

    public void loginButtonOnAction(ActionEvent event){//这个action应该用在login的按钮里面，而不是对标的
        loginMessageLabel.setText("You try to login");
        if(usernameTextField.getText().isBlank()==false && enterPasswordField.getText().isBlank()==false){//如果isBlank为false，那么字符串不为空
           validateLogin();
        }else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){
        DatabaseConnection databaseConnection=new DatabaseConnection();
        Connection connection=databaseConnection.getConnection();

        String verifyLogin="select count(1) from user_account where username='" + usernameTextField.getText() +"' AND password ='" + enterPasswordField.getText() +"'";

        try{
            Statement statement=connection.createStatement();
            ResultSet queryResult =statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1)==1){//如果是查询结果有1，那么则存在一条这样的记录
                   // loginMessageLabel.setText("Congratulations!");  //开始用来标记数据库中的可以使用
                    createAccountForm();
                }else {
                    loginMessageLabel.setText("invalid Login. Please try again");
                }
            }


        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createAccountForm(){
        try {
            Parent root =FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage =new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root,582,687));
            registerStage.show();
        }catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

}