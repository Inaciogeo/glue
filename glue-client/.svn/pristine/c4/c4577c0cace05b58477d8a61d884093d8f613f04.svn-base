package br.org.funcate.glue.model.thread;

/**
 * @author MOREIRA, Vinicius Fernandes \brief Singleton class that start the
 *         Thread that try clean the VM memory. Date.: 23/06/2010
 */
public class Cleaner {
	private static Cleaner _instance;
	private GarbageCollectorExecutor _gcExecutor;
	private Thread _gcThread;

	/**
	 * Main constructor that initializes a new Cleaner.
	 */
	private Cleaner() {
		_gcExecutor = new GarbageCollectorExecutor();
		_gcThread = new Thread(_gcExecutor);
	}

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Method to get the unique
	 *         existing Cleaner object. Date.: 23/06/2010.
	 * @return The unique existing Cleaner object.
	 */
	public static Cleaner getInstance() {
		if (_instance == null) {
			_instance = new Cleaner();
		}

		return _instance;
	}

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Method to start the
	 *         GarbageCollectorExecutor. Date.: 23/06/2010. Updated 01.:
	 *         24/06/2010
	 * @return TRUE - Success !
	 */
	public boolean start() {
		try {
			_gcThread.start();
			return true;
		} catch (Exception e) {
			synchronized (_gcExecutor) {
				_gcExecutor.start();
				return true;
			}
		}
	}

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Method to pause the
	 *         GarbageCollectorExecutor. Date.: 23/06/2010. Updated 01.:
	 *         24/06/2010
	 * @return TRUE - Success !
	 */
	public boolean pause() {
		if (_gcThread.isAlive()) {
			synchronized (_gcExecutor) {
				_gcExecutor.pausedExecution();
			}
			return true;
		}

		return false;
	}

}
