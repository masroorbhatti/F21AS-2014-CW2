package main;
import java.io.FileNotFoundException;

import view.GetOrdersView;
import Backup.IOClass;
import controller.GetOrdersController;

import java.util.*;
public class Main
{
    public static void main (String arg[]) {
    	
    	IOClass model = new IOClass();  //the model
    	
        GetOrdersView   view  = new GetOrdersView  (model);
        GetOrdersController controller = new GetOrdersController(model, view);   
        view.setVisible(true);


    }


}
