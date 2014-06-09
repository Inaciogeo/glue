package br.org.funcate.glue.exception;

@SuppressWarnings("serial")
public class GlueClientException extends Exception {

	public GlueClientException(String message, Throwable cause) {

		super(message, cause);

		System.out.println(message);
		System.out.println("\nAtenção! Uma Exception foi gerada pelo sistema: \nPossível erro no método " + message + ".\n"
				+ cause.getMessage() + "\n");
		cause.printStackTrace();
	}
}