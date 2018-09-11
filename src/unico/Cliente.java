package unico;

import java.util.ArrayList;

public class Cliente extends Thread {
	private ArrayList<Mensaje> mensajes;
	private static Buffer buff;

	public Cliente( Buffer pBuff, int tamano) {

		mensajes = new ArrayList<Mensaje>();
		for (int i = 0; i < tamano; i++) {
			mensajes.add(new Mensaje(i));
		}
		buff = pBuff;
	}

	public ArrayList<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(ArrayList<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public static Buffer getBuff() {
		return buff;
	}

	public static void setBuff(Buffer buff) {
		Cliente.buff = buff;
	}

	@Override
	public void run()
	{
		for (int i = 0; i < mensajes.size(); i++)
		{
			buff.almacenar(mensajes.get(i));
		}
		
	}
	
	
	public static void main(String[] args) {
		
		Cliente cliente = new Cliente(null,4);
		System.out.println(cliente.mensajes.size());
		System.out.println(cliente.mensajes.get(0).getMensaje());
		System.out.println(cliente.mensajes.get(1).getMensaje());
		System.out.println(cliente.mensajes.get(2).getMensaje());

	}
}
