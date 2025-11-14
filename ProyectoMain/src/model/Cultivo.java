package model;

import java.util.Collection;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Modela la información de los diferentes cultivos presentes en los lotes de un lugar de producción.
 * Incluye clasificación científica y común de las plantas cultivadas para facilitar el monitoreo fitosanitario.
 * 
 * Esta clase es esencial para la clasificación de cultivos dentro del sistema de inspección fitosanitaria.
 * Permite establecer relaciones entre las plantas cultivadas, las plagas asociadas y los resultados técnicos
 * obtenidos de las inspecciones, facilitando el análisis de la salud fitosanitaria por tipo de cultivo.
 */
public class Cultivo {

	/**
	 * Identificador único del cultivo dentro del sistema. Permite distinguir entre diferentes cultivos y mantener trazabilidad.
	 */
	private String id;

	/**
	 * Nombre de la variedad cultivada (por ejemplo, “Maíz Amarillo” o “Café Caturra”). Ayuda a diferenciar tipos de plantas dentro de la misma especie.
	 */
	private String nombreVariedad;

	/**
	 * Nombre común del cultivo, como “Maíz”, “Café”, “Arroz”, etc.
	 */
	private String nombreComun;

	/**
	 * Nombre científico o botánico de la especie vegetal (por ejemplo, Zea mays o Coffea arabica). Este atributo es importante para clasificaciones técnicas o científicas.
	 */
	private String nombreCientifico;

	/**
	 * Texto que describe las características generales del cultivo, tales como el ciclo productivo, condiciones ideales de siembra, clima o tipo de suelo.
	 */
	private String descripcion;

	private Lote lote;

	private Collection<Plaga> plaga;

	private ResultadoTecnico resultadoTecnico;

	/**
	 * Constructor por defecto que inicializa un cultivo vacío, permitiendo asignar valores posteriormente.
	 * 
	 *  
	 */
	public Cultivo() {

	}

	/**
	 * Asigna el identificador único al cultivo, garantizando su individualidad dentro del sistema.
	 */
	public void setId(String pId) {
		this.id = pId;
	}

	/**
	 * Devuelve el identificador actual del cultivo, útil en búsquedas y relaciones con inspecciones.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Permite registrar o modificar el nombre de la variedad del cultivo.
	 */
	public void setNombreVariedad(String pNombreVariedad) {
		this.nombreVariedad = pNombreVariedad;
	}

	/**
	 * Retorna el nombre actual de la variedad sembrada.
	 */
	public String getNombreVariedad() {
		return this.nombreVariedad;
	}

	/**
	 * signa el nombre común del cultivo, facilitando su identificación en los reportes o registros.
	 */
	public void setNombreComun(String pNombreComun) {
		this.nombreComun = pNombreComun;
	}

	/**
	 * Devuelve el nombre del cultivo.
	 */
	public String getNombreCultivo() {
		return this.nombreComun;
	}

	/**
	 * Permite registrar el nombre científico de la especie vegetal, garantizando precisión técnica.
	 */
	public void setNombreCientifico(String pNombreCientifico) {
		this.nombreCientifico = pNombreCientifico;
	}

	/**
	 * Devuelve el nombre científico de la especie asociada.
	 */
	public String getNombreCientifico() {
		return this.nombreCientifico;
	}

	/**
	 * Define o actualiza la descripción general del cultivo.
	 */
	public void setDescripcion(String pDescripcion) {
		this.descripcion = pDescripcion;
	}

	/**
	 * Retorna la descripción actual registrada del cultivo.
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Asocia el cultivo con un lote específico donde está sembrado.
	 * Parámetro: pLote → objeto Lote.
	 */
	public void agregarLote(Lote pLote) {
		this.lote = pLote;
	}

	/**
	 * Devuelve el lote al que pertenece el cultivo.
	 */
	public Lote getLote() {
		return this.lote;
	}

	/**
	 * Asocia una plaga que ha sido detectada en el cultivo.
	 * Parámetro: pPlaga → objeto Plaga.
	 */
	public void setPlaga(Plaga pPlaga) {
		this.plaga.add(pPlaga);
	}

	/**
	 * Devuelve la plaga asociada al cultivo, si existe.
	 */
	public Plaga getPlaga() {
		return (Plaga) this.plaga;
	}

	/**
	 * Enlaza el cultivo con un resultado técnico que contiene datos sobre su inspección o análisis fitosanitario.
	 * Parámetro: pResultadoTecnico → objeto ResultadoTecnico.
	 */
	public void agregarResultadoTecnico(ResultadoTecnico pResultadoTecnico) {
		this.resultadoTecnico = pResultadoTecnico;
	}

	/**
	 * Devuelve el resultado técnico vinculado al cultivo.
	 */
	public ResultadoTecnico getResultadoTecnico() {
		return this.resultadoTecnico;
	}

}
