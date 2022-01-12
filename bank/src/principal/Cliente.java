package principal;

public class Cliente {
	
	private String nombreYApellido;
	private int dni;
	private boolean monotributista;
	
	public String getNombreYApellido() {
		return nombreYApellido;
	}
	
	public void setNombreYApellido(String nombreYApellido) {
		this.nombreYApellido = nombreYApellido;
	}
	
	public int getDni() {
		return dni;
	}
	
	public void setDni(int dni) {
		this.dni = dni;
	}

	public boolean esMonotributista() {
		return monotributista;
	}

	public void setMonotributista(boolean monotributista) {
		this.monotributista = monotributista;
	}
	

}
