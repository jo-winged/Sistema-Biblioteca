package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.src.Categoria;
import library.src.Editora;
import library.src.Livro;
import library.src.Pessoa;

public class DBLivro {
	private static DBLivro self = null;
	Statement s;
	Connect c;
	
	public DBLivro() throws SQLException, ClassNotFoundException {
		c = Connect.New();	
		this.s = c.getC().createStatement();
	}
	
	public DBLivro New() throws SQLException, ClassNotFoundException{
		if(DBLivro.self == null)
			DBLivro.self = new DBLivro();
		return DBLivro.self;
	}
	
	public boolean insertBook(Livro b) throws Throwable{
		//antes de inserir buscar editora, categoria e autor de suas tabelas.
		
		int ideditora = DBEditora.New().buscaIdEditora(b.getEditora().getNome());
		int idAutor = DBAutor.New().buscaIdAutor(b.getAutores().get(0).getNome());
		int idCat = DBCategoria.New().buscaIdCategoria(b.getCat().getDescricao());
		
		PreparedStatement p = this.c.getC().prepareStatement("INSERT INTO livro(" +
				"categoria_idcategoria, editora_ideditora, autor_idautor," +
				"titulo, isbn, ano, idioma)VALUES (?, ?, ?, ?, ?, ?, ?);");
		//falta o numero de exemplares de cada livro...precisa ver como ficou na classe Livro...
		p.setInt(1, idCat);
		p.setInt(2, ideditora);
		p.setInt(3, idAutor);
		p.setString(4, b.getTitulo());
		p.setString(5, b.getISBN());
		p.setInt(6, b.getAno());
		p.setString(7, b.getIdioma());
		
		return p.execute();
	}
	
	public ArrayList<Livro> buscaLivroTitulo(String t) throws SQLException{
		ArrayList<Livro> livros = new ArrayList<Livro>();
		ResultSet rs = s.executeQuery("SELECT * FROM livro WHERE titulo LIKE '%" + t + "%'");
		while(rs.next()){
			Categoria c = new Categoria();
			Editora e = new Editora();
			Pessoa a = new Pessoa();
			Livro l = new Livro();
			ResultSet rs1 = s.executeQuery("SELECT descricao FROM categoria WHERE idcategoria=" +
					rs.getInt("categoria_idcategoria")+";");
			while(rs1.next()){
				c.setDescricao(rs1.getString("descricao"));
			}
			ResultSet rs2 = s.executeQuery("SELECT nome FROM editora WHERE ideditora=" +
					rs.getInt("editora_ideditora")+";");
			while(rs2.next()){
				e.setNome(rs2.getString("nome"));
			}
			ResultSet rs3 = s.executeQuery("SELECT nome FROM autor WHERE idautor=" +
					rs.getInt("autor_idautor")+";");
			while(rs3.next()){
				a.setNome(rs3.getString("idautor"));
			}
			
			ArrayList<Pessoa> p = new ArrayList<Pessoa>();
			p.add(a);
			l.setAutores(p);
			l.setCat(c);
			l.setEditora(e);
			l.setTitulo(rs.getString("titulo"));
			l.setISBN(rs.getString("isbn"));
			l.setAno(rs.getInt("ano"));
			l.setIdioma(rs.getString("idioma"));
			
			livros.add(l);
		}
		return livros;
	}
	
	public ArrayList<Livro> buscaLivroISBN(String isbn) throws SQLException{
		ArrayList<Livro> livros = new ArrayList<Livro>();
		ResultSet rs = s.executeQuery("SELECT * FROM livro WHERE isbn LIKE '%" + isbn + "%'");
		while(rs.next()){
			Categoria c = new Categoria();
			Editora e = new Editora();
			Pessoa a = new Pessoa();
			Livro l = new Livro();
			ResultSet rs1 = s.executeQuery("SELECT descricao FROM categoria WHERE idcategoria=" +
					rs.getInt("categoria_idcategoria")+";");
			while(rs1.next()){
				c.setDescricao(rs1.getString("descricao"));
			}
			ResultSet rs2 = s.executeQuery("SELECT nome FROM editora WHERE ideditora=" +
					rs.getInt("editora_ideditora")+";");
			while(rs2.next()){
				e.setNome(rs2.getString("nome"));
			}
			ResultSet rs3 = s.executeQuery("SELECT nome FROM autor WHERE idautor=" +
					rs.getInt("autor_idautor")+";");
			while(rs3.next()){
				a.setNome(rs3.getString("idautor"));
			}
			
			ArrayList<Pessoa> p = new ArrayList<Pessoa>();
			p.add(a);
			l.setAutores(p);
			l.setCat(c);
			l.setEditora(e);
			l.setTitulo(rs.getString("titulo"));
			l.setISBN(rs.getString("isbn"));
			l.setAno(rs.getInt("ano"));
			l.setIdioma(rs.getString("idioma"));
			
			livros.add(l);
		}
		return livros;
	}
	
	public ArrayList<Livro> buscaLivroEditora(String ed) throws SQLException{
		ArrayList<Livro> livros = new ArrayList<Livro>();
		ResultSet rs = s.executeQuery("SELECT * FROM livro WHere editora_ideditora " +
				"in (select ideditora from editora where editora.nome LIKE '%" + ed + "%');");
		while(rs.next()){
			Categoria c = new Categoria();
			Editora e = new Editora();
			Pessoa a = new Pessoa();
			Livro l = new Livro();
			ResultSet rs1 = s.executeQuery("SELECT descricao FROM categoria WHERE idcategoria=" +
					rs.getInt("categoria_idcategoria")+";");
			while(rs1.next()){
				c.setDescricao(rs1.getString("descricao"));
			}
			ResultSet rs2 = s.executeQuery("SELECT nome FROM editora WHERE ideditora=" +
					rs.getInt("editora_ideditora")+";");
			while(rs2.next()){
				e.setNome(rs2.getString("nome"));
			}
			ResultSet rs3 = s.executeQuery("SELECT nome FROM autor WHERE idautor=" +
					rs.getInt("autor_idautor")+";");
			while(rs3.next()){
				a.setNome(rs3.getString("idautor"));
			}
			
			ArrayList<Pessoa> p = new ArrayList<Pessoa>();
			p.add(a);
			l.setAutores(p);
			l.setCat(c);
			l.setEditora(e);
			l.setTitulo(rs.getString("titulo"));
			l.setISBN(rs.getString("isbn"));
			l.setAno(rs.getInt("ano"));
			l.setIdioma(rs.getString("idioma"));
			
			livros.add(l);			
		}
		return livros;
	}
	//peeeen...percebi que na tabela livro naum tem o atributo numExemplares, e nem na classe...
	//Quando for deletar perguntar se quer deletar um exemplar ou todos...
	public boolean removeBook(Livro l) throws SQLException{
		s.executeUpdate("DELETE FROM livro WHERE isbn=");
		
		return true;
	}

	
}
