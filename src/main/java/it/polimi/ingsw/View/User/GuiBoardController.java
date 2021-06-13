package it.polimi.ingsw.View.User;

import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.WarehouseDepot;
import it.polimi.ingsw.View.Client.Client;
import it.polimi.ingsw.View.Client.ClientConnection;
import it.polimi.ingsw.View.ReducedModel.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
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

public class GuiBoardController extends GuiInitController{
    private Stage cardPicker;
    private Stage market;
    @FXML private Text cardTitle;
    @FXML private GridPane cardPane;
    private ImageView[][] card;
    private int cardColumn;
    private int cardRow;
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

    /**
     * Constructor for Gui
     *
     * @param client
     */
    public GuiBoardController(Client client,ClientConnection clientConnection,ClientExceptionHandler clientExceptionHandler) {
        super(client,clientConnection,clientExceptionHandler);
    }

    public void dragDrop(Stage stage){
        stage.setTitle("Hello Drag And Drop");

        Group root = new Group();
        Scene scene = new Scene(root, 400, 200);
        scene.setFill(Color.LIGHTGREEN);

        final Text source = new Text(50, 100, "DRAG ME");
        source.setScaleX(2.0);
        source.setScaleY(2.0);

        final Text target = new Text(250, 100, "DROP HERE");
        target.setScaleX(2.0);
        target.setScaleY(2.0);

        source.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");

                /* allow any transfer mode */
                Dragboard db = source.startDragAndDrop(TransferMode.ANY);

                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(source.getText());
                db.setContent(content);

                event.consume();
            }
        });

        target.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 * and if it has a string data */
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        target.setOnDragEntered(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasString()) {
                    target.setFill(Color.GREEN);
                }

                event.consume();
            }
        });

        target.setOnDragExited(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                target.setFill(Color.BLACK);

                event.consume();
            }
        });

        target.setOnDragDropped(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                System.out.println("onDragDropped");
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    target.setText(db.getString());
                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        source.setOnDragDone(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture ended */
                System.out.println("onDragDone");
                /* if the data was successfully moved, clear it */
                if (event.getTransferMode() == TransferMode.MOVE) {
                    source.setText("");
                }

                event.consume();
            }
        });

        root.getChildren().add(source);
        root.getChildren().add(target);
        stage.setScene(scene);
        stage.show();
    }

    public void payResource(RedResourceStack resourceStack) throws Exception{
            Parent payResource;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/PayResource.fxml"));
            loader.setController(this);
            payResource = loader.load();
            Stage payResources = new Stage();
            payResources.initModality(Modality.APPLICATION_MODAL);
            payResources.setTitle("Pay Resource");
            Player currentPlayer = this.getGame().getMyPlayer();
            remCoins.setText(String.valueOf(resourceStack.getResource(ResourceType.COINS)));
            remServants.setText(String.valueOf(resourceStack.getResource(ResourceType.SERVANTS)));
            remShields.setText(String.valueOf(resourceStack.getResource(ResourceType.SHIELDS)));
            remStones.setText(String.valueOf(resourceStack.getResource(ResourceType.STONES)));

        if(warehouse1.getImage()!=null)
            warehouse1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(resourceStack.hasResource(currentPlayer.getWarehouse().getWarehouseDepots()[0].getResourceType())){
                        warehouse1.setImage(null);
                    }else
                    {
                        displayError("This resource is not required");
                    }
                }
            });


        Scene scene = new Scene(payResource);
        payResources.showAndWait();
    }

    private Object[] getResourceData(Player player){
        Object[] resourceData;
        resourceData = new Object[2];
        Image[] resourceImages;
        resourceImages = new Image[10];
        String[] resourceText;
        resourceText = new String[4];

        RedWarehouseDepot warehouseDepots[] = player.getWarehouse().getWarehouseDepots();
        //Gets the images from the Warehouse of the player
        if(warehouseDepots[0].isEmpty())
            resourceImages[0] = null;
        else
            resourceImages[0]=getImage(warehouseDepots[0].getResourceType());
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
        if(warehouseDepots[2].isEmpty()){
            resourceImages[3]=null;
            resourceImages[4]=null;
            resourceImages[5]=null;
        }else{
            Image resource = getImage(warehouseDepots[2].getResourceType());
            resourceImages[3]=resource;
            if(warehouseDepots[2].getStoredResources()==2){
                resourceImages[4]=resource;
                resourceImages[5]=null;
            }
            else if(warehouseDepots[1].getStoredResources()==3){
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

    private void setResourceData(Object[] resourceData){
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

    private Image[] getCardData(Player player){
        Image[] cardData;
        cardData = new Image[11];

        RedLeaderCard[] leaderCards = player.getLeaderCards();

        //Show back of unactivated LeaderCards if looking another player's board
        if(getGame().getMyPlayer().equals(player))
        {
            cardData[9] = getImage(leaderCards[0].getCardId(),true);
            cardData[10] = getImage(leaderCards[1].getCardId(),true);
        }else
        {
            cardData[9] = getImage(leaderCards[0].getCardId(),leaderCards[0].isActive());
            cardData[10] = getImage(leaderCards[1].getCardId(),leaderCards[0].isActive());
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

    private void setCardData(Image[] cardData){
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

    public void cardPicker(boolean singleplayerDiscard) throws Exception{
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
                    cardPane.add(card[cardColumn][cardRow] = new ImageView(super.getImage(1+cardColumn+cardRow,true)),cardColumn,cardRow);
                    //cardPane.add(card[cardColumn][cardRow] = new ImageView(super.getImage(super.getGame().getDevelopmentCardTable().getTopCardFromDeck(cardColumn,cardRow).getCardId(),true)),cardColumn,cardRow);
                    card[cardColumn][cardRow].setFitHeight(190);
                    card[cardColumn][cardRow].setFitWidth(125);
                    int finalCardColumn = cardColumn;
                    int finalCardRow = cardRow;
                    card[cardColumn][cardRow].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            System.out.print("Ciao bel gabibbo, carta colonna: "+ finalCardColumn);
                            System.out.println(",riga: "+ finalCardRow);
                            //if card is the required type...
                            card[finalCardColumn][finalCardRow].setImage(getImage(1+finalCardColumn+finalCardRow,false));
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
                    cardPane.add(card[cardColumn][cardRow] = new ImageView(super.getImage(1+cardColumn+cardRow,true)),cardColumn,cardRow);
                    card[cardColumn][cardRow].setFitHeight(190);
                    card[cardColumn][cardRow].setFitWidth(125);
                    int finalCardColumn = cardColumn;
                    int finalCardRow = cardRow;
                    card[cardColumn][cardRow].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            System.out.print("Ciao bel gabibbo, carta colonna: "+ finalCardColumn);
                            System.out.println(",riga: "+ finalCardRow);
                        }
                    });
                }
        }
        Scene scene = new Scene(developmentCardBoard);
        cardPicker.setScene(scene);
        cardPicker.showAndWait();
    }

    public void market() throws Exception{
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
            for(int marketRow=0;marketRow<3;marketRow++)
            {
                marketPane.add(activeMarket[marketColumn][marketRow] = new ImageView(super.getImage(Marble.YELLOW)),marketColumn,marketRow);
                //marketPane.add(activeMarket[marketColumn][marketRow] = new ImageView(super.getImage(super.getGame().getMarket().getMarble(marketColumn,marketRow))),marketColumn,marketRow);
                extraMarble.setImage(super.getImage(Marble.YELLOW));
                //extraMarble.setImage(super.getImage(super.getGame().getMarket().getExtraMarble()));

                activeMarket[marketColumn][marketRow].setFitHeight(41);
                activeMarket[marketColumn][marketRow].setFitWidth(41);
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
                    if(marketColumn1.isArmed()){
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

            }
        Scene scene = new Scene(marketParent);
        market.setScene(scene);
        market.showAndWait();
    }

}
