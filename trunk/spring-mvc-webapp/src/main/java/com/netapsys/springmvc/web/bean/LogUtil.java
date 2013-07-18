package com.netapsys.springmvc.web.bean;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;

public class LogUtil {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(LogUtil.class.getName());

	// Cette méthode est appelée à chaque fois (et avant) qu'une méthode du
	// package ew.service est interceptée
	public void logMethodEntry(JoinPoint joinPoint) {

		Object[] args = joinPoint.getArgs();

		// Nom de la méthode interceptée
		String name = joinPoint.getSignature().toLongString();
		StringBuffer sb = new StringBuffer(name + " called with: [");

		// Liste des valeurs des arguments reçus par la méthode
		for (int i = 0; i < args.length; i++) {
			Object o = args[i];
			sb.append("'" + o + "'");
			sb.append((i == args.length - 1) ? "" : ", ");
		}
		sb.append("]");

		log.info("trace log**********************************************"+sb.toString());
	}

	// Cette méthode est appelée à chaque fois (et après) qu'une méthode du
	// package ew.service est interceptée
	// Elle reçoit en argument 'result' qui est le retour de la méthode
	// interceptée
	public void logMethodExit(StaticPart staticPart, Object result) {

		// Nom de la méthode interceptée
		String name = staticPart.getSignature().toLongString();
		log.info(name + " returning: [" + result + "]");

	}
}
