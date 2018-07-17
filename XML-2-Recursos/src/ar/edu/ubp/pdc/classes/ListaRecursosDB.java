package ar.edu.ubp.pdc.classes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import ar.edu.ubp.pdc.beans.RecursoBean;;

public class ListaRecursosDB {

	public static void main(String p[]) {
		try {
			LinkedList<RecursoBean> listaPropitarios= getRecursos();
			//System.out.println(listaPropitarios.toString());
		}catch(Exception e) {
			System.out.println("catch exception on ListaRecursosDB: " +e.getMessage());
		}
	}
	public static LinkedList<RecursoBean> getRecursos() throws ClassNotFoundException, SQLException{
		Connection conn;
		Statement stmt;		
		ResultSet result;
		LinkedList<RecursoBean> listaRecursos= new LinkedList<>();
		RecursoBean p;
		Integer nro_ar = 0 ;
		Integer nro_leg = 0;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa","98Thk7oo08L#F");
			conn.setAutoCommit(true);
			stmt = conn.createStatement();
			result = stmt.executeQuery("select desc_recurso     = desc_recurso," + 
						"       cod_tipo_recurso = cod_tipo_recurso," + 
						"	   tipo_propietario = case when nro_leg_personal is not null " + 
						"	                           then 'P'" + 
						"							   else 'A'" + 
						"						  end,        	   " + 
						"	   nro_leg_personal = nro_leg_personal," + 
						"	   nro_area         = nro_area," + 
						"	   vigente          = vigente," + 
						"	   nro_recurso      = nro_recurso" + 
						"  from dbo.recursos (nolock)" + 
						" order by desc_recurso");
			while(result.next()) {
				    nro_ar = null ;
				    nro_leg = null;
					p = new RecursoBean();
					p.setDesc_recurso(result.getString("desc_recurso"));
					p.setCod_tipo_recurso(result.getString("cod_tipo_recurso"));
					p.setTipo_propietario(result.getString("tipo_propietario"));
					nro_leg = result.getInt("nro_leg_personal");
					if(result.wasNull()) {
						p.setNro_leg_personal(null);
					}else {
						p.setNro_leg_personal(nro_leg);
					}
					nro_ar = result.getInt("nro_area");
					if(result.wasNull()) {
						p.setNro_area(null);
					}else {
					    p.setNro_area(nro_ar);	
					}
					p.setVigente(result.getString("vigente"));
					p.setNro_recurso(result.getInt("nro_recurso"));
					listaRecursos.add(p);
			    }
				stmt.close();
				conn.close();
				return listaRecursos;
			}catch(ClassNotFoundException | SQLException e) {
				throw e;
			}
		}
	
	public ListaRecursosDB() {
		// TODO Auto-generated constructor stub
		
	}

}
