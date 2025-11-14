package model;

import java.util.Collection;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Representa al técnico encargado de realizar las inspecciones fitosanitarias 
 * y brindar apoyo técnico a productores.
 * Extiende de Usuario para incluir información profesional específica de los técnicos.
 * 
 * Esta clase gestiona la información profesional del asistente técnico, incluyendo 
 * su tarjeta profesional, los lugares de producción que supervisa y las inspecciones 
 * fitosanitarias que realiza. Es fundamental para el seguimiento y trazabilidad 
 * de las actividades de inspección en el sistema.
 */
public class AsistenteTecnico extends Usuario {

	/**
	 * Identificador único del asistente técnico.
	 */
	private String id;

	/**
	 * Número de la tarjeta profesional que acredita al asistente técnico.
	 */
	private String numeroTarjetaProfesional;

	private Collection<LugarProduccion> lugarProduccion;

	private Collection<InspeccionFitosanitaria> inspeccionFitosanitaria;

	/**
	 * Constructor por defecto que permite crear un objeto productor vacío para luego asignarle datos mediante los métodos setter.
	 * 
	 *  
	 */
	public AsistenteTecnico() {

	}

	/**
	 * Asigna un valor único al identificador del asistente técnico. Garantiza la unicidad en el registro del sistema.
	 */
	@Override
	public void setId(String pId) {
		this.id = pId;
	}

	/**
	 * Devuelve el identificador actual del asistente técnico.
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * Permite registrar o modificar el número de la tarjeta profesional del asistente técnico.
	 */
	public void setNumeroTarjetaProfesional(String pNumeroTarjetaProfesional) {
		this.numeroTarjetaProfesional = pNumeroTarjetaProfesional;
	}

	/**
	 * Retorna el número de la tarjeta profesional registrada.
	 */
	public String getNumeroTarjetaProfesional() {
		return this.numeroTarjetaProfesional;
	}

	/**
	 * Asocia un lugar de producción que el asistente técnico supervisa o asesora. Este método establece una relación directa entre el técnico y los lugares donde realiza su labor técnica.
	 */
	public void agregarLugarProduccion(LugarProduccion pLugarProduccion) {
		this.lugarProduccion.add(pLugarProduccion);
	}

	/**
	 * Registra una inspección fitosanitaria realizada por el asistente técnico en un lugar determinado. Permite llevar control sobre las inspecciones ejecutadas.
	 */
	public LugarProduccion getLugarProduccion() {
		return (LugarProduccion) this.lugarProduccion;
	}

	/**
	 * Registra una inspección fitosanitaria realizada por el asistente técnico en un lugar determinado. Permite llevar control sobre las inspecciones ejecutadas.
	 */
	public void agregarInspeccionFitosanitaria(InspeccionFitosanitaria pInspeccionFitosanitaria) {
		this.inspeccionFitosanitaria.add(pInspeccionFitosanitaria);
	}

	/**
	 * Retorna las inspeccion fitosanitaria  vinculados.
	 */
	public InspeccionFitosanitaria getInspeccionFitosanitaria() {
		return (InspeccionFitosanitaria) this.inspeccionFitosanitaria;
	}

}
