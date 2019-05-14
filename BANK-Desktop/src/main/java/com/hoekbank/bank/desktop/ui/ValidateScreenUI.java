package com.hoekbank.bank.desktop.ui;

import com.hoekbank.bank.desktop.BaseScreen;
import javafx.scene.control.*;

public abstract class ValidateScreenUI extends BaseScreen {

    public Button btnRegistreren, btnAfwijzen, btnTerug;
    public TextField txtBsn;
    public TextArea txtRedenAfwijzing;
    public Label lbTitle,lbBsn, lbBkrGoedkeuren, lbZwarteLijst, lbRedenAfwijzing;
    public CheckBox checkBkrJa, checkBkrNee, checkZwrtLstJa, checkZwrtLstNee;
    public Alert alertAfwijsWarning, alertAfwijsBevestigen, alertBtnRegisterBevestigen, alertBtnRegisterWarning;

    /**
     * @author Chahine
     */

    @Override
    protected void setupMainUI() {

        // Buttons
        btnRegistreren = new Button("Registreren >");
        btnAfwijzen = new Button("Afwijzen");
        btnTerug = new Button("< Terug");
        // TextFields
        txtBsn = new TextField();
        txtBsn.setPromptText("123456789");
        txtRedenAfwijzing = new TextArea();
        txtRedenAfwijzing.setPromptText("Reden?");
        // Labels
        lbTitle = new Label("Gebruiker Controleren");
        lbBsn = new Label("BSN / KvK");
        lbBkrGoedkeuren = new Label ("BKR Afgekeurd?");
        lbZwarteLijst = new Label("Aanwezig in zwarte lijst?");
        lbRedenAfwijzing = new Label("Reden van afwijzing?");
        // CheckBoxes
        checkBkrJa = new CheckBox("Ja");
        checkBkrNee = new CheckBox("Nee");
        checkZwrtLstJa = new CheckBox("Ja");
        checkZwrtLstNee = new CheckBox("Nee");
        // Alerts, Fout afhandelingen
        // Voor knop Afwijzen
        alertAfwijsWarning = new Alert(Alert.AlertType.WARNING);
        alertAfwijsBevestigen = new Alert(Alert.AlertType.CONFIRMATION);
        // Voor knop Registreren
        alertBtnRegisterWarning = new Alert(Alert.AlertType.WARNING);
        alertBtnRegisterBevestigen = new Alert(Alert.AlertType.CONFIRMATION);



    }
}