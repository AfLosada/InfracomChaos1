
package unico;
import java.util.ArrayList;

public class Cliente extends Thread {
	private ArrayList<Mensaje> mensajes;
	private static Buffer buff;
	

	public Cliente( Buffer pBuff, int tamano) {

		mensajes = new ArrayList<Mensaje>();
		for (int i = 0; i < tamano; i++) {
			mensajes.add(new Mensaje(i,this));
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
		for (int i = 0; i< mensajes.size(); i++)
		{	
			Mensaje m = mensajes.get(i);
			almacenar(m);
			System.out.println("El cliente almaceno el mensaje " + i);
			
		}
		
		 buff.numClientes= buff.numClientes -1;	
		 System.out.println("el numero de clientes es " +buff.numClientes); 
	}
	
	public synchronized void  almacenar(Mensaje mensaje)
	{
		while (buff.tamano <= 0)
		{
			yield();
		}
		buff.almacenar(mensaje);
		synchronized(mensaje)
		{
			try {
				
				mensaje.wait();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
