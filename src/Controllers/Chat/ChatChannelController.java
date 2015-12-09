package Controllers.Chat;

import Controllers.ControllerMediator;
import javafx.scene.control.TitledPane;

/**
 * Created by JesperB on 09-12-2015.
 */
public class ChatChannelController {
    public TitledPane ChatChannelPane;

    public ChatChannelController() {
        ControllerMediator.getInstance().chatChannelControllers.add(this);
    }
}
