package br.org.funcate.glue.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.org.funcate.glue.controller.PanelAddViewThemeAdapterController;

@SuppressWarnings("serial")
public class PanelViewThemeAddButton extends JPanel {

	private JButton lblAddView;
	private JButton lblAddTheme;

	public PanelViewThemeAddButton() {
		this.setLayout(null);

		PanelAddViewThemeAdapterController adapter = new PanelAddViewThemeAdapterController(this);
		String _htmlView = "Criar Vista";
		lblAddView = new JButton(_htmlView);
		lblAddView.setBackground(new Color(255, 255, 255));
		lblAddView.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblAddView.setBounds(6, 6, 120, 30);
		lblAddView.setFont(new Font("default", Font.BOLD, 13));

		//String _htmlTheme = "<html><font color=\"#000080\"><u>Criar Tema</u></font></html>";
		String _htmlTheme = "Criar Tema";
		lblAddTheme = new JButton(_htmlTheme);
		lblAddTheme.setBackground(new Color(255, 255, 255));
		lblAddTheme.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblAddTheme.setBounds(126, 6, 120, 30);
		lblAddTheme.setFont(new Font("default", Font.BOLD, 13));

		lblAddView.addMouseListener(adapter);
		lblAddTheme.addMouseListener(adapter);

		this.add(lblAddView);
		this.add(lblAddTheme);
	}

	public JButton getLblAddView() {
		return lblAddView;
	}

	public void setLblAddView(JButton lblAddView) {
		this.lblAddView = lblAddView;
	}

	public JButton getLblAddTheme() {
		return lblAddTheme;
	}

	public void setLblAddTheme(JButton lblAddTheme) {
		this.lblAddTheme = lblAddTheme;
	}
}
