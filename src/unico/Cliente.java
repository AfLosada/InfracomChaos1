package unico;

public class Cliente extends Thread {
	private Mensaje mensaje;
	private static Buffer buff;

	public Cliente( Buffer pBuff) {
		mensaje = new Mensaje();
		buff = pBuff;
	}

	@Override
	public void run()
	{
		buff.almacenar(mensaje);
	}
}
