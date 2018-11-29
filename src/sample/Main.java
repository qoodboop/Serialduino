package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

public class Main extends Application implements Observer {

                private static XYChart.Series seriesHumidity;
                private static XYChart.Series seriesTemperature;

    public static void main(String[] args) {
                    launch(args);
                    System.out.println("TEST");

                }


                @Override
                public void start(Stage primaryStage) throws Exception{
                   // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    primaryStage.setTitle("Area Chart ");
                    //primaryStage.setScene(new Scene(root, 300, 275));

                    final NumberAxis xAxis = new NumberAxis(1, 31, 1);
                    final NumberAxis yAxis = new NumberAxis();
                    final AreaChart<Number,Number> areachart = new AreaChart<Number,Number>(xAxis,yAxis);
                    areachart.setTitle("controle de la temperature");

                    seriesHumidity = new XYChart.Series();
                    seriesHumidity.setName("Humidity");

                     seriesTemperature= new XYChart.Series();
                    seriesTemperature.setName("Temperature");


                    Scene scene  = new Scene(areachart,800,600);

                    areachart.getData().addAll(seriesHumidity,seriesTemperature);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    TimeUnit.SECONDS.sleep(5); // METTRE une valeur qui est en opposition de phase avec les envois de l'arduino


                    Commduino comm = new Commduino(this);
                    Thread t = new Thread(comm);
                    t.start();
                    //Controller COntro = new Controller();
                    //COntro.test();

                    //COntro.TEST();

                        seriesHumidity.getData().add(new XYChart.Data(1,16));
                        seriesHumidity.getData().add(new XYChart.Data(10, 10));
                        seriesHumidity.getData().add(new XYChart.Data(20, 3));
                        seriesHumidity.getData().add(new XYChart.Data(30, 8));

                        seriesTemperature.getData().add(new XYChart.Data(1,2));
                        seriesTemperature.getData().add(new XYChart.Data(10, 30));
                        seriesTemperature.getData().add(new XYChart.Data(20, 9));
                        seriesTemperature.getData().add(new XYChart.Data(30, 20));


                }


    @Override
    public void update(Observable o, Object arg) {
        System.out.println("blzalfvedb");

    }
}
