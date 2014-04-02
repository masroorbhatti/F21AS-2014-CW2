package controller;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;


import model.IOClass;
import view.GetOrdersView;
import controller.GetOrdersController;




public class GetOrdersController  {

    private IOClass io;
	private GetOrdersView view;

	
	
	public GetOrdersController(IOClass a, GetOrdersView v){
		io = a;
		view = v;

		view.addProcessBidsListener(new ProcessBidsController());
	}
		
    
    class ProcessBidsController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
        	view.disableProcessButton();
    		Thread thread = new Thread (io);
    		thread.start();
    		

        }
    }
    



}
