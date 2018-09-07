/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birds;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Abdelkader
 */
public class BirdsController implements Initializable {

    OrderedDictionary instance1;
    BirdRecord recordDisplayed;

    MediaPlayer mediaPlayer;

    @FXML
    private Button prevBtn;

    @FXML
    private Separator topHdivider;
    @FXML
    private Separator rightHdiv;

    @FXML
    private Separator leftHdiv;
    @FXML
    private Separator botHdivider;

    @FXML
    private TextField BirdnameField;

    @FXML
    private MenuBar mainMenu;

    @FXML
    private Button findBtn;

    @FXML
    private ComboBox<String> birdSize;

    @FXML
    private Label BirdDescripLabel;

    @FXML
    private BorderPane borderP1;

    @FXML
    private Button deleteBtn;

    @FXML
    private ImageView birdImage;

    @FXML
    private Label BirdnameLabel;

    @FXML
    private Button firstBtn;

    @FXML
    private Button stopBtn;

    @FXML
    private Button lastBtn;

    @FXML
    private Pane Pane1;

    @FXML
    private Button playBtn;

    @FXML
    private Button nextBtn;

    Node a;

    @FXML
    public void exit() {
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        birdSize.getItems().addAll("Small", "Medium", "Large");
        a = borderP1.getLeft();
//        borderP1.setRight(null);
        borderP1.setLeft(null);
        //borderP1.setBottom(null);

        //borderP1.setCenter(null);
    }

    public void searchBird() throws MalformedURLException {
        //Get from text field of Birdname search and get size from small,medium and large
        int sizeOfBird = 0;

        if (birdSize.getValue() == "Small") {
            sizeOfBird = 1;
        } else if (birdSize.getValue() == "Medium") {
            sizeOfBird = 2;
        } else {
            sizeOfBird = 3;
        }
        DataKey key1 = new DataKey(BirdnameField.getText(), sizeOfBird);

        try {
            recordDisplayed = instance1.find(key1);
            //Set Image, Set Title of Birdname, Set Description
            BirdnameLabel.setText(recordDisplayed.getDataKey().getbirdName());
            BirdDescripLabel.setText(recordDisplayed.getAbout());
            File file = new File("D:\\Toni 9\\College Work Western\\Second Year- Software Engineering\\SE2205 Algorithms and Data Structures\\SE2205B-Assignment2\\Birds\\src\\images\\" + recordDisplayed.getImage());
            String localURl = file.toURI().toURL().toString();
            Image image1 = new Image(localURl);
            birdImage.setImage(image1);
        } catch (DictionaryException ex) {
            Logger.getLogger(BirdsController.class.getName()).log(Level.SEVERE, null, ex);
            displayAlert(ex.getMessage());
        }
    }

    public void play() throws MalformedURLException {
        File file = new File("D:\\Toni 9\\College Work Western\\Second Year- Software Engineering\\SE2205 Algorithms and Data Structures\\SE2205B-Assignment2\\Birds\\src\\sounds\\" + recordDisplayed.getSound());
        String localURl = file.toURI().toURL().toString();
        Media sound = new Media(localURl);
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        //plays sound
        playBtn.setDisable(true);//Change
        stopBtn.setDisable(false);
    }

    public void stop() {
        mediaPlayer.stop();
        stopBtn.setDisable(true);
        playBtn.setDisable(false);
    }

    public void delete() throws MalformedURLException {
        if (instance1.root.left==null && instance1.root.right==null){
            borderP1.setLeft(null);
                displayAlert("No more birds in the database to show");
        
        }
        else {
        try {
            if (mediaPlayer != null) {
                stop();
            }
            BirdRecord resettingBird = recordDisplayed;
            //if ((instance1.successor(recordDisplayed.getDataKey()) != null) || (instance1.predecessor(recordDisplayed.getDataKey()) != null)) {
                if (instance1.successorHelper3(recordDisplayed.getDataKey())== true) {
                    resettingBird = instance1.successor(recordDisplayed.getDataKey());
                } else if (instance1.predecessorHelper2(recordDisplayed.getDataKey()) ==true) {
                    resettingBird = instance1.predecessor(recordDisplayed.getDataKey());
                }

                instance1.remove(recordDisplayed.getDataKey());
                //Set bird title and description GUI to previous or next in instance using successor or predecessor
                recordDisplayed = resettingBird;

                BirdnameLabel.setText(recordDisplayed.getDataKey().getbirdName());
                BirdDescripLabel.setText(recordDisplayed.getAbout());
                File file = new File("D:\\Toni 9\\College Work Western\\Second Year- Software Engineering\\SE2205 Algorithms and Data Structures\\SE2205B-Assignment2\\Birds\\src\\images\\" + recordDisplayed.getImage());
                String localURl = file.toURI().toURL().toString();
                Image image1 = new Image(localURl);
                birdImage.setImage(image1);
            //}
//            } else if (instance1.isEmpty()) {
//                borderP1.setLeft(null);//Reset to load first scene.
           // }
        } catch (DictionaryException ex) {
            Logger.getLogger(BirdsController.class.getName()).log(Level.SEVERE, null, ex);
            //displayAlert(ex.getMessage());

        }
        }
    }

    public void nextBird() throws MalformedURLException {
        try {
            recordDisplayed = instance1.successor(recordDisplayed.getDataKey());
            BirdnameLabel.setText(recordDisplayed.getDataKey().getbirdName());
            BirdDescripLabel.setText(recordDisplayed.getAbout());
            File file = new File("D:\\Toni 9\\College Work Western\\Second Year- Software Engineering\\SE2205 Algorithms and Data Structures\\SE2205B-Assignment2\\Birds\\src\\images\\" + recordDisplayed.getImage());
            String localURl = file.toURI().toURL().toString();
            Image image1 = new Image(localURl);
            birdImage.setImage(image1);
            if (mediaPlayer != null) {
                stop();
            }
        } catch (DictionaryException ex) {
            Logger.getLogger(BirdsController.class.getName()).log(Level.SEVERE, null, ex);
            displayAlert(ex.getMessage());
        }
    }

    public void previousBird() throws MalformedURLException {
        try {
            recordDisplayed = instance1.predecessor(recordDisplayed.getDataKey());
            BirdnameLabel.setText(recordDisplayed.getDataKey().getbirdName());
            BirdDescripLabel.setText(recordDisplayed.getAbout());
            File file = new File("D:\\Toni 9\\College Work Western\\Second Year- Software Engineering\\SE2205 Algorithms and Data Structures\\SE2205B-Assignment2\\Birds\\src\\images\\" + recordDisplayed.getImage());
            String localURl = file.toURI().toURL().toString();
            Image image1 = new Image(localURl);
            birdImage.setImage(image1);
            if (mediaPlayer != null) {
                stop();
            }
        } catch (DictionaryException ex) {
            Logger.getLogger(BirdsController.class.getName()).log(Level.SEVERE, null, ex);
            displayAlert(ex.getMessage());
        }
    }

    public void firstBird() throws MalformedURLException {
        try {
            recordDisplayed = instance1.smallest();
            BirdnameLabel.setText(recordDisplayed.getDataKey().getbirdName());
            BirdDescripLabel.setText(recordDisplayed.getAbout());
            File file = new File("D:\\Toni 9\\College Work Western\\Second Year- Software Engineering\\SE2205 Algorithms and Data Structures\\SE2205B-Assignment2\\Birds\\src\\images\\" + recordDisplayed.getImage());
            String localURl = file.toURI().toURL().toString();
            Image image1 = new Image(localURl);
            birdImage.setImage(image1);
            if (mediaPlayer != null) {
                stop();
            }
        } catch (DictionaryException ex) {
            Logger.getLogger(BirdsController.class.getName()).log(Level.SEVERE, null, ex);
            displayAlert(ex.getMessage());
        }
    }

    public void lastBird() throws MalformedURLException {
        try {
            recordDisplayed = instance1.largest();
            BirdnameLabel.setText(recordDisplayed.getDataKey().getbirdName());
            BirdDescripLabel.setText(recordDisplayed.getAbout());
            File file = new File("D:\\Toni 9\\College Work Western\\Second Year- Software Engineering\\SE2205 Algorithms and Data Structures\\SE2205B-Assignment2\\Birds\\src\\images\\" + recordDisplayed.getImage());
            String localURl = file.toURI().toURL().toString();
            Image image1 = new Image(localURl);
            birdImage.setImage(image1);
            if (mediaPlayer != null) {
                stop();
            }
        } catch (DictionaryException ex) {
            Logger.getLogger(BirdsController.class.getName()).log(Level.SEVERE, null, ex);
            displayAlert(ex.getMessage());
        }
    }

    public void loadDictionary() throws DictionaryException, MalformedURLException, FileNotFoundException {

        borderP1.setLeft(a);

        instance1 = new OrderedDictionary();
        String name;

        int i = 0;
        String nowSize;
        int realSize;

        DataKey[] key;
        BirdRecord[] records;

        key = new DataKey[20];
        records = new BirdRecord[20];

        Scanner s = new Scanner(new File("BirdsDatabase.txt"));
        while (i < 20) {
            //name=s.nextLine();
            //System.out.println(name);
            nowSize = s.nextLine();
            System.out.println("Bird's Size is" + nowSize);//FOR TESTING
            name = s.nextLine();
            System.out.println("Bird's Name is" + name);//FOR TESTING
            if (nowSize.equals("1")) {
                realSize = 1;
            } else if (nowSize.equals("2")) {
                realSize = 2;
            } else {
                realSize = 3;
            }
            key[i] = new DataKey(name, realSize);

            records[i] = new BirdRecord(key[i], s.nextLine(), name + ".mp3", name + ".jpg");
            System.out.println("Bird's description is" + records[i].getAbout());
            i++;//Need to for assigning records and key array
        }

        for (int x = 0; x < 20; x++) {
            // System.out.println("Before inserting: "+records[x].getDataKey().getbirdName());//For testing Purposes only 
            instance1.insert(records[x]);
        }

        // instance1.display(instance1.root);// For testing purposes only
        recordDisplayed = instance1.smallest();
        System.out.println("Smallest is " + recordDisplayed.getDataKey().getbirdName());
        //Now instantiate this one
        if (!(instance1.isEmpty())) {
            // Instantiate the 3 green buttons up top which are  First,Next, Previous and Last// Done
            // Instantiate and Set Title Text of Bird to first Bird Record.Datakey.birdname and description too
            BirdnameLabel.setText(recordDisplayed.getDataKey().getbirdName());
            BirdDescripLabel.setText(recordDisplayed.getAbout());

            File file = new File("D:\\Toni 9\\College Work Western\\Second Year- Software Engineering\\SE2205 Algorithms and Data Structures\\SE2205B-Assignment2\\Birds\\src\\images\\" + recordDisplayed.getImage());
            String localURl = file.toURI().toURL().toString();
            Image image1 = new Image(localURl);
            birdImage.setImage(image1);

            firstBtn.setStyle("-fx-background-color: palegreen;");
            nextBtn.setStyle("-fx-background-color: palegreen;");
            prevBtn.setStyle("-fx-background-color: palegreen;");
            lastBtn.setStyle("-fx-background-color: palegreen;");
            deleteBtn.setStyle("-fx-background-color: red;");
            playBtn.setStyle("-fx-background-color: green;");
            stopBtn.setStyle("-fx-background-color: green;");
            topHdivider.setStyle("-fx-background-color: black;");
            botHdivider.setStyle("-fx-background-color: black;");
            leftHdiv.setStyle("-fx-background-color: black;");
            rightHdiv.setStyle("-fx-background-color: black;");

            // Instantiate Red Button with name Delete//Done
            // Instantiate Image view pane, set image view pane with image of first bird//Done
            // Instantiate Center Border Right part with Play and Stop buttons//Done
            // Instantiate Text Field Bird name and Bird Size Combo box with a button find
        }

    }

    private void displayAlert(String msg) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AlertMessages.fxml"));
            Parent ERROR = loader.load();
            AlertMessagesController controller = (AlertMessagesController) loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.getIcons().add(new Image("file:src/birds/WesternLogo.png"));
            stage.setTitle("Dictionary Exception");
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

        }
    }

}
