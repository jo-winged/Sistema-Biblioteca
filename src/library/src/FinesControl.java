package library.src;

import java.util.ArrayList;

public class FinesControl {
	private static FinesControl self = null;
	private ArrayList<Fine> finesNotPaid;
	private ArrayList<Fine> finesPaid;
	private FinesControl(){
		
	}
	
	public void addFine(Fine fine){
		finesNotPaid.add(fine);
	}
	
	public boolean pay(Fine fine){
		if (finesNotPaid.remove(fine)){
			finesPaid.add(fine);
			return true;
		}else{
			return false;
		}
	}
	
	public static FinesControl New(){
		if (FinesControl.self == null)
			FinesControl.self = new FinesControl();
		return FinesControl.self;
	}
}

