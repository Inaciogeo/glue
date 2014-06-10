package br.org.funcate.glue.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import br.org.funcate.glue.controller.listener.TransparencySliderAdapter;

@SuppressWarnings("serial")
public class TransparencySliderView extends JPanel {

	private JLabel lblTransparency;
	private JSlider sldTransparency;
	private JTextField txfTransparencyShow;

	public TransparencySliderView() {
		this.setLayout(null);
		this.initComponents();
		this.add(sldTransparency);
		this.add(lblTransparency);
		this.add(txfTransparencyShow);
	}

	private void initComponents() {
		lblTransparency = new JLabel("Transparência: %");
		lblTransparency.setBounds(3, 0, 100, 22);

		sldTransparency = new JSlider();
		sldTransparency.setBounds(0, 18, 100, 20);
		sldTransparency.addChangeListener(new TransparencySliderAdapter(this));

		txfTransparencyShow = new JTextField("50");
		txfTransparencyShow.setBounds(110, 8, 35, 25);
		txfTransparencyShow.setEditable(false);
		txfTransparencyShow.setFocusable(false);
	}

	public JSlider getSldTransparency() {
		return sldTransparency;
	}

	public JTextField getTxfTransparencyShow() {
		return txfTransparencyShow;
	}

	public JLabel getLblTransparency() {
		return lblTransparency;
	}

	public void setTransparencyEnabled(boolean b) {
		lblTransparency.setEnabled(b);
		sldTransparency.setEnabled(b);
		txfTransparencyShow.setEnabled(b);
	}
}
