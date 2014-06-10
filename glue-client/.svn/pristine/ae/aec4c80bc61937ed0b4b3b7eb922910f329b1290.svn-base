package br.org.funcate.glue.view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SessaoUsuarioPanel extends JPanel {

	public SessaoUsuarioPanel() {
		this.setBackground(Color.white);
		this.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuário: ");
		lblUsuario.setBounds(10, 5, 50, 20);

		JLabel lblPerfil = new JLabel("Perfil: ");
		lblPerfil.setBounds(125, 5, 40, 20);

		JLabel lblSessao = new JLabel("Sessão: ");
		lblSessao.setBounds(225, 5, 50, 20);

		JLabel lblUsuarioRes = new JLabel("<html><font color=#828282><i>FATEC</i></font></html>");
		lblUsuarioRes.setBounds(70, 5, 50, 20);

		JLabel lblPerfilRes = new JLabel("<html><font color=#828282><i>Público</i></font></html>");
		lblPerfilRes.setBounds(170, 5, 50, 20);

		MyTimer tmrSessaoRes = new MyTimer();
		tmrSessaoRes.setBounds(280, 5, 50, 20);

		this.add(lblUsuario);
		this.add(lblPerfil);
		this.add(lblSessao);
		this.add(lblUsuarioRes);
		this.add(lblPerfilRes);
		this.add(tmrSessaoRes);
	}
}
