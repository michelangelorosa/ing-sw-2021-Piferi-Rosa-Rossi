package it.polimi.ingsw.View.User;

import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.Model.GameModel.Warehouse;
import it.polimi.ingsw.Model.GameModel.WarehouseDepot;
import it.polimi.ingsw.Model.MessagesToClient.BoughtCardMessage;
import it.polimi.ingsw.Model.MessagesToClient.EndTurnSingleplayerMessage;
import it.polimi.ingsw.View.Client.Client;
import it.polimi.ingsw.View.Client.ClientConnection;
import it.polimi.ingsw.View.ReducedModel.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class GuiBoardController extends GuiInitController{
    private Stage cardPicker;
    private Stage chooser;
    private Stage market;
    private Stage leaderSelection;
    private Stage resourcePicker;
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
    @FXML private ImageView leaderDepot;
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
    @FXML protected Button cardTableBtn;
    @FXML protected Button marketBtn;
    @FXML protected ImageView strongboxStonesClickable;
    @FXML protected ImageView strongboxShieldsClickable;
    @FXML protected ImageView strongboxServantsClickable;
    @FXML protected ImageView strongboxCoinsClickable;

    private int resourceNumber;

    /**
     * Constructor for Gui
     *
     * @param client
     */
    protected GuiBoardController(Client client,ClientConnection clientConnection,ClientExceptionHandler clientExceptionHandler) {
        super(client,clientConnection,clientExceptionHandler);
    }
    private void getInitResource(){
        this.resourceNumber=this.client.getUserInteraction().getInitNumberOfResources();
    }
    private void gotInitResource(){
        resourceNumber--;
        remServants.setText(String.valueOf(resourceNumber));
        remStones.setText(String.valueOf(resourceNumber));
        remCoins.setText(String.valueOf(resourceNumber));
        remShields.setText(String.valueOf(resourceNumber));
    }
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
    protected Player myPlayer(){
        return this.client.getUserInteraction().getGame().getMyPlayer();
    }

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
            resourcePicker.close();
            sendAction(new EndMarket());
            try {
                board();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

    if(!initialResources){
        Scene scene = new Scene(getResources);
        resourcePicker.setScene(scene);
        resourcePicker.showAndWait();
        }
    }
    @FXML
    protected void payResource() throws Exception{
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

    private void afterPayment(){
        if(myPlayer().getPossibleActions().contains(ActionType.CHOOSE_CARD_SLOT)) {
            cardTableBtn.setText("Choose card slot");
        }

    }

    @FXML
    private Text cardTitle1,cardTitle2,cardTitle3;
    @FXML
    private RadioButton slotOne,slotTwo,slotThree;
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
            /*
            marketPane.add(slotOne,1,1);
            slotOne.setAlignment(Pos.CENTER);
            marketPane.add(slotTwo,2,1);
            slotTwo.setAlignment(Pos.CENTER);
            marketPane.add(slotThree,3,1);
            slotThree.setAlignment(Pos.CENTER);
            */
            submitMarket.setDisable(true);

            marketPane.add(cardToAdd = new ImageView(getImage(temporaryCard.getCardId(),true)),5,5);
            cardToAdd.setFitHeight(250);
            cardToAdd.setFitWidth(165);

            if(myPlayer().getSlots().getSlots()[0].isEmpty())
                cardTitle1.setText("First\nSlot");
            else{
                marketPane.add(card1 = new ImageView(getImage(myPlayer().getSlots().getSlots()[0].getFirstCard().getCardId(),true)),1,1);
                card1.setFitHeight(250);
                card1.setFitWidth(165);
            }
                if(myPlayer().getSlots().getSlots()[1].isEmpty())
                    cardTitle2.setText("Second\nSlot");
                else{
                    marketPane.add(card2 = new ImageView(getImage(myPlayer().getSlots().getSlots()[1].getFirstCard().getCardId(),true)),2,2);
                    card2.setFitHeight(250);
                    card2.setFitWidth(165);
                }
            if(myPlayer().getSlots().getSlots()[2].isEmpty())
                cardTitle3.setText("Third\nSlot");
            else {
                marketPane.add(card3 = new ImageView(getImage(myPlayer().getSlots().getSlots()[2].getFirstCard().getCardId(),true)),3,3);
                card3.setFitHeight(250);
                card3.setFitWidth(165);
            }

            /*
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
            */
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
        Scene scene = new Scene(choose);
        chooser.setScene(scene);
        chooser.showAndWait();
    }

    @FXML
    private void cardPicker(ActionEvent event) throws Exception{
        if(cardTableBtn.getText().equals("Choose card slot"))
            try {
                choose();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        Boolean singleplayerDiscard=false;
        Parent developmentCardBoard;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/CardBoard.fxml"));
        loader.setController(this);
        developmentCardBoard = loader.load();
        cardPicker = new Stage();
        cardPicker.initModality(Modality.APPLICATION_MODAL);
        cardPicker.setTitle("Card Board");
        card = new ImageView[4][3];

        if(singleplayerDiscard){
            //Handling of discard mode in singlePlayer game
            cardTitle.setText("Choose the cards you want to discard");
            for(int cardColumn=0;cardColumn<4;cardColumn++)
                for(int cardRow=0;cardRow<3;cardRow++)
                {
                    cardPane.add(card[cardColumn][cardRow] = new ImageView(super.getImage(this.client.getUserInteraction().getGame().getDevelopmentCardTable().getTopCardFromDeck(cardColumn,cardRow).getCardId(),true)),cardColumn,cardRow);
                    card[cardColumn][cardRow].setFitHeight(190);
                    card[cardColumn][cardRow].setFitWidth(125);
                    int finalCardColumn = cardColumn;
                    int finalCardRow = cardRow;
                    card[cardColumn][cardRow].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            try {
                                getClientConnection().send(new BuyCard(finalCardRow,finalCardColumn));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //if card is the required type...
                            //card[finalCardColumn][finalCardRow].setImage(getImage(1+finalCardColumn+finalCardRow,false));
                        }
                    });
                }
        }
        else {
            //Handling of buy mode in game
            cardTitle.setText("Click on the card you want to buy");
            for(int cardColumn=0;cardColumn<4;cardColumn++)
                for(int cardRow=0;cardRow<3;cardRow++)
                {
                    cardPane.add(card[cardColumn][cardRow] = new ImageView(super.getImage(this.client.getUserInteraction().getGame().getDevelopmentCardTable().getTopCardFromDeck(cardRow,cardColumn).getCardId(),true)),cardRow,cardColumn);
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
        }
        button.setOnAction(e->cardPicker.close());
        Scene scene = new Scene(developmentCardBoard);
        cardPicker.setScene(scene);
        cardPicker.showAndWait();
    }

    @FXML
    private void market(ActionEvent actionEvent) throws Exception{
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

        for(int marketColumn=0;marketColumn<4;marketColumn++)
            for(int marketRow=0;marketRow<3;marketRow++) {
                marketPane.add(activeMarket[marketColumn][marketRow] = new ImageView(super.getImage(this.client.getUserInteraction().getGame().getMarket().getMarble(marketColumn, marketRow))), marketColumn, marketRow);
                extraMarble.setImage(super.getImage(this.client.getUserInteraction().getGame().getMarket().getExtraMarble()));

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
                        marketBtn.setText("End Market");
                    }
                });
        button.setOnAction(e->market.close());
        Scene scene = new Scene(marketParent);
        market.setScene(scene);
        market.showAndWait();
    }

    private boolean addResource(int shelfNumber, String resource){
        ResourceType resourceType=stringToResource(resource);
        RedWarehouseDepot warehouse = null;
        if(shelfNumber>=0&&shelfNumber<=2){
            warehouse = myPlayer().getWarehouse().getWarehouseDepots()[shelfNumber];
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

    protected void board() throws IOException {
        Parent board;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/Board.fxml"));
        loader.setController(this);
        board = loader.load();
        if(this.client.getUserInteraction().getGame().getGameType().equals(GameType.SINGLEPLAYER))
        {
            inkwell.setImage(getImage(false));
        }
        else if(!this.client.getUserInteraction().getGame().getMyPlayer().hasInkwell())
            inkwell.setImage(null);

        Scene scene = new Scene(board);
        refresh();
        Stage window = getStage();
        window.setScene(scene);
    }

    protected void leaderChoser(boolean discount) throws IOException {
            RedLeaderCard[] leaderCards = this.client.getUserInteraction().getGame().getMyPlayer().getLeaderCards();
            LeaderCardAction action;
            if(discount)
                action=LeaderCardAction.DISCOUNT;
            else
                action=LeaderCardAction.WHITEMARBLE;
            Image card2 = getImage(leaderCards[1].getCardId(),leaderCards[1].getAction().equals(action));
            Image card3 = getImage(leaderCards[2].getCardId(),leaderCards[1].getAction().equals(action));

            Parent leaderCard;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/Leader.fxml"));
            loader.setController(this);
            leaderCard = loader.load();
            leaderSelection = new Stage();
            leaderSelection.initModality(Modality.APPLICATION_MODAL);
            leaderSelection.setTitle("Choose a Leader Card");
            Scene scene = new Scene(leaderCard);
            card1.setDisable(true);
            card4.setDisable(true);
            if(discount)
            cardTitle.setText("Would you like a discount?");
            else
                cardTitle.setText("Which conversion would you like?");
            card2image.setImage(card2);
            card3image.setImage(card3);
            leaderSelection.setScene(scene);
            leaderSelection.showAndWait();
    }

    private void leaderCardCheck(ActionEvent event,FXMLLoader loader){
        //Se sono entrambe WM deve sceglierne una
        //Se una non è WM deve sceglierne fino a 2
        card2image.setPickOnBounds(true);
        card3image.setPickOnBounds(true);
        card2image.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                card2.setSelected(!card2.isSelected());
            }
        });
        card3image.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                card3.setSelected(!card3.isSelected());
            }
        });
        confirmLeader.setOnAction((EventHandler) event1 -> {
            int selected=0;
            if(card2.isSelected()){
                selected++;
            }
            if(card3.isSelected()){
                selected++;
            }
            if(selected==2){

            }else {
                try {
                    if(selected==1)
                        displayError("You've picked just "+selected+" leader card!\nYou've got to pick two");
                    else{
                        displayError("You've picked "+selected+" leader cards!\nYou've got to pick two");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void turnHandler(){
        if(this.client.getUserInteraction().getGame().getGameType().equals(GameType.SINGLEPLAYER)){
            if(this.client.getUserInteraction().getGame().getMyPlayer().getPossibleActions().contains(ActionType.END_TURN_SINGLEPLAYER))
                sendAction(new EndTurn());
            else displayError("You can't pass the turn now!");
        }
        else
        {
            if(this.client.getUserInteraction().getGame().getMyPlayer().getPossibleActions().contains(ActionType.END_TURN))
                sendAction(new EndTurn());
            else displayError("You can't pass the turn now!");
        }
    }

    @FXML
    private void boardClickHandler(ActionEvent event){
        Node node = (Node) event.getTarget();
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
            } case "inkwell":{
                displayError("You have ye inkwell!");
            }
        }
    }

    @FXML
    public void activateProduction() throws Exception{
        RedCardSlot[] slots = this.client.getUserInteraction().getGame().getMyPlayer().getSlots().getSlots();

        Parent production;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/ProductionPowers.fxml"));
        loader.setController(this);
        production = loader.load();
        Stage productionChoice = new Stage();
        productionChoice.initModality(Modality.APPLICATION_MODAL);
        productionChoice.setTitle("Activate a production power");
        Scene scene = new Scene(production);

        Image card1img,card2img,card3img,card4img,card5img;

        if(slots[0].isEmpty()){
            card1img=getImage(64,false);
            card1.setDisable(true);
        }else{
            card1img=getImage(slots[0].getFirstCard().getCardId(),true);
        }
        if(slots[1].isEmpty()){
            card2img=getImage(64,false);
            card2.setDisable(true);
        }else{
            card2img=getImage(slots[0].getFirstCard().getCardId(),true);
        }
        if(slots[2].isEmpty()){
            card3img=getImage(64,false);
            card3.setDisable(true);
        }else{
            card3img=getImage(slots[0].getFirstCard().getCardId(),true);
        }
        if(this.client.getUserInteraction().getGame().getMyPlayer().getLeaderCards()[0].getAction().equals(LeaderCardAction.PRODUCTIONPOWER)&&this.client.getUserInteraction().getGame().getMyPlayer().getLeaderCards()[0].isActive()){
            card4img=getImage(this.client.getUserInteraction().getGame().getMyPlayer().getLeaderCards()[0].getCardId(),true);
        }else{
            card4img=getImage(64,false);
            card4.setDisable(true);
        }if(this.client.getUserInteraction().getGame().getMyPlayer().getLeaderCards()[1].getAction().equals(LeaderCardAction.PRODUCTIONPOWER)&&this.client.getUserInteraction().getGame().getMyPlayer().getLeaderCards()[1].isActive()){
            card5img=getImage(this.client.getUserInteraction().getGame().getMyPlayer().getLeaderCards()[1].getCardId(),true);
        }else{
            card5img=getImage(64,false);
            card5.setDisable(true);
        }

        card1image.setImage(card1img);
        card2image.setImage(card2img);
        card3image.setImage(card3img);
        card4image.setImage(card4img);
        card5image.setImage(card5img);
        leaderCardCheck(event,true);

        productionChoice.setScene(scene);
        productionChoice.showAndWait();
    }

    @FXML
    private void getResourcesGame() throws IOException {
        if(this.client.getUserInteraction().getGame().getMyPlayer().getPossibleActions().contains(ActionType.ADD_RESOURCE))
            getResources(false);
        else
            displayError("You don't have any resource left to store!");
    }

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

    private void sendAction(Action action){
        try {
            this.clientConnection.send(action);
        } catch (IOException e) {
            displayError(e.toString());
        }
    }

    protected void refresh(){
        setResourceData(getResourceData(this.client.getUserInteraction().getGame().getMyPlayer()));
        setCardData(getCardData(this.client.getUserInteraction().getGame().getMyPlayer()));
    }

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
