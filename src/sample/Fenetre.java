package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Fenetre extends Application {

   public void start(Stage primaryStage) {
       primaryStage.setTitle("Pimp My Fridge");
       Group root = new Group();
       Scene scene = new Scene(root,300, 250, Color.GRAY);
       Button btn = new Button();
       btn.setLayoutX(100);
       btn.setLayoutY(80);
       btn.setText("Lancer le chronomètre");
       btn.setOnAction(new EventHandler<ActionEvent>() {

           public void handle(ActionEvent event) {
                System.out.println("Hello World");
           }
       });




       //creation espace pour changer la température
       GridPane grid = new GridPane ();
       grid.setAlignment (Pos.TOP_CENTER);
       grid.setHgap (10);
       grid.setVgap (10);
       grid.setPadding(new Insets(10,10,10,10));
       Label changeTemp = new Label("Choisir la température du Frigo");
       grid.add(changeTemp, 0,0);
       TextField TempFridge =  new TextField();
       grid.add(TempFridge,0,1);

       //creation du menu deroulant avec affichage des données
       Menu menuVar = new Menu();
       MenuItem temperature = new MenuItem();
       MenuItem humidite = new MenuItem();
       MenuItem pointDeRosee = new MenuItem();

       //Menu déroulant
       menuVar.getItems().addAll(temperature, humidite, pointDeRosee);



       root.getChildren().add(btn);
       primaryStage.setScene(scene);
       primaryStage.se(true);
   }

    // fonction qui permet de choisir la température du fridge

}
