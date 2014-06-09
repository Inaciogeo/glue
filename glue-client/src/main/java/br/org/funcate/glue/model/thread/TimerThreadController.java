package br.org.funcate.glue.model.thread;

import java.util.ArrayList;

import br.org.funcate.glue.utilities.patterns.ActionObserver;
import br.org.funcate.glue.utilities.patterns.ActionSubject;

/**
 * @author MOREIRA, Vinicius Fernandes \brief Class to control the timer thread.
 *         It's was built with Singleton Design Pattern.
 */
public class TimerThreadController implements ActionSubject {
	private static TimerThreadController _instance;
	private AutomaticApplicator _timerThread;

	private ArrayList<ActionObserver> _actionObservers;

	private TimerThreadController() {
	}

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Method to get the unique
	 *         instance of the class.
	 * @return The unique instance of the class.
	 */
	public static TimerThreadController getInstance() {
		if (_instance == null) {
			_instance = new TimerThreadController();
		}

		return _instance;

	}

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Method to pause the timer
	 *         thread.
	 */
	public void pauseTimerThread() {
		if (!_timerThread.isPaused()) {
			_timerThread.pauseTimerThread();
		}

	}

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Method to start the timer
	 *         thread.
	 */
	public void startTimerThread() {
		if (_timerThread == null) {
			_timerThread = new AutomaticApplicator();
			Thread threadMother = new Thread(_timerThread);
			_timerThread.setThreadMother(threadMother);

			addActionObserver(_timerThread);
			threadMother.start();
		} else {
			_timerThread.restartTimerThread();
		}
	}

	@Override
	public boolean addActionObserver(ActionObserver o) {
		if (_actionObservers == null) {
			_actionObservers = new ArrayList<ActionObserver>();
		}

		return _actionObservers.add(o);
	}

	@Override
	public boolean removeActionObserver(ActionObserver o) {
		if (_actionObservers == null) {
			_actionObservers = new ArrayList<ActionObserver>();
			return false;
		}

		return _actionObservers.remove(o);
	}

	@Override
	public void notifyActionObservers() {
		for (ActionObserver o : _actionObservers) {
			o.updateActionOcurred();
		}

	}

}
