package br.org.funcate.glue.model.thread;

import java.util.Calendar;

import br.org.funcate.glue.utilities.patterns.ActionObserver;

/**
 * @author MOREIRA, Vinicius Fernandes \brief Class to apply the current
 *         settings in memory of the application. The task is performed in
 *         periods of 15 / 2.
 */
public class AutomaticApplicator implements Runnable, ActionObserver {

	/** < Attribute type int > */
	private int _initSeconds;

	/** < Attribute type int > */
	private int _currentSeconds;

	/** < Attribute type Thread > */
	@SuppressWarnings("unused")
	private Thread _motherThread;

	/** < Attribute type boolean > */
	private boolean _actionOcurred;

	/** < Attribute type boolean > */
	private boolean _paused;

	/**
	 * Builder's single class that receives a Tree object.
	 * 
	 * @param pTreeController
	 *            The Tree object.
	 */
	public AutomaticApplicator() {
	}

	@Override
	public void run() {
		_initSeconds = 0;
		_currentSeconds = 0;
		_actionOcurred = false;
		_paused = false;

		while (true) {
			// Verifica se ocorreu uma ação
			if (actionOccured() == false && _paused == false) {
				// Se não ocorreu:

				// Inicializa o contador pegando os segundos iniciais:
				_initSeconds = Calendar.getInstance().get(Calendar.SECOND);

				// Inicializa o laço que irá verificar a contagem dos segundos:
				while (true) {
					// Verifica novamente se ocorreu alguma ação:
					if (actionOccured() || _paused == true) {
						// Se ocorreu a contagem é parada.
						_actionOcurred = false;
						break;
					}

					// Captura os segundos atuais para realizar a contagem
					_currentSeconds = Calendar.getInstance().get(Calendar.SECOND);

					if (_currentSeconds < _initSeconds) {
						_currentSeconds = _initSeconds + _currentSeconds;
					}

					// Verifica se já se passaram 15 segundos
					if (canApply()) {
						// Verifica novamente se ocorreu alguma ação:
						if (actionOccured() || _paused == true) {
							// Se ocorreu a contagem é parada.
							_actionOcurred = false;
							break;
						}

						_actionOcurred = false;
						pauseTimerThread();

						break;
					}
				}
			}
		}

	}

	@Override
	public void updateActionOcurred() {
		_actionOcurred = true;
	}

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Method to verify if an action
	 *         ocurred.
	 * @return TRUE - Ocurred.
	 */
	private boolean actionOccured() {
		return _actionOcurred;
	}

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Method to verify whether 15
	 *         seconds have passed since the last action.
	 * @return TRUE - Yes.
	 */
	private boolean canApply() {
		int difference = _currentSeconds - _initSeconds;

		if (difference == 15) {
			return true;
		}

		return false;
	}

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Method to set the Thread that
	 *         is holding this object.
	 * @param pThreadMother
	 *            The Thread object that is holding this object.
	 */
	public void setThreadMother(Thread pThreadMother) {
		_motherThread = pThreadMother;
	}

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Method to stop the
	 *         AutomaticApplicator to do it's job.
	 */
	public void pauseTimerThread() {
		_paused = true;
	}

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Method to restart the
	 *         AutomaticApplicator to do it's job.
	 */
	public void restartTimerThread() {
		_paused = false;
		_actionOcurred = false;
	}

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Method to verify is the timer
	 *         thread is paused. Date.: 22/06/2010
	 */
	public boolean isPaused() {
		return _paused;
	}

}
