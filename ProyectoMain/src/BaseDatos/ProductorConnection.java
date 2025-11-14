package BaseDatos;

import java.sql.Connection;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Clase singleton que proporciona conexión para usuarios productores.
 * Los productores tienen acceso a información de sus lugares de producción y lotes.
 * 
 * Implementa el patrón Singleton para garantizar una única conexión por rol.
 */
public class ProductorConnection implements DBConnection {

	private static ProductorConnection instance;
	private final Conexion conexion;

	/**
	 * Constructor privado para el patrón Singleton.
	 */
	private ProductorConnection() {
		this.conexion = Conexion.getInstance();
	}

	/**
	 * Obtiene la instancia única de ProductorConnection.
	 * 
	 * @return ProductorConnection - Instancia única
	 */
	public static synchronized ProductorConnection getInstance() {
		if (instance == null) {
			instance = new ProductorConnection();
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
