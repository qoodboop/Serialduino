package sample;
import com.fazecast.jSerialComm.*  ;

import java.io.InputStream;
import java.io.IOException;

public class Controller {

    public void test(){
                SerialPort comPort = SerialPort.getCommPorts()[0];
                InputStream in = comPort.getInputStream();

                 SerialPort ports[] = SerialPort.getCommPorts();
                int i = 1;
                //User port selection
                System.out.println("COM Ports available on machine");

                for (SerialPort port : ports) {
                //iterator to pass through port array
                System.out.println(i++ + ": " + port.getSystemPortName()); //print windows com ports
                }

        try
                {
                    comPort.openPort();
                   // comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
                    //for (int j = 0; j < 1000; ++j)
                     //   System.out.print((char)in.read());
                    //in.close();

                    while (true)
                    {
                        while (comPort.bytesAvailable() == 0)
                            Thread.sleep(20);

                        byte[] readBuffer = new byte[comPort.bytesAvailable()];
                        int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                        //System.out.println("Read " + numRead + " bytes.");
                        //System.out.println("Read " + readBuffer + " bytes.");
                        String s = new String(readBuffer);
                        System.out.println(s);
                    }

                }
                    catch (Exception e){ e.printStackTrace(); comPort.closePort(); }
            }
            public void TEST(/*String[] args*/)throws IOException, InterruptedException {
                SerialPort sp = SerialPort.getCommPort("/dev/ttyACM0"); // device name TODO: must be changed
                sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
                sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written

                if (sp.openPort()) {
                    System.out.println("Port is open :)");
                } else {
                    System.out.println("Failed to open port :(");
                    return;
                }

                /*for (Integer i = 0; i < 5; ++i) {
                    sp.getOutputStream().write(i.byteValue());
                    sp.getOutputStream().flush();
                    System.out.println("Sent number: " + i);
                    Thread.sleep(1000);
                }
*/
                if (sp.closePort()) {
                    System.out.println("Port is closed :)");
                } else {
                    System.out.println("Failed to close port :(");
                    return;
                }

            }
}
