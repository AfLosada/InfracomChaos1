package unico;

public class Servidor extends Thread
{
	private Mensaje mensaje;
	private static Buffer buff;
	
	public Servidor( Mensaje pMen, Buffer pBuff)
	{
		mensaje = pMen;
		buff = pBuff;
	}
	
	public void run()
	{
		while(buff.numClientes > 0)
		mensaje = buff.retirar();
	}
}
