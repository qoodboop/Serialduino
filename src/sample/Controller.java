package sample;
import com.fazecast.jSerialComm.*  ;

import java.io.InputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

public class Controller {

    float hum ;
    float temp ;

    public void test(){
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
                                                  float f = Float.parseFloat(m.group());
                                                  System.out.println(f);
                                                  System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
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
                                                  float f = Float.parseFloat(m.group());
                                                  System.out.println(f);
                                                  System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                                                  System.out.println("\n");
                                              }
                                            }

                                        }

                            }
                    catch (Exception e){ e.printStackTrace(); comPort.closePort(); }
                          }
}
