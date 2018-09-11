package unico;

import java.util.ArrayList;

public class Buffer extends Thread{
	
	private int tamano;
	private ArrayList<Mensaje> mensajes;
	private int numClientes;
	
	public Buffer(int pTamano, int pNumClientes)
	{
		tamano = pTamano;
		mensajes = new ArrayList<Mensaje>();
		numClientes = pNumClientes;
	}

	public synchronized void almacenar(Mensaje mensaje) {
		// TODO Auto-generated method stub
		tamano--;
		while(tamano < 0)
		{
			yield();
		}
		mensajes.add(mensaje);
		try {
			wait();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void retirar()
	{
		while(tamano > 0)
		{
			yield();
		}
		mensajes.remove( ((int) Math.random() * mensajes.size() ) );
		tamano++;
		notify();
	}

}
