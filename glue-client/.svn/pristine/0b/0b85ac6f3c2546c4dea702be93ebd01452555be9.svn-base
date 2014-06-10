package br.org.funcate.glue.utilities;

import java.util.Calendar;

/**
 * \brief Abstract class to obtain information from the system clock. Date.:
 * 13/07/2010
 * 
 * @author MOREIRA, Vinicius Fernandes
 */
public abstract class Clock {
	/**
	 * @author MOREIRA, Vinicius Fernandes. Date.: 13/07/2010 \brief Method that
	 *         returns the current time.
	 * @return A String object representing the time.
	 */
	public static String getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		int hour = getCurrentHour(calendar);
		int minute = getCurrentMinute(calendar);
		int second = getCurrentSecond(calendar);

		return ("" + hour + ":" + minute + ":" + second);
	}

	/**
	 * @author MOREIRA, Vinicius Fernandes. Date.: 13/07/2010 \brief Method that
	 *         returns the current hour.
	 * @return The current hour.
	 */
	public static int getCurrentHour(Calendar calendar) {
		return calendar.get(Calendar.HOUR);
	}

	/**
	 * @author MOREIRA, Vinicius Fernandes. Date.: 13/07/2010 \brief Method that
	 *         returns the current minute.
	 * @return The current minute.
	 */
	public static int getCurrentMinute(Calendar calendar) {
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * @author MOREIRA, Vinicius Fernandes. Date.: 13/07/2010 \brief Method that
	 *         returns the current second.
	 * @return The current second.
	 */
	public static int getCurrentSecond(Calendar calendar) {
		return calendar.get(Calendar.SECOND);
	}

}
