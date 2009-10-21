package library.src;

import java.util.ArrayList;

public class ReserveControl {
	private static ReserveControl self = null;
	private ArrayList<Reservas> reserves;
	private ReserveControl(){
		reserves = new ArrayList<Reservas>();
	}
	
	public static ReserveControl New(){
		if (ReserveControl.self == null)
			ReserveControl.self = new ReserveControl();
		return ReserveControl.self;
	}
	
	public int numReserves(Livro book){
	      int reserved = 0;
	      for (Reservas r: reserves){
		  if (r.getBook().equals(book))
		      reserved++;
	      }
	      return reserved;
	}
	public void setReserve(Reservas reserve){
	    reserves.add(reserve);
	}

	public void removeReserve(Reservas reserve){
	      //ToDo Tem alguma função no arraylist para remover objetos equais (pelo equals)?
	      for (Reservas r:reserves){
		    if (reserve.equals(r))
			reserves.remove(r);
	      }
	}

	public int getNumReservas() {
		// TODO Auto-generated method stub
		return reserves.size();
	}
}		