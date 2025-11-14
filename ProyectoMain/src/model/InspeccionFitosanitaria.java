package model;

import java.util.Collection;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Modela las evaluaciones técnicas realizadas sobre los cultivos o lotes para identificar plagas, enfermedades y condiciones fitosanitarias.
 * Registra los detalles de cada inspección realizada por un asistente técnico en un lugar de producción específico.
 * 
 * Esta clase es el núcleo del sistema de inspecciones fitosanitarias, permitiendo documentar cada evaluación realizada.
 * Asocia los resultados técnicos obtenidos con la inspección, el inspector responsable y el lugar inspeccionado,
 * creando un registro completo y trazable de todas las actividades de monitoreo fitosanitario.
 */
public class InspeccionFitosanitaria {

	/**
	 * Identificador único de la inspección fitosanitaria.
	 */
	private String id;

	/**
	 * Fecha en la que se llevó a cabo la inspección, generalmente almacenada en formato estándar (por ejemplo, "2025-10-11").
	 */
	private String fechaInspeccion;

	/**
	 * Estado actual de la inspección (Pendiente, En Proceso, Completada, Cancelada).
	 */
	private String estado;

	/**
	 * Tipo de inspección realizada (Rutinaria, Emergencia, Seguimiento).
	 */
	private String tipoInspeccion;

	/**
	 * Observaciones generales realizadas durante la inspección.
	 */
	private String observaciones;

	/**
	 * Recomendaciones técnicas dadas como resultado de la inspección.
	 */
	private String recomendaciones;

	private Collection<ResultadoTecnico> resultadoTecnico;

	private AsistenteTecnico asistenteTecnico;

	private LugarProduccion lugarProduccion;

	/**
	 * Constructor que crea una nueva inspección sin valores iniciales, lista para ser completada.
	 * 
	 *  
	 */
	public InspeccionFitosanitaria() {

	}

	/**
	 * Asigna un identificador único a la inspección.
	 */
	public void setId(String pId) {
		this.id = pId;
	}

	/**
	 * Devuelve el identificador actual de la inspección.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Registra o actualiza la fecha en que se realizó la inspección, garantizando la trazabilidad temporal de los datos.
	 */
	public void setFechaInspeccion(String pFechaInspeccion) {
		this.fechaInspeccion = pFechaInspeccion;
	}

	public String getFechaInspeccion() {
		return this.fechaInspeccion;
	}

	/**
	 * Asigna el estado de la inspección.
	 */
	public void setEstado(String pEstado) {
		this.estado = pEstado;
	}

	/**
	 * Devuelve el estado actual de la inspección.
	 */
	public String getEstado() {
		return this.estado;
	}

	/**
	 * Asigna el tipo de inspección.
	 */
	public void setTipoInspeccion(String pTipoInspeccion) {
		this.tipoInspeccion = pTipoInspeccion;
	}

	/**
	 * Devuelve el tipo de inspección.
	 */
	public String getTipoInspeccion() {
		return this.tipoInspeccion;
	}

	/**
	 * Asigna las observaciones de la inspección.
	 */
	public void setObservaciones(String pObservaciones) {
		this.observaciones = pObservaciones;
	}

	/**
	 * Devuelve las observaciones de la inspección.
	 */
	public String getObservaciones() {
		return this.observaciones;
	}

	/**
	 * Asigna las recomendaciones técnicas.
	 */
	public void setRecomendaciones(String pRecomendaciones) {
		this.recomendaciones = pRecomendaciones;
	}

	/**
	 * Devuelve las recomendaciones técnicas.
	 */
	public String getRecomendaciones() {
		return this.recomendaciones;
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

	/**
	 * Asigna el asistente técnico responsable de ejecutar la inspección.
	 * Parámetro: pAsistenteTecnico → objeto de tipo AsistenteTecnico.
	 * 
	 *  
	 */
	public void setAsistenteTecnico(AsistenteTecnico pAsistenteTecnico) {
		this.asistenteTecnico = pAsistenteTecnico;
	}

	/**
	 * Devuelve el asistente técnico actualmente vinculado a la inspección. Permite consultar los datos del técnico encargado.
	 */
	public AsistenteTecnico getAsistenteTecnico() {
		return this.asistenteTecnico;
	}

	/**
	 * Añade un nuevo lugar de producción dentro del predio.
	 * Parámetro: lugarProduccion (LugarProduccion).
	 */
	public void setLugarProduccion(LugarProduccion pLugarProduccion) {
		this.lugarProduccion = pLugarProduccion;
	}

	/**
	 * Retorna la lista de lugares de producción vinculados al predio.
	 * Uso: permite visualizar o procesar la información agrícola de la finca.
	 */
	public LugarProduccion getLugaresProduccion() {
		return this.lugarProduccion;
	}

}
