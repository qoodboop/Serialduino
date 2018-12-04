package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.EventListener;
import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;

import Model.Model;
import View.Interface;

public class Controller implements EventListener{
	private Interface frame;
	SerialPort chosenPort;
	static int x = 0;
	static int temp = 20;
	static int hum = 60;
	private Model model;
	
	public Controller(){
		frame = new Interface();
		model = new Model();
		SerialPort[] portNames = SerialPort.getCommPorts();
		for(int i = 0; i < portNames.length; i++)
		frame.getDropdown().addItem(portNames[i].getSystemPortName());
		addEventListener();
		frame.setVisible(true);
	}
	
	
	public void addEventListener(){
		frame.getButton_1().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.setCon(model.getCon()-1);
				model.setCon(model.getCon()-1);				
			}});
		frame.getButton_2().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub				
				frame.setCon(model.getCon()+1);
				model.setCon(model.getCon()+1);
			}});
		frame.getButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(frame.getButton().getText().equals("Connect")) {
					// attempt to connect to the serial port
					chosenPort = SerialPort.getCommPort(frame.getDropdown().getSelectedItem().toString());
					chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
					if(chosenPort.openPort()) {
						frame.getButton().setText("Disconnect");
						frame.getDropdown().setEnabled(false);
					}
					
					// create a new thread that listens for incoming text and populates the graph
					Thread thread = new Thread(){
						@Override public void run() {
							Scanner scanner = new Scanner(chosenPort.getInputStream());
							int c = 0;
							while(scanner.hasNextLine()) {
								try {
									String line = scanner.nextLine();
									String[] parts = line.split("-");
									String part1 = parts[0];
									String part2 = parts[1];
									int number = Integer.parseInt(part2);
									if(part1.equals("t")) {
										temp = number;
										frame.setTemp(number);
										model.setTemp(number);
									}
									else if (part1.equals("h")){
										hum = number;
										frame.setHum(number);
										model.setHum(number);
									}
									else {
										System.out.println("Communication Error");
									}
									calculPdr(model.getTemp(), model.getHum());
									frame.getSeries().add(x++, temp);
									frame.getSeries2().add(x++, model.getPdr());
									OutputStream output = chosenPort.getOutputStream();
									String stop = "STOP";
									String go = "GO";
									if(model.getCon()>model.getTemp()){
										if(c==0){
											System.out.println("STOP");
											output.write(stop.getBytes());
											output.flush();
											c = 1;
										}
									}
									if(model.getCon()<=model.getTemp()){
										if(c==1){
											System.out.println("GO");
											output.write(go.getBytes());
											output.flush();
											c = 0;
										}
									}
									frame.repaint();
								} catch(Exception e) {}
							}
							scanner.close();
						}
					};
					thread.start();
					Thread thread2 = new Thread(){
						@Override public void run() {
							Scanner scanner2 = new Scanner(chosenPort.getInputStream());
							int i = 0;
							while(scanner2.hasNextLine()){
								if(model.getPdr()>(model.getTemp()-1)){
									if(i == 0){
										i = 1;
										frame.alertePdr();
									}									
								}
								else {
									i = 0;
								}
							}
						}
					};
					thread2.start();
					}
				 else {
					// disconnect from the serial port
					chosenPort.closePort();
					frame.getDropdown().setEnabled(true);
					frame.getButton().setText("Connect");
					frame.getSeries().clear();
					frame.getSeries2().clear();
					x = 0;
				}
			}
		});
	}
	
	private void calculPdr(int temp, int hum){
		double PDR;
		float humidity = hum;
		float rh = (float) humidity/100;	
		float temperature = temp;
		
		
		PDR = (((17.27 * temperature)/ (237.7 + temperature)) + Math.log(rh));
		PDR = ((237.7 * PDR)/(17.27 - PDR));
		model.setPdr(PDR);
		frame.setPdr(PDR);
	}
}
