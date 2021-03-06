package br.org.funcate.glue.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import br.org.funcate.glue.exception.GlueClientException;

public class TableControllerTriggerReflection {

	private class TableEventComparator implements Comparator<Object[]> {

		/**
		 * @author Juliana Hohara de S. Coelho
		 * 
		 *         \brief The comparison is made as follows: first is to compare
		 *         the name of the each Object, and then, if equal, will be
		 *         compared the number of parameters.
		 */

		@Override
		public int compare(Object[] o1, Object[] o2) {

			Method nomeMetodo1 = (Method) o1[2];
			Method nomeMetodo2 = (Method) o2[2];

			if (nomeMetodo1.getName().equals(nomeMetodo2.getName())) {

				Object obj1 = o1[3];
				Object obj2 = o2[3];

				if (obj1.getClass().getName().equals(obj2.getClass().getName())) {

					Object[] pContem1 = (Object[]) o1[1];
					Object[] pContem2 = (Object[]) o2[1];

					if (pContem1.length != pContem2.length)
						return 1;
					else {

						for (int i = 0; i < pContem1.length; i++) {

							if (pContem1[i] != pContem2[i])
								return 1;

						}
					}

					return 0;

				}

			}

			return 1;

		}

	}

	@SuppressWarnings("rawtypes")
	private final List<Class> cla;
	/** List of classes with methods added to events */
	@SuppressWarnings("rawtypes")
	private final List object;
	/** Lista de inst�ncias de classes com m�todos adicionados aos eventos */
	private final TableEventComparator tableComparator;

	/** Stores the methods that should be called in specific events */

	/** Class comparator */

	private final Set<Object[]> listeners;

	/**
	 * @author Juliana Hohara de S. Coelho \brief Constructor
	 */

	@SuppressWarnings("rawtypes")
	public TableControllerTriggerReflection() {

		cla = new ArrayList<Class>();
		object = new ArrayList<Object>();
		tableComparator = new TableEventComparator();

		listeners = new TreeSet<Object[]>(tableComparator);

	}

	/**
	 * @author Juliana Hohara de S. Coelho
	 * 
	 *         \brief Add to a list the method to be invoked on a specific
	 *         event.
	 * 
	 * @param nameMethodClass
	 *            Name of method that belongs to a class Listener
	 * @param instance
	 *            Instance of the class to which the method being invoked
	 *            belongs
	 * @param nameMethod
	 *            Instance of the class to which the method being invoked
	 *            belongs
	 * @param pathNameClass
	 *            Path of the class to which the method being invoked belongs
	 * @param paramsMethod
	 *            Parameters of the method to be invoked
	 * @return true - Added Successfully false - Unable to add
	 * @throws GlueClientException
	 */

	public boolean addListener(String nameMethodClass, Object instance,
			String nameMethod, String pathNameClass, Object... paramsMethod)
			throws GlueClientException {

		try {

			Object[] listener = reflectionObjects(nameMethodClass, instance,
					nameMethod, pathNameClass, paramsMethod);

			listeners.add(listener);

			return true;

		} catch (Exception e) {

			throw new GlueClientException("addListener()", e);

		}

	}

	/**
	 * @author Juliana Hohara de S. Coelho \brief Checks whether is there method
	 *         with the same name
	 * @param name
	 *            Method Name
	 * @param aux
	 *            Vector methods
	 * @return returns the method found
	 * @throws GlueClientException
	 */

	private Method findMethod(String name, Method[] aux,
			Object[] parametrosMetodo) throws GlueClientException {

		try {

			for (Method parametro : aux) {

				if (parametro.getName().equals(name)) {

					@SuppressWarnings("rawtypes")
					Class[] param = parametro.getParameterTypes();

					if (parametrosMetodo.length != param.length) {

						continue;

					} else {

						/*
						 * Lack the condition:
						 * 
						 * Names of the methods equal, number of arguments equal
						 * but different parameter types.
						 */

						/*
						 * for(int i = 0 ; i < param.length; i++){
						 * 
						 * }
						 */

					}

					return parametro;

				}

			}

		} catch (NullPointerException e) {

			throw new GlueClientException(
					"findReflection() - The parameters of the method '" + name
							+ "' are null.", e);

		}

		return null;
	}

	/**
	 * @author Juliana Hohara de S. Coelho \brief Invokes all the listeners of a
	 *         certain event
	 * @param listeners
	 *            Listener to be invoked
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */

	public void forInvoke(String nameMethodClass)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {

		Object[] aux = new Object[5];

		Iterator<Object[]> it = listeners.iterator();

		while (it.hasNext()) {

			aux = it.next();

			if (aux[4].equals(nameMethodClass)) {

				Method nomeMetodo = (Method) aux[2];
				Object obj = aux[3];
				Object[] pContem = (Object[]) aux[1];

				nomeMetodo.invoke(obj, pContem);

			}
		}

	}

	/**
	 * @author Juliana Hohara de S. Coelho \brief Gets the instance of a class
	 *         that has gone through reflection
	 * @param cl
	 *            Name Class
	 * @return The instance of the class
	 */

	private Object getInstanceOfClass(@SuppressWarnings("rawtypes") Class cl) {

		if (object.isEmpty() == false) {

			for (Object o : object) {

				if (isInstance(o, cl))
					return o;
			}

		}

		return null;

	}

	/**
	 * @author Juliana Hohara de S. Coelho \brief Class will be checked before
	 *         it was used for reflection
	 * @param aux
	 *            Class will be checked before it was used for reflection
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	private boolean isClass(Class aux) {

		if (cla.isEmpty() == false) {

			for (Class c : cla) {

				String a = c.getName();
				String b = aux.getName();

				if (a == b)
					return true;
			}

		}

		return false;
	}

	/**
	 * @author Juliana Hohara de S. Coelho \brief Method that compares if the
	 *         object is an instance of a class specifies
	 * @param aux
	 *            Object class
	 * @param c
	 *            Name class
	 * @return True - The object is an instance of the class False - The object
	 *         is not an instance of the class
	 */

	@SuppressWarnings("rawtypes")
	private boolean isInstance(Object aux, Class c) {

		String a = aux.getClass().getName();

		String b = c.getName();

		if (b == a)
			return true;

		return false;

	}

	/**
	 * @author Juliana Hohara de S. Coelho \brief Using reflection, a method is
	 *         passed as parameter, and will be invoked later on one particular
	 *         event
	 * @param instance
	 *            object class to which the method you want to
	 * @param nomeMetodo
	 *            method name you want to run on a specific event
	 * @param caminhoNomeClasse
	 *            path where the class to which the desired method belongs
	 * @param parametrosMetodo
	 *            parameters of the desired method
	 * @throws GlueClientException
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object[] methodReflection(String nameMethodClass, Object instance,
			String nameMethod, String caminhoNomeClasse,
			Object... parametrosMetodo) throws GlueClientException {

		Object[] aux = new Object[5];

		Method o = null;
		Object obj;
		Object obje = null;
		Class[] pvec = null;
		Object[] pContem;
		Class cls = null;

		Method[] method = null;

		// Fazer consistencia caso o construtor da classe tenha argumentos!

		try {

			if (caminhoNomeClasse != null) {

				// obt�m o nome da classe
				cls = Class.forName(caminhoNomeClasse);

			} else {

				// obt�m o nome da classe
				String name = instance.getClass().getName();

				cls = Class.forName(name);

			}

			// obt�m os m�todos que existem na classe
			method = cls.getDeclaredMethods();

			// caso n�o tenha passado a inst�ncia da classe como argumento do
			// m�todo
			if ((instance == null) && (isClass(cls) == false)) {

				cla.add(cls);

				// obt�m o construtor da classe

				Constructor c = cls.getConstructor();
				obje = c.newInstance();
				object.add(obje);

			} else if ((instance == null) && (isClass(cls) == true)) {

				obje = getInstanceOfClass(cls);

			} else {

				obje = instance;
				if (getInstanceOfClass(cls) == null) {

					object.add(obje);
					cla.add(cls);

				}

			}

			o = findMethod(nameMethod, method, parametrosMetodo);

			try {

				pvec = o.getParameterTypes();
				aux[0] = pvec;

			} catch (NullPointerException e) {

				throw new GlueClientException(
						"methodReflection() - The parameters of the method '"
								+ nameMethod + "' are null.", e);
			}

			pContem = parametrosMetodo;
			aux[1] = pContem;

			Method something = obje.getClass().getDeclaredMethod(nameMethod,
					pvec);
			aux[2] = something;

			obj = obje;
			aux[3] = obj;

			aux[4] = nameMethodClass;

		} catch (SecurityException e) {

			throw new GlueClientException("methodReflection()", e);

		} catch (NoSuchMethodException e) {
			throw new GlueClientException(
					"methodReflection() - Method Not Found: Check the name, or the address(package)",
					e);

		} catch (Exception e) {

			throw new GlueClientException("methodReflection()", e);

		}

		return aux;

	}

	/**
	 * @author Juliana Hohara de S. Coelho \brief Using reflection method, a
	 *         method is passed as parameter, and will be invoked later on one
	 *         particular event
	 * @param instance
	 *            object class to which the method you want to
	 * @param nomeMetodo
	 *            method name you want to run on a specific event
	 * @param caminhoNomeClasse
	 *            path where the class to which the desired method belongs
	 * @param parametrosMetodo
	 *            parameters of the desired method
	 * @throws GlueClientException
	 */

	public Object[] reflectionObjects(String nameMethodClass, Object instance,
			String nameMethod, String pathNameClass, Object... paramsMethod)
			throws GlueClientException {

		Object[] listeners = new Object[5];

		listeners = methodReflection(nameMethodClass, instance, nameMethod,
				pathNameClass, paramsMethod);

		return listeners;

	}

	/**
	 * 
	 * @author Juliana Hohara de S. Coelho
	 * 
	 *         \brief Remove all the methods of the list that would be invoked
	 *         by an event listener class.
	 * 
	 * @return
	 * @throws GlueClientException
	 */

	public boolean removeAll() throws GlueClientException {

		try {

			if (listeners.size() > 0) {

				listeners.clear();
				return true;

			} else {
				System.out.println("Lista de listeners vazia!");
				return false;
			}

		} catch (Exception e) {

			throw new GlueClientException(
					"removeAll() - N�o foi poss�vel remover os m�todos da lista.",
					e);
		}

	}

	/**
	 * 
	 * @author Juliana Hohara de S. Coelho
	 * 
	 *         \brief Remove from the list the method that would be invoked on a
	 *         specific event.
	 * 
	 * @param nameMethodClass
	 *            Name of method that belongs to a class Listener
	 * @param instance
	 *            Instance of the class to which the method belongs to be
	 *            removed
	 * @param nameMethod
	 *            Name of method to be removed
	 * @param pathNameClass
	 *            Path of the class to which the method belongs to be removed
	 * @param paramsMethod
	 *            Parameters of the method to be removed
	 * @return true - Successfully removed false - Unable to remove
	 * @throws GlueClientException
	 */

	@SuppressWarnings("rawtypes")
	public boolean removeListener(String nameMethodClass, Object instance,
			String nameMethod, String pathNameClass, Object... paramsMethod)
			throws GlueClientException {

		try {

			Object[] listener = reflectionObjects(nameMethodClass, instance,
					nameMethod, pathNameClass, paramsMethod);

			Iterator it = listeners.iterator();

			Method nomeMetodo1 = (Method) listener[2];

			while (it.hasNext()) {

				Object[] aux = (Object[]) it.next();

				Method nomeMetodo2 = (Method) aux[2];

				if (nomeMetodo1.equals(nomeMetodo2)) {

					it.remove();
				}
			}

			return true;

		} catch (Exception e) {

			throw new GlueClientException("removeListener()", e);

		}

	}
}
