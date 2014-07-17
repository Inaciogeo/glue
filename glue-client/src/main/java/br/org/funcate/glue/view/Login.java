package br.org.funcate.glue.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Login extends JDesktopPane {

	private static final long serialVersionUID = 1L;
	private JEditorPane jEditorPane = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel11 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel12 = null;
	private JLabel jLabel13 = null;
	private JLabel jLabel131 = null;
	private JTextField jTextField = null;
	private JTextField jTextField1 = null;
	private JTextField jTextField11 = null;
	private JLabel jLabel111 = null;
	private JLabel jLabel1111 = null;
	@SuppressWarnings("rawtypes")
	private JComboBox jComboBox = null;
	private JPanel jPanel = null;
	private JTextField jTextField111 = null;
	private JLabel jLabel1112 = null;
	private JTextField jTextField1111 = null;
	private JTextField jTextField2 = null;
	private JTextField jTextField112 = null;
	@SuppressWarnings("rawtypes")
	private JComboBox jComboBox1 = null;
	private JLabel jLabel1113 = null;
	private JLabel jLabel1311 = null;
	@SuppressWarnings("rawtypes")
	private JComboBox jComboBox11 = null;
	private JLabel jLabel1312 = null;
	@SuppressWarnings("rawtypes")
	private JComboBox jComboBox12 = null;
	private JLabel jLabel11131 = null;
	@SuppressWarnings("rawtypes")
	private JComboBox jComboBox121 = null;
	private JLabel jLabel111311 = null;
	private JLabel jLabel13121 = null;
	private JTextField jTextField21 = null;
	private JTextField jTextField1121 = null;
	private JPanel jPanel1 = null;
	private JLabel jLabel21 = null;
	private JLabel jLabel131211 = null;
	private JTextField jTextField211 = null;
	private JLabel jLabel1113111 = null;
	private JTextField jTextField212 = null;
	private JPanel jPanel11 = null;
	private JLabel jLabel211 = null;
	private JLabel jLabel1312111 = null;
	private JTextField jTextField2111 = null;
	private JLabel jLabel1312112 = null;
	private JLabel jLabel1312113 = null;
	private JCheckBox jCheckBox = null;
	private JPasswordField jPasswordField = null;
	private JPasswordField jPasswordField1 = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;

	/**
	 * This is the default constructor
	 */
	public Login() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		jLabel1312113 = new JLabel();
		jLabel1312113.setBounds(new Rectangle(360, 259, 55, 17));
		jLabel1312113.setText("Confirma:");
		jLabel1312113.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel1312112 = new JLabel();
		jLabel1312112.setBounds(new Rectangle(182, 259, 39, 17));
		jLabel1312112.setText("Senha:");
		jLabel1312112.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel1312111 = new JLabel();
		jLabel1312111.setBounds(new Rectangle(7, 259, 35, 17));
		jLabel1312111.setText("Login:");
		jLabel1312111.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel1113111 = new JLabel();
		jLabel1113111.setBounds(new Rectangle(342, 203, 22, 17));
		jLabel1113111.setText("Cel:");
		jLabel1113111.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel131211 = new JLabel();
		jLabel131211.setBounds(new Rectangle(55, 203, 26, 17));
		jLabel131211.setText("Fixo:");
		jLabel131211.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel13121 = new JLabel();
		jLabel13121.setBounds(new Rectangle(31, 148, 32, 17));
		jLabel13121.setText("Email:");
		jLabel13121.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel111311 = new JLabel();
		jLabel111311.setBounds(new Rectangle(415, 129, 70, 17));
		jLabel111311.setText("Tipo Vínculo:");
		jLabel111311.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel11131 = new JLabel();
		jLabel11131.setBounds(new Rectangle(207, 129, 68, 17));
		jLabel11131.setText("Tipo Vínculo:");
		jLabel11131.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel1312 = new JLabel();
		jLabel1312.setBounds(new Rectangle(21, 129, 42, 17));
		jLabel1312.setText("Vínculo:");
		jLabel1312.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel1311 = new JLabel();
		jLabel1311.setBounds(new Rectangle(415, 110, 64, 17));
		jLabel1311.setText("Supervisor:");
		jLabel1311.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel1113 = new JLabel();
		jLabel1113.setBounds(new Rectangle(201, 110, 74, 17));
		jLabel1113.setText("Outra Função:");
		jLabel1113.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel1112 = new JLabel();
		jLabel1112.setBounds(new Rectangle(369, 53, 109, 17));
		jLabel1112.setText("Data de Nascimento:");
		jLabel1112.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel1111 = new JLabel();
		jLabel1111.setBounds(new Rectangle(206, 72, 24, 17));
		jLabel1111.setText("CPF:");
		jLabel1111.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel111 = new JLabel();
		jLabel111.setBounds(new Rectangle(201, 53, 29, 17));
		jLabel111.setText("Sexo:");
		jLabel111.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel2 = new JLabel();
		jLabel2.setBackground(new Color(226, 230, 178));
		jLabel2.setEnabled(false);
		jLabel2.setText("CADASTRO");
		jLabel11 = new JLabel();
		jLabel11.setBounds(new Rectangle(28, 34, 35, 17));
		jLabel11.setText("Nome:");
		jLabel11.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel1 = new JLabel();
		jLabel1.setBounds(new Rectangle(10, 53, 53, 17));
		jLabel1.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel1.setText("Matrícula:");
		jLabel12 = new JLabel();
		jLabel12.setBounds(new Rectangle(44, 72, 19, 17));
		jLabel12.setText("RG:");
		jLabel12.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel13 = new JLabel();
		jLabel13.setBounds(new Rectangle(11, 91, 52, 17));
		jLabel13.setText("Endereço:");
		jLabel13.setFont(new Font("Dialog", Font.BOLD, 10));
		jLabel131 = new JLabel();
		jLabel131.setBounds(new Rectangle(22, 110, 41, 17));
		jLabel131.setText("Função:");
		jLabel131.setFont(new Font("Dialog", Font.BOLD, 10));
		this.setSize(644, 488);
		this.add(jLabel1, null);
		this.add(jLabel11, null);
		this.add(jLabel12, null);
		this.add(jLabel13, null);
		this.add(jLabel131, null);
		this.add(getJTextField(), null);
		this.add(getJTextField1(), null);
		this.add(getJTextField11(), null);
		this.add(jLabel111, null);
		this.add(jLabel1111, null);
		this.add(getJComboBox(), null);
		this.add(getJPanel(), null);
		this.add(getJTextField111(), null);
		this.add(jLabel1112, null);
		this.add(getJTextField1111(), null);
		this.add(getJTextField2(), null);
		this.add(getJTextField112(), null);
		this.add(getJComboBox1(), null);
		this.add(jLabel1113, null);
		this.add(jLabel1311, null);
		this.add(getJComboBox11(), null);
		this.add(jLabel1312, null);
		this.add(getJComboBox12(), null);
		this.add(jLabel11131, null);
		this.add(getJComboBox121(), null);
		this.add(jLabel111311, null);
		this.add(jLabel13121, null);
		this.add(getJTextField21(), null);
		this.add(getJTextField1121(), null);
		this.add(getJPanel1(), null);
		this.add(jLabel131211, null);
		this.add(getJTextField211(), null);
		this.add(jLabel1113111, null);
		this.add(getJTextField212(), null);
		this.add(getJPanel11(), null);
		this.add(jLabel1312111, null);
		this.add(getJTextField2111(), null);
		this.add(jLabel1312112, null);
		this.add(jLabel1312113, null);
		this.add(getJCheckBox(), null);
		this.add(getJPasswordField(), null);
		this.add(getJPasswordField1(), null);
		this.add(getJScrollPane(), null);
		this.add(getJEditorPane(), null);
		this.add(getJButton(), null);
		this.add(getJButton1(), null);
		this.add(getJButton2(), null);
		this.add(getJButton3(), null);
	}

	/**
	 * This method initializes jEditorPane
	 * 
	 * @return javax.swing.JEditorPane
	 */
	private JEditorPane getJEditorPane() {
		if (jEditorPane == null) {
			jEditorPane = new JEditorPane();
			jEditorPane.setBounds(new Rectangle(5, 5, 634, 447));
			jEditorPane.setEnabled(false);
		}
		return jEditorPane;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(65, 34, 573, 18));
			jTextField.setName("txtNome");
			jTextField.setFont(new Font("Dialog", Font.PLAIN, 12));
		}
		return jTextField;
	}

	/**
	 * This method initializes jTextField1
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setBounds(new Rectangle(65, 53, 125, 18));
			jTextField1.setName("txtMatricula");
		}
		return jTextField1;
	}

	/**
	 * This method initializes jTextField11
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField11() {
		if (jTextField11 == null) {
			jTextField11 = new JTextField();
			jTextField11.setBounds(new Rectangle(65, 72, 125, 18));
			jTextField11.setName("txtRG");
		}
		return jTextField11;
	}

	/**
	 * This method initializes jComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	@SuppressWarnings("rawtypes")
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.setBounds(new Rectangle(232, 53, 125, 17));
			jComboBox.setName("cboSexo");
		}
		return jComboBox;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridwidth = 1;
			gridBagConstraints.gridy = 0;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.setBounds(new Rectangle(7, 7, 630, 22));
			jPanel.setBackground(new Color(226, 233, 180));
			jPanel.setEnabled(false);
			jPanel.add(jLabel2, gridBagConstraints);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextField111
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField111() {
		if (jTextField111 == null) {
			jTextField111 = new JTextField();
			jTextField111.setBounds(new Rectangle(232, 72, 173, 18));
			jTextField111.setName("txtCPF");
		}
		return jTextField111;
	}

	/**
	 * This method initializes jTextField1111
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField1111() {
		if (jTextField1111 == null) {
			jTextField1111 = new JTextField();
			jTextField1111.setBounds(new Rectangle(480, 53, 158, 18));
			jTextField1111.setName("dtaNasc");
		}
		return jTextField1111;
	}

	/**
	 * This method initializes jTextField2
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setBounds(new Rectangle(65, 91, 573, 18));
			jTextField2.setName("txtEndereco");
			jTextField2.setFont(new Font("Dialog", Font.PLAIN, 12));
		}
		return jTextField2;
	}

	/**
	 * This method initializes jTextField112
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField112() {
		if (jTextField112 == null) {
			jTextField112 = new JTextField();
			jTextField112.setBounds(new Rectangle(277, 110, 128, 18));
			jTextField112.setName("txtOutraFuncao");
		}
		return jTextField112;
	}

	/**
	 * This method initializes jComboBox1
	 * 
	 * @return javax.swing.JComboBox
	 */
	@SuppressWarnings("rawtypes")
	private JComboBox getJComboBox1() {
		if (jComboBox1 == null) {
			jComboBox1 = new JComboBox();
			jComboBox1.setBounds(new Rectangle(65, 110, 124, 17));
			jComboBox1.setName("cboFuncao");
		}
		return jComboBox1;
	}

	/**
	 * This method initializes jComboBox11
	 * 
	 * @return javax.swing.JComboBox
	 */
	@SuppressWarnings("rawtypes")
	private JComboBox getJComboBox11() {
		if (jComboBox11 == null) {
			jComboBox11 = new JComboBox();
			jComboBox11.setBounds(new Rectangle(481, 110, 156, 17));
			jComboBox11.setName("cboSupervisor");
		}
		return jComboBox11;
	}

	/**
	 * This method initializes jComboBox12
	 * 
	 * @return javax.swing.JComboBox
	 */
	@SuppressWarnings("rawtypes")
	private JComboBox getJComboBox12() {
		if (jComboBox12 == null) {
			jComboBox12 = new JComboBox();
			jComboBox12.setBounds(new Rectangle(65, 129, 124, 17));
			jComboBox12.setName("cboVinculo");
		}
		return jComboBox12;
	}

	/**
	 * This method initializes jComboBox121
	 * 
	 * @return javax.swing.JComboBox
	 */
	@SuppressWarnings("rawtypes")
	private JComboBox getJComboBox121() {
		if (jComboBox121 == null) {
			jComboBox121 = new JComboBox();
			jComboBox121.setBounds(new Rectangle(277, 129, 127, 17));
			jComboBox121.setName("cboTipoVinculo");
		}
		return jComboBox121;
	}

	/**
	 * This method initializes jTextField21
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField21() {
		if (jTextField21 == null) {
			jTextField21 = new JTextField();
			jTextField21.setBounds(new Rectangle(65, 148, 573, 18));
			jTextField21.setName("txtEmail");
			jTextField21.setFont(new Font("Dialog", Font.PLAIN, 12));
		}
		return jTextField21;
	}

	/**
	 * This method initializes jTextField1121
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField1121() {
		if (jTextField1121 == null) {
			jTextField1121 = new JTextField();
			jTextField1121.setBounds(new Rectangle(487, 129, 151, 18));
			jTextField1121.setName("txtTipoVinculo");
		}
		return jTextField1121;
	}

	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridwidth = 1;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.gridx = 0;
			jLabel21 = new JLabel();
			jLabel21.setBackground(new Color(226, 230, 178));
			jLabel21.setText("TELEFONES");
			jLabel21.setEnabled(false);
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.setBounds(new Rectangle(7, 175, 630, 22));
			jPanel1.setEnabled(false);
			jPanel1.setBackground(new Color(226, 233, 180));
			jPanel1.add(jLabel21, gridBagConstraints1);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jTextField211
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField211() {
		if (jTextField211 == null) {
			jTextField211 = new JTextField();
			jTextField211.setBounds(new Rectangle(83, 203, 216, 18));
			jTextField211.setName("txtFixo");
			jTextField211.setFont(new Font("Dialog", Font.PLAIN, 12));
		}
		return jTextField211;
	}

	/**
	 * This method initializes jTextField212
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField212() {
		if (jTextField212 == null) {
			jTextField212 = new JTextField();
			jTextField212.setBounds(new Rectangle(365, 203, 216, 18));
			jTextField212.setName("txtCel");
			jTextField212.setFont(new Font("Dialog", Font.PLAIN, 12));
		}
		return jTextField212;
	}

	/**
	 * This method initializes jPanel11
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel11() {
		if (jPanel11 == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridwidth = 1;
			gridBagConstraints11.gridy = 0;
			gridBagConstraints11.gridx = 0;
			jLabel211 = new JLabel();
			jLabel211.setBackground(new Color(226, 230, 178));
			jLabel211.setText("ACESSO");
			jLabel211.setEnabled(false);
			jPanel11 = new JPanel();
			jPanel11.setLayout(new GridBagLayout());
			jPanel11.setBounds(new Rectangle(7, 231, 630, 22));
			jPanel11.setEnabled(false);
			jPanel11.setBackground(new Color(226, 233, 180));
			jPanel11.add(jLabel211, gridBagConstraints11);
		}
		return jPanel11;
	}

	/**
	 * This method initializes jTextField2111
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField2111() {
		if (jTextField2111 == null) {
			jTextField2111 = new JTextField();
			jTextField2111.setBounds(new Rectangle(44, 259, 120, 18));
			jTextField2111.setName("pwdLogin");
			jTextField2111.setFont(new Font("Dialog", Font.PLAIN, 12));
		}
		return jTextField2111;
	}

	/**
	 * This method initializes jCheckBox
	 * 
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox() {
		if (jCheckBox == null) {
			jCheckBox = new JCheckBox();
			jCheckBox.setBounds(new Rectangle(553, 259, 83, 18));
			jCheckBox.setName("chkBloqueio");
			jCheckBox.setText("Bloqueio");
		}
		return jCheckBox;
	}

	/**
	 * This method initializes jPasswordField
	 * 
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			jPasswordField = new JPasswordField();
			jPasswordField.setBounds(new Rectangle(223, 259, 122, 18));
			jPasswordField.setName("pwdSenha");
		}
		return jPasswordField;
	}

	/**
	 * This method initializes jPasswordField1
	 * 
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getJPasswordField1() {
		if (jPasswordField1 == null) {
			jPasswordField1 = new JPasswordField();
			jPasswordField1.setBounds(new Rectangle(417, 259, 122, 18));
			jPasswordField1.setName("pwdConfirma");
		}
		return jPasswordField1;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(8, 298, 628, 153));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
			jTable.setName("grdUsuario");
		}
		return jTable;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(37, 456, 120, 25));
			jButton.setName("btnIncluir");
			jButton.setText("Incluir");
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(187, 456, 120, 25));
			jButton1.setText("Atualizar");
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new Rectangle(340, 456, 120, 25));
			jButton2.setName("btnExcluir");
			jButton2.setText("Excluir");
		}
		return jButton2;
	}

	/**
	 * This method initializes jButton3
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setBounds(new Rectangle(490, 456, 120, 25));
			jButton3.setName("btnPesquisar");
			jButton3.setText("Pesquisar");
		}
		return jButton3;
	}

} // @jve:decl-index=0:visual-constraint="32,-3"