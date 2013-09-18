package fr.gfi.cmg.QuizzCmg.util;



import org.apache.log4j.Logger;
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
		
		String[] tab=name.split(" ");
		
		if(tab!=null && tab.length>0){
			name=tab[tab.length-1];
		}
		StringBuffer sb = new StringBuffer("Début de la méthode *************************** "+name  /*+" argument de la méthode: ["*/);

		// Liste des valeurs des arguments reçus par la méthode
//		for (int i = 0; i < args.length; i++) {
//			Object o = args[i];
//			sb.append("'" + o + "'");
//			sb.append((i == args.length - 1) ? "" : ", ");
//		}
//		sb.append("]");

		log.debug(sb.toString());
	}

	// Cette méthode est appelée à chaque fois (et après) qu'une méthode du
	// package ew.service est interceptée
	// Elle reçoit en argument 'result' qui est le retour de la méthode
	// interceptée
	public void logMethodExit(StaticPart staticPart, Object result) {

		// Nom de la méthode interceptée
		String name = staticPart.getSignature().toLongString();
		//String nomClass=staticPart.getSignature().getDeclaringTypeName();
		
		//String name =nomClass+"."+staticPart.getSignature().getName();
		
		String[] tab=name.split(" ");
		
		if(tab!=null && tab.length>0){
			name=tab[tab.length-1];
		}
		log.debug("Fin de la méthode *************************** "+name/*+ " éléments retournés: [" + result + "]"*/);

	}
	
	public void logDBException( JoinPoint joinPoint, Exception ex) {
      
            log.error(ex.getMessage()+" "+ ex.getCause());
      
 }


}
