package principal;

public class Cuenta {
	
	private final int MAX_MOVIMIENTOS = 10;
	
	private int numero;
	private Cliente cliente;
	private double monto;
	private Movimiento[] movimientos = new Movimiento[MAX_MOVIMIENTOS];
	
	private void calcularIIBB(double montoIngreso) {
		// calcular 2%
		double iibb = montoIngreso * 2 / 100;
		monto -= iibb;
		
		Movimiento mov = new Movimiento();
		mov.setTipo(3);
		mov.setOperacion("Ingresos Brutos");
		mov.setMonto(iibb);
		guardarMovimiento(mov);
	}
	
	public void mostrarMovimientos() {
		for (int i = 0; i < MAX_MOVIMIENTOS; i++) {
			if(movimientos[i] != null)
				System.out.println("Tipo: "+movimientos[i].getTipo()+" - Operacion: "+movimientos[i].getOperacion()+" - Monto: "+movimientos[i].getMonto());
		}
	}
	
	public void realizarDeposito(double montoDeposito) {
		monto += montoDeposito;
		
		Movimiento mov = new Movimiento();
		mov.setTipo(1);
		mov.setOperacion("Deposito");
		mov.setMonto(montoDeposito);
		guardarMovimiento(mov);
		
		// TODO: realizar movimiento
		if(cliente.esMonotributista())
			calcularIIBB(montoDeposito);
	}
	
	public boolean retiroPorTransferencia(double montoRetiro) {
		if(monto > montoRetiro) {
			monto -= montoRetiro;
			
			Movimiento mov = new Movimiento();
			mov.setTipo(4);
			mov.setOperacion("Egreso por transferencia");
			mov.setMonto(montoRetiro);
			guardarMovimiento(mov);
			
			return true;
		} else {
			System.out.println("No puedes transferir más dinero del que tienes");
			return false;
		}
	}
	
	public void ingresoPorTransferencia(double montoIngreso, Cuenta origen) {
		
		monto += montoIngreso;
		
		Movimiento mov = new Movimiento();
		mov.setTipo(2);
		mov.setOperacion("Ingreso por transferencia");
		mov.setMonto(montoIngreso);
		guardarMovimiento(mov);
		
		if(cliente.esMonotributista() && origen.obtenerCliente().getDni() != cliente.getDni())
			calcularIIBB(montoIngreso);
	}
	
	public boolean realizarRetiro(double montoRetiro) {
		if(monto > montoRetiro) {
			monto -= montoRetiro;
			
			Movimiento mov = new Movimiento();
			mov.setTipo(5);
			mov.setOperacion("Retiro");
			mov.setMonto(montoRetiro);
			guardarMovimiento(mov);
			
			return true;
		} else {
			System.out.println("No puedes retirar más dinero del que tienes");
			return false;
		}
	}
	
	private void guardarMovimiento(Movimiento movimiento) {
		for(int i = MAX_MOVIMIENTOS-1; i>0; i--) {
			movimientos[i] = movimientos[i-1];
		}
		movimientos[0] = movimiento;
	}
	
	public Movimiento[] obtenerMovimientos() {
		return movimientos;
	}
	
	public void vaciarMovimientos() {
		movimientos = null;
	}

	public Cliente obtenerCliente() {
		return cliente;
	}

	public void asignarCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	
	
}
