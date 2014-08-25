package br.org.funcate.glue.os.model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JTable;

import sun.swing.table.DefaultTableCellHeaderRenderer;

@SuppressWarnings("serial")
public class HighlightRenderer extends DefaultTableCellHeaderRenderer {

	 @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

	        // everything as usual
	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	        // added behavior
	        if(row == table.getSelectedRow()) {

	            // this will customize that kind of border that will be use to highlight a row
	            setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1, Color.BLACK));
	            setBackground(Color.orange);
	        }

	        return this;
	    }

}
