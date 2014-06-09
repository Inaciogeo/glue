package br.org.funcate.glue.view;

import java.lang.reflect.InvocationTargetException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.org.funcate.glue.exception.GlueClientException;

public class ExampleTableListSelectionListener implements ListSelectionListener {

	private TableControllerTriggerReflection reflection;

	public ExampleTableListSelectionListener() {

		reflection = new TableControllerTriggerReflection();

	}

	public void valueChanged(ListSelectionEvent arg0) {

		try {

			this.invokeMethodValueChanged();

		} catch (Exception e1) {

			e1.printStackTrace();

		}

	}

	public void addDeclareMethodValueChanged(Object instance, String nameMethod, String pathNameClass, Object... paramsMethod)
			throws GlueClientException {

		reflection.addListener("DeclareMethodValueChanged", instance, nameMethod, pathNameClass, paramsMethod);

	}

	public void removeDeclareMethodValueChanged(Object instance, String nameMethod, String pathNameClass, Object... paramsMethod)
			throws GlueClientException {

		reflection.removeListener("DeclareMethodValueChanged", instance, nameMethod, pathNameClass, paramsMethod);

	}

	private void invokeMethodValueChanged() throws Exception {

		reflection.forInvoke("DeclareMethodValueChanged");

	}

	public void removeAllTableListSelectionListeners() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			GlueClientException {

		reflection.removeAll();

	}

}
