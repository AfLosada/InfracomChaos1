package unico;

public class Cliente extends Thread {
	private Mensaje mensaje;
	private static Buffer buff;

	public Cliente(Mensaje pMensaje) {
		mensaje = pMensaje;
	}

	@Override
	public void run()
	{
		buff.almacenar(mensaje);
	}
}
