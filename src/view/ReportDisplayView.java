package view;

import java.util.ArrayList;

import interfaces.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.ActivityLog;
import model.DeliverOrder;
import model.Order;
import model.ReceiveOrder;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.List;

import javax.swing.JList;
import javax.swing.JLabel;

public class ReportDisplayView extends JPanel  {

	/**
	 * Create the panel.
	 */
	private List repdata;
	private DefaultListModel<String> model;
	private String report="";
	private JTextArea textArea ;

	/**
	 * @wbp.nonvisual location=103,284
	 */

	private JLabel labeltitle = new JLabel("R e p o r t s     V i e w e r");

	public ReportDisplayView() {

		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		
		add(scrollPane, BorderLayout.CENTER);
		
		
		textArea = new JTextArea();
		//textArea.setBounds(24, 82, 227, 172);
		textArea.setFont(new Font (Font.MONOSPACED, Font.PLAIN,11));
		textArea.setEditable(false);
		textArea.setCaretPosition(0);
	
		scrollPane.setViewportView(textArea);
		add(labeltitle,BorderLayout.NORTH);
		
	

	}
	
	public void setReportData(String report){
		this.report=report;
		//repdata.removeAll();
		//repdata.add(report,0);
		textArea.setText(report);
	}

}
