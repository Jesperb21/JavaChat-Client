package Controllers.Chat;

import Controllers.ControllerMediator;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

/**
 * Created by JesperB on 09-12-2015.
 */
public class ChatPanelController {
    public Accordion chatList;
    private ControllerMediator mediator = ControllerMediator.getInstance();

    public ChatPanelController() {
        mediator.chatPanelController = this;
    }

    public void addChannel (String name){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CustomControls/ChatChannel.fxml"));
            TitledPane channel = loader.load();
            ChatChannelController controller = loader.getController();
            channel.setId(name);
            controller.setName(name);

            mediator.channels.put(name, controller);

            chatList.getPanes().add(channel);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void closeChannel(String channelName){
        TitledPane pane = mediator.channels.get(channelName).ChatChannelPane;
        chatList.getPanes().remove(pane);
        mediator.channels.remove(channelName);
    }
    public void closeSelectedChannel(){
        closeChannel(getSelectedChannel());
    }
    public String getSelectedChannel(){
        return chatList.getExpandedPane().getId();
    }
}
