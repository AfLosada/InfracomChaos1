package unico;

public class Cliente extends Thread {
	private Mensaje mensaje;
	private static Buffer buff;

	public Cliente(Mensaje pMensaje, Buffer pBuff) {
		mensaje = pMensaje;
		buff = pBuff;
	}

	@Override
	public void run()
	{
		buff.almacenar(mensaje);
	}
}
