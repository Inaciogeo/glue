package br.org.funcate.glue.view;

import javax.swing.JComboBox;

import br.org.funcate.glue.controller.listener.ComboBoxScaleAdapter;

@SuppressWarnings("serial")
public class ComboBoxScale extends JComboBox<Object> {

	@SuppressWarnings("unchecked")
	public ComboBoxScale() {
		ComboBoxScaleAdapter adapter = new ComboBoxScaleAdapter(this);
		this.addActionListener(adapter);
		this.setModel(adapter.getModel());
	}
}
