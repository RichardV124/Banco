package co.edu.eam.ingesoft.pa.negocio.excepciones;

import javax.ejb.ApplicationException;

/**
 * Exception de negocio.
 * @author Richard Vanegas
 *
 */
@ApplicationException(rollback=true)
public class ExcepcionNegocio extends RuntimeException {

	/**
	 * COnstructor
	 * @param message
	 */
	public ExcepcionNegocio(String message) {
		super(message);
	}

}
