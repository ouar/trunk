package fr.gfi.cmg.QuizzCmg.util;



import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;

public class LogUtil {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(LogUtil.class.getName());

	// Cette m�thode est appel�e � chaque fois (et avant) qu'une m�thode du
	// package ew.service est intercept�e
	public void logMethodEntry(JoinPoint joinPoint) {

		Object[] args = joinPoint.getArgs();		

		// Nom de la m�thode intercept�e
		String name = joinPoint.getSignature().toLongString();
		
		String[] tab=name.split(" ");
		
		if(tab!=null && tab.length>0){
			name=tab[tab.length-1];
		}
		StringBuffer sb = new StringBuffer("D�but de la m�thode *************************** "+name  /*+" argument de la m�thode: ["*/);

		// Liste des valeurs des arguments re�us par la m�thode
//		for (int i = 0; i < args.length; i++) {
//			Object o = args[i];
//			sb.append("'" + o + "'");
//			sb.append((i == args.length - 1) ? "" : ", ");
//		}
//		sb.append("]");

		log.debug(sb.toString());
	}

	// Cette m�thode est appel�e � chaque fois (et apr�s) qu'une m�thode du
	// package ew.service est intercept�e
	// Elle re�oit en argument 'result' qui est le retour de la m�thode
	// intercept�e
	public void logMethodExit(StaticPart staticPart, Object result) {

		// Nom de la m�thode intercept�e
		String name = staticPart.getSignature().toLongString();
		//String nomClass=staticPart.getSignature().getDeclaringTypeName();
		
		//String name =nomClass+"."+staticPart.getSignature().getName();
		
		String[] tab=name.split(" ");
		
		if(tab!=null && tab.length>0){
			name=tab[tab.length-1];
		}
		log.debug("Fin de la m�thode *************************** "+name/*+ " �l�ments retourn�s: [" + result + "]"*/);

	}
	
	public void logDBException( JoinPoint joinPoint, Exception ex) {
      
            log.error(ex.getMessage()+" "+ ex.getCause());
      
 }


}
