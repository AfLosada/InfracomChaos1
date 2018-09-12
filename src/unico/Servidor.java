package unico;

public class Servidor extends Thread
{
	private Mensaje mensaje;
	private static Buffer buff;

	public Servidor(Buffer pBuff)
	{
		mensaje = null;
		buff = pBuff;
	}

	public void run()
	{
		while(buff.mensajes.size() > 0 || buff.numClientes > 0 )
		{
			while(buff.mensajes.size() == 0)
			{
				yield();
			}
			if(buff.mensajes.size()> 0 )
			{
				mensaje = buff.pedir();
				procesarMensaje(mensaje);
			}						
		}

	}

	public void procesarMensaje(Mensaje mensaje)
	{
		System.out.println("comienza a procesar el mensaje" + mensaje.getMensaje());
		mensaje.setMensaje(mensaje.getMensaje()+1);
		buff.finalizacion = buff.finalizacion +1;
		System.out.println(" el numero es  " + buff.finalizacion);
		synchronized(mensaje)
		{
			mensaje.notify();
			System.out.println("notifica");
		}
	}
}
