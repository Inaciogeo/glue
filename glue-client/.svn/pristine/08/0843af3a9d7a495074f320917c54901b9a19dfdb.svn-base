package br.org.funcate.glue.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.org.funcate.glue.model.canvas.ThemeAttributesInfoTool;

@SuppressWarnings("serial")
public class ThemeAttributesPanel extends JScrollPane {

	private JPanel mainPanel;
	private JLabel labelAlias;
	private JLabel labelValue;
	private ThemeAttributesInfoTool info;
	private String alias;
	private String tooltip; 
	private String value;


	public ThemeAttributesPanel(ThemeAttributesInfoTool info) {
		setBackground(new Color(255, 255, 255));
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 255));
		this.info = info;
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

		List<Object> attributes = this.info.getAttributes();
		
		for (int i = 0; i < attributes.size(); i++) {
			
			JPanel panel = new JPanel(new GridLayout(0, 2));
				
			panel.setBackground(new Color(255,255,255));
				
			if(!attributes.isEmpty()) {
				alias = " " + (String) attributes.get(i)+":";
				tooltip = (String) attributes.get(i);
				value = (String) attributes.get(++i) + " ";		
			}
		
			labelAlias = new JLabel(alias);
			labelAlias.setFont(new Font("Arial", 1, 14));
			labelAlias.setForeground(Color.BLACK);
			labelAlias.setToolTipText(tooltip);
			panel.add(labelAlias);
			
			labelValue = new JLabel(value);
			labelValue.setFont(new Font("Arial", 1, 11));
			labelValue.setForeground(Color.BLUE);

			panel.add(labelValue);

			mainPanel.add(panel);
		}
		JPanel pnlLine = new JPanel();
		pnlLine.setBackground(new Color(255, 255, 255));
		mainPanel.add(pnlLine);
		pnlLine.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.setViewportView(mainPanel);
		
		JPanel pnlLine1 = new JPanel();
		pnlLine1.setBackground(Color.WHITE);
		mainPanel.add(pnlLine1);
		pnlLine1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel pnlLine2 = new JPanel();
		pnlLine2.setBackground(Color.WHITE);
		mainPanel.add(pnlLine2);
		pnlLine2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}

	/**
	 * @return the info
	 */
	public ThemeAttributesInfoTool getInfo() {
		return info;
	}
}
