package model.conta;

import model.Cliente;

public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato model.conta.Conta Corrente ===");
        super.imprimirInfosComuns();
    }

}
