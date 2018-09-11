package unico;

import java.util.ArrayList;

public class Buffer{
	
	private int tamano;
	private ArrayList<Mensaje> mensajes;
	public int numClientes;
	public int numServi;
	
	public Buffer(int pTamano, int pNumClientes, int pNumServi)
	{
		tamano = pTamano;
		mensajes = new ArrayList<Mensaje>();
		numClientes = pNumClientes;
		numServi = pNumServi;
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
	
	public synchronized Mensaje retirar()
	{
		while(tamano > 0)
		{
			yield();
		}
		int aleatorio = ((int) Math.random() * mensajes.size() );
		Mensaje rta = mensajes.get(aleatorio);
		mensajes.remove( aleatorio );
		tamano++;
		notify();
		return rta;
	}
	
	public static void main(String[] args) {
		
		Buffer buffer = new Buffer(5, 2);
		for( int i = 0; i < buffer.numClientes ; i++)
		{
			Cliente temp = new Cliente(new Mensaje(i), buffer);
			(new Thread(temp)).start();
		}
		
		
		
	}
	
	

}
