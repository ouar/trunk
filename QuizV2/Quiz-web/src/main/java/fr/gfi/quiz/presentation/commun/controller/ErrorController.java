package fr.gfi.quiz.presentation.commun.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author yguenoun-e
 *
 */
@Controller
public class ErrorController {

	private final static Logger LOGGER= LoggerFactory.getLogger(ErrorController.class);

	@RequestMapping(value = "/errors/404")
    public String pageNotFound(HttpServletRequest request) {
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("404 error : " + request.getAttribute("javax.servlet.forward.request_uri"));
        }
        return "error.404.scene";
    }

    @RequestMapping(value = "/errors/500")
    public String internalServerError(HttpServletRequest request) {
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("500 error !");
        }
        return "error.500.scene";
    }

    @RequestMapping(value = "/errors/403")
    public String accessDeniedException() {
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("403 error !");
        }
        return "security.exception.scene";
    }

    @RequestMapping(value = "/errors/xss")
    public String xssException() {
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("xss error !");
        }
        return "security.xss.exception.scene";
    }

	@RequestMapping(value = "/errors/bfa")
	public String bfaException() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("bfa detected !");
		}
		return "bfa.exception.scene";
	}

}
