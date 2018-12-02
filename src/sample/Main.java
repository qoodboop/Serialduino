package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.stage.Stage;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application implements Observer {


    private static XYChart.Series seriesHumidity;
    private static XYChart.Series seriesTemperature;
    private float temperature ;



    private float humidity ;


    public static XYChart.Series getSeriesHumidity() {
        return seriesHumidity;
    }

    public static void setSeriesHumidity(XYChart.Series seriesHumidity) {
        Main.seriesHumidity = seriesHumidity;
    }

    public static XYChart.Series getSeriesTemperature() {
        return seriesTemperature;
    }

    public static void setSeriesTemperature(XYChart.Series seriesTemperature) {
        Main.seriesTemperature = seriesTemperature;
    }
    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getSecond() {
        return second;
    }

    public void setSecond() {
        second += 1;
    }

    float second  ;


    public static void main(String[] args) {
                    launch(args);
                    System.out.println("TEST");

                }
   // private static float second ;
    private static float minute;
    private static float hour ;

                @Override
                public void start(Stage primaryStage) throws Exception{
                   // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    primaryStage.setTitle("Area Chart ");
                    //primaryStage.setScene(new Scene(root, 300, 275));

                    final NumberAxis xAxis = new NumberAxis(1, 81, 3);
                    final NumberAxis yAxis = new NumberAxis(0,100,5);
                    final AreaChart<Number,Number> areachart = new AreaChart<Number,Number>(xAxis,yAxis);
                    areachart.setTitle("control de la temperature");

                    seriesHumidity = new XYChart.Series();
                    seriesHumidity.setName("Humidity");

                     seriesTemperature= new XYChart.Series();
                     seriesTemperature.setName("Temperature");



                    Scene scene  = new Scene(areachart,800,600);

                    areachart.getData().addAll(seriesHumidity,seriesTemperature);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    TimeUnit.SECONDS.sleep(2); // METTRE une valeur qui est en opposition de phase avec les envois de l'arduino


                    Commduino comm = new Commduino(this);
                    Thread t = new Thread(comm);
                    t.start();




                }


    @Override
    public void update(Observable o, Object args) {
        setSecond();
        //System.out.println("Update ! " + args);
       //Platform.runLater(() -> seriesHumidity.getData().add(new XYChart.Data(LocalDateTime.now().getSecond(), 30)));
        //String arg =  new String(args);
        String arg = String.valueOf(args);  //method 1
       //System.out.println(getSecond());
        // String arg = "" + args;   //method 2
        // String arg = args.toString();
        System.out.println(arg);
            //Pattern T = Pattern.compile("[-+]?([0-9]Temperature = *\\.[0-9]+|[0-9]+)  .* [-+]?([0-9]Humidity = *\\.[0-9]+|[0-9]+) ");
            Pattern T = Pattern.compile(" [0-9]*\\.[0-9]+");
           // Pattern H = Pattern.compile("[-+]?([0-9]Humidity = *\\.[0-9]+|[0-9]+)");
            Matcher t = T.matcher(arg);
            //Matcher h = H.matcher(arg);
            if( t.find() ){
                temperature = Float.parseFloat(t.group());
             //   System.out.println(temperature);
                int fin =t.end();
                t.find(fin);
                if(t.find(fin)) {
                    humidity = Float.parseFloat(t.group());
               //     System.out.println(humidity);
                }
            }
           /* if( h.find() ){
                this.humidity = Float.parseFloat(h.group());
                System.out.println(this.humidity);
            }*/
        //System.out.println(this.humidity + " TEST " + this.temperature);
        Platform.runLater(() -> this.seriesHumidity.getData().add(new XYChart.Data(this.getSecond(), getHumidity())));
        Platform.runLater(() -> this.seriesTemperature.getData().add(new XYChart.Data(this.getSecond() ,getTemperature())));
        //Commduino com2 = new Commduino(this) ;
        //this.seriesHumidity.getData().add(new XYChart.Data(1,16));
        //this.seriesTemperature.getData().add(new XYChart.Data(1,2));
}}
