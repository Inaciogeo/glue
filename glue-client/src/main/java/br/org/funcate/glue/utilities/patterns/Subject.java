package br.org.funcate.glue.utilities.patterns;

/**
 * \brief This interface defines methods used by Subject in the Observer
 * Pattern.
 * 
 * @author Moraes, Emerson Leite
 * 
 */
public interface Subject {

	/**
	 * This method is responsible for adding Observers on Subject
	 * 
	 * @param pObserver
	 */
	public void addObserver(Observer pObserver);

	/**
	 * This method notifies all the Observers when there is a change in Subject
	 */
	public void notifyObserver();
}
