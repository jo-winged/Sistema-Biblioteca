package library.src;

public class Fine {
	private static final double DayValue = 1.00;
	private long numDays;
	Usuario user;
	boolean paid;
	Emprestimo emp;
	
	public Fine() {
		emp = new Emprestimo();
		paid = false;
	}
	
	public Emprestimo getEmp() {
		return emp;
	}

	public void setEmp(Emprestimo emp) {
		this.emp = emp;
	}

	public double fineValue(){
		return DayValue * numDays;
	}

	public void setNumDays(long days){
		numDays = days;
	}
	
	public long getNumDays(){
		return numDays;
	}
	
	public Usuario getUser(){
		return user;
	}
	
	
	public void setUser(Usuario new_value){
		new_value.setFine();
		user = new_value;
	}
	
	public boolean wasPaid(){
		return paid;
	}
	
	public void payFine(){
		if (!paid){
			user.payFine();
		}
		paid = true;
	}
}
