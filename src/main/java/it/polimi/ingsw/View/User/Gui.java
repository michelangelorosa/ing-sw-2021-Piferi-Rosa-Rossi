package it.polimi.ingsw.View.User;
import it.polimi.ingsw.View.Client.Client;
import it.polimi.ingsw.View.Client.ClientConnection;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Gui extends Application{

    public static final CountDownLatch latch = new CountDownLatch(1);
    public static Gui gui = null;
    @FXML private TextField port;
    @FXML private TextField server;
    private Font Baskerville;
    private Font Dominican;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Baskerville=Font.loadFonts(getClass().getResource("Assets/Fonts/Baskerville.ttc").toExternalForm(),20)[0];
        Dominican=Font.loadFont(getClass().getResource("Assets/Fonts/Dominican.ttf").toExternalForm(),38);

        ClientExceptionHandler gui= new ClientExceptionHandler();
        gui.visualType(false);

        Parent root;
        root = FXMLLoader.load(getClass().getResource("Assets/Fxml/Intro.fxml"));
        //Creates a scene object
        Scene scene = new Scene(root);

        primaryStage.setTitle("Masters Of Renaissance");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        GuiInitController.setStage(primaryStage);
    }

    public static Gui waitForStartUp() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return gui;
    }

    public static void setStartUpTest(Gui startUpTest0) {
        gui = startUpTest0;
        latch.countDown();
    }


    public Gui(){
        setStartUpTest(this);
    }

    /**
     * Quits the game, invoked if the user clicks on "Exit" button
     * @param event
     */
    public void close(ActionEvent event){
        System.exit(0);
    }

    /**
     * Takes the parameters from the fields, validates them and tries to create a new Socket
     * @param event
     */
    public void addressPort(ActionEvent event) throws Exception {
        ClientExceptionHandler gui = new ClientExceptionHandler();
        gui.visualType(false);
        try{
            int portNo=Integer.parseInt(port.getText());
            if(gui.addressValidator(server.getText()))
                if(gui.portValidator(portNo)){
                    ArrayList<Object> initial = new ArrayList<>();
                    initial.add(server.getText());
                    initial.add(portNo);
                    Client client = new Client((String) initial.get(0),(int) initial.get(1));
                    ArrayList<Object> arrayList = client.startUp(initial);
                    if(arrayList.size()==2){
                        GuiInitController guiInitController;
                        guiInitController=new GuiInitController(client,(ClientConnection) arrayList.get(1),gui);
                        guiInitController.nameSelection();
                    }else
                        System.out.println("Connection Error");
                }
        }catch (NumberFormatException e){
            gui.guiError("Port number not valid!");
        }
    }

}
