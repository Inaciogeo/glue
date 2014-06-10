package br.org.funcate.glue.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

/**
 * @author Siqueira, Felipe V.
 * 
 * 
 *         \brief Class that implements a timer for user session
 */
@SuppressWarnings("serial")
public class MyTimer extends JLabel {

	private JLabel _lblTimer;
	/** < Attribute type label */
	private Integer _hour;
	/** < Attribute type integer */
	private Integer _minutes;
	/** < Attribute type integer */
	private Integer _seconds;
	/** < Attribute type integer */
	private javax.swing.Timer _tmrTimer;

	/** < Attribute type timer */

	public MyTimer() {

		setLayout(null);

		// How the label timer need to start
		_lblTimer = new JLabel("<html><font color=#828282><i>00:00:00</i></font></html>");
		_lblTimer.setBounds(0, 0, 50, 20);

		_hour = 0;
		_minutes = 0;
		_seconds = 0;

		// Start the action
		start();

		add(_lblTimer);

	}

	/**
	 * @author Siqueira, Felipe V. \brief Method that start the timer action.
	 */
	public void start() {

		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				++_seconds;

				if (_seconds == 59) {
					if (_minutes == 59) {
						_seconds = 0;
						_minutes = 0;
						_hour++;
					} else {
						_seconds = 0;
						_minutes++;
					}

				}

				// Formating the label text
				_lblTimer.setText("<html><font color=#828282><i>" + (_hour < 10 ? "0" + _hour : _hour) + ":"
						+ (_minutes < 10 ? "0" + _minutes : _minutes) + ":" + (_seconds < 10 ? "0" + _seconds : _seconds)
						+ "</i></font></html>");
			}
		};

		_tmrTimer = new javax.swing.Timer(1000, action);
		_tmrTimer.start();

	}

}
