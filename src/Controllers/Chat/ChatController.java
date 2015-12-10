package Controllers.Chat;

import Controllers.ControllerMediator;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
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
            SendMsgEventHandler();
        });

        ChatMessageArea.setOnAction(event -> {
            SendMsgEventHandler();
        });
    }
    private void SendMsgEventHandler(){
        ControllerMediator mediator = ControllerMediator.getInstance();
        mediator.CSS.SendMsg("Client",ChatMessageArea.getText());
        ChatMessageArea.clear();
    }
}
