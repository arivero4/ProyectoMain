package model;

import java.util.Collection;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Modela los organismos o factores bióticos que pueden afectar negativamente los cultivos.
 * Registra información técnica sobre cada plaga identificada durante las inspecciones fitosanitarias.
 * 
 * Esta clase es esencial para el monitoreo y control sanitario del sistema agrícola, permitiendo
 * vincular plagas detectadas con los cultivos afectados y los resultados técnicos de las inspecciones.
 * Facilita el seguimiento de poblaciones de plagas y la evaluación de amenazas fitosanitarias.
 */
public class Plaga {

	/**
	 * Identificador único de la plaga dentro del sistema.
	 */
	private String id;

	/**
	 * Nombre común con el que se conoce la plaga (por ejemplo, “Gusano cogollero”, “Pulgón verde”).
	 */
	private String nombreComun;

	/**
	 * Denominación científica de la plaga, utilizada en reportes técnicos y estudios biológicos (por ejemplo, Spodoptera frugiperda).
	 */
	private String nombreCientifico;

	/**
	 * Detalles descriptivos de la plaga, como su morfología, ciclo biológico, síntomas en el cultivo o métodos de control.
	 */
	private String descripcion;

	private ResultadoTecnico resultadoTecnico;

	private Collection<Cultivo> cultivo;

	/**
	 * Constructor por defecto. Crea una instancia vacía de la plaga.
	 * 
	 *  
	 */
	public Plaga() {

	}

	/**
	 * Establece el identificador único de la plaga.
	 */
	public void setId(String pId) {
		this.id = pId;
	}

	/**
	 * Devuelve el identificador actual.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Registra o modifica el nombre común de la plaga.
	 */
	public void setNombreComun(String pNombreComun) {
		this.nombreComun = pNombreComun;
	}

	/**
	 * Retorna el nombre común actual.
	 */
	public String getNombreComun() {
		return this.nombreComun;
	}

	/**
	 * Asigna o actualiza el nombre científico de la plaga.
	 */
	public void setNombreCientifico(String pNombreCientifico) {
		this.nombreCientifico = pNombreCientifico;
	}

	/**
	 * Devuelve el nombre científico registrado.
	 */
	public String getNombreCientifico() {
		return this.nombreCientifico;
	}

	/**
	 * Permite escribir o actualizar la descripción detallada de la plaga.
	 */
	public void setDescripcion(String pDescripcion) {
		this.descripcion = pDescripcion;
	}

	/**
	 * Devuelve la descripción almacenada.
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Asocia la plaga a un cultivo específico donde fue detectada.
	 * Parámetro: pCultivo → objeto Cultivo.
	 */
	public void setCultivo(Cultivo pCultivo) {
		this.cultivo.add(pCultivo);
	}

	/**
	 * Devuelve el cultivo al que está asociada la plaga.
	 */
	public Cultivo getCultivo() {
		return (Cultivo) this.cultivo;
	}

	/**
	 * Asocia el resultado técnico que registró la presencia de esta plaga.
	 * Parámetro: pResultadoTecnico → objeto ResultadoTecnico.
	 */
	public void agregarResultadoTecnico(ResultadoTecnico pResultadoTecnico) {
		this.resultadoTecnico = pResultadoTecnico;
	}

	/**
	 * 	Retorna el resultado técnico vinculado a la plaga detectada.
	 */
	public ResultadoTecnico getResultadoTecnico() {
		return this.resultadoTecnico;
	}

}
