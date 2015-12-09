package Controllers.Chat;

import Controllers.ControllerMediator;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

import java.io.IOException;

/**
 * Created by JesperB on 09-12-2015.
 */
public class ChatPanelController {
    public Accordion chatList;

    public ChatPanelController() {
        ControllerMediator.getInstance().chatPanelController = this;
    }

    public void addChannel (){
        try {
            TitledPane channel = FXMLLoader.load(getClass().getResource("/View/CustomControls/ChatChannel.fxml"));
            chatList.getPanes().add(channel);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void closeChannel(ChatChannelController channel){
        ControllerMediator.getInstance().chatChannelControllers.remove(channel);
        chatList.getPanes().remove(channel.ChatChannelPane);
    }
}
