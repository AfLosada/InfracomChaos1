package unico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
			tamano = tamano - 1;
			
	}

	public synchronized Mensaje pedir()
	{
		Mensaje m = mensajes.get(0);
		mensajes.remove(0);
		tamano = tamano+ 1;
		return m;
		
	}
	


	
	
	public static void main(String[] args) throws IOException {
		
		File archivo = new File("datos.txt");
		BufferedReader bf = new BufferedReader(new FileReader(archivo));
		String linea = bf.readLine();
		String[] chars = linea.split(" ");
		int numCli = Integer.parseInt(chars[0]);
		int numSer = Integer.parseInt(chars[1]);
		int tamano = Integer.parseInt(chars[2]);
		Buffer buffer = new Buffer(tamano, numCli, numSer);
		System.out.println("Se creo el buffer con tamano " + buffer.tamano+" , num Clientes de "+ buffer.numClientes+ " y con " + buffer.numServidores+" servidores");
		int temp = 0;
		for (int i = 0; i < buffer.numClientes; i++)
		{
			linea = bf.readLine();
			temp = Integer.parseInt(linea);
			Cliente nuevo = new Cliente(buffer, temp );
			System.out.println("se creo el cliente "+(i+1) + " con "+ nuevo.getMensajes().size()+" mensajes");
			(new Thread(nuevo)).start();
			
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
