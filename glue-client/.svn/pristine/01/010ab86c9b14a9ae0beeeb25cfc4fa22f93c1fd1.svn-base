package br.org.funcate.glue.view;

/** \file NodeType.java
 * This file contains the types of the nodes from the Tree
 */

/**
 * \class NodeType In this class are defined the possible types of a node.
 * \brief In this class are defined the possible types of a node. \author André
 * Gomes de Oliveira \author Paulo Renato Morais Oliveira
 */

public enum NodeType {

	/** < enum value View. > */

	VIEW,

	/** < enum value DEFAULT. > */

	DEFAULT,

	/** < enum value TREE_THEME. > */

	TREE_THEME,

	/** < enum value HIDDEN_TREE_THEME. > */

	NETWORK_THEME,

	/** < enum value EXTERN_THEME. > */

	EXTERN_THEME,

	/** < enum value FILE_THEME. > */

	FILE_THEME,

	/** < enum value WMS_THEME. > */

	WMS_THEME,

	/** < enum value WFS_THEME. > */

	WFS_THEME,

	/** < enum value GOOGLE. > */

	GOOGLE,

	/** < enum value TOOLBAR. > */

	TOOLBAR,

	/** < enum value ROOT. > */

	ROOT;

	/**
	 * @name Access
	 * 
	 *       Functions that return the state of the attributes of the NodeType
	 */

	// @{

	/**
	 * \brief Function that verifies the type of the node and returns a string
	 * with a matching name
	 * 
	 * @return A String that indicates the node type
	 */

	public String toString() {
		switch (this) {

		case DEFAULT:

			/** < enum value DEFAULT returns the string "Default". > */

			return "Tema Padrão";

		case ROOT:

			/** < enum value ROOT returns the string "Root". > */

			return "Raiz";

		case VIEW:

			/** < enum value VIEW returns the string "View". > */

			return "Vista";

		case TREE_THEME:

			/** < enum value TREE_THEME returns the string "Tree_Theme". > */

			return "Tema Árvore";

		case NETWORK_THEME:

			/**
			 * < enum value HIDDEN_TREE_THEME returns the string "Tree_Theme". >
			 */

			return "Tema Árvore";

		case EXTERN_THEME:

			/** < enum value EXTERN_THEME returns the string "Extern_Theme". > */

			return "Tema Externo";

		case FILE_THEME:

			/** < enum value FILE_THEME returns the string "File_Theme". > */

			return "Tema Arquivo";

		case WMS_THEME:

			/** < enum value WMS_THEME returns the string "WMS_Theme". > */

			return "Tema WMS";

		case WFS_THEME:

			/** < enum value WFS_THEME returns the string "WFS_Theme". > */

			return "Tema WFS";

		case GOOGLE:

			/** <enum value GOOGLE returns the string "Google".> */

			return "Tema Google";

		case TOOLBAR:

			/** < enum value TOOLBAR returns the string "Toolbar". > */

			return "Toolbar";

		default:

			/** < undefined enum value returns the string "Invalid Choice". > */

			return "Invalid Choice.";

		}
	}

	public boolean isTheme() {
		switch (this) {

		case DEFAULT:
			return true;

		case ROOT:
			return false;

		case VIEW:
			return false;

		case TREE_THEME:
			return true;

		case NETWORK_THEME:
			return true;

		case EXTERN_THEME:
			return true;

		case FILE_THEME:
			return true;

		case WMS_THEME:
			return true;

		case WFS_THEME:
			return true;

		case GOOGLE:
			return true;

		case TOOLBAR:
			return false;

		default:
			return false;

		}
	}
	
	// @}

}
