package br.org.funcate.glue.tool;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.ImageIcon;

import br.org.funcate.eagles.kernel.dispatcher.EventHandler;
import br.org.funcate.eagles.kernel.listener.ListenersHandler;
import br.org.funcate.eagles.kernel.listener.ListenersHandlerImpl;
import br.org.funcate.eagles.kernel.transmitter.DirectedEventTransmitter;
import br.org.funcate.eagles.kernel.transmitter.EventTransmitter;
import br.org.funcate.glue.event.AfterToolChangedEvent;
import br.org.funcate.glue.event.BeforeToolChangedEvent;
import br.org.funcate.glue.event.FocusedThemeEvent;
import br.org.funcate.glue.event.MousePressedEvent;
import br.org.funcate.glue.event.SelectedThemeEvent;
import br.org.funcate.glue.event.UnselectedThemeEvent;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.InfoClick;
import br.org.funcate.glue.model.canvas.InfoToolService;
import br.org.funcate.glue.model.canvas.ThemeAttributesInfoTool;
import br.org.funcate.glue.model.canvas.ToolRedirectionService;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.model.toolbar.ToolEnum;
import br.org.funcate.glue.model.toolbar.ToolService;
import br.org.funcate.glue.model.toolbar.ToolState;
import br.org.funcate.glue.view.ImageIconLoader;
import br.org.funcate.glue.view.InfoToolView;

public class InfoTool implements Tool {

	private ListenersHandler listeners;
	private EventHandler eventHandler;
	@SuppressWarnings("unused")
	private EventTransmitter transmitter;

	private List<String> eventsToListen;

	private Cursor cursor;

	private InfoToolView infoToolView;
	private InfoClick infoClick;

	public InfoTool() {
		listeners = new ListenersHandlerImpl();
		eventHandler = new EventHandler();
		transmitter = new DirectedEventTransmitter(this);

		ImageIcon imgIconInfo = ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/info_cursor.gif",
				ToolRedirectionService.class);
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				imgIconInfo.getImage(), new Point(10, 10), "Informações");

		eventsToListen = new ArrayList<String>();
		eventsToListen.add(MousePressedEvent.class.getName());
		eventsToListen.add(BeforeToolChangedEvent.class.getName());
		eventsToListen.add(AfterToolChangedEvent.class.getName());
		eventsToListen.add(SelectedThemeEvent.class.getName());
		eventsToListen.add(UnselectedThemeEvent.class.getName());
		eventsToListen.add(FocusedThemeEvent.class.getName());
		infoClick = new InfoClick();
	}

	@Override
	public ListenersHandler getListenersHandler() {
		return this.listeners;
	}

	@Override
	public EventHandler getEventHandler() {
		return this.eventHandler;
	}

	@Override
	public void dispatch(EventTransmitter tc, EventObject e) throws Exception {
		tc.dispatch(e);
	}

	@Override
	public void handle(EventObject e) throws GlueServerException {
		if (e instanceof BeforeToolChangedEvent) {
			this.handle((BeforeToolChangedEvent) e);
		} else if (e instanceof AfterToolChangedEvent) {
			this.handle((AfterToolChangedEvent) e);
		} else if (e instanceof MousePressedEvent) {
			this.handle((MousePressedEvent) e);
		} else if (e instanceof SelectedThemeEvent) {
			this.handle((SelectedThemeEvent) e);
		} else if (e instanceof UnselectedThemeEvent) {
			this.handle((UnselectedThemeEvent) e);
		} else if (e instanceof FocusedThemeEvent) {
			this.handle((FocusedThemeEvent) e);
		}
	}

	private void handle(SelectedThemeEvent e) throws GlueServerException {
		// this.addInfoTab(e.getTheme().getName(), e.getTheme());
		this.showInfo(e.getTheme().getName());
	}

	private void handle(UnselectedThemeEvent e) {
		this.removeInfoTab(e.getThemeName());
	}

	private void handle(FocusedThemeEvent e) {
		infoClick.setSelectedThemeName(e.getThemeName());
		this.setSelectedTab(e.getThemeName());
	}

	private void handle(BeforeToolChangedEvent e) {
		ToolState toolState = ToolService.getTool(ToolEnum.INFO);
		toolState.setSelected(false);
		AppSingleton.getInstance().getMediator().updateToolBar();
	}

	private void handle(AfterToolChangedEvent e) {
	}

	private void handle(MousePressedEvent e) throws GlueServerException {
		infoClick.setxPosition(e.getX());
		infoClick.setyPosition(e.getY());

		this.showInfo();
	}

	private void showInfo(String themeName) throws GlueServerException {
		infoClick.setSelectedThemeName(themeName);
		this.showInfo();
	}

	private void showInfo() throws GlueServerException {
		AppSingleton singleton = AppSingleton.getInstance();
		if (infoClick.getxPosition() != 0.0) {
			List<ThemeAttributesInfoTool> info = InfoToolService
					.getInfoByClick(infoClick.getxPosition(),
							infoClick.getyPosition());
			InfoToolView infoToolView = singleton.getMediator().showInfo(info);
			if (infoToolView != null) {
				this.infoToolView = infoToolView;
				this.setSelectedTab(infoClick.getSelectedThemeName());
			}
		}
	}

	// public void addInfoTab(String themeName, Theme theme)
	// throws GlueServerException {
	// ThemeAttributesInfoTool themeAttributesInfoTool = InfoToolService
	// .getThemeAttributesInfoTool(theme, infoClick.getxPosition(),
	// infoClick.getyPosition());
	// if (themeAttributesInfoTool != null) {
	// this.infoToolView.addTab(themeAttributesInfoTool);
	// infoClick.setSelectedThemeName(themeName);
	// }
	// }

	private void removeInfoTab(String themeName) {
		this.infoToolView.removeTab(themeName);
	}

	public void setSelectedTab(String themeName) {
		if (themeName != null && this.infoToolView != null) {
			this.infoToolView.setSelectedTab(themeName);
		}
	}

	@Override
	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}

	@Override
	public Cursor getCursor() {
		return cursor;
	}

	@Override
	public List<String> getEventsToListen() {
		return eventsToListen;
	}

	public InfoToolView getInfoToolView() {
		return infoToolView;
	}
}
