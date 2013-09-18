package fr.gfi.cmg.QuizzCmg.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		
		
		System.out.println(15 & 4);

		SimpleDateFormat formater = null;

		Date aujourdhui = new Date();

		formater = new SimpleDateFormat("dd-MM-yy");
		System.out.println(formater.format(aujourdhui));

		formater = new SimpleDateFormat("yyyyMMddhhmmss");
		System.out.println(formater.format(aujourdhui));

		formater = new SimpleDateFormat("yyMMdd");
		System.out.println(formater.format(aujourdhui));

		formater = new SimpleDateFormat("h:mm a");
		System.out.println(formater.format(aujourdhui));

		formater = new SimpleDateFormat("K:mm a, z");
		System.out.println(formater.format(aujourdhui));

		formater = new SimpleDateFormat("hh:mm a, zzzz");
		System.out.println(formater.format(aujourdhui));

		formater = new SimpleDateFormat("EEEE, d MMM yyyy");
		System.out.println(formater.format(aujourdhui));

		formater = new SimpleDateFormat("'le' dd/MM/yyyy 'à' hh:mm:ss");
		System.out.println(formater.format(aujourdhui));

		formater = new SimpleDateFormat("'le' dd MMMM yyyy 'à' hh:mm:ss");
		System.out.println(formater.format(aujourdhui));

		formater = new SimpleDateFormat("dd MMMMM yyyy GGG, hh:mm aaa");
		System.out.println(formater.format(aujourdhui));

		formater = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println(formater.format(aujourdhui));

	}

}
