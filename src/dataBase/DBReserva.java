package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import library.src.Livro;
import library.src.Reservas;

public class DBReserva {
	private static  DBReserva self = null;
	Statement s;
	Connection c;
	public DBReserva() throws ClassNotFoundException, SQLException {
		c = Connect.New().getC();
		s = c.createStatement();
	}

	public static DBReserva New() throws Throwable{
		if(DBReserva.self == null)
			DBReserva.self = new DBReserva();
		return DBReserva.self;
	}
	
	public boolean insertReserva(Reservas r) throws SQLException, ClassNotFoundException{
		int idUser = DBUsuario.New().buscaIdUsuario(r.getUser().getLogin());
		int idLivro = DBLivro.New().buscaIdLivro(r.getBook().getISBN());
		PreparedStatement p = c.prepareStatement("INSERT INTO reserva(" +
				"usuario_idusuario, livro_idlivro, data) VALUES (?, ?, ?);");
		p.setInt(1, idUser);
		p.setInt(2, idLivro);
		
		java.sql.Date date = new java.sql.Date(r.getReservedDate().year(), r.getReservedDate().month(), r.getReservedDate().day());
		p.setDate(3, date);
		
		return p.execute();
	}
	
	public int getNumReservas(Livro l) throws SQLException, ClassNotFoundException{
		int idLivro = DBLivro.New().buscaIdLivro(l.getISBN());
		int numReservas = 0;
		ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM reservas WHERE livro_idlivro="
				+idLivro+";");
		while(rs.next())
			numReservas = rs.getInt(1);
		
		return numReservas;
	}
	
	public int removeReserva(Reservas r) throws SQLException, ClassNotFoundException{
		int idUser = DBUsuario.New().buscaIdUsuario(r.getUser().getLogin());
		int idLivro = DBLivro.New().buscaIdLivro(r.getBook().getISBN());
		
		return s.executeUpdate("DELETE FROM reserva WHERE usuario_idusuario="+idUser+
			" AND livro_idlivro="+idLivro+" AND data="+r.getReservedDate().toString()+";");
	
	}

}
