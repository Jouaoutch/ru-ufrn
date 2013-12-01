package br.ufrn.ru_ufrn.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarioUtil {

	
	public static String[] getDiasDaSemana() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar c = new GregorianCalendar();		
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		String[] dias = new String[7];
		for (int i = 0; i < dias.length; i++) {			
			dias[i] = fmt.format(c.getTime());
			c.roll(Calendar.DAY_OF_WEEK,true);
		}
		return dias;
	}
}
