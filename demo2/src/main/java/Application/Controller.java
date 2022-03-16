package Application;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;

import Application.TemperatureSystem;
import Application.dayThread;
import MyLog.Log;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import Application.UserInterface;
import Objects.Plant;
import Objects.Bush;
import Objects.Tree;
import Objects.Flower;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Controller extends Thread{

    public Thread cthread;
    UserInterface ui=new UserInterface();
    Log log=new Log();
    dayThread dt= new dayThread();

    private double sceneWidth = 600;
    private double sceneHeight = 600;

    private int n = 6;
    private int m = 6;

    double gridWidth = sceneWidth / n;
    double gridHeight = sceneHeight / m;

    MyNode[][] playfield = new MyNode[n][m];

    @FXML
    public Label dayLabel;
    @FXML
    public Label tempLabel1;
    @FXML
    public Label tempLabel2;
    @FXML
    public Label tempLabel3;
    @FXML
    public Label tempLabel4;

    @Override
    public void run() {

    }
    public void start(){
        if(this.cthread == null){
            this.cthread = new Thread(this, "MyTimerThread");
            this.cthread.start();
        }
    }

    @FXML
    public void systemStart(){
        start();
    }

    @FXML
    public Button startButton;
    public void dayPass()
    {
        startButton.setOnAction(actionEvent -> UserInterface.oneDayPass());
    }


    @FXML
    public Button Tree;
    public void plantTree()
    {

        Tree.setOnAction(actionEvent ->
        {
            TextField textField1 = new TextField();
            TextField textField2 = new TextField();
            TextField textField3 = new TextField();
            Text text = new Text("Planting a Tree");
            Label l1 = new Label("X: ");
            Label l2 = new Label("Y: ");
            Label l3 = new Label("Name it! ");


            Button button = new Button("Plant!");
            button.setOnAction(actionEvent1 ->
            {
                int i1=Integer.parseInt((textField1.getText()));
                int i2=Integer.parseInt((textField2.getText()));
                String t=textField1.getText();
                UserInterface.growTree(i1, i2, t+" Tree");
            });
            HBox box=new HBox(7);
            box.getChildren().addAll(l1,textField1,l2,textField2,l3,textField3);
            //Group root = new Group(box,button,text);
        });
    }

    @FXML
    public Button Flower;
    public void plantFlower()
    {
        Flower.setOnAction(actionEvent ->
        {
            TextField textField1 = new TextField();
            TextField textField2 = new TextField();
            TextField textField3 = new TextField();
            Text text = new Text("Planting a flower");
            Label l1 = new Label("X: ");
            Label l2 = new Label("Y: ");
            Label l3 = new Label("Name it! ");


            Button button = new Button("Plant!");
            button.setOnAction(actionEvent1 ->
            {
                int i1=Integer.parseInt((textField1.getText()));
                int i2=Integer.parseInt((textField2.getText()));
                String t=textField1.getText();
                UserInterface.growTree(i1, i2, t+" flower");
            });
            HBox box=new HBox(7);
            box.getChildren().addAll(l1,textField1,l2,textField2,l3,textField3);
            Group root = new Group(box,button,text);
        });
    }

    @FXML
    public Button Bush;
    public void plantBush()
    {
        Bush.setOnAction(actionEvent ->
        {
            TextField textField1 = new TextField();
            TextField textField2 = new TextField();
            TextField textField3 = new TextField();
            Text text = new Text("Planting a Bush");
            Label l1 = new Label("X: ");
            Label l2 = new Label("Y: ");
            Label l3 = new Label("Name it! ");


            Button button = new Button("Plant!");
            button.setOnAction(actionEvent1 ->
            {
                int i1=Integer.parseInt((textField1.getText()));
                int i2=Integer.parseInt((textField2.getText()));
                String t=textField1.getText();
                UserInterface.growTree(i1, i2, t+" Bush");
            });
            HBox box=new HBox(7);
            box.getChildren().addAll(l1,textField1,l2,textField2,l3,textField3);
            Group root = new Group(box,button,text);
        });
    }

    @FXML
    public Button Sprinkler;
    public void onSpin()
    {
        Tree.setOnAction(actionEvent ->
        {
            TextField textField1 = new TextField();
            TextField textField2 = new TextField();
            Text text = new Text("Placing a Sprinkler");
            Label l1 = new Label("X: ");
            Label l2 = new Label("Y: ");


            Button button = new Button("Place");
            button.setOnAction(actionEvent1 ->
            {
                int i1=Integer.parseInt((textField1.getText()));
                int i2=Integer.parseInt((textField2.getText()));
                UserInterface.growTree(i1, i2, "Sprinkler");
            });
            HBox box=new HBox(7);
            box.getChildren().addAll(l1,textField1,l2,textField2);
            Group root = new Group(box,button,text);
        });
    }

    public static class MyNode extends StackPane {

        public MyNode( String name, double x, double y, double width, double height) {

            // create rectangle
            Rectangle rectangle = new Rectangle( width, height);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.LIGHTBLUE);

            // create label
            Label label = new Label( name);

            // set position
            setTranslateX( x);
            setTranslateY( y);

            getChildren().addAll( rectangle, label);

        }

    }

}