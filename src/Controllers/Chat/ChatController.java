package Controllers.Chat;

import Controllers.ControllerMediator;
import Models.Chat.Packages.MessagePackage;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.util.ResourceBundle;

/**
 * Created by JesperB on 09-12-2015.
 */
public class ChatController implements Initializable{
    public TextArea ChatTextArea;
    public TextField ChatMessageArea;
    public Button ChatSendButton;

    public ChatController(){
        ControllerMediator.getInstance().chatController = this;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChatSendButton.setOnAction(event -> {
            //todo send message via client service...
            // maybe, or just bypass that step on server and send directly on server socket
            // haven't quite figured out how this'll work yet

            //this is redundant... when the server receives the msgPackage it will be sent to all clients
            //including this one, if we add the msg when we receive the msgPackage again we can confirm that
            //it was actually sent to the server
            addMessage(new MessagePackage("Server", Date.from(Instant.now()), ChatMessageArea.getText()));
            ChatMessageArea.clear();
        });

        ChatMessageArea.setOnAction(event -> {
            System.out.println("Enter key was pressed in Chat Message Area! :O");
        });
    }

    public void addMessage(MessagePackage msgPackage){
        ChatTextArea.appendText(
                msgPackage.getAuthor()
                        .concat(": ")
                        .concat(msgPackage.getMessage())
                        .concat("\n"));
    }

}
