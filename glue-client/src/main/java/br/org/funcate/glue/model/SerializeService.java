package br.org.funcate.glue.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class SerializeService {
	
	public static byte[] serialize(java.lang.Object obj) throws RuntimeException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(out);
			os.writeObject(obj);
			return out.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new RuntimeException();
	}
	
	public static java.lang.Object deserialize(byte[] data) throws RuntimeException {
	    ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is;
		try {
			is = new ObjectInputStream(in);
			return (Object) is.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new RuntimeException();
	}
}
