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

	public boolean almacenar(Mensaje mensaje) {
		// TODO Auto-generated method stub
		if(--tamano < 0)
		{
			return false;
		}
		else
		{
			mensajes.add(mensaje);
			return true;
		}
	}
	
	public synchronized Mensaje retirar()
	{
		while(tamano > 0)
		{
			
		}
		int aleatorio = ( (int) Math.random() * mensajes.size() );
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
