package service.usuario;

import java.sql.SQLException;
import java.util.logging.Level;

import DAO.UsuarioDAO;
import model.Usuario;
import service.base.ServiceBase;
import service.exceptions.ServiceException;
import service.exceptions.ValidationException;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Servicio para gestionar operaciones de usuario.
 * Proporciona métodos para crear, actualizar, buscar y eliminar usuarios.
 * Incluye validaciones de datos y reglas de negocio.
 */
public class UsuarioService extends ServiceBase<Usuario, UsuarioDAO> {

	/**
	 * Constructor del servicio.
	 * 
	 * @param dao - DAO de usuario inyectado
	 */
	public UsuarioService(UsuarioDAO dao) {
		super(dao);
	}

	/**
	 * Busca un usuario por su ID.
	 * 
	 * @param id - ID del usuario a buscar
	 * @return Usuario encontrado
	 * @throws ServiceException si hay error
	 */
	public Usuario obtenerPorId(long id) throws ServiceException {
		try {
			validatePositive((double) id, "id");
			
			Usuario usuario = dao.obtenerPorId(id);
			
			if (usuario == null) {
				LOGGER.fine("Usuario no encontrado con ID: " + id);
			} else {
				LOGGER.fine("Usuario encontrado: " + usuario.getCorreoElectronico());
			}
			
			return usuario;
			
		} catch (ValidationException e) {
			throw new ServiceException("VALIDATION_ERROR", e.getMessage(), e);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al buscar usuario por ID", e);
			throw new ServiceException("SEARCH_ERROR", "Error buscando usuario", e);
		}
	}

	/**
	 * Busca un usuario por su correo electrónico.
	 * 
	 * @param email - Correo del usuario
	 * @return Usuario encontrado
	 * @throws ServiceException si hay error
	 */
	public Usuario obtenerPorEmail(String email) throws ServiceException {
		try {
			validateNotEmpty(email, "email");
			validateEmail(email);
			
			Usuario usuario = dao.obtenerPorEmail(email);
			
			if (usuario == null) {
				LOGGER.fine("Usuario no encontrado con email: " + email);
			}
			
			return usuario;
			
		} catch (ValidationException e) {
			throw new ServiceException("VALIDATION_ERROR", e.getMessage(), e);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al buscar usuario por email", e);
			throw new ServiceException("SEARCH_ERROR", "Error buscando por email", e);
		}
	}

	/**
	 * Busca un usuario por cédula.
	 * 
	 * @param cedula - Número de cédula
	 * @return Usuario encontrado
	 * @throws ServiceException si hay error
	 */
	public Usuario obtenerPorCedula(String cedula) throws ServiceException {
		try {
			validateNotEmpty(cedula, "cedula");
			validateNumeric(cedula, "cedula");
			
			Usuario usuario = dao.obtenerPorCedula(cedula);
			
			if (usuario == null) {
				LOGGER.fine("Usuario no encontrado con cedula: " + cedula);
			}
			
			return usuario;
			
		} catch (ValidationException e) {
			throw new ServiceException("VALIDATION_ERROR", e.getMessage(), e);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al buscar usuario por cedula", e);
			throw new ServiceException("SEARCH_ERROR", "Error buscando por cedula", e);
		}
	}

	/**
	 * Obtiene todos los usuarios.
	 * 
	 * @return Lista de todos los usuarios
	 * @throws ServiceException si hay error
	 */
	public java.util.List<Usuario> obtenerTodos() throws ServiceException {
		try {
			java.util.List<Usuario> usuarios = dao.obtenerTodos();
			LOGGER.fine("Se obtuvieron " + (usuarios != null ? usuarios.size() : 0) + " usuarios");
			return usuarios;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al obtener todos los usuarios", e);
			throw new ServiceException("GET_ALL_ERROR", "Error obteniendo usuarios", e);
		}
	}

	@Override
	public String getServiceName() {
		return "UsuarioService";
	}
}
