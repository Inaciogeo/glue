package br.org.funcate.glue.model.tree;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultTreeModel;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.CalculatorService;
import br.org.funcate.glue.model.Layer;
import br.org.funcate.glue.model.Theme;
import br.org.funcate.glue.model.ThemeAttribute;
import br.org.funcate.glue.model.UserType;
import br.org.funcate.glue.model.View;
import br.org.funcate.glue.model.ViewSet;
import br.org.funcate.glue.model.canvas.CanvasState;
import br.org.funcate.glue.model.enumeration.AttributeType;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.service.TerraJavaClient;

public class TreeService {

	private static TreeState treeState;

	public static void renameNode() {
		treeState = AppSingleton.getInstance().getTreeState();
		treeState.renameNode();
	}

	public static Double updateTreeVisibility() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		TreeState treeState = singleton.getTreeState();
		
		Double result = treeState.updateVisibility(state.getScale());
		if (result == 0) {
			Box box = treeState.getCurrentView().getView().getBox();
			result = CalculatorService.calculateScale(treeState.getCurrentView().getView().getProjection(), box.getX1(), box.getY1(),
					box.getX2(), box.getY2());
			List<Theme> temas = treeState.getCurrentView().getView().getThemes();
			for (Theme t : temas) {
				if (t.getScaleLimit().getMin() == 0) {
					t.getScaleLimit().setMin(1.0);
				}
				if (t.getScaleLimit().getMax() == 0) {
					t.getScaleLimit().setMax(result);
				}

			}

			treeState.getCurrentView().getView().setThemes(temas);
		}
		return result;
	}

	public static void createViewUpdator(Boolean reload, Boolean memory, Boolean remove) {
		treeState = AppSingleton.getInstance().getTreeState();
		treeState.createViewUpdator(reload, memory, remove);
	}

	public static UserType getUserType() {
		treeState = AppSingleton.getInstance().getTreeState();
		return treeState.getUserType();
	}

	public static CustomNode getSelectedNode() {
		treeState = AppSingleton.getInstance().getTreeState();
		return treeState.getSelectedNode();
	}

	public static CustomNode createToolbar() {
		treeState = AppSingleton.getInstance().getTreeState();
		return treeState.createToolBar();
	}

	public static CustomNode createRoot() throws GlueServerException {
		treeState = AppSingleton.getInstance().getTreeState();
		return treeState.createRoot();
	}

	public static CustomNode getCurrentView() {
		treeState = AppSingleton.getInstance().getTreeState();
		return treeState.getCurrentView();
	}

	public static void setCurrentView(CustomNode view) {
		treeState = AppSingleton.getInstance().getTreeState();
		treeState.setCurrentView(view);
	}

	public static void setDefaultTreeModel(DefaultTreeModel defaultTreeModel) {
		treeState = AppSingleton.getInstance().getTreeState();
		treeState.setDefaultTreeModel(defaultTreeModel);
	}

	public static DefaultTreeModel getDefaultTreeModel() {
		treeState = AppSingleton.getInstance().getTreeState();
		return treeState.getDefaultTreeModel();
	}

	public static void removeNode() {
		treeState = AppSingleton.getInstance().getTreeState();
		treeState.removeNode();
	}

	public static CustomNode getCurrentTheme() {
		treeState = AppSingleton.getInstance().getTreeState();
		return treeState.getCurrentTheme();
	}

	public static void setCurrentTheme(CustomNode theme) {
		treeState = AppSingleton.getInstance().getTreeState();
		treeState.setCurrentTheme(theme);
		
	}

	public static boolean checkAllNodes(CustomNode root, CustomNode compare) {
		treeState = AppSingleton.getInstance().getTreeState();
		return treeState.checkAllNodes(root, compare);
	}

	public static void setUserType(UserType userType) {
		treeState = AppSingleton.getInstance().getTreeState();
		treeState.setUserType(userType);
	}

	public static void setSelectedNode(CustomNode selectedNode) {
		treeState = AppSingleton.getInstance().getTreeState();
		treeState.setSelectedNode(selectedNode);
	}

	public static void copyNode() {
		treeState = AppSingleton.getInstance().getTreeState();
		treeState.copyNode();
	}

	public static void cutNode() {
		treeState = AppSingleton.getInstance().getTreeState();
		treeState.cutNode();
	}

	public static void pasteNode() {
		treeState = AppSingleton.getInstance().getTreeState();
		treeState.pasteNode();
	}

	public static CustomNode getNodeView(CustomNode parent) {
		treeState = AppSingleton.getInstance().getTreeState();
		return treeState.getNodeView(parent);
	}

	public static void rearrange(int index, CustomNode root, CustomNode nodeSource) {
		treeState = AppSingleton.getInstance().getTreeState();
		treeState.rearrange(index, root, nodeSource);
	}

	public static void moveNode(CustomNode targetView, CustomNode sourceView) {
		treeState = AppSingleton.getInstance().getTreeState();
		treeState.moveNode(targetView, sourceView);
	}

	public static CustomNode getRoot() {
		treeState = AppSingleton.getInstance().getTreeState();
		return treeState.getRoot();
	}

	public static void applyView() {
		treeState = AppSingleton.getInstance().getTreeState();
		treeState.applyView();
	}

	public static List<CustomNode> getSelectedThemes() {
		treeState = AppSingleton.getInstance().getTreeState();
		return treeState.getSelectedThemes();
	}

	public static void addNode(CustomNode node, CustomNode parent) {
		treeState.addNode(node, parent);
	}

	public static List<Layer> getLayers() {
		return treeState.getLayers();
	}

	public static void removeNodeFromParent(CustomNode node) {
		treeState.getDefaultTreeModel().removeNodeFromParent(node);
	}

	public static Boolean updateSelectedView(Boolean isReload, Boolean isViewMem) throws GlueServerException {
		return treeState.updateSelectedView(isReload, isViewMem);
	}

	public static void reload() {
		treeState.getDefaultTreeModel().reload();
	}

	public static void setNodeSource(CustomNode nodeSource) {
		treeState.setNodeSource(nodeSource);
	}

	public static void setThematicTheme(String viewName, String themeName) {
		treeState = AppSingleton.getInstance().getTreeState();
		
		cleanGroupThemes();
		
		ViewSet viewSet = treeState.getViewSet();

		for (View view : viewSet.getViews()) {
			if (view.getName().equals(viewName)) {
				for (Theme theme : view.getThemes()) {
					if (theme.getName().equals(themeName)) {
						theme.setGroup(true);
						return;
					}
				}
			}
		}
	}

	public static void cleanGroupThemes() {
		treeState = AppSingleton.getInstance().getTreeState();

		ViewSet viewSet = treeState.getViewSet();

		for (View view : viewSet.getViews()) {
			for (Theme theme : view.getThemes()) {
				theme.setGroup(false);
			}
		}
	}
	
	public static List<View> getViews(){
		treeState = AppSingleton.getInstance().getTreeState();
		
		return treeState.getViewSet().getViews();
	}
	
	public static List<ThemeAttribute> getAttributes(String viewName, String themeName) throws GlueServerException{
		TerraJavaClient service = AppSingleton.getInstance().getServices();
		List<String> attrStrings = null;
		
		List<ThemeAttribute> attributes = new ArrayList<ThemeAttribute>();
			 attrStrings = service.getAttributesList(themeName, viewName);
		
		for (String attr : attrStrings){
			String [] spaceDiv = attr.split(" ");
			String [] pointDiv = spaceDiv[0].split("\\.");
			
			AttributeType type = getType(spaceDiv[1]);
			
			ThemeAttribute attribute = new ThemeAttribute(pointDiv[0], pointDiv[1], type);
			attributes.add(attribute);
		}
		return attributes;
	}

	private static AttributeType getType(String textType){
		
		if (textType == null){
			return null;
		} else if (textType.equals("(textual)")){
			return AttributeType.STRING;
		} else if (textType.equals("(numerico)")){
			return AttributeType.NUMBER;
		}
		return null;
	}
	public static String theme ="";

	public static boolean checkThemeChange(String th){
		boolean result = false;
		String themeName="";
		if(TreeService.getSelectedNode().getTheme()!=null){
			themeName = th;
			if(TreeService.theme==""){
				TreeService.theme = themeName;
				result=true;
			}
			else if (TreeService.theme!="" && TreeService.theme!= th) {
				TreeService.theme = themeName;
				result=true;
				
			}
			else if (TreeService.theme!="" && TreeService.theme==th) {
				TreeService.theme = themeName;
				result=false;	
			}
		}
		return result;
	
	}
	
}
