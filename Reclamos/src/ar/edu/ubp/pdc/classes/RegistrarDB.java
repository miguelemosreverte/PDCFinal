package ar.edu.ubp.pdc.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrarDB {
	
	public static void main(String p[]) {
		String nro_chasis = "8F8693A675F14DE9A";
		String dominio = "MDA277";
		String km = null;
		String apellido = "APELLIDO2";
		String nombre = "Miguel";
		String email = "nombre2.apellido2@ubp.edu.ar";
		String telefono = "03514144444";
		String contactar = "S";
		String reclamo = "muy enojado";
		try {
				registrar(
						nro_chasis,
						dominio,
						km,
						apellido,
						nombre,
						email,
						telefono,
						contactar,
						reclamo
						);
				System.out.println("Done");
		}catch(Exception e) {
			
		}
	}
	
	public static void registrar(
			String nro_chasis,
			String dominio,
			String km,
			String apellido,
			String nombre,
			String email,
			String telefono,
			String contactar,
			String reclamo
			) throws ClassNotFoundException, SQLException{
		
		/*insert into dbo.reclamos(nro_chasis, dominio, km, apellido, nombre, email, telefono, contactar, reclamo)
		values('8F8693A675F14DE9A', 'MDA277', NULL, 'APELLIDO2', 'NOMBRE2', 'nombre2.apellido2@ubp.edu.ar', '03514144444', 'S', 'RECLAMO');
		*/

		
		Connection conn;
		PreparedStatement stmt;		
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa","98Thk7oo08L#F");
			conn.setAutoCommit(false);
			/*
			 * insert into 
			 * dbo.reclamos(nro_chasis, dominio, km, apellido, nombre, email, telefono, contactar, reclamo)
				values(
				NULL, 
				NULL, 
				NULL, 
				'APELLIDO1', 
				'NOMBRE1', 
				'nombre1.apellido1@ubp.edu.ar', 
				 NULL, 
				'S', 
				'RECLAMO'),
				
      			('8F8693A675F14DE9A', 
      			'MDA277', 
      			 NULL, 
      			'APELLIDO2', 
      			'NOMBRE2', 
      			'nombre2.apellido2@ubp.edu.ar', 
      			'03514144444', 
      			'S', 
      			'RECLAMO');
 */
			try {
				stmt = conn.prepareStatement(
						"insert into " + 
						"			  dbo.reclamos(nro_chasis, dominio, km, apellido, nombre, email, telefono, contactar, reclamo)" + 
						"				values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
				Integer index = 1;
				
				if(nro_chasis != null) {
					stmt.setString(index, nro_chasis);
				}
				else {
					stmt.setNull(index, java.sql.Types.VARCHAR);
					
				}
				index += 1;
				
				if(dominio != null) {
					stmt.setString(index, dominio);
				}
				else {
					stmt.setNull(index, java.sql.Types.VARCHAR);
					
				}
				index += 1;
				
				if(km != null) {
					stmt.setInt(index, Integer.parseInt(km));
				}
				else {
					stmt.setNull(index, java.sql.Types.INTEGER);
					
				}
				index += 1;
				

				
				stmt.setString(index++, apellido);
				stmt.setString(index++, nombre);
				stmt.setString(index++, email);
				

				
				if(telefono != null) {
					stmt.setString(index, telefono);
				}
				else {
					stmt.setNull(index, java.sql.Types.VARCHAR);
					
				}
				index += 1;
				

				stmt.setString(index++, contactar);
				stmt.setString(index++, reclamo);

				stmt.execute();
				conn.commit();
				stmt.close();
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
	
	public RegistrarDB() {
		
	
	}

}
