package DAO;

import java.sql.SQLException;

import beans.Admin;

public class TesteDao {
	public static void main(String[] args) throws SQLException {
		//Admin admin1 = new Admin("Felipe", "123.123.123-22");
		//Admin admin2 = new Admin("Paulo","321.321.123-01");
		
		AdminDao c = new AdminDao();
		
		//c.inserirAdmin(admin2);
		
		//System.out.println("Inserido com Sucesso!!");
		
		c.listar();
		
		
		}
}
