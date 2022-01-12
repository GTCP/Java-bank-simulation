package principal;

public class SimularBanco {
	
	public void crearYAsignarClientes(int cantClientes, int cantCuentas, Banco banco) {
		
		int dniBase = 38674516;
		String nombreBase = "Alfred";
		boolean monotributista = true;
		
		for (int i = 0; i < cantClientes; i++) {
			Cliente cliente = banco.crearCliente(dniBase+i, nombreBase+i, monotributista);
			banco.altaCliente(cliente);
			for (int j = 0; j < cantCuentas; j++) {
				banco.abrirCuenta(cliente);
			}
			monotributista = !monotributista;
		}
		
	}
	
}
