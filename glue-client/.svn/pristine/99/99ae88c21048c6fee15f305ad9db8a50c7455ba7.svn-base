package br.org.funcate.glue.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.org.funcate.glue.controller.ToolbarController;
import br.org.funcate.glue.view.Toolbar;
/**
 * 
 * @author Moraes, Emerson Leite
 * 
 *         This class is the listener of the Toolbar component.
 * 
 */
public class ToolbarListener implements ActionListener {

	/**
	 * Toolbar attribute
	 */
	private Toolbar toolbar;

	/**
	 * ToolbarController attribute
	 */
	private ToolbarController toolController;

	/**
	 * Toolbar Listener contructor
	 * 
	 * @param toolbar
	 */
	public ToolbarListener(Toolbar toolbar) {
		this.toolbar = toolbar;
		this.toolController = new ToolbarController(this.toolbar);
	}

	@Override
	/**
	 * Implementation of Action Listener interface method.
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == toolbar.getTerraLib()) {
			toolController.setTerraLib();
		}

		if (e.getSource() == toolbar.getPaint()) {
			toolController.setPaint();
		}

		if (e.getSource() == toolbar.getRebuild()) {
			toolController.setRebuild();
		}

		if (e.getSource() == toolbar.getZoomIn()) {
			toolController.setZoomIn();
		}

		if (e.getSource() == toolbar.getZoomOut()) {
			toolController.setZoomOut();
		}

		if (e.getSource() == toolbar.getSelectArea()) {
			toolController.setZoomArea();
		}

		if (e.getSource() == toolbar.getPan()) {
			toolController.setPan();
		}

		if (e.getSource() == toolbar.getDistance()) {
			toolController.setDistance();
		}

		if (e.getSource() == toolbar.getUnDo()) {
			toolController.setUnDo();
		}

		if (e.getSource() == toolbar.getReDo()) {
			toolController.setReDo();
		}

		if (e.getSource() == toolbar.getPhotoLocation()) {
			toolController.setPhotoLocation();
		}

		if (e.getSource() == toolbar.getInfo()) {
			toolController.setInfo();
		}

		if (e.getSource() == toolbar.getLinks()) {
			toolController.setLinks();
		}

		if (e.getSource() == toolbar.getAtribs()) {
			toolController.setAtribs();
		}

		if (e.getSource() == toolbar.getClean()) {
			toolController.setClean();
		}

		if (e.getSource() == toolbar.getPdf()) {
			toolController.setPdf();
		}

		if (e.getSource() == toolbar.getExport()) {
			toolController.setExport();
		}

		if (e.getSource() == toolbar.getHelpOnline()) {
			toolController.setHelpOnline();
		}

		if (e.getSource() == toolbar.getBtnTileRequest()) {
			toolController.setTile();			
		}

		if (e.getSource() == toolbar.getWms()) {
			toolController.setWMS();
		}
		
	}

}