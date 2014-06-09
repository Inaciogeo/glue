package br.org.funcate.glue.view;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * \brief Class to deal with the layers of the table component of Java Swing API
 * as if it were a single. With this class you can work with a component table
 * as if he were single, and can still access their components separately.
 * 
 * @author MOREIRA, Vinicius F.
 * @version 1.0.0
 */
public class TableManager {
	/** < Attribute type TableModel > */
	private TableModel _model;
	/** < Attribute type JTable > */
	private JTable _controller;
	/** < Attribute type JScrollPane > */
	private JScrollPane _view;

	/**
	 * \brief Constructor that receives a new table model and joining with the
	 * other components.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param newModel
	 *            A new data model.
	 */
	public TableManager(TableModel newModel) {
		this._model = newModel;
		this._controller = new JTable(_model);
		this._view = new JScrollPane(_controller);
	}

	/**
	 * \brief Constructor that receives a new table model and joining with the
	 * other components and define which columns are editable.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param newModel
	 *            A new data model
	 * @param columnEditable
	 *            An array of booleans where each element represents which
	 *            columns are editable. The indexes of this array are related to
	 *            the indexes of the columns.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public TableManager(TableModel newModel, final boolean[] columnEditable) {
		DefaultTableModel aux = (DefaultTableModel) newModel;

		Vector data = aux.getDataVector();
		Vector columns = new Vector();

		for (int i = 0; i < aux.getColumnCount(); i++) {
			columns.add(aux.getColumnName(i));
		}

		this._model = new DefaultTableModel(data, columns) {
			boolean[] canEdit = columnEditable;

			public boolean isCellEditable(int row, int column) {
				return canEdit[column];
			}
		};

		this._controller = new JTable(_model);
		this._view = new JScrollPane(_controller);
	}

	/**
	 * \brief Constructor that instantiates a new data model and connects with
	 * the other components through an array containing names of your columns,
	 * and another containing the contents of the lines.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param data
	 *            An array containing all lines. Each row of the array
	 *            represents a table row.
	 * @param columns
	 *            An array containing the names of the columns.
	 */
	public TableManager(String[][] data, String[] columns) {
		this._model = new DefaultTableModel(data, columns);
		this._controller = new JTable(_model);
		this._view = new JScrollPane(_controller);
	}

	/**
	 * \brief Constructor that instantiates a new data model and connects with
	 * the other components through an array containing names of your columns,
	 * and another containing the contents of the lines.It defines which columns
	 * are editable.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param data
	 *            An array containing all lines. Each row of the array
	 *            represents a table row.
	 * @param columns
	 *            An array containing the names of the columns.
	 * @param columnEditable
	 *            An array of booleans where each element represents which
	 *            columns are editable. The indexes of this array are related to
	 *            the indexes of the columns.
	 */
	@SuppressWarnings("serial")
	public TableManager(String[][] data, String[] columns, final boolean[] columnEditable) {
		this._model = new DefaultTableModel(data, columns) {
			boolean[] canEdit = columnEditable;

			public boolean isCellEditable(int row, int column) {
				return canEdit[column];
			}
		};
		this._controller = new JTable(_model);
		this._view = new JScrollPane(_controller);
	}

	/**
	 * \brief Method to set the mode of selection of table rows.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param listSelectionModel
	 *            A constant that represents the selection mode.
	 * 
	 *            Ex.:
	 * 
	 *            JTableManager jtm = new JTableManager(dtf,new
	 *            boolean[]{false,false,true});
	 * 
	 *            jtm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	 * 
	 */
	public void setSelectionMode(int listSelectionModel) {
		this._controller.setSelectionMode(listSelectionModel);
	}

	/**
	 * \brief Method to get the current ListSelectionModel utilized.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @return Returns an object representing a ListSelectionModel;
	 */
	public ListSelectionModel getSelectionMode() {
		return this._controller.getSelectionModel();
	}

	/**
	 * \brief Method to set the background color of the lines.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param c
	 *            A constant representing the color.
	 */
	public void setBackgroundColor(Color c) {
		this._controller.setBackground(c);
	}

	/**
	 * \brief Method to get the background color of the lines.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @return Returns the background color of the lines.
	 */
	public Color getBackgroundColor() {
		return this._controller.getBackground();
	}

	/**
	 * \brief Method to set the text color of the cells.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param c
	 *            A constant representing the color.
	 */
	public void setForegroundColor(Color c) {
		this._controller.setForeground(c);
	}

	/**
	 * \brief Method to get the text color of the cells.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @return Returns the text color of the cells.
	 */
	public Color getForegroundColor() {
		return this._controller.getForeground();
	}

	/**
	 * \brief Method to set the grid color of the table.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param c
	 *            A constant representing the color.
	 */
	public void setGridColor(Color c) {
		this._controller.setGridColor(c);
	}

	/**
	 * \brief Method to get the grid color of the table.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @return Returns the grid color of the table.
	 */
	public Color getGridColor() {
		return this._controller.getGridColor();
	}

	/**
	 * \brief Method to set the background color when a cell is selected.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param c
	 *            A constant representing the color.
	 */
	public void setSelectedCellBackgroundColor(Color c) {
		this._controller.setSelectionBackground(c);
	}

	/**
	 * \brief Method to get the background color when a cell is selected.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @return Returns the background color when a cell is selected.
	 */
	public Color getSelectedCellBackgroundColor() {
		return this._controller.getSelectionBackground();
	}

	/**
	 * \brief Method to set the text color when a cell is selected.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param c
	 *            A constant representing the color.
	 */
	public void setSelectedCellForegroundColor(Color c) {
		this._controller.setSelectionForeground(c);
	}

	/**
	 * \brief Method to get the text color when a cell is selected.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @return Returns the the text color when a cell is selected.
	 */
	public Color getSelectedCellForegroundColor() {
		return this._controller.getSelectionForeground();
	}

	/**
	 * \brief Method to remove all rows of the table.
	 * 
	 * @author MOREIRA, Vinicius F.
	 */
	public void removeAllRows() {
		DefaultTableModel dtm = (DefaultTableModel) this._model;
		dtm.setRowCount(0);
	}

	/**
	 * \brief Method to remove all columns of the table.
	 * 
	 * @author MOREIRA, Vinicius F.
	 */
	public void removeAllColumns() {
		DefaultTableModel dtm = (DefaultTableModel) this._model;
		dtm.setColumnCount(0);
	}

	/**
	 * \brief Method to add a new column in the table.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param columnName
	 *            The name of the new column.
	 */
	public void addColumn(Object columnName) {
		DefaultTableModel dtm = (DefaultTableModel) this._model;
		dtm.addColumn(columnName);
	}

	/**
	 * \brief Method to add new line to the table.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param newRow
	 *            An array containing the contents of the new line.
	 */
	public void addRow(Object[] newRow) {
		DefaultTableModel dtm = (DefaultTableModel) this._model;
		dtm.addRow(newRow);
	}

	/**
	 * \brief Method to add new line to the table.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param newRow
	 *            A vector containing the contents of the new line.
	 */
	@SuppressWarnings("rawtypes")
	public void addRow(Vector newRow) {
		DefaultTableModel dtm = (DefaultTableModel) this._model;
		dtm.addRow(newRow);
	}

	/**
	 * \brief Method to remove a desired row of the table.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param rowIndex
	 *            The row index.
	 */
	public void removeRow(int rowIndex) {
		DefaultTableModel dtm = (DefaultTableModel) this._model;
		dtm.removeRow(rowIndex);
	}

	/**
	 * \brief Method to remove desired lines of the table.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param indexes
	 *            An array containing the index of rows to be removed.
	 */
	public void removeRows(int[] indexes) {
		DefaultTableModel dtm = (DefaultTableModel) this._model;

		for (int i = 0; i < indexes.length; i++) {
			dtm.removeRow(indexes[i]);
		}
	}

	/**
	 * \brief Method to get the name of a desired column.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param columnIndex
	 *            The column index.
	 * @return The name of the desired column.
	 */
	public String getColumnName(int columnIndex) {
		return this._model.getColumnName(columnIndex);
	}

	/**
	 * \brief Method to get the number of table rows.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @return The number of the table rows.
	 */
	public int getRowNumber() {
		return this._model.getRowCount();
	}

	/**
	 * \brief Method to get the number of table columns.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @return The number of the table columns.
	 */
	public int getColumnCount() {
		return this._model.getColumnCount();
	}

	/**
	 * \brief Method to get the value of a desired cell.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param row
	 *            The row index.
	 * @param column
	 *            The column index.
	 * @return An object representing the cell value.
	 */
	public Object getCellValue(int row, int column) {
		return this._model.getValueAt(row, column);
	}

	/**
	 * \brief Method to set a new value to a desired cell.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param newValue
	 *            An object representing the value.
	 * @param row
	 *            The row index.
	 * @param column
	 *            The column index.
	 */
	public void setCellValue(Object newValue, int row, int column) {
		this._controller.setValueAt(newValue, row, column);
	}

	/**
	 * \brief Method to set the table position and its size.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param x
	 *            Table x coordinate.
	 * @param y
	 *            Table y coordinate.
	 * @param width
	 *            Table width.
	 * @param height
	 *            Table height.
	 */
	public void setViewSize(int x, int y, int width, int height) {
		this._view.setBounds(x, y, width, height);
	}

	/**
	 * \brief Method to enables the drag mode columns.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param enabled
	 *            True: enabled. False: disables.
	 */
	public void setColumnDragMode(boolean enabled) {
		this._controller.getTableHeader().setReorderingAllowed(enabled);
	}

	/**
	 * \brief Method to set the table visible.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param visible
	 *            True: visible mode. False: invisible mode.
	 */
	public void setTableVisible(boolean visible) {
		this._view.setVisible(visible);
	}

	/**
	 * \brief Method to get the table data model utilized.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @return The table data model.
	 */
	public TableModel getModel() {
		return _model;
	}

	/**
	 * \brief Set a new data model to the table.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param model
	 *            The new table data model.
	 */
	public void setModel(TableModel model) {
		this._model = model;
		this._controller.setModel(model);
		this._view.setViewportView(_controller);
	}

	/**
	 * \brief Method to get the controller of the table model.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @return The table model controller.
	 */
	public JTable getController() {
		return _controller;
	}

	/**
	 * \brief Method to set a new model controller to the table.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param controller
	 *            The new model controller.
	 */
	public void setController(JTable controller) {
		this._controller = controller;
		this._controller.setModel(_model);
		this._view.setViewportView(controller);
	}

	/**
	 * 
	 * \brief Method to get the container that is storing the table.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @return The table container.
	 */
	public JScrollPane getView() {
		return _view;
	}

	/**
	 * \brief Method to set a new table container.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param view
	 *            A new table view.
	 */
	public void setView(JScrollPane view) {
		this._view = view;
		this._view.setViewportView(_controller);
	}

	/**
	 * \brief Method to set a column is resizable.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param resizable
	 *            True - Resizable. False - Not Resizable.
	 * @param columnIndex
	 *            The index of the target column.
	 */
	public void setColumnResizable(boolean resizable, int columnIndex) {
		this._controller.getColumnModel().getColumn(columnIndex).setResizable(resizable);
	}

	/**
	 * \brief Method to set the target columns are resizable.
	 * 
	 * @author MOREIRA, Vinicius F.
	 * @param resizable
	 *            An array that relates to the contents of the vector indices of
	 *            the columns, and through this relationship, says if a column
	 *            is resizable or not.
	 * @param columnIndexes
	 *            An array containing the index of the target columns.
	 */
	public void setColumnResizable(boolean[] resizable, int[] columnIndexes) {
		for (int i = 0; i < columnIndexes.length; i++) {
			this._controller.getColumnModel().getColumn(columnIndexes[i]).setResizable(resizable[i]);
		}
	}
}