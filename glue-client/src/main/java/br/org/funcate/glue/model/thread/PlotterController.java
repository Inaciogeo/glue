package br.org.funcate.glue.model.thread;

/**
 * \brief Class to control the AutomaticPlotter thread. It's was built with
 * Singleton Design Pattern.
 * 
 * @author MOREIRA, Vinicius Fernandes
 * @version 1.1.0
 */
public class PlotterController {
	/** The single instance of this class. */
	private static PlotterController _instance;
	/** The AutomaticPlotter instance controlled by the instance of the class. */
	private static AutomaticPlotter _plotter;

	/** Private main constructor. Only the own class can call it. */
	private PlotterController() {
	}

	/**
	 * \brief Method to get the unique instance of the class.
	 * 
	 * @author MOREIRA, Vinicius Fernandes
	 * @return The single of the class.
	 */
	public static PlotterController getInstance() {
		if (_instance == null) {
			_instance = new PlotterController();
		}

		return _instance;

	}

	/**
	 * \brief Method to start the AutomaticPlotter thread.
	 * 
	 * @author MOREIRA, Vinicius Fernandes
	 */
	public void startPlotter() {
		if (_plotter == null) {
			// Passando 5 segundos para o construtor do AutomaticApplicator:
			_plotter = new AutomaticPlotter(this, 3);
		} else {
			synchronized (this) {
				this.notifyAll();
			}
		}
	}

	/**
	 * \brief Method to try pause the AutomaticPlotter thread.
	 * 
	 * @author MOREIRA, Vinicius Fernandes
	 */
	public void pausePlotter() {
		if (_plotter != null) {
			_plotter.pausePlotter();
		}
	}

}
