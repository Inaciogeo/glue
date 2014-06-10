package br.org.funcate.glue.model.toolbar;

import br.org.funcate.glue.main.AppSingleton;

/**
 * This class is a Service responsible to enables/disables and select current
 * tools of GLUE Application.
 * 
 * @author Moraes, Emerson Leite
 * 
 */
public abstract class ToolService {

	/**
	 * This method sets the current tool of the system, all the business logic
	 * is here.
	 * 
	 * @param tool
	 */
	public static void setSelectedTool(ToolEnum tool) {
		AppSingleton singleton = AppSingleton.getInstance();
		ToolbarState toolbarState = singleton.getToolbarState();

		ToolState selectedTool = toolbarState.getToolState(tool);
		if (tool.equals(ToolEnum.TERRALIB) || tool.equals(ToolEnum.GOOGLE) || tool.equals(ToolEnum.WMS)) {
			setTerraLibGoogleWMS(tool, selectedTool, toolbarState);
		} else {
			toolbarState.setDefaultTools();
			selectedTool.setSelected(true);
		}
	}

	/**
	 * This method returns the requested ToolState.
	 * 
	 * @param tool
	 * @return
	 */
	public static ToolState getTool(ToolEnum tool) {
		AppSingleton singleton = AppSingleton.getInstance();
		ToolbarState toolbarState = singleton.getToolbarState();

		return toolbarState.getToolState(tool);
	}

	/**
	 * This method sets TerraLib, WMS or Google tool.
	 * 
	 * @param tool
	 * @param selectedTool
	 * @param toolbarState
	 */
	private static void setTerraLibGoogleWMS(ToolEnum tool, ToolState selectedTool, ToolbarState toolbarState) {
		switch (tool) {

		case TERRALIB:
			if (selectedTool.isSelected()) {
				selectedTool.setSelected(false);
			} else {
				selectedTool.setSelected(true);
			}
			break;

		case GOOGLE:
			ToolState wmsTool = toolbarState.getToolState(ToolEnum.WMS);
			if (selectedTool.isSelected()) {
				wmsTool.setEnabled(true);
				selectedTool.setSelected(false);
			} else {
				wmsTool.setEnabled(false);
				selectedTool.setSelected(true);
			}
			break;

		case WMS:
			ToolState googleTool = toolbarState.getToolState(ToolEnum.GOOGLE);
			if (selectedTool.isSelected()) {
				googleTool.setEnabled(true);
				selectedTool.setSelected(false);
			} else {
				googleTool.setEnabled(false);
				selectedTool.setSelected(true);
			}
			break;
		}
	}

	/**
	 * This method enable or disable one tool.
	 * 
	 * @param tool
	 * @param enabled
	 */
	public static void setToolEnabled(ToolEnum tool, boolean enabled) {
		AppSingleton singleton = AppSingleton.getInstance();
		ToolbarState toolbarState = singleton.getToolbarState();
		ToolState toolState = toolbarState.getToolState(tool);
		toolState.setEnabled(enabled);
	}

	/**
	 * This method enable/disable all toolbar's tools except TerraLib / WMS /
	 * Google / Paint.
	 * 
	 * @param enabled
	 */
	public static void setAllToolsEnabled(boolean enabled) {
		AppSingleton singleton = AppSingleton.getInstance();
		ToolbarState toolbarState = singleton.getToolbarState();
		for (ToolEnum tool : ToolEnum.values()) {

			if (tool.equals(ToolEnum.TERRALIB) || tool.equals(ToolEnum.GOOGLE) || tool.equals(ToolEnum.WMS) || tool.equals(ToolEnum.PAINT)
					|| tool.equals(ToolEnum.ATRIBS) || tool.equals(ToolEnum.HELPONLINE) || tool.equals(ToolEnum.LINKS)
					|| tool.equals(ToolEnum.PHOTOLOCATION) || tool.equals(ToolEnum.PDF)) {
				continue;
			}
			ToolState toolState = toolbarState.getToolState(tool);
			toolState.setEnabled(enabled);
		}
	}

	/**
	 * This method return if terraLib is selected or not.
	 * 
	 * @return
	 */
	public static boolean isTerraLibSelected() {
		AppSingleton singleton = AppSingleton.getInstance();
		ToolbarState toolbarState = singleton.getToolbarState();

		ToolState terraLib = toolbarState.getToolState(ToolEnum.TERRALIB);

		if (terraLib.isSelected()) {
			return true;
		}
		return false;
	}

	/**
	 * This method return selected tool.
	 * 
	 * @throws NullPointerException
	 * @return selectedTool
	 */
	public static ToolEnum getSelectedTool() {
		AppSingleton singleton = AppSingleton.getInstance();
		ToolbarState toolbarState = singleton.getToolbarState();

		for (ToolEnum toolEnum : ToolEnum.values()) {
			if (toolEnum.equals(ToolEnum.TERRALIB) || toolEnum.equals(ToolEnum.GOOGLE) || toolEnum.equals(ToolEnum.WMS)) {
				continue;
			}

			ToolState currentTool = toolbarState.getToolState(toolEnum);
			if (currentTool.isSelected()) {
				return toolEnum;
			}
		}

		return null;
	}

	public static boolean isToolEnabled(ToolEnum tool) {
		AppSingleton singleton = AppSingleton.getInstance();
		ToolbarState toolbarState = singleton.getToolbarState();

		ToolState selectedTool = toolbarState.getToolState(tool);

		return selectedTool.isEnabled();
	}
}
