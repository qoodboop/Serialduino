package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class Interface extends JFrame implements EventListener{

	private JPanel contentPane;

	
	private JLabel temp;
	private JLabel hum;
	private JLabel pdr;
	private JComboBox<String> Dropdown;
	private JButton button;
	private XYSeries series;
	private XYSeries series2;
	private XYSeries series3;
	private JPanel panel_2;
	private JButton button_1;
	private JLabel con;
	private JButton button_2;

	
	/**
	 * Create the frame.
	 */
	public Interface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		Dropdown = new JComboBox<String>();
		panel.add(Dropdown);
		
		button = new JButton("Connect");
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(4, 2, 0, 0));
		
		JLabel lblTemperature = new JLabel("Temp\u00E9rature :");
		lblTemperature.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperature.setForeground(Color.RED);
		panel_1.add(lblTemperature);
		
		temp = new JLabel("25 \u00B0C");
		temp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		temp.setHorizontalAlignment(SwingConstants.CENTER);
		temp.setForeground(Color.RED);
		panel_1.add(temp);
		
		JLabel lblHumidity = new JLabel("Humidit\u00E9 :");
		lblHumidity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHumidity.setHorizontalAlignment(SwingConstants.CENTER);
		lblHumidity.setForeground(Color.ORANGE);
		panel_1.add(lblHumidity);
		
		hum = new JLabel("70 %");
		hum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		hum.setHorizontalAlignment(SwingConstants.CENTER);
		hum.setForeground(Color.ORANGE);
		panel_1.add(hum);
		
		JLabel lblDewPoint = new JLabel("Point de ros\u00E9e :");
		lblDewPoint.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDewPoint.setHorizontalAlignment(SwingConstants.CENTER);
		lblDewPoint.setForeground(Color.BLUE);
		panel_1.add(lblDewPoint);
		
		pdr = new JLabel("18 \u00B0C");
		pdr.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pdr.setHorizontalAlignment(SwingConstants.CENTER);
		pdr.setForeground(Color.BLUE);
		panel_1.add(pdr);
		
		JLabel lblConsigne = new JLabel("Consigne :");
		lblConsigne.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsigne.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConsigne.setForeground(Color.GREEN);
		panel_1.add(lblConsigne);
		
		panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		button_1 = new JButton("-");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_2.add(button_1);
		
		con = new JLabel("0 \u00B0C");
		con.setForeground(Color.GREEN);
		con.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_2.add(con);
		
		button_2 = new JButton("+");
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_2.add(button_2);
		
		series = new XYSeries("Temperature");
		series2 = new XYSeries("Point de rosée");
		series3 = new XYSeries("Consigne");
		XYSeriesCollection dataset = new XYSeriesCollection(series);
		dataset.addSeries(series2);
		dataset.addSeries(series3);
		JFreeChart chart = ChartFactory.createXYLineChart("DHT Temperature Readings", "Time (seconds)", "Temperature (Degree Celsius)", dataset);
		contentPane.add(new ChartPanel(chart), BorderLayout.SOUTH);
		pack();
	}
	/**
	 * @return the series3
	 */
	public XYSeries getSeries3() {
		return series3;
	}
	/**
	 * @param series3 the series3 to set
	 */
	public void setSeries3(XYSeries series3) {
		this.series3 = series3;
	}
	/**
	 * @return the dropdown
	 */
	public JComboBox<String> getDropdown() {
		return Dropdown;
	}
	/**
	 * @param dropdown the dropdown to set
	 */
	public void setDropdown(JComboBox<String> dropdown) {
		Dropdown = dropdown;
	}
	/**
	 * @return the temp
	 */
	public JLabel getTemp() {
		return temp;
	}
	/**
	 * @param temp the temp to set
	 */
	public void setTemp(int temp) {
		this.temp.setText(temp + " °C");
	}
	/**
	 * @return the hum
	 */
	public JLabel getHum() {
		return hum;
	}
	/**
	 * @param hum the hum to set
	 */
	public void setHum(int hum) {
		this.hum.setText(hum + " %");
	}
	/**
	 * @return the pdr
	 */
	public JLabel getPdr() {
		return pdr;
	}
	/**
	 * @param pdr2 the pdr to set
	 */
	public void setPdr(double pdr2) {
		this.pdr.setText(String.format("%.1f", pdr2) + " °C");
	}
	/**
	 * @return the con
	 */
	public JLabel getCon() {
		return con;
	}
	/**
	 * @param con the con to set
	 */
	public void setCon(int con) {
		this.con.setText(con + " °C");
	}
	
	/**
	 * @return the button
	 */
	public JButton getButton() {
		return button;
	}
	/**
	 * @param button the button to set
	 */
	public void setButton(JButton button) {
		this.button = button;
	}
	
	/**
	 * @return the series
	 */
	public XYSeries getSeries() {
		return series;
	}
	/**
	 * @param series the series to set
	 */
	public void setSeries(XYSeries series) {
		this.series = series;
	}
	/**
	 * @return the series2
	 */
	public XYSeries getSeries2() {
		return series2;
	}
	/**
	 * @param series2 the series2 to set
	 */
	public void setSeries2(XYSeries series2) {
		this.series2 = series2;
	}
	
	/**
	 * @return the button_1
	 */
	public JButton getButton_1() {
		return button_1;
	}
	/**
	 * @param button_1 the button_1 to set
	 */
	public void setButton_1(JButton button_1) {
		this.button_1 = button_1;
	}
	/**
	 * @return the button_2
	 */
	public JButton getButton_2() {
		return button_2;
	}
	/**
	 * @param button_2 the button_2 to set
	 */
	public void setButton_2(JButton button_2) {
		this.button_2 = button_2;
	}
	
	public void alertePdr() {
        JOptionPane.showMessageDialog(null, "Attention, risque de condensation");
    }
}
