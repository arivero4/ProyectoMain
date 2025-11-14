package BaseDatos;

import java.sql.Connection;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Clase singleton que proporciona conexión para asistentes técnicos.
 * Los asistentes técnicos tienen acceso para registrar inspecciones y resultados técnicos.
 * 
 * Implementa el patrón Singleton para garantizar una única conexión por rol.
 */
public class AsistenteTecnicoConnection implements DBConnection {

	private static AsistenteTecnicoConnection instance;
	private final Conexion conexion;

	/**
	 * Constructor privado para el patrón Singleton.
	 */
	private AsistenteTecnicoConnection() {
		this.conexion = Conexion.getInstance();
	}

	/**
	 * Obtiene la instancia única de AsistenteTecnicoConnection.
	 * 
	 * @return AsistenteTecnicoConnection - Instancia única
	 */
	public static synchronized AsistenteTecnicoConnection getInstance() {
		if (instance == null) {
			instance = new AsistenteTecnicoConnection();
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
