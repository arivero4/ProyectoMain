package model;

import java.util.Collection;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Modela una unidad específica dentro de un lugar de producción agrícola, representando parcelas o secciones de terreno.
 * Cada lote posee identificación única, extensión y contiene cultivos específicos sometidos a inspecciones.
 * 
 * Esta clase permite la subdivisión de áreas de producción para realizar un monitoreo fitosanitario más detallado y preciso.
 * Facilita el seguimiento de cultivos por lote y el registro de resultados técnicos específicos para cada sección de terreno,
 * permitiendo análisis comparativos de la salud fitosanitaria dentro de una misma área de producción.
 */
public class Lote {

	/**
	 * Identificador único del lote dentro del sistema. Se utiliza para distinguirlo de otros lotes, facilitando su búsqueda y gestión.
	 */
	private String id;

	/**
	 * Valor numérico que representa el área total del lote (por ejemplo, en hectáreas o metros cuadrados). Es importante para cálculos de producción o distribución de cultivos.
	 */
	private float area;

	/**
	 * Texto descriptivo que detalla las características del lote, como el tipo de terreno, el uso del suelo o información adicional relevante.
	 */
	private String fechaSiembra;

	private String fechaEliminacion;

	private Collection<Cultivo> cultivo;

	private Collection<ResultadoTecnico> resultadoTecnico;

	/**
	 * Constructor de la clase. Inicializa un objeto vacío, permitiendo asignar los valores posteriormente mediante los métodos setter.
	 * 
	 *  
	 */
	public Lote() {

	}

	/**
	 * signa el valor del identificador del lote. Se utiliza para establecer un código único y mantener la integridad de los datos.
	 */
	public void setId(String pId) {
		this.id = pId;
	}

	/**
	 * Retorna el identificador actual del lote, útil para búsquedas, reportes o relaciones con otros objetos del sistema.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Define o modifica la descripción del lote, permitiendo registrar detalles o actualizaciones sobre su estado o uso.
	 */
	public void setFechaSiembra(String pFechaSiembra) {
		this.fechaSiembra = pFechaSiembra;
	}

	public String getFechaSiembra() {
		return this.fechaSiembra;
	}

	/**
	 * Permite establecer o actualizar la extensión del lote, asegurando que los datos de tamaño sean precisos.
	 */
	public void setArea(float pArea) {
		this.area = pArea;
	}

	/**
	 * Devuelve la extensión actual del lote. Es útil en procesos donde se calculan rendimientos, costos o distribución de recursos.
	 */
	public float getArea() {
		return this.area;
	}

	/**
	 *  
	 */
	public void setFechaEliminacion(String pfechaEliminacion) {
		this.fechaEliminacion = pfechaEliminacion;
	}

	public String getFechaEliminacion() {
		return this.fechaEliminacion;
	}

	/**
	 * Establece el lugar de producción al que pertenece el lote.
	 * Parámetro: pLugarProduccion → objeto de tipo LugarProduccion
	 * 
	 *  
	 */
	public void setPredio(Predio pPredio) {
		// Este atributo se debería reconfigurar según la estructura del modelo
	}

	/**
	 * Devuelve el lugar de producción actualmente asociado al lote.
	 */
	public Predio getPredio() {
		return null; // Este atributo se debería reconfigurar según la estructura del modelo
	}

	/**
	 * Permite asociar un cultivo al lote. Cada lote puede tener uno o más cultivos registrados.
	 * ?Parámetro: pCultivo → objeto de tipo Cultivo.
	 */
	public void agregarCultivo(Cultivo pCultivo) {
		this.cultivo.add(pCultivo);
	}

	/**
	 * Retorna el cultivo registrado en el lote.
	 */
	public Cultivo getCultivo() {
		return (Cultivo) this.cultivo;
	}

	/**
	 * Permite añadir un resultado técnico a la inspección, registrando los hallazgos y mediciones obtenidas
	 */
	public void agregarResultadoTecnico(ResultadoTecnico pResultadoTecnico) {
		this.resultadoTecnico.add(pResultadoTecnico);
	}

	/**
	 * Devuelve el resultado técnico asociado a esta inspección. Contiene la información técnica del cultivo evaluado.
	 */
	public ResultadoTecnico getResultadoTecnico() {
		return (ResultadoTecnico) this.resultadoTecnico;
	}

}
