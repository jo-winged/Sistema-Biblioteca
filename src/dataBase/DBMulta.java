package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import library.src.Emprestimo;
import library.src.Fine;

public class DBMulta {
	private static DBMulta self = null;
	Statement s;
	Connection c;

	public DBMulta() throws ClassNotFoundException, SQLException {
		c = Connect.New().getC();
		s = c.createStatement();
	}

	public static DBMulta New() throws Throwable {
		if (DBMulta.self == null)
			DBMulta.self = new DBMulta();
		return DBMulta.self;
	}
	
	public boolean insertMulta(Fine m) throws SQLException, ClassNotFoundException{
		int idEmp = DBEmprestimo.New().buscaIdEmprestimo(m.getEmp());
		
		PreparedStatement p = c.prepareStatement("INSERT INTO multas(emprestimo_idemprestimo, valor, " +
				"diasatraso, paga)" +
				" VALUES (?, ?, ?, ?);");
		p.setInt(1, idEmp);
		p.setDouble(2, m.fineValue());
		p.setLong(3, m.getNumDays());
		p.setBoolean(4, false);
		
		return p.execute();
	}
	
	public int pagarMulta(Fine f) throws SQLException, ClassNotFoundException{
		int idEmp = DBEmprestimo.New().buscaIdEmprestimo(f.getEmp());
		
		return s.executeUpdate("UPDATE multas SET paga = true WHERE " +
				"emprestimo_idemprestimo="+idEmp+";");
		
	}

}
