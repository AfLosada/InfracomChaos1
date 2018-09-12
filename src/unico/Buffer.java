package unico;

import java.util.ArrayList;

public class Buffer{
	
	public int tamano;
	public ArrayList<Mensaje> mensajes;
	public static int numClientes;
	public int numServidores;
	public static int finalizacion; 
	
	public Buffer(int pTamano, int pNumClientes, int pnumServidores)
	{
		tamano = pTamano;
		mensajes = new ArrayList<Mensaje>();
		numClientes = pNumClientes;
		numServidores = pnumServidores;
	}

	public synchronized void almacenar(Mensaje mensaje)
	{
			mensajes.add(mensaje);
			tamano = tamano -1;
			
	}

	public synchronized Mensaje pedir()
	{
		Mensaje m = mensajes.get(0);
		mensajes.remove(0);
		tamano = tamano+ 1;
		return m;
		
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
		
		Buffer buffer = new Buffer(5, 8, 2);
		System.out.println("Se creo el buffer con tamano " + buffer.tamano+" , num Clientes de "+ buffer.numClientes+ " y con " + buffer.numServidores+" servidores");
		for( int i = 0; i < buffer.numClientes ; i++)
		{
			Cliente temp = new Cliente(buffer, 5 );
			System.out.println("se creo el cliente "+(i+1) + " con "+ temp.getMensajes().size()+" mensajes");
			(new Thread(temp)).start();
			
			System.out.println("el tamano del buffer es " + buffer.tamano);
		}
		for (int i = 0; i < buffer.numServidores; i++) {
			Servidor serv = new Servidor(buffer);
			System.out.println("se creo el servidor "+(i+1) );

			(new Thread(serv)).start();
		}
		
		while (numClientes != 0)
				{
			
				}
		
		
		System.out.println("Al final el numero de clientes es "+ buffer.numClientes);
		
	}
}
