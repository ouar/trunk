package fr.gfi.cmg.QuizzCmg.util;

public class Test {

	public static void main(String[] args) {
		
	String numDate="20130828";
	String numcmd="";
			
					//le premier num�ro de la liste des num�ro de commande est � 1
					int tmp = 1 ;
					
				
						tmp = Integer.parseInt("00242") + 1;
					
					
					if (tmp<10)
						numcmd = numDate+"0000"+tmp;
					else if (tmp<100)
						numcmd = numDate+"000"+tmp;
					else if (tmp<1000)
						numcmd = numDate+"00"+tmp;
					else if (tmp<10000)
						numcmd = numDate+""+tmp;
	
	
		System.out.println(numcmd);

	}

}
