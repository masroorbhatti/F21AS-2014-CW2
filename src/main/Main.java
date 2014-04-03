package main;

import model.AllItems;
import model.AllTables;
import model.ReadFileData;
public class Main
{
    public static void main (String arg[]) {
    	
    	/*IOClass model = new IOClass();  //the model
    	
    	
        GetOrdersView   view  = new GetOrdersView  (model);
        GetOrdersController controller = new GetOrdersController(model, view);   
        view.setVisible(true);*/
    	
    	System.out.println(AllTables.getInstance().getTotalNumberOfTables());
    	ReadFileData rfd = new ReadFileData();
    	try {
    		rfd.readMenuFile();
    		rfd.readOrderFile();
    	}
    	catch (Exception e){
    		System.out.println(e);
    	}

    	System.out.println(AllItems.getInstance().getItemListByCategory("Main"));
    	System.out.println(AllItems.getInstance().getCategoryList().length);
    }


}
