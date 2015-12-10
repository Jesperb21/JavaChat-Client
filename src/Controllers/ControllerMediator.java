package Controllers;

import Controllers.Chat.ChatChannelController;
import Controllers.Chat.ChatController;
import Controllers.Chat.ChatPanelController;
import Controllers.Modals.PrefController;
import Services.ServerSocketService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by JesperB on 09-12-2015.
 * A singleton that handles communication between controllers
 */
public class ControllerMediator {
    //region Singleton
    private static ControllerMediator instance = new ControllerMediator();

    public static ControllerMediator getInstance() {
        return instance;
    }
    //endregion

    public Controller mainController;
    public PrefController prefController;

    //chat controllers
    public ChatPanelController chatPanelController;
    public ChatController chatController;
    public HashMap<String, ChatChannelController> channels = new HashMap<>();

    //services
    public ServerSocketService SSS;
}
