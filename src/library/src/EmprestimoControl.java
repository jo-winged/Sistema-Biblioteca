package library.src;

import java.util.ArrayList;

import com.trolltech.qt.core.QDate;

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

	 public void addEmprestimo(Emprestimo emp) throws Throwable{
		 if(emp.getUser().howManyFines() == 0){
			 	if (CadastroLivros.New().getAvaliables(emp.getBook()) > 1){
			 		if(emp.getUser().isProfessor())
			 			//emp.setDate(QDate.currentDate().addDays(15));
			 		//else
			 			//p.setDate(QDate.currentDate().addDays(7));
	                emprestimos.add(emp);
			 	}else{
			 		throw new Throwable("No avaliable book!");
			 	}
			}else	{
		 		throw new Throwable("User have Fines!");
			}
			
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

	public Emprestimo getEmprestimo(Usuario user, Livro book, QDate date) {
		Emprestimo tmpEmp = new Emprestimo();
		tmpEmp.setBook(book);
		tmpEmp.setUser(user);
		tmpEmp.setDate(date);
		int index = emprestimos.indexOf(tmpEmp);
		return emprestimos.get(index);
	}

	public void delivery(Emprestimo emp) {
		QDate dataEntrega = QDate.currentDate();
        if(emp.getDate().compareTo(dataEntrega) < 0){
                FinesControl fine = FinesControl.New();
                fine.addFine(emp.getUser(), usefulDays(emp.getDate(), dataEntrega), emp);
        }
		emprestimos.remove(emp);
		
	}
	
	public int usefulDays(QDate date1, QDate date2){
		//pega a diferenca entre os dias faz um for e ve o dia da semana de cada dia...
		int dif = date1.daysTo(date2);
		int weekend = 0;
		int days = dif;
		while(dif >= 0){
			if(date1.addDays(1).dayOfWeek() == 1 || date1.addDays(1).dayOfWeek() == 7){
				weekend++;
			}
			dif--;
		}
		return (days-weekend);
	}
}
