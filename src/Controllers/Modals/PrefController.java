package Controllers.Modals;

import Controllers.ControllerMediator;
import Services.ServerSocketService;
import Services.XML.XMLHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by JesperB on 08-12-2015.
 */
public class PrefController implements Initializable{

    public TextField txtPort;
    public TextField txtIPAddress;

    public int Port = 0;
    public String IPAddress = "127.0.0.1";

    public String FilePath = System.getProperty("user.dir");

    /*
     * Get the prefcontroller
     */
    public PrefController() {
        ControllerMediator.getInstance().prefController = this;
    }

    /*
     * Get and set the port, if there isn
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getPortFromXml();
        txtPort.setText(String.valueOf(Port));
    }

    //todo move this to a menubar controller
    /**
     * open a preferences modal
     */
    public void openPref() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../View/Modals/Preferences.fxml"));
            Parent root1 = null;
            root1 = (Parent) fxmlLoader.load();

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

    private void getPortFromXml(){
        XMLHandler xmlHandler = new XMLHandler();
        Port = xmlHandler.readPortFromXML();
    }

    public void Connect() {
        Port = Integer.parseInt(txtPort.getText());
        IPAddress = txtIPAddress.getText();

        try {
            Socket Client = new Socket(IPAddress, Port);
            OutputStream outToServer = Client.getOutputStream();

            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF("Hello from" + Client.getLocalSocketAddress());

            InputStream inFromServer = Client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            ControllerMediator.getInstance().chatController.ChatTextArea.appendText(String.valueOf(in) + "\n");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void StartServer() {
        // get port from txtfield
        Port = Integer.parseInt(txtPort.getText());

        //save port to xml
        XMLHandler xmlHandler = new XMLHandler();
        xmlHandler.WritePortToXML(Port);

        try {
            ServerSocketService s = new ServerSocketService(Port);
            s.StartListening();

            System.out.println("Server started on port: " + Port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void SaveLog() {
        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {
            // This block configure the logger with handler and formatter
            fh = new FileHandler(FilePath + "\\logs\\Chat_" + System.currentTimeMillis() + ".log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info(ControllerMediator.getInstance().chatController.ChatTextArea.getText());

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}