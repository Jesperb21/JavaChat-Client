package Controllers.Modals;

import Controllers.ControllerMediator;
import Models.Chat.Packages.LoginPackage;
import Services.ClientSocketService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.Socket;

/**
 * Created by JesperB on 08-12-2015.
 */
public class PrefController{

    public TextField txtPort;
    public TextField txtIPAddress;
    public TextField txtName;

    public int Port = 33000;
    public String IPAddress = "192.168.1.10";
    public String Name = "Unnamed";

    /**
     * Get properties of the prefcontroller
     */
    public PrefController() {
        ControllerMediator.getInstance().prefController = this;
    }

    //todo move this to a menubar controller
    /**
     * open a preferences modal
     */
    public void openPref() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../View/Modals/Preferences.fxml"));
            Parent root1 = fxmlLoader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Preferences");
            stage.setScene(new Scene(root1));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Connect to a server
     */
    public void Connect() {
        if(txtPort.getText() == "" || txtIPAddress.getText() == "") {
            Port = Integer.parseInt(txtPort.getText());
            IPAddress = txtIPAddress.getText();
            Name = txtName.getText();
        }

        System.out.println(Port);
        System.out.println(IPAddress);
        System.out.println(Name);

        try {
            Socket Client = new Socket(IPAddress, Port);
            ClientSocketService css = new ClientSocketService(Client);
            css.TransferPckg(new LoginPackage(Name, "Default0"));
        } catch (IOException e) {
            System.out.println("Connection timed out..");
        }
    }
}