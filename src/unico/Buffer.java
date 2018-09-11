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
			System.out.println("El cliente con id:  " + this.getId() + " intentó almacenar un mensaje: " + mensaje.getMensaje());
			yield();
		}
		System.out.println("El cliente con id:  " + this.getId() + " pudo almacenar un mensaje: " + mensaje.getMensaje());
		mensajes.add(mensaje);
		try {
			wait();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("El cliente con id:  " + this.getId() + " fue despertado por el servidor");
	}
	
	public synchronized Mensaje retirar()
	{
		while(tamano > 0)
		{
			System.out.println("El servidor con id:  " + this.getId() + " intentó retirar un mensaje");
			yield();
		}
		int aleatorio = ((int) Math.random() * mensajes.size() );
		Mensaje rta = mensajes.get(aleatorio);
		mensajes.remove( aleatorio );
		System.out.println("El servidor con id:  " + this.getId() + " retiró un mensaje con id: " + rta.getMensaje());
		tamano++;
		notify();
		System.out.println("El servidor con id:  " + this.getId() + " despertó a algún cliente");
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
