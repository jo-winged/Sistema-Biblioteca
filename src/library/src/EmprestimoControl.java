package library.src;

import java.util.ArrayList;

public class EmprestimoControl {
	private static EmprestimoControl self = null;
	private ArrayList<Emprestimo> emprestimos;
	private EmprestimoControl(){
		emprestimos = new ArrayList<Emprestimo>();
	}
	
	public static EmprestimoControl New(){
		if (EmprestimoControl.self == null)
			EmprestimoControl.self = new EmprestimoControl();
		return EmprestimoControl.self;
	}

	 public void addEmprestimo(Emprestimo emp){
	      emprestimos.add(emp);
	  }
	  public int howManyBorrows(Livro l){
	      int count = 0;
	      for (Emprestimo e: emprestimos){
		    if (e.getBook().equals(l)){
			count++;
		    }
	      }	
	      return count;
	  }


	  public int howManyBorrows(Usuario user){
	      int count = 0;
	      for (Emprestimo e: emprestimos){
		    if (e.getUser().equals(user)){
			count++;
		    }
	      }	
	      return count;
	  }

	public int getNumEmprestimos() {
		return emprestimos.size();
	}
}