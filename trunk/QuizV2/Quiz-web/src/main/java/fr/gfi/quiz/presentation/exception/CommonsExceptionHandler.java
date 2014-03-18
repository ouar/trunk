package fr.gfi.quiz.presentation.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import fr.gfi.quiz.metier.exception.BusinessServiceException;

/**
 * Gestionnaire d'exception commun à toute l'application Quiz.
 * Ce gestionnaire traite pour actuellement  les exceptions :<br><ul>
 * <li><code>BusinessServiceException</code></li><li><code>XSSSecurityException</code></li> </ul>
 *
 * @author CCMT Team
 *
 */
@Component
public class CommonsExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception ex) {

		// Handling Business Service exception
		if (ex instanceof BusinessServiceException) {
			// Handling Business Service exception

			// Rendering the "error" view to handle the exception
			ModelAndView mav = new ModelAndView("business.exception.scene");
			mav.addObject("exceptionMessage", ex.getMessage());

			return mav;
		} else {
			return null;
		}

	}

}