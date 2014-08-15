package br.org.funcate.glue.exception;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/*
 * Esta classe utiliza recursos da biblioteca log4j para gerar e armazenar relatórios de erros
 * diariamente com validade de uma semana. Os arquivos estão localizados na pasta ExceptionLogFile
 */

public class DailyExceptionLogger {

	static Logger logger = Logger.getLogger(DailyExceptionLogger.class);

	@SuppressWarnings("deprecation")
	public static void logging(String textError, Exception e) {

		// CUIDADO: a alteracao do diretorio abaixo (FileTest) pode acarretar
		// na exclusao de arquivos importantes do sistema
		File file = new File("ExceptionLogFile");

		if (!file.exists())
			file.mkdir();

		File[] fl = file.listFiles();

		Date currentDate = new Date();
		Date testDate = new Date();

		BasicConfigurator.configure();
		DailyRollingFileAppender fileAppender;

		// removendo arquivo de log de sete dias anteriores (ou anteriores)
		for (int i = 0; i < fl.length; i++) {
			Date da = new Date(fl[i].lastModified());
			testDate.setTime(currentDate.getTime() - da.getTime());

			if ((testDate.getDate() + 1) >= 7
					&& !fl[i].getName().equals("log_")) {
				fl[i].delete();
			}
		}

		try {
			// definindo
			fileAppender = new DailyRollingFileAppender(new PatternLayout(),
					"ExceptionLogFile//log_", "yyyy-MM-dd");
			fileAppender.setDatePattern("yyyy-MM-dd");
			logger.addAppender(fileAppender);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		logger.error(textError, e);
	}
}