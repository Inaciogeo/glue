package br.org.funcate.glue.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.org.funcate.glue.controller.listener.ScaleViewAdapter;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.ComboBoxScaleService;
import br.org.funcate.glue.model.ScaleElement;
import br.org.funcate.glue.model.ScaleLimit;
import br.org.funcate.glue.model.tree.CustomNode;

@SuppressWarnings("serial")
public class ScaleView extends JFrame {

	private JPanel mainPanel;
	private JLabel lblTitle;
	private static JLabel lblTema;
	private static JComboBox cmbMinima;
	private static JComboBox cmbMaxima;
	private JButton btConfirm;
	private JButton btCancel;
	private static int width = 370;
	private static int height = 490;
	private boolean isActionPerformedBlocked = false;
	private static ScaleView instance;

	public static ScaleView getInstance() {
		if (instance == null) {
			instance = new ScaleView();
//			instance.setVisible(true);
//			instance.setFocusable(true);
		}else {
			CustomNode currentTheme = AppSingleton.getInstance().getMediator().getCurrentTheme();
			ScaleElement[] generated = ComboBoxScaleService.getScaleElementValues();
			ScaleLimit scaleLimit = currentTheme.getScaleLimit();
			int search = 2;
			cmbMaxima.setSelectedIndex(cmbMaxima.getItemCount()-1);
			for(int x=0; x<generated.length && search >0;x++){
				if ( generated[x].getScale() == scaleLimit.getMin()) cmbMinima.setSelectedIndex(x);
				else if (generated[x].getScale() == scaleLimit.getMax()) cmbMaxima.setSelectedIndex(x);
				else if (generated[x].getScale() > scaleLimit.getMax()) cmbMaxima.setSelectedIndex(x);
				else continue;
				search--;
			}
			
			lblTema.setText("<html>Tema: <i>" + currentTheme.getName() );
			instance.dispose();
			instance.setVisible(true);
			instance.setFocusable(true);
			
		}
		return instance;
	}

	private ScaleView() {
		// CustomNode currentTheme =
		// AppSingleton.getInstance().getMediator().getCurrentTheme();
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.white);
		mainPanel.setBounds(5, 5, width - 10, height - 10);
		mainPanel.setLayout(null);

		lblTitle = new JLabel("Escala de Visualização");
		lblTitle.setFont(new Font("default", Font.BOLD, 16));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 10, width - 10, 25);
		mainPanel.add(lblTitle);

		lblTema = new JLabel();
		lblTema.setFont(new Font("default", Font.BOLD, 14));
		lblTema.setHorizontalAlignment(SwingConstants.CENTER);
		lblTema.setBounds(0, 57, width - 10, 25);
		mainPanel.add(lblTema);

		ScaleElement[] generated = ComboBoxScaleService.getScaleElementValues();
		ScaleElement[] minScales = new ScaleElement[generated.length - 1];
		ScaleElement[] maxScales = new ScaleElement[generated.length - 1];

		// ScaleElement[] currentMin = { new
		// ScaleElement(currentTheme.getScaleLimit().getMin(),
		// Math.round(currentTheme.getScaleLimit()
		// .getMin()) + "") };
		// ScaleElement[] currentMax = { new
		// ScaleElement(currentTheme.getScaleLimit().getMax(),
		// Math.round(currentTheme.getScaleLimit()
		// .getMax()) + "") };

		// System.arraycopy(currentMin, 0, minScales, 0, 1);
		// System.arraycopy(currentMax, 0, maxScales, 0, 1);
		System.arraycopy(generated, 0, minScales, 0, generated.length-1);
		System.arraycopy(generated, 0, maxScales, 0, generated.length-1);

		JLabel lblMinScale = new JLabel("Escala Mínima:");
		lblMinScale.setFont(new Font("default", Font.PLAIN, 14));
		lblMinScale.setBounds(50, 110, 200, 25);
		mainPanel.add(lblMinScale);

		cmbMinima = new JComboBox(minScales);
		cmbMinima.setBounds(160, 110, 140, 25);
		cmbMinima.setSelectedIndex(0);
		mainPanel.add(cmbMinima);

		JLabel lblMaxScale = new JLabel("Escala Máxima:");
		lblMaxScale.setFont(new Font("default", Font.PLAIN, 14));
		lblMaxScale.setBounds(47, 150, 200, 25);
		mainPanel.add(lblMaxScale);

		cmbMaxima = new JComboBox(maxScales);
		cmbMaxima.setBounds(160, 150, 140, 25);
		cmbMaxima.setSelectedIndex(maxScales.length-1);
		mainPanel.add(cmbMaxima);

		JLabel lblText = new JLabel(
				"<html><ul><li>O intervalo de visibilidade compreende o intervalo de valores de escalas no qual "
						+ "os objetos geográficos do tema serão exibidos.</li><li>Escala minima é o limite inferior de "
						+ "desenho do intervalo de visibilidade.</li><li>Escala máxima é limite superior de desenho do "
						+ "intervalo de visibilidade.</li><li>É possível definir o limite inferior (escala mínima) e/ou o "
						+ "limite superior (escala máxima).</li><li>Na prática os valores esperados no campo escala mínima "
						+ "são números absolutos maiores do que os esperados no campo escala máxima, pois se tratam de "
						+ "denominadores.</li></ul></html>");
		lblText.setBounds(-20, 105, 370, 400);
		mainPanel.add(lblText);

		btConfirm = new JButton("Confirmar");
		btConfirm.setBounds(((width - 10) / 2) - 140, height - 60, 100, 25);
		mainPanel.add(btConfirm);

		btCancel = new JButton("Cancelar");
		btCancel.setBounds(((width - 10) / 2) + 40, height - 60, 100, 25);
		mainPanel.add(btCancel);

		this.add(mainPanel);

		DragPanel drag = new DragPanel(this);
		this.addMouseListener(drag);
		this.addMouseMotionListener(drag);

		this.setLayout(null);

		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void resetCombos() {
		setActionPerformedBlocked(true);
		cmbMinima.setSelectedIndex(0);
		cmbMaxima.setSelectedIndex(cmbMaxima.getItemCount()-1);
	}

	public void setActionPerformedBlocked(boolean isActionPerformedBlocked) {
		this.isActionPerformedBlocked = isActionPerformedBlocked;
	}

	public boolean isActionPerformedBlocked() {
		return isActionPerformedBlocked;
	}

	/**
	 * @return the btConfirm
	 */
	public JButton getBtConfirm() {
		return btConfirm;
	}

	/**
	 * @return the btCancel
	 */
	public JButton getBtCancel() {
		return btCancel;
	}

	/**
	 * @return the cmbMinima
	 */
	public JComboBox getCmbMinima() {
		return cmbMinima;
	}

	/**
	 * @return the cmbMaxima
	 */
	public JComboBox getCmbMaxima() {
		return cmbMaxima;
	}

	public static void main(String[] args) {
		new ScaleView().setVisible(true);

	}

	public void setAdapter(ScaleViewAdapter scaleAdapter) {
		cmbMinima.addActionListener(scaleAdapter);
		cmbMaxima.addActionListener(scaleAdapter);
		btConfirm.addActionListener(scaleAdapter);
		btCancel.addActionListener(scaleAdapter);
	}
}
