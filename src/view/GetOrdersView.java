package view;

//This View doesn't know about the Controller, 
//except that it provides methods for registering a Controller's listeners. 
//Other organizations are possible
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Backup.IOClass;

import java.util.*;

import controller.GetOrdersController;

public class GetOrdersView  extends JFrame  implements Observer
{
    private IOClass io;
    private int numCusts;
   //GUI components
    JButton processButton;
    private JTextArea orders ;

    
    /**
     * Create the frame with its panels.
     */
    public GetOrdersView(IOClass io)
    {
        this.io = io;
        io.addObserver(this);

        //set up window title
        setTitle("Restaurant");
        //ensure program ends when window closes
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(100,600);
        setLocation(10,20);
 
        
        //add button panel and result field to the content pane
        Container contentPane = getContentPane();
        contentPane.add(createNorthPanel(), BorderLayout.NORTH);
        contentPane.add(createCustPanel(), BorderLayout.CENTER);
        //pack and set visible
        pack();
        setVisible(true);
    }
    
   
    private JPanel createCustPanel() {
    	//cheating - know there are 6 customers
    	JPanel custPanel = new JPanel(new GridLayout (3,2));
		orders  = new JTextArea();
		orders = new JTextArea(15,80);
			//monospaced allows nice tabular layout
		orders.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		orders.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY));
			custPanel.add(orders);
		
		return custPanel;
    }

    private JPanel createNorthPanel() {
        //north panel shows the button to start processing
        processButton = new JButton("Take Orders");
        JPanel northPanel = new JPanel();
        northPanel.add(processButton);
        return northPanel;
    }
    
    /////////////////////////////////////////////////////
    //MVC pattern - allows listeners to be added
    public void addProcessBidsListener(ActionListener al) {
        processButton.addActionListener(al);
    }
    
    public void disableProcessButton() {
    	processButton.setEnabled(false);
    	
    }
    /////////////////////////////////////////////////////////

    //OBSERVER pattern - must provide update methods
    //synchronized blocks access to sync methods of the same object until finished
    //possibly investigate SwingWorker
    public synchronized void update(Observable o, Object args) {
    	System.out.println(io.getTestOutput());
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String result = "";
    		result = io.getTestOutput();
    	
    	this.orders.setText(result);	

    	

    }

}
