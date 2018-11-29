package sample;

import com.fazecast.jSerialComm.SerialPort;

import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commduino extends Observable implements Runnable {

    private float temp ;
    private float hum ;
    private String msg ;



    public Commduino(Observer observer){
        this.addObserver(observer);
    }




    public void setHum(float hum) {
        this.hum = hum;
    }

    public float getHum() {
        return hum;
    }


    public  void setTemp(float temp) {
        this.temp = temp;
    }

    public float getTemp() {
        return temp;
    }


    @Override
    public void run() {

        SerialPort comPort = SerialPort.getCommPorts()[0];
        InputStream in = comPort.getInputStream();
        SerialPort ports[] = SerialPort.getCommPorts();
        //User port selection
        System.out.println("COM Ports available on machine");

        for (SerialPort port : ports) {
            int i = 1;                                               //iterateur afin de passer dans le tableau des ports
            System.out.println(i++ + ": " + port.getSystemPortName()); //print windows com ports
        }

        try {
            comPort.openPort();
            comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);
            // comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
            //for (int j = 0; j < 1000; ++j)
            //   System.out.print((char)in.read());
            //in.close();

            while (true)
            {
                while (comPort.bytesAvailable() == 0)
                    Thread.sleep(200);

                byte[] readBuffer = new byte[comPort.bytesAvailable()];
                //byte[] readBuffer = new byte[30];
                int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                //System.out.println("Read " + numRead + " bytes.");
                //System.out.println("Read " + readBuffer + " bytes.");
                String s = new String(readBuffer);
                Scanner T = new Scanner(s);

                //String yourPattern= "(.*)(\\d+)(.*)";
                String yourPattern= "(.*)(\\b)(.*)";

                String nextMatch = T.findWithinHorizon(yourPattern,0);

                TimeUnit.SECONDS.sleep(1); // METTRE une valeur qui est en opposition de phase avec les envois de l'arduino

                if(nextMatch.contains("Temperature = ")){

                    System.out.println("TTTTTTTTTTTTT");
                    System.out.println("regex1 :"+ nextMatch);
                    Pattern p = Pattern.compile("[-+]?([0-9]*\\.[0-9]+|[0-9]+)");
                    TimeUnit.SECONDS.sleep(2);
                    Matcher m = p.matcher(nextMatch);
                    if( m.find() ){
                        //System.out.println( "-->>" + m.group() );
                        this.temp = Float.parseFloat(m.group());
                        System.out.println(temp);
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        System.out.println("TEMPERATURE ======" + getTemp());
                        System.out.println("\n");

                    }
                } else if (nextMatch.contains("Humidity = ")){

                    System.out.println("HHHHHHHHHHHHHHHHHHHH");
                    System.out.println("regex2 :"+ nextMatch);
                    Pattern p = Pattern.compile("[-+]?([0-9]*\\.[0-9]+|[0-9]+)");
                    TimeUnit.SECONDS.sleep(2);
                    Matcher m = p.matcher(nextMatch);
                    if( m.find() ){
                        //  System.out.println( "-->>" + m.group() );
                        this.hum = Float.parseFloat(m.group());
                        System.out.println(hum);
                        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                        System.out.println("\n");
                        System.out.println("HUMIDITé =====" + getHum());
                    }
                }

                msg = this.getTemp() + ";"+ this.getHum();
                setChanged();
                notifyObservers(msg);

            }

        }
        catch (Exception e){ e.printStackTrace(); comPort.closePort(); }

    }
}
