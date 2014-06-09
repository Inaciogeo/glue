package br.org.funcate.glue.model.thread;

import java.util.Calendar;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.LoadingStatusService;
import br.org.funcate.glue.utilities.Clock;

/**
 * \brief Class to plot the current settings. The task is performed in periods
 * of 5 seconds.
 * 
 * @author MOREIRA, Vinicius Fernandes
 * @version 1.1.0
 */
public class AutomaticPlotter extends Thread {

	/** The initial seconds of the timer. */
	private int _initSeconds;
	/** The current seconds of the timer. */
	private int _currentSeconds;
	/** The necessary seconds to AutomaticApplicator apply changes in the view. */
	private int _secondsToApply;
	/** The object that controls this thread. */
	private Object _controller;
	/** Attribute that says if the AutomaticPlotter plotted. */
	private boolean _plotted;
	/** Feedback of Treeview. (Gif loading) */
	private boolean _feedBack;

	/**
	 * Main constructor that receives the seconds necessary to the instance of
	 * the class apply changes in the view.
	 * 
	 * @author MOREIRA, Vinicius Fernandes
	 * @param c
	 *            The object that will control this thread.
	 * @param secondsToApply
	 *            The seconds necessary to the instance of the class apply
	 *            changes in the view
	 */
	public AutomaticPlotter(Object c, int secondsToApply) {
		_secondsToApply = secondsToApply;
		_plotted = false;
		_controller = c;
		start();
	}

	@Override
	public void run() {
		_initSeconds = 0;
		_currentSeconds = 0;

		while (true) {
			_plotted = false;
			_feedBack= false;
			// Inicializa o contador pegando os segundos iniciais:
			_initSeconds = Clock.getCurrentSecond(Calendar.getInstance());

			// Inicializa o laço que irá verificar a contagem dos segundos:
			while (true) {
				// Captura os segundos atuais para realizar a contagem
				_currentSeconds = Clock.getCurrentSecond(Calendar.getInstance());

				if (_currentSeconds < _initSeconds) {
					_currentSeconds = _initSeconds + _currentSeconds;
				}

				// Verifica se já se passaram os segundos necessários
				if (canApply()) {
					if (!_plotted) {
						AppSingleton singleton = AppSingleton.getInstance();
						Mediator mediator = singleton.getMediator();
						mediator.reDrawView();
						_plotted = true;
						LoadingStatusService.removeThreadCount();
					}
					pause();
					break;

				//Verifica se não ativou o feedback do gif "carregando"
				}else if (!_feedBack){
					LoadingStatusService.addThreadCount();
					_feedBack = true;
				}
			}
		}
	}

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Method to verify whether 5
	 *         seconds have passed since the last action.
	 * @return True - Success.
	 */
	private boolean canApply() {
		int difference = _currentSeconds - _initSeconds;

		if (difference == _secondsToApply) {
			return true;
		}

		return false;
	}

	/**
	 * \brief Method to pause an AutomaticPlotter instance.
	 * 
	 * @author MOREIRA, Vinicius Fernandes
	 * @throws InterruptedException
	 */
	private void pause() {
		synchronized (_controller) {
			try {
				_controller.wait();
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * \brief Method to pause the AutomaticPlotter manually.
	 * 
	 * @author MOREIRA, Vinicius Fernandes
	 */
	void pausePlotter() {
		_plotted = true;
	}
}
