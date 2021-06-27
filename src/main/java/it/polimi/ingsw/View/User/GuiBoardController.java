package it.polimi.ingsw.View.User;

import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.View.Client.Client;
import it.polimi.ingsw.View.Client.ClientConnection;
import it.polimi.ingsw.View.ReducedModel.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
/**
 * GuiBoardController handles all the gui interactions related with the user's interaction with the Game via the Board.
 */

public class GuiBoardController extends GuiInitController{
    private Stage cardPicker;
    private Stage chooser;
    private Stage market;
    private Stage leaderSelection;
    private Stage resourcePicker;
    private Stage xtraBoard;
    @FXML private Text cardTitle;
    @FXML private GridPane cardPane;
    private ImageView[][] card;
    private int cardColumn;
    private int cardRow;
    protected ActionEvent event;
    private RedDevelopmentCard temporaryCard;
    @FXML GridPane marketPane;
    @FXML RadioButton marketRow1;
    @FXML RadioButton marketRow2;
    @FXML RadioButton marketRow3;
    @FXML RadioButton marketColumn1;
    @FXML RadioButton marketColumn2;
    @FXML RadioButton marketColumn3;
    @FXML RadioButton marketColumn4;
    @FXML Button submitMarket;
    @FXML ImageView extraMarble;
    private ImageView[][] activeMarket;
    @FXML private Text resourcesTitle;
    @FXML private Label remStones;
    @FXML private Label remShields;
    @FXML private Label remCoins;
    @FXML private Label remServants;
    @FXML private Label strongboxStones;
    @FXML private Label strongboxShields;
    @FXML private Label strongboxCoins;
    @FXML private Label strongboxServants;
    @FXML private Button resetPayment;
    @FXML private Button cancelPayment;
    @FXML private Button confirmPayment;
    @FXML private ImageView inkwell;
    @FXML private ImageView warehouse1;
    @FXML private ImageView warehouse21;
    @FXML private ImageView warehouse22;
    @FXML private ImageView warehouse31;
    @FXML private ImageView warehouse32;
    @FXML private ImageView warehouse33;
    @FXML private ImageView leader1;
    @FXML private ImageView leader2;
    @FXML private ImageView leader1resource1;
    @FXML private ImageView leader1resource2;
    @FXML private ImageView leader2resource1;
    @FXML private ImageView leader2resource2;
    @FXML private ImageView dev11;
    @FXML private ImageView dev12;
    @FXML private ImageView dev13;
    @FXML private ImageView dev21;
    @FXML private ImageView dev22;
    @FXML private ImageView dev23;
    @FXML private ImageView dev31;
    @FXML private ImageView dev32;
    @FXML private ImageView dev33;
    @FXML private ImageView shields;
    @FXML private ImageView servants;
    @FXML private ImageView stones;
    @FXML private ImageView coins;
    @FXML private ImageView firstFloor;
    @FXML private ImageView secondFloor;
    @FXML private ImageView thirdFloor;
    @FXML private Button button;
    @FXML protected Button produciton;
    @FXML protected Button nextTurn;
    @FXML protected Button payResBtn;
    @FXML protected Button cardTableBtn;
    @FXML protected Button marketBtn;
    @FXML protected Button storeResBtn;
    @FXML protected ImageView strongboxStonesClickable;
    @FXML protected ImageView strongboxShieldsClickable;
    @FXML protected ImageView strongboxServantsClickable;
    @FXML protected ImageView strongboxCoinsClickable;
    @FXML protected GridPane faithPane;
    @FXML private GridPane otherBoards;
    private Boolean[] productionOutput = new Boolean[3];
    private int resourceNumber;

    /**
     * Constructor for the GuiBoardController class
     * @param client                    the client for getting the UI
     * @param clientConnection          the clientConnection for network handling
     * @param clientExceptionHandler    the clientExcepionHandler
     */
    protected GuiBoardController(Client client,ClientConnection clientConnection,ClientExceptionHandler clientExceptionHandler) {
        super(client,clientConnection,clientExceptionHandler);
    }

    /**
     * Gets the initial number of resources and sets them in the class
     */
    private void getInitResource(){
        this.resourceNumber=this.client.getUserInteraction().getInitNumberOfResources();
    }

    /**
     * Method called when the user gets an inital resource.
     * When called the resource number is decreased and the labels are set accordingly
     */
    private void gotInitResource(){
        resourceNumber--;
        remServants.setText(String.valueOf(resourceNumber));
        remStones.setText(String.valueOf(resourceNumber));
        remCoins.setText(String.valueOf(resourceNumber));
        remShields.setText(String.valueOf(resourceNumber));
    }

    /**
     * Method called when the user successfully gets a resource.
     * When called the resource number is decreased and the labels are set accordingly
     */
    private void gotResource(String resource){
        resourceNumber--;
        ResourceType resourceType=stringToResource(resource);
        if (resourceType.equals(ResourceType.SERVANTS))
            remServants.setText(String.valueOf((Integer.parseInt(remServants.getText())-1)));
        else if (resourceType.equals(ResourceType.COINS))
            remCoins.setText(String.valueOf((Integer.parseInt(remCoins.getText())-1)));
        else if (resourceType.equals(ResourceType.SHIELDS))
            remShields.setText(String.valueOf((Integer.parseInt(remShields.getText())-1)));
        else if (resourceType.equals(ResourceType.STONES))
            remStones.setText(String.valueOf((Integer.parseInt(remStones.getText())-1)));
    }

    /**
     * Method used to quickly get One's player reference
     * @return  the player whose board is opened
     */
    protected Player myPlayer(){
        return this.client.getUserInteraction().getGame().getMyPlayer();
    }

    /**
     * Allows the player to store the resources in his depots, if it has any, by drag-dropping for both the in-game phase and the initial resources.
     * Once there are 0 resources left to store or when the player ends the transaction beforehand the board is reloaded to update the resources shown.
     * @param initialResources      If the Player gets the initial resources or from other sources
     * @throws IOException          If there are errors while loading the FXML
     */
    protected void getResources(boolean initialResources) throws IOException {
        HashMap<Integer, ArrayList<ResourceType>> depotResource = new HashMap<>();
        ArrayList<ResourceType> firstDepot = new ArrayList<>();
        ArrayList<ResourceType> secondDepot = new ArrayList<>();
        ArrayList<ResourceType> thirdDepot = new ArrayList<>();

        Parent getResources;
        if(initialResources){
            getInitResource();
            if(resourceNumber<=0){
                displayError("No resources to chose from");
                depotResource.put(0, new ArrayList<>());
                depotResource.put(1, new ArrayList<>());
                depotResource.put(2, new ArrayList<>());
                sendAction(new InitChooseResources(depotResource));
                return;
            }
            String remaining = String.valueOf(resourceNumber);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/GetResources.fxml"));
            loader.setController(this);
            getResources = loader.load();
            Scene scene = new Scene(getResources);

            setResourceData(getResourceData(myPlayer()));

            Stage window = getStage();
            window.setScene(scene);

            resourcesTitle.setText("Drag any resource on the warehouse depot");
            remStones.setText(remaining);             remCoins.setText(remaining);
            remShields.setText(remaining);            remServants.setText(remaining);
        }
        else{
            if(this.client.getUserInteraction().getGame().getMyPlayer().getTemporaryResources().isEmpty()) {
                displayError("No more resources to add");
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/GetResources.fxml"));
            loader.setController(this);
            getResources = loader.load();
            resourcePicker = new Stage();

            resourcePicker.initModality(Modality.APPLICATION_MODAL);
            resourcePicker.setTitle("Store Resources");

            RedResourceStack resourcesToAdd = myPlayer().getTemporaryResources();

            resourceNumber=resourcesToAdd.getResource(ResourceType.STONES)+resourcesToAdd.getResource(ResourceType.SHIELDS)+resourcesToAdd.getResource(ResourceType.COINS)+resourcesToAdd.getResource(ResourceType.SERVANTS);

            resourcesTitle.setText("Drag the resources on the warehouse depot");
            remStones.setText(String.valueOf(resourcesToAdd.getResource(ResourceType.STONES)));                 remCoins.setText(String.valueOf(resourcesToAdd.getResource(ResourceType.COINS)));
            remShields.setText(String.valueOf(resourcesToAdd.getResource(ResourceType.SHIELDS)));               remServants.setText(String.valueOf(resourcesToAdd.getResource(ResourceType.SERVANTS)));
            refresh();
        }

        shields.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected (shields)");

                if(initialResources||getClientConnection().getClient().getUserInteraction().getGame().getMyPlayer().getTemporaryResources().hasResource(ResourceType.SHIELDS)){
                    /* allow any transfer mode */
                    Dragboard db = shields.startDragAndDrop(TransferMode.ANY);
                    /* put a string on dragboard */
                    ClipboardContent content = new ClipboardContent();
                    content.putString("shields");
                    db.setContent(content);
                }else
                    displayError("You have no more shields to store");

                event.consume();
            }
        });
        servants.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected (servants)");

                if(initialResources||getClientConnection().getClient().getUserInteraction().getGame().getMyPlayer().getTemporaryResources().hasResource(ResourceType.SERVANTS)){
                    /* allow any transfer mode */
                Dragboard db = servants.startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString("servants");
                db.setContent(content);
                }else
                    displayError("You have no more servants to store");

                event.consume();
            }
        });
        coins.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected (coins)");

                if(initialResources||getClientConnection().getClient().getUserInteraction().getGame().getMyPlayer().getTemporaryResources().hasResource(ResourceType.COINS)){
                    /* allow any transfer mode */
                Dragboard db = coins.startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString("coins");
                db.setContent(content);}else
                    displayError("You have no more coins to store");

                event.consume();
            }
        });
        stones.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected (stones)");

                if(initialResources||getClientConnection().getClient().getUserInteraction().getGame().getMyPlayer().getTemporaryResources().hasResource(ResourceType.STONES)){
                    /* allow any transfer mode */
                Dragboard db = stones.startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString("stones");
                db.setContent(content);}else
                    displayError("You have no more stones to store");

                event.consume();
            }
        });

        firstFloor.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 * and if it has a string data */
                if (event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });
        secondFloor.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 * and if it has a string data */
                if (event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });
        thirdFloor.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 * and if it has a string data */
                if (event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        firstFloor.setOnDragDropped(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                System.out.println("onDragDropped");
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    String resource = db.getString();
                    if(initialResources){
                        if(thirdDepot.isEmpty())
                        {
                        thirdDepot.add(stringToResource(resource));
                        warehouse1.setImage(getImage(stringToResource(resource)));
                        gotInitResource();
                        if(resourceNumber==0)
                        {
                            depotResource.put(0, firstDepot);
                            depotResource.put(1, secondDepot);
                            depotResource.put(2, thirdDepot);
                            sendAction(new InitChooseResources(depotResource));
                            return;
                        }
                        }else
                            displayError("This depot is full!");
                        success=true;
                    }else{
                        success=addResource(2,resource);
                        if(success){
                            warehouse1.setImage(getImage(stringToResource(resource)));
                            gotResource(resource);
                            if(resourceNumber==0)
                            {
                                sendAction(new EndMarket());
                                marketBtn.setText("Open Market");
                                refresh();
                                resourcePicker.close();
                                try {
                                    board();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            setResourceData(getResourceData(client.getUserInteraction().getGame().getMyPlayer()));

                        }

                    }
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);
                if(success){
                    System.out.println("Set On drag dropped success for resource "+db.getString());
                }
                event.consume();
            }
        });
        secondFloor.setOnDragDropped(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                System.out.println("onDragDropped");
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    String resource = db.getString();
                    if(initialResources){
                        if((secondDepot.contains(stringToResource(resource))&&secondDepot.size()==1)||secondDepot.isEmpty()){
                        secondDepot.add(stringToResource(resource));
                        if(secondDepot.size()==0)
                            warehouse21.setImage(getImage(stringToResource(resource)));
                        else
                            warehouse22.setImage(getImage(stringToResource(resource)));
                        gotInitResource();
                        if(resourceNumber==0)
                        {
                            depotResource.put(2, firstDepot);
                            depotResource.put(1, secondDepot);
                            depotResource.put(0, thirdDepot);
                            sendAction(new InitChooseResources(depotResource));
                            return;
                        }
                    }else
                        displayError("You can't add this resource here!");
                        success=true;
                    }else{
                        success=addResource(1,resource);
                        if(success)
                            gotResource(resource);
                        if(resourceNumber==0)
                        {
                            sendAction(new EndMarket());
                            marketBtn.setText("Open Market");
                            refresh();
                            resourcePicker.close();
                            try {
                                board();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        setResourceData(getResourceData(client.getUserInteraction().getGame().getMyPlayer()));
                    }
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);
                if(success){
                    System.out.println("Set On drag dropped success for resource "+db.getString());
                }
                event.consume();
            }
        });
        thirdFloor.setOnDragDropped(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                System.out.println("onDragDropped");
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    String resource = db.getString();
                    if(initialResources){
                        if((firstDepot.contains(stringToResource(resource))&&firstDepot.size()<3)||firstDepot.isEmpty()){
                        firstDepot.add(stringToResource(resource));
                        if(firstDepot.size()==0)
                        warehouse31.setImage(getImage(stringToResource(resource)));
                        else if(firstDepot.size()==1)
                            warehouse32.setImage(getImage(stringToResource(resource)));
                        else
                            warehouse33.setImage(getImage(stringToResource(resource)));
                        gotInitResource();
                        if(resourceNumber==0)
                        {
                            depotResource.put(2, firstDepot);
                            depotResource.put(1, secondDepot);
                            depotResource.put(0, thirdDepot);
                            sendAction(new InitChooseResources(depotResource));
                            return;
                        }
                        }else
                            displayError("You can't add this resource here!");
                        success=true;
                    }else{
                        success=addResource(0,resource);
                        if(success)
                            gotResource(resource);
                        if(resourceNumber==0)
                        {
                            sendAction(new EndMarket());
                            marketBtn.setText("Open Market");
                            resourcePicker.close();
                            try {
                                board();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        setResourceData(getResourceData(client.getUserInteraction().getGame().getMyPlayer()));
                    }
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);
                if(success){
                    System.out.println("Set On drag dropped success for resource "+db.getString());
                }
                event.consume();
            }
        });

        confirmPayment.setOnAction(e->{
            sendAction(new EndMarket());
            marketBtn.setText("Open Market");
            resourcePicker.close();
            try {
                board();
            } catch (IOException f) {
                f.printStackTrace();
            }
        });

    if(!initialResources){
        Scene scene = new Scene(getResources);
        resourcePicker.setScene(scene);
        resourcePicker.showAndWait();
        }
    }

    /**
     * Allows the player to pay what he has to pay by clicking on the resource he wants to pay with.
     * Once what is due is paid the board is reloaded to update the resources shown.
     * @throws IOException          If there's a problem loading the FXML
     */
    @FXML
    protected void payResource() throws IOException{
        Player currentPlayer = this.client.getUserInteraction().getGame().getMyPlayer();
        if(!(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_CARD)||currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_PRODUCTION))){
            displayError("You don't have anything to pay now.");
            return;
        }
            Parent payResource;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/PayResource.fxml"));
            loader.setController(this);
            payResource = loader.load();
            Stage payResources = new Stage();
            payResources.initModality(Modality.APPLICATION_MODAL);
            payResources.setTitle("Pay Resource");
            RedResourceStack resourcesToPay = this.client.getUserInteraction().getGame().getMyPlayer().getTemporaryResources();

            Scene scene = new Scene(payResource);

            resourceNumber=resourcesToPay.getResource(ResourceType.STONES)+resourcesToPay.getResource(ResourceType.SHIELDS)+resourcesToPay.getResource(ResourceType.COINS)+resourcesToPay.getResource(ResourceType.SERVANTS);

            remCoins.setText(String.valueOf(resourcesToPay.getResource(ResourceType.COINS)));
            remServants.setText(String.valueOf(resourcesToPay.getResource(ResourceType.SERVANTS)));
            remShields.setText(String.valueOf(resourcesToPay.getResource(ResourceType.SHIELDS)));
            remStones.setText(String.valueOf(resourcesToPay.getResource(ResourceType.STONES)));

            refresh();

        if(warehouse1.getImage()!=null)
            warehouse1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(resourcesToPay.hasResource(currentPlayer.getWarehouse().getWarehouseDepots()[2].getResourceType())){
                        if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_CARD))
                            sendAction(new PayResourceBuyCard(true,2,ResourceType.NONE));
                        else if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_PRODUCTION))
                            sendAction(new PayResourceProduction(true,2,ResourceType.NONE));
                        gotResource(currentPlayer.getWarehouse().getWarehouseDepots()[2].getResourceType().toString());
                        warehouse1.setImage(null);
                        refresh();
                        if(resourceNumber==0)
                        {
                            payResources.close();
                            afterPayment();
                        }
                    }else
                    {
                        displayError("This resource is not required");
                    }
                }
            });
        if(warehouse21.getImage()!=null)
            warehouse21.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(resourcesToPay.hasResource(currentPlayer.getWarehouse().getWarehouseDepots()[1].getResourceType())){
                        if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_CARD))
                            sendAction(new PayResourceBuyCard(true,1,ResourceType.NONE));
                        else if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_PRODUCTION))
                            sendAction(new PayResourceProduction(true,1,ResourceType.NONE));
                        gotResource(currentPlayer.getWarehouse().getWarehouseDepots()[1].getResourceType().toString());
                        warehouse21.setImage(null);
                        refresh();
                        if(resourceNumber==0)
                        {
                            payResources.close();
                            afterPayment();
                        }
                    }else
                    {
                        displayError("This resource is not required");
                    }
                }
            });
        if(warehouse22.getImage()!=null)
            warehouse22.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(resourcesToPay.hasResource(currentPlayer.getWarehouse().getWarehouseDepots()[1].getResourceType())){
                        if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_CARD))
                            sendAction(new PayResourceBuyCard(true,1,ResourceType.NONE));
                        else if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_PRODUCTION))
                            sendAction(new PayResourceProduction(true,1,ResourceType.NONE));
                        gotResource(currentPlayer.getWarehouse().getWarehouseDepots()[1].getResourceType().toString());
                        warehouse22.setImage(null);
                        refresh();
                        if(resourceNumber==0)
                        {
                            payResources.close();
                            afterPayment();
                        }
                    }else
                    {
                        displayError("This resource is not required");
                    }
                }
            });
        if(warehouse31.getImage()!=null)
            warehouse31.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(resourcesToPay.hasResource(currentPlayer.getWarehouse().getWarehouseDepots()[0].getResourceType())){
                        if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_CARD))
                            sendAction(new PayResourceBuyCard(true,0,ResourceType.NONE));
                        else if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_PRODUCTION))
                            sendAction(new PayResourceProduction(true,0,ResourceType.NONE));
                        gotResource(currentPlayer.getWarehouse().getWarehouseDepots()[0].getResourceType().toString());
                        warehouse31.setImage(null);
                        refresh();
                        if(resourceNumber==0)
                        {
                            payResources.close();
                            afterPayment();
                        }
                    }else
                    {
                        displayError("This resource is not required");
                    }
                }
            });
        if(warehouse32.getImage()!=null)
            warehouse32.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(resourcesToPay.hasResource(currentPlayer.getWarehouse().getWarehouseDepots()[0].getResourceType())){
                        if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_CARD))
                            sendAction(new PayResourceBuyCard(true,0,ResourceType.NONE));
                        else if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_PRODUCTION))
                            sendAction(new PayResourceProduction(true,0,ResourceType.NONE));
                        gotResource(currentPlayer.getWarehouse().getWarehouseDepots()[0].getResourceType().toString());
                        warehouse32.setImage(null);
                        refresh();
                        if(resourceNumber==0)
                        {
                            payResources.close();
                            afterPayment();
                        }
                    }else
                    {
                        displayError("This resource is not required");
                    }
                }
            });
        if(warehouse33.getImage()!=null)
            warehouse33.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(resourcesToPay.hasResource(currentPlayer.getWarehouse().getWarehouseDepots()[0].getResourceType())){
                        if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_CARD))
                            sendAction(new PayResourceBuyCard(true,0,ResourceType.NONE));
                        else if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_PRODUCTION))
                            sendAction(new PayResourceProduction(true,0,ResourceType.NONE));
                        gotResource(currentPlayer.getWarehouse().getWarehouseDepots()[0].getResourceType().toString());
                        warehouse33.setImage(null);
                        refresh();
                        if(resourceNumber==0)
                        {
                            payResources.close();
                            afterPayment();
                        }
                    }else
                    {
                        displayError("This resource is not required");
                    }
                }
            });
        if(Integer.parseInt(strongboxStones.getText())>0)
            strongboxStonesClickable.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(resourcesToPay.hasResource(ResourceType.STONES)&&currentPlayer.getStrongbox().getStoredResources().hasResource(ResourceType.STONES)){
                        if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_CARD))
                            sendAction(new PayResourceBuyCard(false,0,ResourceType.STONES));
                        else if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_PRODUCTION))
                            sendAction(new PayResourceProduction(false,0,ResourceType.STONES));
                        gotResource("stones");
                        refresh();
                        if(resourceNumber==0)
                        {
                            payResources.close();
                            afterPayment();
                        }
                    }else
                    {
                        displayError("This resource is not required");
                    }
                }
            });
        if(Integer.parseInt(strongboxShields.getText())>0)
            strongboxShieldsClickable.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(resourcesToPay.hasResource(ResourceType.SHIELDS)&&currentPlayer.getStrongbox().getStoredResources().hasResource(ResourceType.SHIELDS)){
                        if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_CARD))
                            sendAction(new PayResourceBuyCard(false,0,ResourceType.SHIELDS));
                        else if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_PRODUCTION))
                            sendAction(new PayResourceProduction(false,0,ResourceType.SHIELDS));
                        gotResource("shields");
                        refresh();
                        if(resourceNumber==0)
                        {
                            payResources.close();
                            afterPayment();
                        }
                    }else
                    {
                        displayError("This resource is not required");
                    }
                }
            });
        if(Integer.parseInt(strongboxServants.getText())>0)
            strongboxServantsClickable.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(resourcesToPay.hasResource(ResourceType.SERVANTS)&&currentPlayer.getStrongbox().getStoredResources().hasResource(ResourceType.SERVANTS)){
                        if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_CARD))
                            sendAction(new PayResourceBuyCard(false,0,ResourceType.SERVANTS));
                        else if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_PRODUCTION))
                            sendAction(new PayResourceProduction(false,0,ResourceType.SERVANTS));
                        gotResource("servants");
                        refresh();
                        if(resourceNumber==0)
                        {
                            payResources.close();
                            afterPayment();
                        }
                    }else
                    {
                        displayError("This resource is not required");
                    }
                }
            });
        if(Integer.parseInt(strongboxCoins.getText())>0)
            strongboxCoinsClickable.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(resourcesToPay.hasResource(ResourceType.COINS)&&currentPlayer.getStrongbox().getStoredResources().hasResource(ResourceType.COINS)){
                        if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_CARD))
                            sendAction(new PayResourceBuyCard(false,0,ResourceType.COINS));
                        else if(currentPlayer.getPossibleActions().contains(ActionType.PAY_RESOURCE_PRODUCTION))
                            sendAction(new PayResourceProduction(false,0,ResourceType.COINS));
                        gotResource("coins");
                        refresh();
                        if(resourceNumber==0)
                        {
                            payResources.close();
                            afterPayment();
                        }
                    }else
                    {
                        displayError("This resource is not required");
                    }
                }
            });

        payResources.setScene(scene);
        payResources.showAndWait();
    }

    /**
     * Called after a payment is completed for reloading the board and changing the text of the button if the user has to choose where to place a card
     */
    private void afterPayment(){
        try {
            board();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(myPlayer().getPossibleActions().contains(ActionType.CHOOSE_CARD_SLOT)) {
            cardTableBtn.setText("Choose card slot");
        }
    }

    @FXML
    private Text cardTitle1,cardTitle2,cardTitle3;

    @FXML
    private RadioButton slotZero,slotOne,slotTwo,slotThree,slotFour,slotFive;
    @FXML
    private GridPane chooserPane;

    /**
     * This method handles the interaction for when the user has to choose something, depending on his possible action:
     * ‣ Choose Card Slot:      for choosing in which card slot to place the card
     * ‣ Activate Production:   for choosing which production power to activate and if the Basic Production power it's activated
     *                          the user is then prompted to choose which resources to activate the basic production which
     * ‣ Choose Production      for choosing the production output if a jolly production output has been activated
     *   output:
     * @throws IOException      If there's a problem loading the FXML
     */
    private void choose() throws IOException {
        Parent choose;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/Chooser.fxml"));
        loader.setController(this);
        choose= loader.load();
        chooser = new Stage();
        chooser.initModality(Modality.APPLICATION_MODAL);
        chooser.setTitle("Choose an option");

        if(myPlayer().getPossibleActions().contains(ActionType.CHOOSE_CARD_SLOT)){

            cardTitle.setText("Choose a development card slot");
            ImageView card1,card2,card3,cardToAdd;

            submitMarket.setDisable(true);

            chooserPane.add(cardToAdd = new ImageView(getImage(temporaryCard.getCardId(),true)),5,0);
            cardToAdd.setFitHeight(250);
            cardToAdd.setFitWidth(165);

            if(myPlayer().getSlots().getSlots()[0].isEmpty())
                cardTitle1.setText("First\nSlot");
            else{
                chooserPane.add(card1 = new ImageView(getImage(myPlayer().getSlots().getSlots()[0].getFirstCard().getCardId(),true)),1,0);
                card1.setFitHeight(250);
                card1.setFitWidth(165);
            }
                if(myPlayer().getSlots().getSlots()[1].isEmpty())
                    cardTitle2.setText("Second\nSlot");
                else{
                    chooserPane.add(card2 = new ImageView(getImage(myPlayer().getSlots().getSlots()[1].getFirstCard().getCardId(),true)),2,0);
                    card2.setFitHeight(250);
                    card2.setFitWidth(165);
                }
            if(myPlayer().getSlots().getSlots()[2].isEmpty())
                cardTitle3.setText("Third\nSlot");
            else {
                chooserPane.add(card3 = new ImageView(getImage(myPlayer().getSlots().getSlots()[2].getFirstCard().getCardId(),true)),3,0);
                card3.setFitHeight(250);
                card3.setFitWidth(165);
            }


            if(myPlayer().getSlots().getSlots()[0].canAdd(temporaryCard)||myPlayer().getSlots().getSlots()[0].isEmpty())
                slotOne.setDisable(false);
            else
                slotOne.setDisable(true);
            if(myPlayer().getSlots().getSlots()[1].canAdd(temporaryCard)||myPlayer().getSlots().getSlots()[1].isEmpty())
                slotTwo.setDisable(false);
            else
                slotTwo.setDisable(true);
            if(myPlayer().getSlots().getSlots()[2].canAdd(temporaryCard)||myPlayer().getSlots().getSlots()[2].isEmpty())
                slotThree.setDisable(false);
                    else
                slotThree.setDisable(true);

            System.out.println("temporary Card Id is: "+temporaryCard.getCardId());
                slotOne.setOnAction(event -> {
                if(slotOne.isArmed()){
                    slotTwo.setSelected(false);
                    slotThree.setSelected(false);
                    submitMarket.setDisable(!slotOne.isSelected());
                }
            });
                slotTwo.setOnAction(event -> {
                if(slotTwo.isArmed()){
                    slotOne.setSelected(false);
                    slotThree.setSelected(false);
                    submitMarket.setDisable(!slotTwo.isSelected());
                }
            });
                slotThree.setOnAction(event -> {
                if(slotThree.isArmed()){
                    slotTwo.setSelected(false);
                    slotOne.setSelected(false);
                    submitMarket.setDisable(!slotThree.isSelected());
                }
            });

            submitMarket.setOnAction(event -> {
                cardTableBtn.setText("Open Card Table");
                int slot=-9;
                if(slotOne.isSelected())
                    slot=0;
                if(slotTwo.isSelected())
                    slot=1;
                if(slotThree.isSelected())
                    slot=2;
                System.out.println("Slot value is "+slot);
                sendAction(new ChooseCardSlot(slot));
                refresh();
                chooser.close();
            });
        }
        if(myPlayer().getPossibleActions().contains(ActionType.ACTIVATE_PRODUCTION)){

            cardTitle.setText("Choose the productions to activate");
            AtomicReference<Boolean> basicProduction = new AtomicReference<>(false);
            ImageView card0,card1,card2,card4,card5;

            ArrayList<ResourceType> productionInputs = new ArrayList<>();

            Boolean activations[] = new Boolean[5];
            cardTitle3.setText("Basic\nProd");
            if(myPlayer().getSlots().getSlots()[0].isEmpty())
            {
                chooserPane.add(card0 = new ImageView(getImage(60,false)),0,0);
                card0.setPreserveRatio(true);
                card0.setFitHeight(250);
                card0.setFitWidth(165);
                slotZero.setDisable(true);
            }else{
                chooserPane.add(card0 = new ImageView(getImage(myPlayer().getSlots().getSlots()[0].getFirstCard().getCardId(),true)),0,0);
                card0.setPreserveRatio(true);
                card0.setFitHeight(250);
                card0.setFitWidth(165);
            }
            if(myPlayer().getSlots().getSlots()[1].isEmpty())
            {
                chooserPane.add(card1 = new ImageView(getImage(60,false)),1,0);
                card1.setPreserveRatio(true);
                card1.setFitHeight(250);
                card1.setFitWidth(165);
                slotOne.setDisable(true);
            }else{
                chooserPane.add(card1 = new ImageView(getImage(myPlayer().getSlots().getSlots()[1].getFirstCard().getCardId(),true)),1,0);
                card1.setPreserveRatio(true);
                card1.setFitHeight(250);
                card1.setFitWidth(165);
            }
            if(myPlayer().getSlots().getSlots()[2].isEmpty())
            {
                chooserPane.add(card2 = new ImageView(getImage(60,false)),2,0);
                card2.setPreserveRatio(true);
                card2.setFitHeight(250);
                card2.setFitWidth(165);
                slotTwo.setDisable(true);
            }else{
                chooserPane.add(card2 = new ImageView(getImage(myPlayer().getSlots().getSlots()[2].getFirstCard().getCardId(),true)),2,0);
                card2.setPreserveRatio(true);
                card2.setFitHeight(250);
                card2.setFitWidth(165);
            }
            if(myPlayer().getLeaderCards()[0].getAction().equals(LeaderCardAction.PRODUCTIONPOWER)&&myPlayer().getLeaderCards()[0].isActive())
            {
                chooserPane.add(card4 = new ImageView(getImage(myPlayer().getLeaderCards()[0].getCardId(),true)),4,0);
                card4.setPreserveRatio(true);
                card4.setFitHeight(250);
                card4.setFitWidth(165);
            }else{
                chooserPane.add(card4 = new ImageView(getImage(60,false)),4,0);
                card4.setPreserveRatio(true);
                card4.setFitHeight(250);
                card4.setFitWidth(165);
                slotFour.setDisable(true);
            }
            if(myPlayer().getLeaderCards()[1].getAction().equals(LeaderCardAction.PRODUCTIONPOWER)&&myPlayer().getLeaderCards()[1].isActive())
            {
                chooserPane.add(card5 = new ImageView(getImage(myPlayer().getLeaderCards()[1].getCardId(),true)),5,0);
                card5.setPreserveRatio(true);
                card5.setFitHeight(250);
                card5.setFitWidth(165);
            }else{
                chooserPane.add(card5 = new ImageView(getImage(60,false)),5,0);
                card5.setPreserveRatio(true);
                card5.setFitHeight(250);
                card5.setFitWidth(165);
                slotFive.setDisable(true);
            }


            submitMarket.setOnAction(event -> {
                //Basic production has been activated

                if(slotThree.isSelected()&&basicProduction.get()==false){
                    productionOutput[0]=true;

                    activations[0]=slotZero.isSelected();
                    activations[1]=slotOne.isSelected();
                    activations[2]=slotTwo.isSelected();
                    activations[3]=productionOutput[1]=slotFour.isSelected();
                    activations[4]=productionOutput[2]=slotFive.isSelected();

                    ImageView card3;
                    basicProduction.set(true);
                    cardTitle.setText("Choose the input to use");
                    slotZero.setSelected(false);slotZero.setDisable(false);
                    slotOne.setSelected(false);slotOne.setDisable(false);
                    slotTwo.setSelected(false);slotTwo.setDisable(false);
                    slotThree.setSelected(false);slotTwo.setDisable(false);
                    slotFour.setSelected(false);slotFour.setDisable(true);
                    slotFive.setSelected(false);slotFive.setDisable(true);
                    card0.setImage(getImage(ResourceType.STONES));
                    card1.setImage(getImage(ResourceType.SERVANTS));
                    card2.setImage(getImage(ResourceType.COINS));
                    card4.setImage(null);
                    card5.setImage(null);
                    chooserPane.add(card3 = new ImageView(getImage(ResourceType.SHIELDS)),3,0);
                    card0.setFitHeight(167);
                    card0.setFitWidth(167);
                    card1.setFitHeight(167);
                    card1.setFitWidth(167);
                    card2.setFitHeight(167);
                    card2.setFitWidth(167);
                    card3.setFitHeight(167);
                    card3.setFitWidth(167);
                    card0.setPreserveRatio(true);card1.setPreserveRatio(true);card2.setPreserveRatio(true);card3.setPreserveRatio(true);
                    slotFour.setDisable(true);
                }else if(slotThree.isSelected()==false&&basicProduction.get()==false){
                    productionOutput[0]=false;productionOutput[1]=slotFour.isSelected();productionOutput[2]=slotFive.isSelected();
                    sendAction(new ActivateProduction(slotZero.isSelected(),slotOne.isSelected(),slotTwo.isSelected(),slotFour.isSelected(),slotFive.isSelected(),slotThree.isSelected(),productionInputs));
                    refresh();
                    chooser.close();
                }else if(basicProduction.get()==true){
                    int selected = 0;
                    selected+=slotZero.isSelected()?1:0;
                    selected+=slotOne.isSelected()?1:0;
                    selected+=slotTwo.isSelected()?1:0;
                    selected+=slotThree.isSelected()?1:0;
                    if(selected==2||selected==1){
                        {
                        if(slotZero.isSelected()){
                            productionInputs.add(ResourceType.STONES);
                            if(selected==1)
                                productionInputs.add(ResourceType.STONES);
                        }
                        if(slotOne.isSelected()){
                            productionInputs.add(ResourceType.SERVANTS);
                            if(selected==1)
                                productionInputs.add(ResourceType.SERVANTS);
                        }
                        if(slotTwo.isSelected()){
                            productionInputs.add(ResourceType.COINS);
                            if(selected==1)
                                productionInputs.add(ResourceType.COINS);
                        }
                        if(slotThree.isSelected()){
                            productionInputs.add(ResourceType.SHIELDS);
                            if(selected==1)
                                productionInputs.add(ResourceType.SHIELDS);
                        }

                        sendAction(new ActivateProduction(activations[0],activations[1],activations[2],activations[3],activations[4],true,productionInputs));
                        refresh();
                        chooser.close();
                        }
                    }else {
                        displayError("Please select 2 inputs, not "+selected);
                        chooser.close();
                    }
                }

            });
        }
        if(myPlayer().getPossibleActions().contains(ActionType.CHOOSE_PRODUCTION_OUTPUT)){
            ArrayList<ResourceType> basicArray,leader1Array,leader2Array;
            basicArray=new ArrayList<>();leader1Array=new ArrayList<>();leader2Array=new ArrayList<>();
            ObservableList<String> resources = FXCollections.observableArrayList("Coins","Servants","Shields","Stones");
            ChoiceBox basicProd,leader1,leader2;
            basicProd=new ChoiceBox(resources);leader1=new ChoiceBox(resources);leader2=new ChoiceBox(resources);
            cardTitle2.setText(null);
            if(productionOutput[0]==true)
            {
                cardTitle1.setText("Basic\nProd");
                chooserPane.add(basicProd,2,0);
            }
            else
                cardTitle1.setText(null);
            if(productionOutput[1]||productionOutput[2])
                cardTitle3.setText("Leader\nCards");
            else
                cardTitle3.setText(null);
            if(productionOutput[1]==true)
            {
                chooserPane.add(leader1,4,0);
            }
            else
                cardTitle1.setText(null);
            if(productionOutput[2]==true)
            {
                cardTitle1.setText("Basic\nProd");
                chooserPane.add(leader2,5,0);
            }
            else
                cardTitle1.setText(null);

            submitMarket.setOnAction(event -> {
                ResourceType resBasic=null,resLeader1=null,resLeader2=null;
                if(productionOutput[0])
                    if(basicProd.getValue()!=null){
                        resBasic=stringToResource(basicProd.getValue().toString());
                        basicArray.add(resBasic);
                    }
                    else
                        displayError("You must choose basic production output!");
                if(productionOutput[1])
                    if(basicProd.getValue()!=null){
                        resLeader1=stringToResource(basicProd.getValue().toString());
                        leader1Array.add(resLeader1);
                    }
                    else
                        displayError("You must choose the output for Leader card 1!");
                if(productionOutput[2])
                    if(basicProd.getValue()!=null){
                        resLeader2=stringToResource(basicProd.getValue().toString());
                        leader2Array.add(resLeader2);
                    }
                    else
                        displayError("You must choose the output for Leader card 2!");
                if(     (productionOutput[0]&&basicProd.getValue()!=null)||!productionOutput[0]&&
                        (productionOutput[1]&&leader1.getValue()!=null)||!productionOutput[1]&&
                        (productionOutput[2]&&leader2.getValue()!=null)||!productionOutput[2]) {
                    ChooseProductionOutput productionOutputAction = new ChooseProductionOutput();
                    productionOutputAction.setBasicProductionOutput(basicArray);
                    productionOutputAction.setBasicProduction(productionOutput[0]);
                    productionOutputAction.setFirstLeaderCard(productionOutput[1]);
                    productionOutputAction.setFirstLeaderCardOutput(leader1Array);
                    productionOutputAction.setSecondLeaderCard(productionOutput[2]);
                    productionOutputAction.setSecondLeaderCardOutput(leader2Array);

                    sendAction(productionOutputAction);
                    chooser.close();
                    refresh();

                }
            });

        }

        Scene scene = new Scene(choose);
        chooser.setScene(scene);
        chooser.showAndWait();
    }

    /**
     * This method opens the card board and allows to buy a card if it's clicked and the user can afford it as per game rules.
     * If a buy card is successful the card value is set in temporaryCard for having it shown when the user has to choose the slot.
     * @throws Exception    If there's a problem reading the FXML
     */
    @FXML
    private void cardPicker() throws IOException, ModelException {
        if(cardTableBtn.getText().equals("Choose card slot"))
            try {
                choose();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        Parent developmentCardBoard;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/CardBoard.fxml"));
        loader.setController(this);
        developmentCardBoard = loader.load();
        cardPicker = new Stage();
        cardPicker.initModality(Modality.APPLICATION_MODAL);
        cardPicker.setTitle("Card Board");
        card = new ImageView[4][3];
            //Handling of buy mode in game
            cardTitle.setText("Click on the card you want to buy");
            for(int cardColumn=0;cardColumn<4;cardColumn++)
                for(int cardRow=0;cardRow<3;cardRow++)
                {
                    cardPane.add(card[cardColumn][cardRow] = new ImageView(super.getImage(this.client.getUserInteraction().getGame().getDevelopmentCardTable().getTopCardFromDeck(cardRow,cardColumn).getCardId(),true)),cardRow,cardColumn);
                    card[cardColumn][cardRow].setCursor(Cursor.HAND);
                    card[cardColumn][cardRow].setFitHeight(190);
                    card[cardColumn][cardRow].setFitWidth(125);
                    int finalCardColumn = cardColumn;
                    int finalCardRow = cardRow;
                    card[cardColumn][cardRow].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            try {
                                temporaryCard=getClientConnection().getClient().getUserInteraction().getGame().getDevelopmentCardTable().getTopCardFromDeck(finalCardRow,finalCardColumn);
                            } catch (ModelException e) {
                                e.printStackTrace();
                            }
                            sendAction(new BuyCard(finalCardRow,finalCardColumn));
                        }
                    });
                }

        button.setOnAction(e->cardPicker.close());
        Scene scene = new Scene(developmentCardBoard);
        cardPicker.setScene(scene);
        cardPicker.showAndWait();
    }

    /**
     * The market screen, where the user can activate the market.
     * Once the market has been activated successfully its button turns to "End Market" to click if the user wants to discard all of the resources gotten;
     * otherwise the user can click onto "Store Resources" and store the resources he wants. Once the store resources is validated the resources not stored are discarded as per game rule.
     *
     * @throws IOException  If there's a problem loading the FXML file
     */
    @FXML
    private void market() throws IOException{
        if(marketBtn.getText().equals("End Market"))
        {
            sendAction(new EndMarket());
            marketBtn.setText("Open Market");
            return;
        }
        Parent marketParent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/Market.fxml"));
        loader.setController(this);
        marketParent = loader.load();
        market = new Stage();
        market.initModality(Modality.APPLICATION_MODAL);
        market.setTitle("Market");
        final boolean[] submitDisabled = {true};
        submitMarket.setDisable(submitDisabled[0]);
        activeMarket = new ImageView[4][3];
        extraMarble.setImage(super.getImage(this.client.getUserInteraction().getGame().getMarket().getExtraMarble()));

        for(int marketColumn=0;marketColumn<4;marketColumn++)
            for(int marketRow=0;marketRow<3;marketRow++) {
                marketPane.add(activeMarket[marketColumn][marketRow] = new ImageView(super.getImage(this.client.getUserInteraction().getGame().getMarket().getMarble(marketColumn, marketRow))), marketColumn, marketRow);
                activeMarket[marketColumn][marketRow].setFitHeight(41);
                activeMarket[marketColumn][marketRow].setFitWidth(41);
            }
                marketColumn1.setOnAction(event -> {
                    if(marketColumn1.isArmed()){
                        marketColumn2.setSelected(false);
                        marketColumn3.setSelected(false);
                        marketColumn4.setSelected(false);
                        marketRow1.setSelected(false);
                        marketRow2.setSelected(false);
                        marketRow3.setSelected(false);
                        submitMarket.setDisable(!marketColumn1.isSelected());
                    }
                });
                marketColumn2.setOnAction(event -> {
                    if(marketColumn2.isArmed()){
                        marketColumn1.setSelected(false);
                        marketColumn3.setSelected(false);
                        marketColumn4.setSelected(false);
                        marketRow1.setSelected(false);
                        marketRow2.setSelected(false);
                        marketRow3.setSelected(false);
                        submitMarket.setDisable(!marketColumn2.isSelected());
                    }
                });
                marketColumn3.setOnAction(event -> {
                    if(marketColumn3.isArmed()){
                        marketColumn2.setSelected(false);
                        marketColumn1.setSelected(false);
                        marketColumn4.setSelected(false);
                        marketRow1.setSelected(false);
                        marketRow2.setSelected(false);
                        marketRow3.setSelected(false);
                        submitMarket.setDisable(!marketColumn3.isSelected());
                    }
                });
                marketColumn4.setOnAction(event -> {
                    if(marketColumn4.isArmed()){
                        marketColumn1.setSelected(false);
                        marketColumn2.setSelected(false);
                        marketColumn3.setSelected(false);
                        marketRow1.setSelected(false);
                        marketRow2.setSelected(false);
                        marketRow3.setSelected(false);
                        submitMarket.setDisable(!marketColumn4.isSelected());
                    }
                });
                marketRow1.setOnAction(event -> {
                    if(marketRow1.isArmed()){
                        marketColumn1.setSelected(false);
                        marketColumn2.setSelected(false);
                        marketColumn3.setSelected(false);
                        marketColumn4.setSelected(false);
                        marketRow2.setSelected(false);
                        marketRow3.setSelected(false);
                        submitMarket.setDisable(!marketRow1.isSelected());
                    }
                });
                marketRow2.setOnAction(event -> {
                    if(marketRow2.isArmed()){
                        marketColumn1.setSelected(false);
                        marketColumn2.setSelected(false);
                        marketColumn3.setSelected(false);
                        marketColumn4.setSelected(false);
                        marketRow1.setSelected(false);
                        marketRow3.setSelected(false);
                        submitMarket.setDisable(!marketRow2.isSelected());
                    }
                });
                marketRow3.setOnAction(event -> {
                    if(marketRow3.isArmed()){
                        marketColumn1.setSelected(false);
                        marketColumn2.setSelected(false);
                        marketColumn3.setSelected(false);
                        marketColumn4.setSelected(false);
                        marketRow1.setSelected(false);
                        marketRow2.setSelected(false);
                        submitMarket.setDisable(!marketRow3.isSelected());
                    }
                });
                submitMarket.setOnAction(event1 -> {
                    boolean row=false;
                    int number=-1;
                    if(marketRow1.isSelected()){
                        number=0;
                        row=true;
                    }
                    else if(marketRow2.isSelected()) {
                        number=1;
                        row=true;
                    }
                    else if(marketRow3.isSelected()) {
                        number=2;
                        row=true;
                    }
                    else if(marketColumn1.isSelected())
                        number=0;
                    else if(marketColumn2.isSelected())
                        number=1;
                    else if(marketColumn3.isSelected())
                        number=2;
                    else if(marketColumn4.isSelected())
                        number=3;
                    if(this.client.getUserInteraction().getGame().getMyPlayer().getPossibleActions().contains(ActionType.MARKET_CHOOSE_ROW)){
                        sendAction(new MarketChooseRow(row, number));
                        market.close();
                        refresh();
                        marketBtn.setText("End Market");
                    }
                });
        button.setOnAction(e->market.close());
        Scene scene = new Scene(marketParent);
        market.setScene(scene);
        market.showAndWait();
    }

    /**
     * This method is used to check if a resource can be added where the user wants to, otherwise the user gets the error.
     * If the resource can be added where the user wants to the method returns true, if it's not possible an explanation is given and false is returned.
     * StoreResources calls this method so that if it returns true the resource is added as an image to the game and the labels of the
     * remanining resources are updated accordingly.
     * @param shelfNumber       The shelf number to add the resource to [0-2] warehouse, [3-4] for leader cards
     * @param resource          The resource name to add to the shelf number
     *                          it is a string because the resource is identified by his dragbord name once its dropped onto the shelf
     * @return                  True if adding the resource is allowed on the depot, false otherwise
     */
    private boolean addResource(int shelfNumber, String resource){
        ResourceType resourceType=stringToResource(resource);
        RedWarehouseDepot warehouse = null;
        if(shelfNumber>=0&&shelfNumber<=2){

            warehouse = myPlayer().getWarehouse().getWarehouseDepots()[shelfNumber];

            if(
                    shelfNumber==0&&(resourceType.equals(myPlayer().getWarehouse().getWarehouseDepots()[1].getResourceType())||resourceType.equals(myPlayer().getWarehouse().getWarehouseDepots()[2].getResourceType()))||
                    shelfNumber==1&&(resourceType.equals(myPlayer().getWarehouse().getWarehouseDepots()[0].getResourceType())||resourceType.equals(myPlayer().getWarehouse().getWarehouseDepots()[2].getResourceType()))||
                    shelfNumber==2&&(resourceType.equals(myPlayer().getWarehouse().getWarehouseDepots()[0].getResourceType())||resourceType.equals(myPlayer().getWarehouse().getWarehouseDepots()[1].getResourceType()))
            )
            {
                displayError("There's another depot with the same type of resources!");
                return false;
            }

        }else if(shelfNumber==3||shelfNumber==4){
            if(shelfNumber==3){
                if(myPlayer().getLeaderCards()[0].isActive()&&myPlayer().getLeaderCards()[0].getAction().equals(LeaderCardAction.EXTRADEPOT)){
                    warehouse = myPlayer().getWarehouse().getExtraWarehouseDepot1();
                }else {
                    displayError("This leader card is not active!");
                    return false;
                }
            }else if(shelfNumber==4){
                if(myPlayer().getLeaderCards()[1].isActive()&&myPlayer().getLeaderCards()[1].getAction().equals(LeaderCardAction.EXTRADEPOT)){
                    warehouse = myPlayer().getWarehouse().getExtraWarehouseDepot2();
                }else {
                    displayError("This leader card is not active!");
                    return false;
                }
            }
        }
        if((warehouse.getResourceType().equals(resourceType)&&!warehouse.isFull())||warehouse.isEmpty()){
                sendAction(new AddResource(shelfNumber,resourceType));
                refresh();
            return true;
        }else
            if(!warehouse.getResourceType().equals(resourceType))
                displayError("You can't mix resource types!");
            else
                displayError("This warehouse is full!");
        return false;
    }

    /**
     * The board screen
     * @throws IOException      if there's a problem loading the FXML
     */
    protected void board() throws IOException {
        Parent board;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/Board.fxml"));
        loader.setController(this);
        board = loader.load();
        if(this.client.getUserInteraction().getGame().getGameType().equals(GameType.SINGLEPLAYER))
        {
            inkwell.setImage(getImage(false));
        }
        else {
            ArrayList<Player> players = this.client.getUserInteraction().getGame().getPlayers();
            for(int i=0;i<players.size();i++){
                if(players.get(i).getNickname()!=myPlayer().getNickname()){
                Button playBtn;
                playBtn=new Button();
                playBtn.setText(players.get(i).getNickname());
                playBtn.setFont(Baskerville);
                playBtn.setOnAction(this::boardClickHandler);
                playBtn.setId(""+i);
                otherBoards.add(playBtn,0,i);
                GridPane.setHalignment(otherBoards,HPos.CENTER);
                GridPane.setValignment(otherBoards,VPos.CENTER);
                }
            }
            if(!this.client.getUserInteraction().getGame().getMyPlayer().hasInkwell())
                inkwell.setImage(null);
        }


        Scene scene = new Scene(board);
        refresh();
        Stage window = getStage();
        window.setScene(scene);
    }

    /**
     * Method used to view other's board
     * @param player
     * @throws IOException
     */
    protected void board(Player player) throws IOException {
        Parent playerBoard;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/Board.fxml"));
        loader.setController(this);
        playerBoard = loader.load();
        Scene scene = new Scene(playerBoard);

        xtraBoard = new Stage();
        xtraBoard.initModality(Modality.APPLICATION_MODAL);
        xtraBoard.setTitle(player.getNickname()+"'s Board");

        if(this.client.getUserInteraction().getGame().getGameType().equals(GameType.SINGLEPLAYER))
        {
            inkwell.setImage(getImage(false));
        }
        else {
            if(!this.client.getUserInteraction().getGame().getMyPlayer().hasInkwell())
                inkwell.setImage(null);
        }

        produciton.setDisable(true);marketBtn.setDisable(true);storeResBtn.setDisable(true);
        cardTableBtn.setDisable(true);payResBtn.setDisable(true);
        nextTurn.setText("Close Board");
        nextTurn.setOnAction(event1 -> {
            xtraBoard.close();
        });
        xtraBoard.setScene(scene);
        xtraBoard.showAndWait();
    }

    /**
     * Method called when the user clicks on the "Next Action" button.
     * If it's possible to end the turn a message is sent, otherwise an explanation is given
     */
    @FXML
    private void turnHandler(){
        {
            if(this.client.getUserInteraction().getGame().getMyPlayer().getPossibleActions().contains(ActionType.END_TURN))
                sendAction(new EndTurn());
            else displayError("You can't pass the turn now!");
        }
    }

    /**
     * This method handles some button tasks in the board.
     * The task are identified by getting the Button's id from the ActionEvent triggered by the press
     * The actions that are done trough this method are:
     * ‣ Activate Leader Card:  if it's possible to activate a leader card a message is sent
     * ‣ Discard Leader Card:   to discard an activated Leader Card
     * ‣ View Other's Board:    for opening another window with their board
     * @param event             The event triggered for getting the id of the Button pressed
     */
    @FXML
    private void boardClickHandler(ActionEvent event){
        Node node = (Node) event.getTarget();
        ArrayList<Player> players = this.client.getUserInteraction().getGame().getPlayers();

        String parent=node.getId();
        switch (parent){
            case "activateLeader1":{
                sendAction(new ActivateLeaderCard(0));
                break;
            }
            case "discardLeader1":{
                sendAction(new DiscardLeaderCard(0));
                break;
            }
            case "activateLeader2":{
                sendAction(new ActivateLeaderCard(1));
                break;
            }case "discardLeader2":{
                sendAction(new DiscardLeaderCard(1));
                break;
            }
        }
        for(int i=0;i<players.size();i++){
            if(parent.equals(players.get(i).getNickname())) {
                try {
                    board(players.get(i));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * This method handles the Activate Production button, by opening the Activate Production screen if the user has the right to
     * or by opening the Production Output otherwise
     * @throws Exception
     */
    @FXML
    public void activateProduction() throws Exception{
        if(myPlayer().getPossibleActions().contains(ActionType.ACTIVATE_PRODUCTION))
            choose();
        else if(myPlayer().getPossibleActions().contains(ActionType.CHOOSE_PRODUCTION_OUTPUT))
            choose();
        else
            displayError("You can't activate a production now!");
    }

    /**
     * This method is called when the user presses on the "Get Resource" button.
     * If there are resources to store the Get resource screen is opened, otherwise the user is informed
     * @throws IOException
     */
    @FXML
    private void getResourcesGame() throws IOException {
        if(this.client.getUserInteraction().getGame().getMyPlayer().getPossibleActions().contains(ActionType.ADD_RESOURCE))
            getResources(false);
        else
            displayError("You don't have any resource left to store!");
    }

    /**
     * This methods transforms a string to a ResourceType.
     * Used in the get resource because the resource is identified by his text property in the dragboard.
     * @param resource      the resource string to get the resource type from
     * @return              the corresponding ResourceType
     */
    private ResourceType stringToResource(String resource){
        ResourceType resourceType;
        switch (resource.toLowerCase(Locale.ROOT)){
            case "stones":{
                resourceType=ResourceType.STONES;
                break;
            }
            case "coins":{
                resourceType=ResourceType.COINS;
                break;
            }
            case "servants":{
                resourceType=ResourceType.SERVANTS;
                break;
            }
            case "shields":{
                resourceType=ResourceType.SHIELDS;
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + resource);
        }
        return resourceType;
    }

    /**
     * sendAction sends the action to the server. Useful for having less Exceptions around the code.
     * @param action    The action to send
     */
    private void sendAction(Action action){
        try {
            this.clientConnection.send(action);
        } catch (IOException e) {
            displayError(e.toString());
        }
    }

    /**
     * By giving a Player's position into the faith track it returns the corresponding Row on the Faith Grid.
     * @param position  The position onto the faith track
     * @return          The corresponding row onto the Faith Grid
     */
    private int faithRow(int position){
        if(position<=2||position>=11&&position<=16)
            return 2;
        else if(position==3||position==10||position==17)
            return 1;
        else return 0;
    }

    /**
     * By giving a Player's position into the faith track it returns the corresponding Column on the Faith Grid.
     * @param position  The position onto the faith track
     * @return          The corresponding column onto the Faith Grid
     */
    private int faithColumn(int position){
        if(position<3)
            return position;
        if(position<=4)
            return 2;
        if(position<=9)
            return position-2;
        if(position==10||position==11)
            return 7;
        if(position<=16)
            return position-4;
        if(position==17)
            return 12;
        else
            return position-6;
    }

    /**
     * Refreshes the board by updating the resource data, the card data and the faith data.
     */
    protected void refresh(){
                setResourceData(getResourceData(myPlayer()));
                setCardData(getCardData(myPlayer()));
                setFaithData(getFaithData());
    }

    /**
     * Gets the faith information from the game as an Object[]
     * @return Object[] of faith information, in particular:
     * ‣ faithData[0]:   4 images of the players to display onto the track
     * ‣ faithData[1]:   their Row position onto the Faith grid
     * ‣ faithData[2]:   their Columns position onto the Faith grid.
     */
    protected Object[] getFaithData(){
        ArrayList<Player> players = this.client.getUserInteraction().getGame().getPlayers();

        Object[] faithData;
        faithData = new Object[3];
        Image[] resourceImages;
        resourceImages = new Image[4];
        int[] faithRow;
        faithRow = new int[4];
        int[] faithColumn;
        faithColumn = new int[4];
        for(int i=0;i<players.size();i++){
            resourceImages[i]=getImage(players.get(i));
            faithColumn[i]=faithColumn(players.get(i).getFaithTrackPosition());
            faithRow[i]=faithRow(players.get(i).getFaithTrackPosition());
        }
        faithData[0] = resourceImages;
        faithData[1] = faithRow;
        faithData[2] = faithColumn;

        return faithData;
    }

    /**
     * Sets the faith data and information onto the grid for it to be shown.
     * Each user is set to a corner of the grid so that up to 4 can be displayed
     * @param faithData getFaithData()
     */
    protected void setFaithData(Object[] faithData){
        ArrayList<Player> players = this.client.getUserInteraction().getGame().getPlayers();

        Image[] resourceImages;
        resourceImages = (Image[]) faithData[0];
        int[] faithRow;
        faithRow = (int[]) faithData[1];
        int[] faithColumn;
        faithColumn = (int[]) faithData[2];

        faithPane.getChildren().clear();

        for(int i=0;i<players.size();i++){
            ImageView faithImage = new ImageView();
            faithImage.setImage(resourceImages[i]);
            faithImage.setFitHeight(38);
            faithImage.setFitWidth(30);
            faithPane.add(faithImage,faithColumn[i],faithRow[i]);
            if(i==0){
                GridPane.setHalignment(faithImage, HPos.LEFT);
                GridPane.setValignment(faithImage, VPos.TOP);
            }else if(i==1){
                GridPane.setHalignment(faithImage, HPos.RIGHT);
                GridPane.setValignment(faithImage, VPos.BOTTOM);
            }else if(i==2){
                GridPane.setHalignment(faithImage, HPos.LEFT);
                GridPane.setValignment(faithImage, VPos.BOTTOM);
            }else if(i==3){
                GridPane.setHalignment(faithImage, HPos.RIGHT);
                GridPane.setValignment(faithImage, VPos.TOP);
            }

        }
    }

    /**
     * Gets the resource data for a given Player
     * @param player        The player to get the data from
     * @return              Object[] as described:
     * ‣ resourceData[0]:   Image[10] with all the resource Image information orderly placed
     *                      [0-5] Warehouse resources, [6-9] LeaderCard resources
     * ‣ resourceData[1]:   String[4] with the number of resources stored into the Strongbox
     *                      ordered as such: coins,shieds,servants,stoned
     */
    protected Object[] getResourceData(Player player){
        Object[] resourceData;
        resourceData = new Object[2];
        Image[] resourceImages;
        resourceImages = new Image[10];
        String[] resourceText;
        resourceText = new String[4];

        RedWarehouseDepot warehouseDepots[] = player.getWarehouse().getWarehouseDepots();
        //Gets the images from the Warehouse of the player
        if(warehouseDepots[2].isEmpty())
            resourceImages[0] = null;
        else
            resourceImages[0]=getImage(warehouseDepots[2].getResourceType());
        if(warehouseDepots[1].isEmpty()){
            resourceImages[1]=null;
            resourceImages[2]=null;
        }else{
            Image resource = getImage(warehouseDepots[1].getResourceType());
            resourceImages[1]=resource;
            if(warehouseDepots[1].getStoredResources()==2)
                resourceImages[2]=resource;
            else
                resourceImages[2]=null;
        }
        if(warehouseDepots[0].isEmpty()){
            resourceImages[3]=null;
            resourceImages[4]=null;
            resourceImages[5]=null;
        }else{
            Image resource = getImage(warehouseDepots[0].getResourceType());
            resourceImages[3]=resource;
            if(warehouseDepots[0].getStoredResources()==2){
                resourceImages[4]=resource;
                resourceImages[5]=null;
            }
            else if(warehouseDepots[0].getStoredResources()==3){
                resourceImages[4]=resource;
                resourceImages[5]=resource;
            }
        }
        //Gets the images from the leader card of the player
        RedWarehouseDepot extraWarehouseDepot = player.getWarehouse().getExtraWarehouseDepot1();
        if(extraWarehouseDepot.isEmpty()){
            resourceImages[6]=null;
            resourceImages[7]=null;
        }else{
            Image resource = getImage(extraWarehouseDepot.getResourceType());
            resourceImages[6]=resource;
            if(extraWarehouseDepot.getStoredResources()==2)
                resourceImages[7]=resource;
            else
                resourceImages[7]=null;
        }
        extraWarehouseDepot = player.getWarehouse().getExtraWarehouseDepot2();
        if(extraWarehouseDepot.isEmpty()){
            resourceImages[8]=null;
            resourceImages[9]=null;
        }else{
            Image resource = getImage(extraWarehouseDepot.getResourceType());
            resourceImages[8]=resource;
            if(extraWarehouseDepot.getStoredResources()==2)
                resourceImages[9]=resource;
            else
                resourceImages[9]=null;
        }

        //Gets the information from the strongbox
        RedStrongbox strongbox = player.getStrongbox();
        resourceText[0]=String.valueOf(strongbox.getStoredResources().getResource(ResourceType.COINS));
        resourceText[1]=String.valueOf(strongbox.getStoredResources().getResource(ResourceType.SHIELDS));
        resourceText[2]=String.valueOf(strongbox.getStoredResources().getResource(ResourceType.SERVANTS));
        resourceText[3]=String.valueOf(strongbox.getStoredResources().getResource(ResourceType.STONES));

        resourceData[0]=resourceImages;
        resourceData[1]=resourceText;
        return resourceData;
    }

    /**
     * Sets the resourceData onto a Board
     * @param resourceData  Object[] from getResourceData
     */
    protected void setResourceData(Object[] resourceData){
                Image[] resourceImage;
                resourceImage=(Image[]) resourceData[0];
                warehouse1.setImage(resourceImage[0]);
                warehouse21.setImage(resourceImage[1]);
                warehouse22.setImage(resourceImage[2]);
                warehouse31.setImage(resourceImage[3]);
                warehouse32.setImage(resourceImage[4]);
                warehouse33.setImage(resourceImage[5]);
                leader1resource1.setImage(resourceImage[6]);
                leader1resource2.setImage(resourceImage[7]);
                leader2resource1.setImage(resourceImage[8]);
                leader2resource2.setImage(resourceImage[9]);
                String[] resourceText;
                resourceText=(String[]) resourceData[1];
                strongboxCoins.setText(resourceText[0]);
                strongboxShields.setText(resourceText[1]);
                strongboxServants.setText(resourceText[2]);
                strongboxStones.setText(resourceText[3]);
    }

    /**
     * Gets the card data for a given Player
     * @param player        The player to get the cards data from
     * @return              Image[11] as described:
     *                     ‣ [0-8] The images of the DevelopmentCards
     *                     ‣ [9-10] The images of the LeaderCards.
     *                      If viewing another Player's board with the cards not activated
     *                      or if One's Leader Card have been discarded the back of the card is shown.
     */
    protected Image[] getCardData(Player player){
        Image[] cardData;
        cardData = new Image[11];

        RedLeaderCard[] leaderCards = player.getLeaderCards();

        //Show back of unactivated LeaderCards if looking another player's board
        if(this.client.getUserInteraction().getGame().getMyPlayer().equals(player))
        {
            cardData[9] = getImage(leaderCards[0].getCardId(),!leaderCards[0].isActive());
            cardData[10] = getImage(leaderCards[1].getCardId(),!leaderCards[1].isActive());
        }else
        {
            cardData[9] = getImage(leaderCards[0].getCardId(),leaderCards[0].isActive());
            cardData[10] = getImage(leaderCards[1].getCardId(),leaderCards[1].isActive());
        }

        RedDevelopmentCardSlots developmentCardSlots = player.getSlots();
        RedCardSlot[] cardSlots = developmentCardSlots.getSlots();

        int[] cardId = new int[9];

        int cardIndex=0;
        for(int i=0;i<3;i++)
        {
            if(cardSlots[i].getLevelOccupied()==0)
            {
                cardId[cardIndex]=-1;
                cardIndex++;
                cardId[cardIndex]=-1;
                cardIndex++;
                cardId[cardIndex]=-1;
                cardIndex++;
            }else if (cardSlots[i].getLevelOccupied()==1){
                cardId[cardIndex]=cardSlots[i].getDevelopmentCards()[0].getCardId();
                cardIndex++;
                cardId[cardIndex]=-1;
                cardIndex++;
                cardId[cardIndex]=-1;
                cardIndex++;
            }else if (cardSlots[i].getLevelOccupied()==2){
                cardId[cardIndex]=cardSlots[i].getDevelopmentCards()[0].getCardId();
                cardIndex++;
                cardId[cardIndex]=cardSlots[i].getDevelopmentCards()[1].getCardId();
                cardIndex++;
                cardId[cardIndex]=-1;
                cardIndex++;
            }else if (cardSlots[i].getLevelOccupied()==3){
                cardId[cardIndex]=cardSlots[i].getDevelopmentCards()[0].getCardId();
                cardIndex++;
                cardId[cardIndex]=cardSlots[i].getDevelopmentCards()[1].getCardId();
                cardIndex++;
                cardId[cardIndex]=cardSlots[i].getDevelopmentCards()[2].getCardId();
                cardIndex++;
            }
        }

        for(int i=0;i<9;i++)
        {
            if(cardId[i]==-1)
                cardData[i]=null;
            else
                cardData[i]=getImage(cardId[i],true);
        }

        return cardData;
    }

    /**
     * Method used for setting the card data onto One's board.
     * @param cardData  The Image[] from getCardData
     */
    protected void setCardData(Image[] cardData){
        dev11.setImage(cardData[0]);
        dev12.setImage(cardData[1]);
        dev13.setImage(cardData[2]);
        dev21.setImage(cardData[3]);
        dev22.setImage(cardData[4]);
        dev23.setImage(cardData[5]);
        dev31.setImage(cardData[6]);
        dev32.setImage(cardData[7]);
        dev33.setImage(cardData[8]);
        leader1.setImage(cardData[9]);
        leader2.setImage(cardData[10]);
    }

}
