package br.org.funcate.glue.utilities.patterns;

/**
 * @author MOREIRA, Vinicius Fernandes \brief Interface that represents the
 *         Subject in the Observer Design Pattern. This kind of subject sends
 *         updates to its observers when there is some action on it.
 */
public interface ActionSubject {
	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Method to add the target
	 *         action observer for this subject.
	 * @param o
	 *            The target action observer
	 * @return TRUE - Added.
	 */
	boolean addActionObserver(ActionObserver o);

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Method to remove the target
	 *         action observer for this subject.
	 * @param o
	 *            The target action observer
	 * @return TRUE - Removed.
	 */
	boolean removeActionObserver(ActionObserver o);

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Method to notify the observers
	 *         of this subject that an action occurred.
	 */
	void notifyActionObservers();

}
