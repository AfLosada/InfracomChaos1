package unico;

public class Mensaje {

	private int mensaje;
	private Cliente cliente;
	
	
	public Mensaje(int men, Cliente cliente)
	{
		//int men = (int) (Math.random()*10);
		this.cliente = cliente;
		mensaje = men;
	}
	
	/**
	 * @return the mensaje
	 */
	public int getMensaje() 
	{
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(int mensaje) 
	{
		this.mensaje = mensaje;
	}

	public Cliente getCliente() 
	{
		return cliente;
	}


	public void setCliente(Cliente cliente) 
	{
		this.cliente = cliente;
	}
	
	
	
	
	
}
