package br.org.funcate.glue.utilities.patterns;

/**
 * @author MOREIRA, Vinicius Fernandes \brief Interface that represents the
 *         Observer in the Observer Design Pattern. This observer updates its
 *         state when some action occurs in the Subject
 */
public interface ActionObserver {
	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Method to apply any change in
	 *         this observer when an action occurred in the subject.
	 */
	void updateActionOcurred();

}
