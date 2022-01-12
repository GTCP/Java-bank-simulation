package principal;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Banco banco = new Banco();
		
		SimularBanco sb = new SimularBanco();
		sb.crearYAsignarClientes(7, 4, banco);
		
		banco.mostrarClientes();
		
		banco.mostrarCuentasDeCliente(38674517);
		banco.mostrarDetallesCuenta(5, 38674517);
		banco.depositar(38674517, 5, 700);
		banco.mostrarDetallesCuenta(5, 38674517);
		
		banco.mostrarCuentasDeCliente(38674518);
		banco.mostrarDetallesCuenta(8, 38674518);
		banco.depositar(38674518, 8, 577);
		banco.mostrarDetallesCuenta(8, 38674518);
		
		banco.retirar(38674518, 8, 566);
		banco.retirar(38674518, 8, 50);
		banco.mostrarDetallesCuenta(8, 38674518);
		
		banco.transferencia(5, 38674517, 8, 200);
		banco.mostrarDetallesCuenta(5, 38674517);
		banco.mostrarDetallesCuenta(8, 38674518);
		
		
		banco.transferencia(8, 38674518, 7, 701.46);
		banco.mostrarDetallesCuenta(5, 38674517);
		banco.mostrarDetallesCuenta(7, 38674517);
		banco.mostrarDetallesCuenta(8, 38674518);
		
		banco.transferencia(7, 38674517, 9, 500);
		banco.transferencia(9, 38674518, 8, 300);
		banco.mostrarDetallesCuenta(7, 38674517);
		banco.mostrarDetallesCuenta(8, 38674518);
		banco.mostrarDetallesCuenta(9, 38674518);
		
//		Cliente cliente = banco.crearCliente(dniCliente, "Alexander E.", false);
//		banco.altaCliente(cliente);
//		Cuenta cuenta = banco.abrirCuenta(cliente);
//		
//		banco.transferencia(cuenta.getNumero(), dniCliente, 789789, 15.5);
//		
//		do {
//			// para que esto no sea un bucle infinito hay que cambiar los valores
//			operacionRealizadaCorrectamente = banco.cerrarCuenta(cuenta.getNumero(), dniCliente);
//		} while(!operacionRealizadaCorrectamente);
	}

}
