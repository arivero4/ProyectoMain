package BaseDatos;

import java.sql.Connection;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Clase singleton que proporciona conexión para usuarios administradores.
 * Los administradores tienen acceso completo a todas las tablas y operaciones.
 * 
 * Implementa el patrón Singleton para garantizar una única conexión por rol.
 */
public class AdministradorConnection implements DBConnection {

	private static AdministradorConnection instance;
	private final Conexion conexion;

	/**
	 * Constructor privado para el patrón Singleton.
	 */
	private AdministradorConnection() {
		this.conexion = Conexion.getInstance();
	}

	/**
	 * Obtiene la instancia única de AdministradorConnection.
	 * 
	 * @return AdministradorConnection - Instancia única
	 */
	public static synchronized AdministradorConnection getInstance() {
		if (instance == null) {
			instance = new AdministradorConnection();
		}
		return instance;
	}

	/**
	 * Obtiene la conexión a la base de datos.
	 * 
	 * @return Connection - Conexión activa
	 */
	@Override
	public Connection getConnection() {
		return conexion.getConnection();
	}

	/**
	 * Obtiene la cadena de conexión JDBC.
	 * 
	 * @return String - Cadena de conexión
	 */
	@Override
	public String getConnectionString() {
		return conexion.getConnectionString();
	}
}
