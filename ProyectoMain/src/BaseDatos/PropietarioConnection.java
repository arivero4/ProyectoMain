package BaseDatos;

import java.sql.Connection;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Clase singleton que proporciona conexión para propietarios.
 * Los propietarios tienen acceso a información de sus predios y propiedades.
 * 
 * Implementa el patrón Singleton para garantizar una única conexión por rol.
 */
public class PropietarioConnection implements DBConnection {

	private static PropietarioConnection instance;
	private final Conexion conexion;

	/**
	 * Constructor privado para el patrón Singleton.
	 */
	private PropietarioConnection() {
		this.conexion = Conexion.getInstance();
	}

	/**
	 * Obtiene la instancia única de PropietarioConnection.
	 * 
	 * @return PropietarioConnection - Instancia única
	 */
	public static synchronized PropietarioConnection getInstance() {
		if (instance == null) {
			instance = new PropietarioConnection();
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
