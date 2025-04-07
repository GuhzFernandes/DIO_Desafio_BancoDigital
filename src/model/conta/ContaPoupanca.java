package model.conta;

import model.Cliente;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato model.conta.Conta Poupan�a ===");
        super.imprimirInfosComuns();
    }
}
