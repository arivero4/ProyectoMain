package model;

import java.util.Collection;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Modela la información cuantitativa y cualitativa recolectada durante una inspección fitosanitaria.
 * Registra observaciones directas de cultivos, plantas evaluadas, afectadas y evaluaciones técnicas del inspector.
 * 
 * Esta clase es crucial para la documentación de resultados de inspecciones, incluyendo cálculos automáticos
 * de niveles de incidencia y alertas. Permite evaluar la salud fitosanitaria de cultivos y plagas específicas,
 * proporcionando información clave para la toma de decisiones en materia de control sanitario.
 */
public class ResultadoTecnico {

	/**
	 * Identificador único del resultado técnico, utilizado para su trazabilidad dentro del sistema.
	 */
	private String id;

	/**
	 * Número total de plantas inspeccionadas durante la visita técnica.
	 */
	private int totalPlantasEvaluadas;

	/**
	 * Cantidad de plantas encontradas con síntomas de plagas o enfermedades. Este valor es crucial para determinar el nivel de infestación.
	 */
	private int plantasAfectadas;

	/**
	 * Texto libre con las observaciones técnicas realizadas por el inspector, incluyendo condiciones del cultivo, presencia de vectores o factores ambientales relevantes.
	 */
	private String observaciones;

	@SuppressWarnings("unused")
	private Lote lote;

	private Plaga plaga;

	private InspeccionFitosanitaria inspeccionFitosanitaria;

	private Collection<Cultivo> cultivo;

	/**
	 * Constructor que inicializa un nuevo registro de resultado técnico vacío.
	 * 
	 *  
	 */
	public ResultadoTecnico() {

	}

	/**
	 * Asigna el identificador único al resultado técnico.
	 */
	public void setId(String pId) {
		this.id = pId;
	}

	/**
	 * Retorna el identificador actual del resultado.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Registra el número total de plantas observadas.
	 */
	public void setTotalPlantasEvaluadas(int pTotalPlantasEvaluadas) {
		this.totalPlantasEvaluadas = pTotalPlantasEvaluadas;
	}

	/**
	 * Devuelve la cantidad total de plantas evaluadas.
	 */
	public int getTotalPlantasEvaluadas() {
		return this.totalPlantasEvaluadas;
	}

	/**
	 * Define cuántas plantas presentaron afectaciones visibles.
	 */
	public void setPlantasAfectadas(int pPlantasAfectadas) {
		this.plantasAfectadas = pPlantasAfectadas;
	}

	/**
	 * Retorna el número de plantas afectadas registradas.
	 */
	public int getPlantasAfectadas() {
		return this.plantasAfectadas;
	}

	/**
	 * Permite ingresar comentarios y detalles sobre las condiciones del cultivo.
	 */
	public void setObservaciones(String pObservaciones) {
		this.observaciones = pObservaciones;
	}

	/**
	 * Devuelve las observaciones realizadas durante la inspección.
	 */
	public String getObservaciones() {
		return this.observaciones;
	}

	public float getNivelInsidencia() {
		return (float) ((this.plantasAfectadas / (float) this.totalPlantasEvaluadas) * 100);
	}

	public String getNivelAlerta() {
		float incidencia = getNivelInsidencia();
		if (incidencia >= 80) {
			return "CRÍTICO";
		} else if (incidencia >= 50) {
			return "ALTO";
		} else if (incidencia >= 20) {
			return "MEDIO";
		} else {
			return "BAJO";
		}
	}

	/**
	 * Asocia un cultivo al resultado técnico.
	 * ?Parámetro: pCultivo → objeto de tipo Cultivo.
	 */
	public void agregarCultivo(Cultivo pCultivo) {
		this.cultivo.add(pCultivo);
	}

	/**
	 * Retorna el cultivo asociado al resultado técnico
	 */
	public Cultivo getCultivo() {
		return (Cultivo) this.cultivo;
	}

	/**
	 * Añade una plaga observada en la inspección técnica.
	 * Parámetro: pPlaga → objeto de tipo Plaga.
	 */
	public void agregarPlaga(Plaga pPlaga) {
		this.plaga = pPlaga;
	}

	/**
	 * Devuelve la plaga registrada durante el proceso de inspección.
	 */
	public Plaga getPlaga() {
		return this.plaga;
	}

	/**
	 * Asocia el resultado técnico con la inspección fitosanitaria correspondiente.
	 * Parámetro: pInspeccionFitosanitaria → objeto InspeccionFitosanitaria
	 */
	public void setInspeccionFitosanitaria(InspeccionFitosanitaria pInspeccionFitosanitaria) {
		this.inspeccionFitosanitaria = pInspeccionFitosanitaria;
	}

	/**
	 * Devuelve la inspección fitosanitaria relacionada.
	 * 
	 *  
	 */
	public InspeccionFitosanitaria getInspeccionFitosanitaria() {
		return this.inspeccionFitosanitaria;
	}

}
