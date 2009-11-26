package library.src;

import java.util.ArrayList;

public class FinesControl {
	private static FinesControl self = null;
	private ArrayList<Fine> finesNotPaid;
	private ArrayList<Fine> finesPaid;
	private FinesControl(){
		finesNotPaid = new ArrayList<Fine>();
		finesPaid = new ArrayList<Fine>();
	}
	
	public static FinesControl New(){
		if (FinesControl.self == null)
			FinesControl.self = new FinesControl();
		return FinesControl.self;
	}
	
	public void addFine(Fine fine){
		finesNotPaid.add(fine);
	}
	
	public boolean pay(Fine fine){
		if (!fine.wasPaid()){
			finesNotPaid.remove(fine);
			finesPaid.add(fine);
			return true;
		}else{
			return false;
		}
	}
	
	public int FinesPaid() {
		return finesPaid.size();
	}
	
	public int FinesNotPaid() {
		return finesNotPaid.size();
	}

	public void addFine(Usuario user, int i, Emprestimo e) {
		Fine newFine = new Fine();
		newFine.setNumDays(i);
		newFine.setUser(user);
		newFine.setEmp(e);
		finesNotPaid.add(newFine);
	}

	public ArrayList<Fine> getFines(Usuario user) {
		ArrayList<Fine> fines = new ArrayList<Fine>();
		for (Fine notPaid:finesNotPaid){
			if (notPaid.getUser().getLogin() == user.getLogin()){
				fines.add(notPaid);
			}
		}
		return fines;
	}

	public void cleanUp() {
		FinesControl.self = null;
		FinesControl.New();
	}
}

