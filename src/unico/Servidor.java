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
		while(buff.numClientes > 0)
		mensaje = buff.retirar();
		
		procesarMensaje(mensaje);
	}
	
	public void procesarMensaje(Mensaje mensaje)
	{
		mensaje.setMensaje(mensaje.getMensaje()+1);
	}
}
