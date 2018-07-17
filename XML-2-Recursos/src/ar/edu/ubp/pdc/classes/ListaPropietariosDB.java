package ar.edu.ubp.pdc.classes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import ar.edu.ubp.pdc.beans.PropietariosBean;

public class ListaPropietariosDB {

	public static void main(String p[]) {
		String tipo_propietario = "A";
		Integer nro_leg_personal = 2 ;
		Integer nro_area = null;
		try {
			LinkedList<PropietariosBean> listaPropitarios= getPropietarios(tipo_propietario,nro_leg_personal,nro_area);
			System.out.println(listaPropitarios.toString());
			//parseResultSet(result);
			
		}catch(Exception e) {
			
		}
	}
	public static LinkedList<PropietariosBean> getPropietarios(
			String tipo_propietario,
			Integer nro_leg_personal,
			Integer nro_area
			
			) throws ClassNotFoundException, SQLException{
		/*
		-- execute dbo.get_lista_propietarios @tipo_propietario='A'
		-- execute dbo.get_lista_propietarios @tipo_propietario='A', @nro_area=2
		-- execute dbo.get_lista_propietarios @tipo_propietario='P', @nro_leg_personal=4
		 */
		Connection conn;
		CallableStatement stmt;		
		ResultSet result;
		LinkedList<PropietariosBean> listaPropietarios= null;
		PropietariosBean p;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa","98Thk7oo08L#F");
			conn.setAutoCommit(true);
			try {
				stmt = conn.prepareCall("{CALL dbo.get_lista_propietarios(?,?,?)}");
				stmt.setString(1, tipo_propietario);
				if(nro_leg_personal==null && nro_area==null) {
					stmt.setNull(2, java.sql.Types.INTEGER);
					stmt.setNull(3, java.sql.Types.INTEGER);
					
				}else if (nro_leg_personal != null) {
					stmt.setInt(2, nro_leg_personal);
					stmt.setNull(3, java.sql.Types.INTEGER);
				
				}else if (nro_area != null) {
					stmt.setNull(2, java.sql.Types.INTEGER);
					stmt.setInt(3, nro_area);
				
				}
				result = stmt.executeQuery();
				listaPropietarios = new LinkedList<PropietariosBean>();
				//nombre,nro_leg_personal ,nro_area,seleccionadon,nro_orden
				Integer nro_leg = null;
				Integer nro_ar = null;
				while(result.next()) {
					nro_leg= null;
					nro_ar = null;
					p = new PropietariosBean();
					p.setNombre(result.getString("nombre"));
					nro_leg = result.getInt("nro_leg_personal");
					if(!result.wasNull()) {
					   p.setNro_leg_personal(nro_leg);
					}
					nro_ar = result.getInt("nro_area");
					if(!result.wasNull()) {
					   p.setNro_area(nro_ar);	
					}
					p.setSeleccionado(result.getString("seleccionado"));
					p.setNro_orden(result.getInt("nro_orden"));
					listaPropietarios.add(p);
				}
				stmt.close();
				return listaPropietarios;
			}
			catch(SQLException e) {
				//System.out.println(e.toString());
				throw e;
			}finally {
				conn.close();
			}
		}
		catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.toString());
			throw e;
		}
	}
	public ListaPropietariosDB() {
		// TODO Auto-generated constructor stub	
	}

}
