package br.org.funcate.glue.model.toolbar;

import java.util.HashMap;
import java.util.Map;

/**
 * This contains the toolbar's state.
 * 
 * @author Moraes, Emerson Leite
 * 
 */
public class ToolbarState {
	/**
	 * This Map save the state of all toolbar's tools.
	 */
	private Map<ToolEnum, ToolState> toolState;

	/**
	 * ToolbarState's constructor.
	 */
	public ToolbarState() {
		toolState = new HashMap<ToolEnum, ToolState>();
		initToolbarState();
	}

	/**
	 * This method is responsible to start the state of toolbar's tools.
	 */
	private void initToolbarState() {
		toolState.put(ToolEnum.TERRALIB, new ToolState(true, true));
		toolState.put(ToolEnum.GOOGLE, new ToolState(true, false));
		toolState.put(ToolEnum.WMS, new ToolState(true, false));
		toolState.put(ToolEnum.PAINT, new ToolState(true, false));
		toolState.put(ToolEnum.REBUILD, new ToolState(true, false));
		toolState.put(ToolEnum.ZOOMIN, new ToolState(true, false));
		toolState.put(ToolEnum.ZOOMOUT, new ToolState(true, false));
		toolState.put(ToolEnum.ZOOMAREA, new ToolState(true, false));
		toolState.put(ToolEnum.PAN, new ToolState(true, false));
		toolState.put(ToolEnum.DISTANCE, new ToolState(true, false));
		toolState.put(ToolEnum.UNDO, new ToolState(true, false));
		toolState.put(ToolEnum.REDO, new ToolState(true, false));
		toolState.put(ToolEnum.PHOTOLOCATION, new ToolState(false, false));
		toolState.put(ToolEnum.INFO, new ToolState(true, false));
		toolState.put(ToolEnum.LINKS, new ToolState(false, false));
		toolState.put(ToolEnum.ATRIBS, new ToolState(false, false));
		toolState.put(ToolEnum.CLEAN, new ToolState(true, false));
		toolState.put(ToolEnum.PDF, new ToolState(false, false));
		toolState.put(ToolEnum.EXPORT, new ToolState(true, false));
		toolState.put(ToolEnum.HELPONLINE, new ToolState(false, false));
	}

	/**
	 * Sets the state of one tool.
	 * 
	 * @param tool
	 * @param isEnabled
	 * @param isSelected
	 */
	public void setState(ToolEnum tool, boolean isEnabled, boolean isSelected) {
		ToolState state = toolState.get(tool);
		state.setEnabled(isEnabled);
		state.setSelected(isSelected);
	}

	/**
	 * Sets Default state of tools.
	 */
	public void setDefaultTools() {
		for (ToolEnum tool : ToolEnum.values()) {
			if (tool.equals(ToolEnum.TERRALIB) || tool.equals(ToolEnum.GOOGLE) || tool.equals(ToolEnum.WMS)) {
				continue;
			} else {
				ToolState state = toolState.get(tool);
				state.setSelected(false);
			}
		}
	}

	/**
	 * This method is responsible to return the state of the requested tool.
	 * 
	 * @param tool
	 * @return ToolState of the requested tool.
	 */
	public ToolState getToolState(ToolEnum tool) {
		return toolState.get(tool);
	}
}
