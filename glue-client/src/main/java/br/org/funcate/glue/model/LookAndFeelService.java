package br.org.funcate.glue.model;

import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.org.funcate.glue.view.Toolbar;

/**
 * 
 * @author Moreira, Vinicius Fernandes
 * @author Juliana Hohara de Souza Coelho
 * 
 */
public abstract class LookAndFeelService {

	private static boolean nimbusActivated = false;

	/**
	 * @brief Method to set the Look And Feel of the GUI to Nimbus.
	 */
	public static void initializeNimbusLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Toolbar.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(Toolbar.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(Toolbar.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(Toolbar.class.getName()).log(Level.SEVERE, null, ex);
		}

		nimbusActivated = true;
	}

	/**
	 * @return the nimbusActivated
	 */
	public static boolean isNimbusActivated() {
		return nimbusActivated;
	}

	/**
	 * @brief Method to update the look and feel of a target component. If the
	 *        component have other components, the other components will be
	 *        updated too.
	 * 
	 * @param comp
	 *            The target component that must update it's look and feel.
	 */
	public static void updateAllGUIComponents(Component comp) {
		/*
		 * if (comp instanceof JComponent) { JComponent jcomp = ((JComponent)
		 * comp);
		 * 
		 * jcomp.updateUI();
		 * 
		 * for (Component c : jcomp.getComponents()) {
		 * updateAllGUIComponents(c); } }
		 */
	}

	/**
	 * @brief Method to get the single instance of the Look And Feel Default.
	 * @return The single instance of the Look And Feel Default
	 */
	public static LookAndFeel getLookAndFeelDefault() {
		/*
		 * if (lookAndFeel == null) { lookAndFeel = UIManager.getLookAndFeel();
		 * } return lookAndFeel;
		 */
		return null;
	}

	/**
	 * @brief Method to get the single instance of the Look And Feel Nimbus.
	 * @return The single instance of the Look And Feel Nimbus
	 */
	public static LookAndFeel getLookAndFeelNimbus() {
		/*
		 * if (lookAndFeelNimbus == null) { lookAndFeelNimbus =
		 * UIManager.getLookAndFeel(); } return lookAndFeelNimbus;
		 */
		return null;
	}

	/**
	 * @brief Method to change the Look And Feel for Nimbus.
	 * @return The single instance of the Look And Feel Nimbus
	 */
	public static void changeLookAndFeelForNimbus() {
		/*
		 * try { UIManager.setLookAndFeel(getLookAndFeelNimbus()); } catch
		 * (UnsupportedLookAndFeelException e) { e.printStackTrace(); }
		 */
	}

	/**
	 * @brief Method to change the Look And Feel for Default.
	 * @return The single instance of the Look And Feel Default
	 */
	public static void changeLookAndFeelForDefault() {
		/*
		 * try { UIManager.setLookAndFeel(getLookAndFeelDefault()); } catch
		 * (UnsupportedLookAndFeelException e) { e.printStackTrace(); }
		 */
	}
}
