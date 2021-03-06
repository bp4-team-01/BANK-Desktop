package com.hoekbank.bank.desktop.screens;

import com.google.gson.JsonObject;
import com.hoekbank.bank.desktop.MainApp;
import com.hoekbank.bank.desktop.api.API;
import com.hoekbank.bank.desktop.api.APIService;
import com.hoekbank.bank.desktop.helpers.AppDataContainer;
import com.hoekbank.bank.desktop.helpers.ScenesController;
import com.hoekbank.bank.desktop.resources.HabboBackButton;
import com.hoekbank.bank.desktop.resources.HabboButton;
import com.hoekbank.bank.desktop.resources.HabboInput;
import com.hoekbank.bank.desktop.ui.LoginScreenUI;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import javax.ws.rs.core.MultivaluedMap;
/**
 *
 * @author Kevin
 */
public class LoginScreen extends LoginScreenUI {

    public LoginScreen(Pane root) {
        setupMainUI();
        //Eventhandler knop inloggen met controle of alles is ingevuld
        loginButton.setOnAction(e->{
            //Controle of beide velden zijn ingevuld
            if (emailField.getText().isEmpty() && passwordField.getText().isEmpty()){
                showError("Geen gegevens ingevuld",
                        "Alle velden zijn leeg",
                        "Voer een e-mailadres en wachtwoord in");
            }
            //Controle of email is ingevuld
            else if(emailField.getText().isEmpty()){
                showError("Geen e-mailadres ingevuld",
                        "Er is geen e-mailadres ingevuld",
                        "Voer een geldig e-mailadres in");
            }
            //Controle of wachtwoord is ingevuld
            else if(passwordField.getText().isEmpty()){
                showError("Geen wachtwoord ingevuld",
                        "Er is geen wachtwoord ingevuld",
                        "Voer een wachtwoord in");
            }
            else{
                login();
            }
        });

        loginGridPane.setMinWidth(MainApp.screenWidth);
        loginGridPane.setMinHeight(MainApp.screenHeight);

        loginGridPane.setAlignment(Pos.CENTER);
        loginGridPane.setVgap(5);
        loginGridPane.setHgap(5);

        loginGridPane.add(imageView, 0, 0);
        loginGridPane.add(titleLabel, 0, 1);
        loginGridPane.add(emailLabel, 0, 2);
        loginGridPane.add(emailField, 0, 3);
        loginGridPane.add(passwordLabel, 0, 4);
        loginGridPane.add(passwordField, 0, 5);
        loginGridPane.add(loginButton, 0, 6);
        
        //Eventhandler knop inloggen met enter toets
        loginGridPane.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                loginButton.fire();
                ev.consume();
            }
        });

        root.getChildren().add(loginGridPane);
    }
    //Gegevens uit database opgalen om in te loggen
    private void login() {
        MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
        formData.add("email", emailField.getText());
        formData.add("password", passwordField.getText());

        JsonObject apiResponse = API.getInstance().post(APIService.USER_LOGIN, formData).getAsJsonObject();

        if(apiResponse.get("success") != null) {
            AppDataContainer.getInstance().setUserToken(apiResponse.get("Token").getAsString());
            AppDataContainer.getInstance().setUserID(apiResponse.get("UserID").getAsString());
            //Als email @hoekbank bevat, inloggen als medewerker
            if(apiResponse.get("Email").getAsString().contains("@hoekbank.tk")) {
                Pane employeePane = new Pane();
                new EmployeeDashboard(employeePane);
                ScenesController.setStage(employeePane);
            } else {
                //TODO: klant dashboard
                Pane userOverview = new Pane();
                new UserOverview(userOverview);
                ScenesController.setStage(userOverview);
            }

            showDashboard();
        } else {
            showError("",
                    "Inloggen mislukt",
                    apiResponse.get("message").getAsString());
        }
    }

    private void showDashboard() {

    }
    //Error message
    private void showError(String title, String header, String content) {
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setTitle(title);
        errorAlert.setHeaderText(header);
        errorAlert.setContentText(content);
        errorAlert.showAndWait();
    }
}
