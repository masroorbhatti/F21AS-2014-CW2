package exceptions;

//This class is responsible of throughing exception where the field is empty
public class EmptyValue extends Exception {

	public EmptyValue(String dup){
		super(dup);
	}
}
