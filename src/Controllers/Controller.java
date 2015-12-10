package Controllers;

import Controllers.Chat.ChatChannelController;
import Controllers.Modals.PrefController;
import javafx.event.ActionEvent;
import java.util.Random;

public class Controller{

    ControllerMediator mediator = ControllerMediator.getInstance();

    public Controller(){
        mediator.mainController = this;
    }


    //region menu actions

    //region channels actions

    public void addChannel(){
        Random random = new Random();
        byte[] randomBytes = new byte[256];
        random.nextBytes(randomBytes);
        mediator.chatPanelController.addChannel(randomBytes.toString());
    }

    //todo change it so that each channel has a close button in the ChatPanel, then call closeChannel from there
    public void closeChannel() {
        String name = mediator.chatPanelController.getSelectedChannel();
        ChatChannelController channel = mediator.channels.get(name);
        mediator.chatPanelController.closeChannel(name);
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
