package br.org.funcate.glue.service.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.org.funcate.glue.model.Theme;
import br.org.funcate.glue.model.View;
import br.org.funcate.glue.model.tree.CustomNode;
import br.org.funcate.glue.view.NodeType;

public class NetworkService {
	private JSONObject viewConfiguration;

	@SuppressWarnings("unchecked")
	public NetworkService() {

		JSONParser parser = new JSONParser();

		try {
			FileReader fileReader = new FileReader("viewConfig.json");
			Object obj = parser.parse(fileReader);
			viewConfiguration = (JSONObject) obj;
			fileReader.close();
		} catch (FileNotFoundException e) {
			viewConfiguration = new JSONObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public void updateView(CustomNode viewNode) {

		View view = viewNode.getView();
		if (view != null) {
			if (viewConfiguration.containsKey(view.getName())) {
				viewConfiguration.remove(view.getName());
			}

			JSONArray networkThemes = new JSONArray();

			int childCount = viewNode.getChildCount();
			for (int i = 0; i < childCount; i++) {
				CustomNode node = (CustomNode) viewNode.getChildAt(i);
				Theme theme = node.getTheme();
				if (node.getNodeType() == NodeType.NETWORK_THEME) {
					JSONObject themeJson = new JSONObject();
					themeJson.put("name", theme.getName());
					themeJson.put("selected", theme.isVisibility());

					CustomNode nodePoints = (CustomNode) node.getChildAt(0);
					Theme pointsTheme = nodePoints.getTheme();
					themeJson.put("pointsTheme", pointsTheme.getName());

					CustomNode nodeLines = (CustomNode) node.getChildAt(1);
					Theme linesTheme = nodeLines.getTheme();
					themeJson.put("linesTheme", linesTheme.getName());

					networkThemes.add(themeJson);
				}

			}

			viewConfiguration.put(view.getName(), networkThemes);

		}

		try {

			FileWriter file = new FileWriter("viewConfig.json");
			file.write(viewConfiguration.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void readView(CustomNode viewNode) {

		View view = viewNode.getView();
		if (view != null) {
			String viewName = view.getName();
			if (viewConfiguration.containsKey(viewName)) {
				JSONArray networkThemes = (JSONArray) viewConfiguration
						.get(viewName);
				for (int i = 0; i < networkThemes.size(); i++) {
					JSONObject networkThemeJSON = (JSONObject) networkThemes
							.get(i);

					// Creates network theme node
					String networkThemeName = (String) networkThemeJSON
							.get("name");
					boolean selected = (Boolean) networkThemeJSON
							.get("selected");
					CustomNode networkThemeNode = new CustomNode(-1l,
							networkThemeName, selected, NodeType.NETWORK_THEME);

					String linesThemeName = (String) networkThemeJSON
							.get("linesTheme");
					String pointsThemeName = (String) networkThemeJSON
							.get("pointsTheme");

					// Gets lines node and point node
					CustomNode linesNode = null;
					CustomNode pointsNode = null;
					int index = 0;
					for (int j = 0; j < viewNode.getChildCount(); j++) {
						CustomNode node = (CustomNode) viewNode.getChildAt(j);
						if (node.getName().equals(linesThemeName)) {
							linesNode = node;
							index = j;
						}
						if (node.getName().equals(pointsThemeName)) {
							pointsNode = node;
						}
					}
					if(linesNode != null && pointsNode != null){

						// Inserts the network node in the view
						viewNode.insert(networkThemeNode, index);
	
						// Move nodes to network node						

						viewNode.remove(pointsNode);
						viewNode.remove(linesNode);
						
						// Inserts first Points and then Lines
						networkThemeNode.insert(pointsNode, 0);
						networkThemeNode.insert(linesNode, 1);
					}
					

				}
			}

		}
	}

	@Override
	public String toString() {
		return viewConfiguration.toJSONString();
	}

}
