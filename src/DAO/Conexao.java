package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
		private Connection conn;
			
			public Connection conectar() {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					this.conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/qchave","root","");
					return (Connection) this.conn;
				}catch (SQLException | ClassNotFoundException ex) {
					System.out.println("Erro: "+ex.getMessage());
					return null;
				}

			}

}
