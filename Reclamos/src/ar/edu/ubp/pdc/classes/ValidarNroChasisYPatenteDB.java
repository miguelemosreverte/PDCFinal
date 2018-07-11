package ar.edu.ubp.pdc.classes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidarNroChasisYPatenteDB {
	
	public static void main(String p[]) {
		String nro_chasis = "8F8693A675F14DE9A";
		String patente = "MDA277";
		try {
			validarNroChasisYPatente(nro_chasis, patente);
		}catch(Exception e) {
			
		}
	}
	public static String validarNroChasisYPatente(
			String nro_chasis,
			String patente) 
					throws ClassNotFoundException, SQLException{
		/*
		declare @existe char(1)
		execute dbo.val_vehiculo @nro_chasis='8F8693A675F14DE9A', @existe=@existe output
		select @existe	 
		 */
		Connection conn;
		CallableStatement stmt;
		//ResultSet result;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa","98Thk7oo08L#F");
			conn.setAutoCommit(false);
			try {
				stmt = conn.prepareCall("{CALL dbo.val_vehiculo(?,?,?)}");
				Integer index = 1;
				stmt.setString(1, nro_chasis);
				stmt.setString(2, patente);
				stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
				//result = stmt.executeQuery();
				stmt.execute();
				String res = stmt.getString(3);
				//System.out.println("Should be 'S': " + res ); // SHOULD BE "S"
				conn.commit();
				stmt.close();
				return res;
			}
			catch(SQLException e) {
				conn.rollback();
				//System.out.println(e.toString());
				throw e;
			}
			finally {
				conn.setAutoCommit(true);
				conn.close();
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			//System.out.println(e.toString());
			throw e;
		}
	}
	
	public ValidarNroChasisYPatenteDB() {
		
	
	}

}
