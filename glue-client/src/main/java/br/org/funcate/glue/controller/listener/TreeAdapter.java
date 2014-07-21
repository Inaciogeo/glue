package br.org.funcate.glue.controller.listener;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.TreePath;

import br.org.funcate.eagles.example.system.EditionTool;
import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.controller.TreeController;
import br.org.funcate.glue.event.FocusedThemeEvent;
import br.org.funcate.glue.event.SelectedThemeEvent;
import br.org.funcate.glue.event.UnselectedThemeEvent;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.LookAndFeelService;
import br.org.funcate.glue.model.thread.PlotterController;
import br.org.funcate.glue.model.tree.CustomNode;
import br.org.funcate.glue.model.tree.TreeService;
import br.org.funcate.glue.view.LocalOptionPane;
import br.org.funcate.glue.view.NodeType;
import br.org.funcate.glue.view.ScaleView;
import br.org.funcate.glue.view.ThemeOptionsView;
import br.org.funcate.glue.view.TreeView;
import br.org.funcate.glue.view.VisualOptionsView;

/** \file TreeAdapter.java
 * This file creates the listener responsible for handling the events related 
 *	to the selection of the nodes 
 */

/**
 * \brief This Class defines the attributes and the functions required to handle
 * the copy, move and rearrange functions of the nodes in a Tree and showing the
 * appropriated popups
 * 
 * @author OLIVEIRA, André G.
 * @author OLIVEIRA, Paulo Renato M.
 */

@SuppressWarnings("unused")
public class TreeAdapter implements MouseListener, MouseMotionListener,
		KeyListener, ActionListener {

	private TreePath path;
	private JTree tree;
	private TreeView treeView;
	private TreeController treeController;
	private CustomNode nodeSource;
	private CustomNode nodeTarget;
	private CustomNode parent;

	public TreeAdapter(TreeView treeView) {
		this.treeView = treeView;
		this.treeController = new TreeController(treeView);
	}
	
	@Override
	public void keyPressed(KeyEvent evt) {

	}

	@Override
	public void keyReleased(KeyEvent evt) {
		if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_C) {
			treeController.copyNode();
		}
		if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_X) {
			treeController.cutNode();
		}
		if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_V) {
			treeController.pasteNode();
		}
		if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
			treeController.deleteNode();
		}
		if (evt.getKeyCode() == KeyEvent.VK_F2) {
			treeController.renameNode();
		}
		if (evt.getKeyCode() == KeyEvent.VK_HOME) {
			treeController.setCurrentView((CustomNode) treeView.getRoot()
					.getFirstChild());
			PlotterController.getInstance().startPlotter();
		}
		if (evt.getKeyCode() == KeyEvent.VK_END) {
			treeController.setCurrentView((CustomNode) treeView.getRoot()
					.getLastChild());
			PlotterController.getInstance().startPlotter();
		}
	}

	@Override
	public void keyTyped(KeyEvent evt) {

	}

	@Override
	public void mouseDragged(MouseEvent evt) {
		int x = evt.getX();
		int y = evt.getY();
		int row = tree.getRowForLocation(x, y);
		path = tree.getPathForRow(row);

		if (path != null) {
			treeView.getTree().setSelectionPath(path);
		}
	}

	@Override
	public void mouseMoved(MouseEvent evt) {
		int x = evt.getX();
		int y = evt.getY();
		int row = tree.getRowForLocation(x, y);
		TreePath path = tree.getPathForRow(row);

		if (path != null) {

			parent = (CustomNode) path.getLastPathComponent();

			switch (parent.getNodeType()) {
			case VIEW:
				if (parent.isSelected()) {
					parent.setToolTipText("Vista selecionada");
				} else {
					parent.setToolTipText("Vista não selecionada");
				}
				break;
			case TOOLBAR:
				Rectangle click = new Rectangle(new Point(x, y));
				int posX = click.x - ((parent.getLevel() - 1) * 23);

				if (LookAndFeelService.isNimbusActivated()) {
					if (posX < 4 || posX > 60) {
						parent.setToolTipText(null);
						return;
					}
					if (posX >= 4 && posX < 20) {
						parent.setToolTipText("Define o visual do tema");
					}
					if (posX >= 24 && posX <= 40) {
						parent.setToolTipText("Consulta por atributo");
					}
					if (posX >= 44 && posX < 60) {
						parent.setToolTipText("Habilita ferramenta de escala");
					}
				} else {
					if (posX < 16 || posX > 60) {
						parent.setToolTipText(null);
						return;
					}
					if (posX >= 16 && posX < 28) {
						parent.setToolTipText("Define o visual do tema");
					}
					if (posX >= 31 && posX <= 44) {
						parent.setToolTipText("Consulta por atributo");
					}
					if (posX >= 46 && posX < 58) {
						parent.setToolTipText("Habilita ferramenta de escala");
					}
				}
				break;
			default:
				if (parent.isSelected()) {
					if (!parent.isScaleVisible()) {
						parent.setToolTipText("Tema não visível");
					} else {
						parent.setToolTipText("Tema selecionado");
					}
				} else {
					parent.setToolTipText("Tema não selecionado");
				}
			}
		}
		treeController.setLastTheme(TreeService.getCurrentTheme());
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		int x = evt.getX();
		int y = evt.getY();
		int row = tree.getRowForLocation(x, y);
		path = tree.getPathForRow(row);
		if (path != null) {
			tree.setSelectionPath(path);
			treeController.setCurrentView((CustomNode) treeView.getTree()
					.getLastSelectedPathComponent());
		}
	}

	@Override
	public void mouseEntered(MouseEvent evt) {
		

	}

	@Override
	public void mouseExited(MouseEvent evt) {

	}

	@Override
	public void mousePressed(MouseEvent evt) {
		int x = evt.getX();
		int y = evt.getY();
		int row = tree.getRowForLocation(x, y);
		Rectangle positionClicked = new Rectangle(new Point(x, y));
		path = tree.getPathForRow(row);

		if (path != null) {

			treeView.getTree().setSelectionPath(path);
			parent = (CustomNode) path.getLastPathComponent();
			treeController.setSelectedNode(parent);
			Mediator mediator = AppSingleton.getInstance().getMediator();
			if (parent.getNodeType() == NodeType.VIEW && parent.isLeaf()) {
				mediator.disableAllTools();
			} else if (!treeController.getNodeView(parent).isPersisted()
					&& !treeController.getNodeView(parent).isApplied()) {
				mediator.disableAllTools();
			} else if (!treeController.getNodeView(parent).getChildSelection(
					treeController.getNodeView(parent))) {
				mediator.disableAllTools();
			} else {
				mediator.enableAllTools();
				mediator.verifyScaleLimits();
			}

			nodeSource = (CustomNode) path.getLastPathComponent();

			if (nodeSource == null)
				return;

			if (evt.isShiftDown() && nodeSource.getNodeType().isTheme()) {
				treeController.copyNode();
				return;
			}

			if (parent.getNodeType() == NodeType.TOOLBAR) {

				treeController.setCurrentTheme((CustomNode) parent.getParent());
				Rectangle click = new Rectangle(new Point(x, y));
				int posX = click.x - ((parent.getLevel() - 1) * 20);

				if (LookAndFeelService.isNimbusActivated()) {
					if (posX < 8 || posX > 65)
						return;
					if (posX >= 11 && posX <= 25) {
						treeController.setCurrentTheme((CustomNode) parent
								.getParent());
						TreePath viewPath = new TreePath(treeController
								.getNodeView(parent).getPath());
						tree.setSelectionPath(viewPath);
						VisualOptionsView.getInstance();
						PlotterController.getInstance().pausePlotter();
					}

					if (posX > 31 && posX <= 43) {

						treeController.getNodeView(parent);
						TreePath viewPath = new TreePath(treeController
								.getNodeView(parent).getPath());
						tree.setSelectionPath(viewPath);

						LocalOptionPane
								.getInstance(
										"Ferramenta temporariamente desabilitada. Desculpe-nos o transtorno.",
										"Atenção");

						// showAttributeSearchScreen();
					}

					if (posX > 50 && posX <= 62) {
						treeController.setCurrentTheme((CustomNode) parent
								.getParent());
						ScaleView.getInstance();
						TreePath viewPath = new TreePath(treeController
								.getNodeView(parent).getPath());
						tree.setSelectionPath(viewPath);
					}
				} else {
					if (posX < 22 || posX > 65)
						return;
					if (posX >= 22 && posX <= 33) {
						treeController.setCurrentTheme((CustomNode) parent
								.getParent());
						TreePath viewPath = new TreePath(treeController
								.getNodeView(parent).getPath());
						tree.setSelectionPath(viewPath);
						VisualOptionsView.getInstance();
						PlotterController.getInstance().pausePlotter();
					}

					if (posX > 38 && posX <= 50) {

						treeController.getNodeView(parent);
						TreePath viewPath = new TreePath(treeController
								.getNodeView(parent).getPath());
						tree.setSelectionPath(viewPath);

						LocalOptionPane
								.getInstance(
										"Ferramenta temporariamente desabilitada. Desculpe-nos o transtorno.",
										"Atenção");

						// showAttributeSearchScreen();
					}

					if (posX > 52 && posX <= 64) {
						treeController.setCurrentTheme((CustomNode) parent
								.getParent());
						ScaleView.getInstance();
						TreePath viewPath = new TreePath(treeController
								.getNodeView(parent).getPath());
						tree.setSelectionPath(viewPath);
					}
				}

			}

			if (parent.getNodeType().isTheme()) {
				treeController.setCurrentTheme(parent);
				int clickPosition = positionClicked.x
						- ((parent.getLevel() - 1) * 20);

				boolean isSelectedTheme = parent.isSelected();
				if (LookAndFeelService.isNimbusActivated()) {
					if (clickPosition > 14 && clickPosition < 28) { // click
																	// sobre o
																	// ícone do
																	// tema na
																	// árvore
																	// caso seja
																	// nimbus
						parent.setSelected(!isSelectedTheme);
						if (!isSelectedTheme) {
							// despachar themeSelectedEvent
							treeController.dispatch(new SelectedThemeEvent(
									this, parent.getTheme()));
						} else {
							treeController.dispatch(new UnselectedThemeEvent(
									this, parent.getName()));
						}
					} else { // click sobre um título de tema na árvore caso
								// seja nimbus
						if (parent.isSelected()) {
							treeController.dispatch(new FocusedThemeEvent(this,
									parent.getName()));
						}
					}
				} else {
					if (clickPosition > 21 && clickPosition < 36) { // click
																	// sobre o
																	// ícone do
																	// tema na
																	// árvore
																	// caso não
																	// seja
																	// nimbus
						parent.setSelected(!isSelectedTheme);
						if (!isSelectedTheme) {
							treeController.dispatch(new SelectedThemeEvent(
									this, parent.getTheme()));
						} else {
							treeController.dispatch(new UnselectedThemeEvent(
									this, parent.getName()));
						}
					} else { // click sobre um título de tema na árvore caso não
								// seja nimbus
						if (parent.isSelected()) {
							treeController.dispatch(new FocusedThemeEvent(this,
									parent.getName()));
						}
					}
				}
			}

			/*
			 * Verifies if the user clicked twice, in that case, nothing is done
			 */

			if (evt.getClickCount() > 1) {
				tree.setSelectionPath(null);
				return;
			}

			if (evt.getButton() == 1) {

				if (parent == null)
					return;

				if (parent != null && parent.getNodeType() != NodeType.VIEW) {

					int X = positionClicked.x - ((parent.getLevel() - 1) * 20);

					tree.setComponentPopupMenu(null);
					treeView.setPopMenu(treeView.createPopupTheme(parent));
					tree.setComponentPopupMenu(treeView.getPopMenu());

					treeController.setCurrentView(treeController
							.getNodeView(parent));

					if (X >= 33 && X <= 49) {
						treeController.setCurrentTheme(parent);
					}
					treeController.setCurrentView(treeController
							.getNodeView(parent));
					treeController.setCurrentView(treeController
							.getNodeView(parent));
				} else {

					/*
					 * Verifies if the user clicked on an selected view, in that
					 * case set's the clicked view as unselected
					 */

					if (parent.getNodeType() == NodeType.VIEW) {

						tree.setComponentPopupMenu(null);
						treeView.setPopMenu(treeView.createPopupView());
						tree.setComponentPopupMenu(treeView.getPopMenu());
						treeController.setCurrentView(treeController
								.getNodeView(parent));

						tree.setSelectionPath(new TreePath(parent.getPath()));
						treeController.setCurrentView(treeController
								.getNodeView(parent));

						tree.repaint();
						PlotterController.getInstance().startPlotter();

					} else {
						treeController.setCurrentView(parent);
						PlotterController.getInstance().startPlotter();
					}
				}

			}

			if (evt.getButton() == 3) {

				if ((parent.getNodeType() != NodeType.TOOLBAR
						&& parent.getNodeType() != NodeType.VIEW && parent
							.getNodeType() != NodeType.ROOT)) {

					tree.setComponentPopupMenu(null);
					treeView.setPopMenu(treeView.createPopupTheme(parent));
					tree.setComponentPopupMenu(treeView.getPopMenu());
					treeController.setCurrentView(treeController
							.getNodeView(parent));

					/*
					 * adjust to prevent moving node when the user clicks with
					 * the left button right after this event
					 */

					nodeSource = null;
					nodeTarget = null;

				} else if (parent.getNodeType() == NodeType.VIEW) {

					/*
					 * adjust to prevent moving node when the user clicks with
					 * the left button right after this event
					 */
					nodeSource = null;
					nodeTarget = null;

					tree.setComponentPopupMenu(null);
					treeView.setPopMenu(treeView.createPopupView());
					tree.setComponentPopupMenu(treeView.getPopMenu());
					treeController.setCurrentView(treeController
							.getNodeView(parent));

				}
			}
		} else { // raiz da vista
			tree.setComponentPopupMenu(null);
			treeView.setPopMenu(treeView.createPopupDefault());
			tree.setComponentPopupMenu(treeView.getPopMenu());
		}
			
			//treeController.dispachLastTheme();
	}

	@Override
	public void mouseReleased(MouseEvent evt) {
		tree.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		if (nodeSource == null)
			return;
		treeController.setNodeSource(nodeSource);

		if (nodeSource.getNodeType() == NodeType.TOOLBAR
				|| nodeSource.getNodeType() == NodeType.ROOT) {
			return;
		}

		Point pt = evt.getPoint();
		TreePath parentPath = tree.getPathForLocation(pt.x, pt.y);
		CustomNode targetView = null;
		CustomNode sourceView = null;

		if (parentPath == null) {
			return;
		}

		nodeTarget = (CustomNode) parentPath.getLastPathComponent();
		treeView.getTree().setSelectionPath(new TreePath(nodeTarget.getPath()));

		if (nodeTarget == nodeSource)
			return;

		if (evt.isShiftDown()) {
			treeController.pasteNode();
			return;
		}

		sourceView = treeController.getNodeView(nodeSource);
		targetView = treeController.getNodeView(nodeTarget);

		int index = ((CustomNode) nodeTarget.getParent()).getIndex(nodeTarget);
		if (nodeTarget.equals(treeController.getNodeView(nodeSource)))
			return;
		// Move a node

		if (evt.getButton() == 1) {
			if (nodeSource.getNodeType() == NodeType.VIEW
					&& nodeTarget.getNodeType() == NodeType.VIEW) {
				treeController.rearrange(index, treeView.getRoot(), nodeSource);
			} else if (targetView.getName().equals(sourceView.getName())) {
				treeController.rearrange(index, targetView, nodeSource);
				return;
			} else {
				treeController.moveNode(targetView, sourceView);
				return;
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource().equals(treeView.getRenameTheme())) {
			treeController.renameNode();
		}
		if (evt.getSource().equals(treeView.getDeleteTheme())) {
			treeController.deleteNode();
		}
		if (evt.getSource().equals(treeView.getRenameTheme())) {
			treeController.setSelectedNode((CustomNode) tree
					.getLastSelectedPathComponent());
			treeController.getSelectedNode().setHiddenExpanded(true);

			if (treeController.getSelectedNode().getNodeType() != NodeType.NETWORK_THEME)
				return;
			tree.expandPath(new TreePath(treeController.getSelectedNode()
					.getPath()));
		}
		// export theme
		if (evt.getSource().equals(treeView.getExportTheme())) {
			LookAndFeelService.changeLookAndFeelForNimbus();

			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Exportar...");
			FileFilter type1 = new FileNameExtensionFilter("SHP", "shp");
			FileFilter type2 = new FileNameExtensionFilter("SHX", "shx");
			FileFilter type3 = new FileNameExtensionFilter("XML", "xml");
			chooser.addChoosableFileFilter(type1);
			chooser.addChoosableFileFilter(type2);
			chooser.addChoosableFileFilter(type3);
			chooser.showSaveDialog(null);
			File file = chooser.getSelectedFile();

			if (file == null)
				return;

			try {

				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);

				CustomNode node = (CustomNode) tree
						.getLastSelectedPathComponent();

				bw.write(node.getName());
				bw.newLine();

				if (node.isSelected())
					bw.write("true");
				else
					bw.write("false");
				bw.newLine();
				if (node.isScaleVisible())
					bw.write("true");
				else
					bw.write("false");
				bw.newLine();
				bw.write(node.getNodeType().toString());
				bw.newLine();

				for (@SuppressWarnings("unchecked")
				Enumeration<CustomNode> e = ((CustomNode) tree
						.getLastSelectedPathComponent()).children(); e
						.hasMoreElements();) {

					node = e.nextElement();

					if (node.getNodeType() == NodeType.TOOLBAR) {
						if (e.hasMoreElements()) {
							node = e.nextElement();
						}
					} else {

						bw.write(node.getName());
						bw.newLine();

						if (node.isSelected())
							bw.write("true");
						else
							bw.write("false");
						bw.newLine();
						if (node.isScaleVisible())
							bw.write("true");
						else
							bw.write("false");
						bw.newLine();
						bw.write(node.getNodeType().toString());
						bw.newLine();
					}

					bw.flush();
					bw.close();
					file.createNewFile();
				}
			} catch (IOException exc) {
				exc.printStackTrace();
			}

			LookAndFeelService.changeLookAndFeelForDefault();
		}
		// collapse
		if (evt.getSource().equals(treeView.getCollapse())) {
			treeController.setSelectedNode((CustomNode) tree
					.getLastSelectedPathComponent());
			treeController.getSelectedNode().setHiddenExpanded(false);

			if (treeController.getSelectedNode().getNodeType() != NodeType.NETWORK_THEME)
				return;

			tree.collapsePath(new TreePath(treeController.getSelectedNode()
					.getPath()));
		}

		// create theme
		if (evt.getSource().equals(treeView.getCreateChildTheme())) {
			CustomNode parent = (CustomNode) tree
					.getLastSelectedPathComponent();

			/*
			 * If the node selected is a toolbar node receives the toolbar's
			 * selectedNode
			 */

			if (parent.getNodeType() == NodeType.TOOLBAR) {
				parent = (CustomNode) parent.getParent();

			}
			if (!parent.isAllowsChildren()) {
				LocalOptionPane.getInstance("Este tema não pode ter subtemas ",
						"Glue - Criar Tema");
				return;
			}

			ThemeOptionsView.getInstance(parent);
		}

		// properties theme
		if (evt.getSource().equals(treeView.getPropertiesTheme())) {
			System.out.println("Busca por propriedades.");
		}

		// srchAtribTheme
		if (evt.getSource().equals(treeView.getSrchAtribTheme())) {
			/*
			 * LookAndFeelService.changeLookAndFeelForNimbus();
			 * Metodos.resetReferences(); DataBaseConnection db =
			 * Metodos.openConnection(); JFrame frame = new
			 * JFrame("Consulta por Atributo"); Metodos.setFrmAttribute(frame);
			 * DynamicPanel pnlConsultaAtributo = new DynamicPanel(db, 139);
			 * Metodos.setThemeName(treeController.getCurrentTheme().getName());
			 * Metodos.setThemeId(treeController.getCurrentTheme().getId());
			 * Metodos.setViewName(treeController.getCurrentView().getName());
			 * try { Metodos.enabledComponents(pnlConsultaAtributo, db); } catch
			 * (SQLException e) { } frame.setUndecorated(true); DragPanel drag =
			 * new DragPanel(frame); frame.addMouseListener(drag);
			 * frame.addMouseMotionListener(drag);
			 * frame.setBounds(pnlConsultaAtributo.getBounds());
			 * frame.add(pnlConsultaAtributo);
			 * frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			 * frame.setLocationRelativeTo(null); frame.setResizable(false);
			 * frame.setVisible(true);/* } // create theme if
			 * (evt.getSource().equals(treeView.getCreateTheme())) { if
			 * (treeController.getSelectedNode() == null) { return; }
			 * 
			 * /* If the node selected is a toolbar node receives the toolbar's
			 * selectedNode
			 */

			if (treeController.getSelectedNode().getNodeType() == NodeType.TOOLBAR) {
				treeController.setSelectedNode((CustomNode) treeController
						.getSelectedNode().getParent());

			}

			if (!treeController.getSelectedNode().isAllowsChildren()) {
				LocalOptionPane.getInstance("Este tema não pode ter subtemas ",
						"Glue - Criar Tema");
				return;
			}

			ThemeOptionsView.getInstance(treeController.getSelectedNode());
		}
		if (evt.getSource().equals(treeView.getRenameView())) {
			treeController.renameNode();
		}

		if (evt.getSource().equals(treeView.getDeleteView())) {
			treeController.deleteNode();
		}

		if (evt.getSource().equals(treeView.getImportView())) {
			LookAndFeelService.changeLookAndFeelForNimbus();

			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Importar...");
			FileFilter type1 = new FileNameExtensionFilter("SHP", "shp");
			FileFilter type2 = new FileNameExtensionFilter("SHX", "shx");
			FileFilter type3 = new FileNameExtensionFilter("XML", "xml");
			chooser.addChoosableFileFilter(type1);
			chooser.addChoosableFileFilter(type2);
			chooser.addChoosableFileFilter(type3);
			chooser.showOpenDialog(null);
			chooser.getSelectedFile();

			LookAndFeelService.changeLookAndFeelForDefault();
		}

		if (evt.getSource().equals(treeView.getPersist())) {
			if (treeController.getSelectedNode() == null)
				return;

			treeController.createViewUpdator(false, false, false);
		}

		if (evt.getSource().equals(treeView.getRevert())) {
			if (treeController.getSelectedNode() == null)
				return;

			if (treeController.getSelectedNode().getId() > 0l) {

				if (treeController.getSelectedNode().isApplied()) {
					treeController.createViewUpdator(true, true, false);

				} else {
					treeController.createViewUpdator(true, false, false);
				}

			} else if (treeController.getSelectedNode().getId() < 0l) {
				treeController.createViewUpdator(true, true, false);
			}
		}

		if (evt.getSource().equals(treeView.getProperties())) {
			if (treeController.getSelectedNode() == null) {
				return;
			}
			String message = new String("Nome: "
					+ treeController.getSelectedNode().getName()
					+ "\n"
					+ "Projeção: "
					+ treeController.getSelectedNode().getView()
							.getProjection().getName());

			JOptionPane.showMessageDialog(null, message,
					"Propriedades da vista", 1);
		}

		if (evt.getSource().equals(treeView.getCreateTheme())){
			LookAndFeelService.changeLookAndFeelForNimbus();
			
			CustomNode parent = treeController.getLastSelectedPathComponent();

			if (parent == null) {
				parent = treeController.getCurrentView();
				if (parent == null)
					return;
			}

			if (parent.getNodeType() == NodeType.TOOLBAR) {
				parent = (CustomNode) parent.getParent();

			}
			ThemeOptionsView.getInstance(parent);
			
		}
		
		if (evt.getSource().equals(treeView.getCreateView())) {
			LookAndFeelService.changeLookAndFeelForNimbus();
			String name = JOptionPane
					.showInputDialog("Digite o nome da nova vista: ");
			LookAndFeelService.changeLookAndFeelForDefault();
			CustomNode root = treeController.getRoot();
			if (name == null || name.trim().equals(""))
				return;

			CustomNode compare = new CustomNode(0l, name, true, treeController
					.getSelectedNode().getNodeType());
			if (treeController.checkAllNodes(root, compare)) {
				LocalOptionPane.getInstance("Este nome já existe",
						"Glue - Criar Vista");
			} else {
				CustomNode node = new CustomNode(0l, name, false, NodeType.VIEW);
				root.add(node);
				node.setApplied(false);
				node.setPersisted(false);
			}

			treeController.getDefaultTreeModel().reload(root);
		}

	}

	public JTree getTree() {
		return tree;
	}

	public void setTree(JTree tree) {
		this.tree = tree;
	}
	


}
