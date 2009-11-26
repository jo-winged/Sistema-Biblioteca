package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.trolltech.qt.core.QDate;

import library.src.Emprestimo;
import library.src.Livro;
import library.src.Usuario;

public class DBEmprestimo {
	private static DBEmprestimo self = null;
	Statement s;
	Connection c;

	public DBEmprestimo() throws ClassNotFoundException, SQLException {
		c = Connect.New().getC();
		s = c.createStatement();
	}

	public boolean insertEmprestimo(Emprestimo emp) throws SQLException, ClassNotFoundException{
		int idLivro = 0;
		int idUser = 0;

		idLivro = DBLivro.New().buscaIdLivro(emp.getBook().getISBN());
		idUser = DBUsuario.New().buscaIdUsuario(emp.getUser().getLogin());
		System.out.println(idLivro+"-"+idUser);
		PreparedStatement p = c.prepareStatement("INSERT INTO emprestimo(" +
				"usuario_idusuario, livro_idlivro, renovacoes, datadev, devolvido) " +
		"VALUES (?, ?, ?, ?, ?);");
		p.setInt(1, idUser);
		p.setInt(2, idLivro);
		p.setInt(3, 0);

		java.sql.Date date = new java.sql.Date(emp.getDate().year(), emp.getDate().month(), emp.getDate().day());
		p.setDate(4, date);
		p.setBoolean(5, false);

		return p.execute();
	}
	
	public int buscaIdEmprestimo(Emprestimo e) throws SQLException, ClassNotFoundException{
		int idUser = DBUsuario.New().buscaIdUsuario(e.getUser().getLogin());
		int idLivro = DBLivro.New().buscaIdLivro(e.getBook().getISBN());
		
		int idEmp = 0;
		
		ResultSet rs = s.executeQuery("SELECT idemprestimo FROM emprestimo WHERE " +
				"usuario_idusuario="+idUser+" AND livro_idlivro="+idLivro+" AND" +
				" devolvido = false;"); 
		while(rs.next()){
			idEmp = rs.getInt(1);
		}
		
		return idEmp;
	}

	public Emprestimo getEmprestimoUsuario(Usuario user) throws SQLException, ClassNotFoundException{
		int idUser = DBUsuario.New().buscaIdUsuario(user.getLogin());

		ResultSet rs = s.executeQuery("SELECT * FROM emprestimo WHERE devolvido = false AND " +
				"usuario_idusuario="+idUser+";");
		while(rs.next()){
			Emprestimo emp = new Emprestimo();
			emp.setUser(user);

			Livro l = DBLivro.New().buscaLivroPorID(rs.getInt("livro_idlivro"));
			emp.setBook(l);
			emp.setDate(QDate.fromString(rs.getDate("datadev").toString()));
			emp.setRenews(rs.getInt("renovacoes"));

			return emp;
		}
		return null;				
	}

	public ArrayList<Emprestimo> getEmprestimoLivro(String isbn) throws SQLException, ClassNotFoundException{
		ArrayList<Livro> l = DBLivro.New().buscaLivroISBN(isbn);
		ArrayList<Emprestimo> listEmp = new ArrayList<Emprestimo>();
		for(Livro e : l){
			int idLivro = DBLivro.New().buscaIdLivro(e.getISBN());
			ResultSet rs = s.executeQuery("SELECT * FROM emprestimo WHERE devolvido = false AND " +
					"livro_idlivro="+idLivro+";");
			while(rs.next()){
				Emprestimo emp = new Emprestimo();
				Usuario user = DBUsuario.New().buscaUsuarioPorID(rs.getInt(1));
				emp.setUser(user);
				emp.setBook(e);
				emp.setDate(QDate.fromString(rs.getDate("datadev").toString()));
				emp.setRenews(rs.getInt("renovacoes"));

				listEmp.add(emp);
			}
		}
		return listEmp;				
	}
	
	public ArrayList<Emprestimo> getEmprestimoTitulo(String titulo) throws SQLException, ClassNotFoundException{
		ArrayList<Livro> l = DBLivro.New().buscaLivroTitulo(titulo);
		ArrayList<Emprestimo> listEmp = new ArrayList<Emprestimo>();
		for(Livro e : l){
			int idLivro = DBLivro.New().buscaIdLivro(e.getISBN());
			ResultSet rs = s.executeQuery("SELECT * FROM emprestimo WHERE devolvido = false AND " +
					"livro_idlivro="+idLivro+";");
			while(rs.next()){
				Emprestimo emp = new Emprestimo();
				Usuario user = DBUsuario.New().buscaUsuarioPorID(rs.getInt(1));
				emp.setUser(user);
				emp.setBook(e);
				emp.setDate(QDate.fromString(rs.getDate("datadev").toString()));
				emp.setRenews(rs.getInt("renovacoes"));

				listEmp.add(emp);
			}
		}
		return listEmp;				
	}

	public ArrayList<Emprestimo> listEmprestimosUsuario(Usuario user) throws SQLException, ClassNotFoundException{
		ArrayList<Emprestimo> emp = new ArrayList<Emprestimo>();

		int idUser = DBUsuario.New().buscaIdUsuario(user.getLogin());


		ResultSet rs1 = s.executeQuery("SELECT * FROM emprestimo WHERE usuario_idusuario =" +
				idUser +";");
		while(rs1.next()){
			Emprestimo e = new Emprestimo();
			Livro l = DBLivro.New().buscaLivroPorID(rs1.getInt("livro_idlivro"));
			e.setUser(user);
			e.setBook(l);
			e.setDate(QDate.fromString(rs1.getDate("datadev").toString()));

			emp.add(e);			
		}

		return emp;
	}

	public boolean renew(Emprestimo emp) throws Exception{
		if(renewable(emp)){
			int idLivro = DBLivro.New().buscaIdLivro(emp.getBook().getISBN());
			int idUser = DBUsuario.New().buscaIdUsuario(emp.getUser().getLogin());

			PreparedStatement p = c.prepareStatement("UPDATE emprestimo SET " +
					"renovacoes = renovacoes + 1 WHERE " +
			"usuario_idusuario = ? AND livro_idlivro = ? AND devolvido = false;");
			p.setInt(1, idUser);
			p.setInt(2, idLivro);

			return p.execute();
		}
		return true;
	}

	public boolean renewable(Emprestimo emp) throws SQLException, Exception{
		int idLivro = DBLivro.New().buscaIdLivro(emp.getBook().getISBN());
		int idUser = DBUsuario.New().buscaIdUsuario(emp.getUser().getLogin());
		System.out.println(idLivro+"-"+idUser);
		ResultSet rs = s.executeQuery("SELECT renovacoes FROM emprestimo WHERE " +
				"devolvido = false AND usuario_idusuario = " +idUser+
				" AND livro_idlivro = " +idLivro+ ";");

		while(rs.next()){
			int r = rs.getInt(1);
			if(r < 3)
				return true;
		}

		return false;
	}

	public boolean devolucao(Emprestimo emp) throws SQLException, Exception{
		int idLivro = DBLivro.New().buscaIdLivro(emp.getBook().getISBN());
		int idUser = DBUsuario.New().buscaIdUsuario(emp.getUser().getLogin());

		PreparedStatement p = c.prepareStatement("UPDATE emprestimo SET devolvido = true WHERE " +
				"usuario_idusuario = ? AND livro_idlivro = ?" +
		" AND devolvido = false");

		p.setInt(1, idUser);
		p.setInt(2, idLivro);

		return p.execute();
	}
	
	public static DBEmprestimo New() throws ClassNotFoundException, SQLException {
		if(DBEmprestimo.self == null)
			DBEmprestimo.self = new DBEmprestimo();
		return DBEmprestimo.self;

	};

}
