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
		while(buff.mensajes.length > 0 || buff.numClientes > 0 )
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
		mensaje.setMensaje(mensaje.getMensaje()+1);
		
		synchronized(mensaje)
		{
			mensaje.notify();
		}
	}
}
