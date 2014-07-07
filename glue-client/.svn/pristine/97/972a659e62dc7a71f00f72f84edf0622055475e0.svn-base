package br.org.funcate.glue.model.thread;

/**
 * \brief Class to call the VM Garbage Collector of 10 in 10 seconds. Date.:
 * 23/06/2010. Updated 1: 12/08/2010.
 * 
 * @author MOREIRA, Vinicius Fernandes
 * @version 1.0.0
 */
public class GarbageCollectorExecutor implements Runnable {
	/** Attribute to indicate if the instance of this class is paused. */
	private boolean _paused;

	@Override
	public void run() {
		while (true) {
			try {
				if (_paused) {
					pause();
				}

				Thread.sleep(10000);

				// Loop para tentar invocar o coletor de lixos da VM 10 vezes !
				for (int i = 0; i < 10; i++) {
					System.gc();
				}
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * \brief Method to try stop the action. Date.: 23/06/2010. Updated 01.:
	 * 24/06/2010
	 * 
	 * @author MOREIRA, Vinicius Fernandes
	 */
	private void pause() {
		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}

	}

	/**
	 * \brief Method to try restart the action. Date.: 23/06/2010. Updated 01.:
	 * 24/06/2010
	 * 
	 * @author MOREIRA, Vinicius Fernandes
	 * @return TRUE - Success !
	 */
	public void start() {
		synchronized (this) {
			this.notify();
			_paused = false;
		}
	}

	/**
	 * \brief Method to verify if the execution is paused. Date.: 24/06/2010
	 * 
	 * @author MOREIRA, Vinicius Fernandes
	 */
	public boolean isPaused() {
		return _paused;
	}

	/**
	 * \brief Method to pause the execution. Date.: 24/06/2010
	 * 
	 * @author MOREIRA, Vinicius Fernandes
	 */
	public void pausedExecution() {
		_paused = true;
	}

}
