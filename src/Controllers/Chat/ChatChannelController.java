package Controllers.Chat;

import Controllers.ControllerMediator;
import Models.Chat.Packages.MessagePackage;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by JesperB on 09-12-2015.
 */
public class ChatChannelController implements Initializable{
    public TitledPane ChatChannelPane;
    private String name;
    public ArrayList<String> Users = new ArrayList<>();
    public ArrayList<String> Messages = new ArrayList<>();

    public ChatChannelController(){}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setName(String name){
        this.name = name;
        ChatChannelPane.setText(name);

    }
    public void addMessage(MessagePackage msgPackage){
        Messages.add(
                msgPackage.getAuthor()
                        .concat(": ")
                        .concat(msgPackage.getMessage())
                        .concat("\n"));
    }

}
