package unico;

public class Mensaje {

	private int mensaje;
	
	
	public Mensaje()
	{
		int men = (int) (Math.random()*10);
		mensaje = men;
	}
	

	/**
	 * @return the mensaje
	 */
	public int getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(int mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
	
	
}
