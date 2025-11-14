package model;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Representa a la persona o entidad responsable de las actividades agrícolas 
 * dentro de un lugar de producción.
 * Su rol es esencial para la administración, ya que está vinculado directamente 
 * con los lotes y las producciones que gestiona.
 * 
 * Esta clase mantiene la información del productor agrícola y facilita la 
 * relación entre las personas que realizan las labores agrícolas y los lugares 
 * de producción. Es fundamental para la trazabilidad y responsabilidad en el 
 * sistema de inspecciones fitosanitarias.
 */
public class Productor extends Usuario {

	/**
	 * Identificador único del productor. Este valor permite relacionarlo con sus lugares de producción y mantener una trazabilidad clara en el sistema.
	 */
	private String id;

	private LugarProduccion lugarProduccion;

	/**
	 * Constructor por defecto que permite crear un objeto productor vacío para luego asignarle datos mediante los métodos setter.
	 * 
	 *  
	 */
	public Productor() {

	}

	/**
	 * Establece el identificador único del productor, asegurando su diferenciación dentro del sistema.
	 */
	@Override
	public void setId(String pId) {
		this.id = pId;
	}

	/**
	 * Devuelve el identificador actual del productor, usado en búsquedas o vínculos con otros elementos (por ejemplo, lugares de producción).
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * Asocia un nuevo lugar de producción al productor.
	 */
	public void agregarLugarProduccion(LugarProduccion pLugarProduccion) {
		this.lugarProduccion = pLugarProduccion;
	}

	/**
	 * Retorna los lugares de producción vinculados.
	 */
	public LugarProduccion getLugaresProduccion() {
		return this.lugarProduccion;
	}

}
