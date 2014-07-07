package br.org.funcate.glue.model.canvas;

import java.util.Stack;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.toolbar.ToolEnum;

public abstract class UndoRedoService {

	public static void addUndoValue() {
		double boxX1 = AppSingleton.getInstance().getCanvasState().getBox().getX1();
		double boxY1 = AppSingleton.getInstance().getCanvasState().getBox().getY1();
		double resolution = AppSingleton.getInstance().getCanvasState().getResolution();

		UndoRedoItem undo = new UndoRedoItem(boxX1, boxY1, resolution);

		AppSingleton.getInstance().getCanvasState().getUndoMemory().addElement(undo);

		updateUndoRedoVisibility();
	}

	public static void addRedoValue() {
		double boxX1 = AppSingleton.getInstance().getCanvasState().getBox().getX1();
		double boxY1 = AppSingleton.getInstance().getCanvasState().getBox().getY1();
		double resolution = AppSingleton.getInstance().getCanvasState().getResolution();

		UndoRedoItem redo = new UndoRedoItem(boxX1, boxY1, resolution);

		AppSingleton.getInstance().getCanvasState().getRedoMemory().addElement(redo);

		updateUndoRedoVisibility();
	}

	public static void redo() {
		Stack<UndoRedoItem> redoMemory = AppSingleton.getInstance().getCanvasState().getRedoMemory();

		if (redoMemory.isEmpty()) {
			throw new RuntimeException("Redo foi chamado, porém a lista está vazia!");
		}

		addUndoValue();

		UndoRedoItem item = redoMemory.pop();

		updateUndoRedoVisibility();

		CanvasService.setUndoRedoBox(item);
	}

	public static void undo() {
		Stack<UndoRedoItem> undoMemory = AppSingleton.getInstance().getCanvasState().getUndoMemory();

		if (undoMemory.isEmpty()) {
			throw new RuntimeException("Undo foi chamado, porém a lista está vazia!");
		}

		addRedoValue();

		UndoRedoItem item = undoMemory.pop();

		updateUndoRedoVisibility();

		CanvasService.setUndoRedoBox(item);
	}

	public static void updateUndoRedoVisibility() {
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		CanvasState state = singleton.getCanvasState();
		Stack<UndoRedoItem> undoMemory = state.getUndoMemory();
		Stack<UndoRedoItem> redoMemory = state.getRedoMemory();

		if (undoMemory.isEmpty()) {
			mediator.setToolEnabled(ToolEnum.UNDO, false);
		} else {
			mediator.setToolEnabled(ToolEnum.UNDO, true);
		}

		if (redoMemory.isEmpty()) {
			mediator.setToolEnabled(ToolEnum.REDO, false);
		} else {
			mediator.setToolEnabled(ToolEnum.REDO, true);
		}
	}

	public static void clearUndoRedoMemory() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		Stack<UndoRedoItem> undoMemory = state.getUndoMemory();
		undoMemory.clear();
		Stack<UndoRedoItem> redoMemory = state.getRedoMemory();
		redoMemory.clear();

		updateUndoRedoVisibility();
	}
}
