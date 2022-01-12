package principal;

public class Banco {
	
	private final int MAX_CLIENTES = 20;
	private final int MAX_CUENTAS = 1000;
	
	private Cliente[] clientes = new Cliente[MAX_CLIENTES];
	private Cuenta[] cuentas = new Cuenta[MAX_CUENTAS];
	
	private int cantidadClientesExistentes = 0; 
	
	public Banco() {
		inicializarCuentas();
	}
	
	public Cliente crearCliente(int dni, String nombreYApellido, boolean esMonotributista) {
		Cliente cliente = new Cliente();
		cliente.setDni(dni);
		cliente.setNombreYApellido(nombreYApellido);
		cliente.setMonotributista(esMonotributista);
		return cliente;
	}
	
	public void altaCliente(Cliente cliente) {
		clientes[cantidadClientesExistentes] = cliente;
		cantidadClientesExistentes++;
	}
	
	public void inicializarCuentas() {
		for (int i = 0; i < MAX_CUENTAS; i++) {
			cuentas[i] = new Cuenta();
			cuentas[i].setNumero(i);
			cuentas[i].setMonto(0);
			cuentas[i].asignarCliente(null);
		}
	}
	
	public Cuenta abrirCuenta(Cliente cliente) {
		int nroCuenta = 0;
		while(cuentas[nroCuenta].obtenerCliente() != null) {
			nroCuenta++;
		}
		cuentas[nroCuenta].asignarCliente(cliente);
		return cuentas[nroCuenta];
	}
	
	public boolean cerrarCuenta(int nroCuenta, int dniCliente) {
		//comprobar si es de este cliente
		if(cuentas[nroCuenta].obtenerCliente().getDni() == dniCliente) {
			double dineroAnterior = cuentas[nroCuenta].getMonto();
			// mi cuenta de banco:
			//cuentas[00000].addMonto(dineroAnterior);
			cuentas[nroCuenta].asignarCliente(null);
			cuentas[nroCuenta].setMonto(0);
			cuentas[nroCuenta].vaciarMovimientos();
			return true;
		} else {
			System.out.println("Esta cuenta no es de este cliente");
			return false;
		}
	}
	
	public Cuenta obtenerCuenta(int numeroCuenta) {
		if(numeroCuenta > 0) {
			return cuentas[numeroCuenta-1];
		} else {
			return null;
		}
	}
	
	public boolean depositar(int dni, int nroCuenta, double monto) {
		if(cuentas[nroCuenta].obtenerCliente().getDni() != dni) {
			System.out.println("Esta cuenta no pertenece a este cliente");
			return false;
		}
		if(monto <= 0) {
			System.out.println("El monto a depositar debe ser mayor a 0");
			return false;
		}
		cuentas[nroCuenta].realizarDeposito(monto);
		return true;	
	}
	
	public boolean retirar(int dni, int nroCuenta, double monto) {
		if(cuentas[nroCuenta].obtenerCliente().getDni() != dni) {
			System.out.println("Esta cuenta no pertenece a este cliente");
			return false;
		}
		if(monto <= 0) {
			System.out.println("El monto a retirar debe ser mayor a 0");
			return false;
		}
		return cuentas[nroCuenta].realizarRetiro(monto);
	}
	
	// acá se podría devolver un comprobante
	public boolean transferencia(int nroCuentaOrigen, int dniOrigen, int nroCuentaDestino, double monto) {
		if(cuentas[nroCuentaOrigen].obtenerCliente().getDni() != dniOrigen) {
			System.out.println("Esta cuenta no pertenece a este cliente");
			return false;
		}
		boolean operacionRealizada = cuentas[nroCuentaOrigen].retiroPorTransferencia(monto);
		if(operacionRealizada == true) {
			cuentas[nroCuentaDestino].ingresoPorTransferencia(monto, cuentas[nroCuentaOrigen]);
			return true;
		} else {
			return false;
		}
	}
	
	public void mostrarClientes() {
		System.out.println("========");
		System.out.println("Clientes del banco");
		for (int i = 0; i < MAX_CLIENTES; i++) {
			if(clientes[i] != null) {
				String monotributista;
				if(clientes[i].esMonotributista())
					monotributista = "Si";
				else
					monotributista = "no";
				System.out.println("> :"+clientes[i].getDni()+" - "+clientes[i].getNombreYApellido()+" Monotributista "+monotributista);
			}
		}
	}
	
	public void mostrarCuentasDeCliente(int dniCliente) {
		System.out.println("========");
		System.out.println("Cuentas del Cliente: "+dniCliente);
		for (int i = 0; i < MAX_CUENTAS; i++) {
			if(cuentas[i].obtenerCliente() != null && cuentas[i].obtenerCliente().getDni() == dniCliente) {
				System.out.println("> ["+cuentas[i].getNumero()+"]");
			}
		}
	}
	
	public void mostrarDetallesCuenta(int nroCuenta, int dni) {
		System.out.println("========");
		Cuenta cuentaMostrar = cuentas[nroCuenta];
		if(cuentaMostrar.obtenerCliente().getDni() == dni) {
			System.out.println("Nro Cuenta: "+cuentaMostrar.getNumero());
			System.out.println("Monto: "+cuentaMostrar.getMonto());
			System.out.println("Movimientos:");
			cuentaMostrar.mostrarMovimientos();
		} else {
			System.out.println("Esta cuenta no corresponde a este cliente");
		}
	}
	
}
