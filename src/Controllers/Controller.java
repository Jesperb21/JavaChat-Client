package Controllers;

import Controllers.Chat.ChatChannelController;
import Controllers.Modals.PrefController;
import Models.Chat.Packages.MessagePackage;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.util.ResourceBundle;

public class Controller{

    ControllerMediator mediator = ControllerMediator.getInstance();

    public Controller(){
        mediator.mainController = this;
    }


    //region menu actions

    //region channels actions

    public void addChannel(){
        mediator.chatPanelController.addChannel();
    }

    //todo change it so that each channel has a close button in the ChatPanel, then call closeChannel from there
    public void closeChannel() {
        ChatChannelController channel = mediator.chatChannelControllers.get(0);
        mediator.chatPanelController.closeChannel(channel);
    }

    //endregion


    public void openPreferences(ActionEvent event) {

        PrefController prefCtrl = new PrefController();
        prefCtrl.openPref();
        System.out.println("Preference Modal Opened");
    }

    public void closeServer() {
        System.out.println("graceful exit activated");
        System.exit(0);
    }


    //endregion
}
