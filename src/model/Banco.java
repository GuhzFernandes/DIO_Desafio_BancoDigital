package model;

import model.conta.Conta;
import model.conta.ContaCorrente;
import model.conta.ContaPoupanca;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Banco {

    private String nome;
    private List<Conta> contas;
    private HashSet<Cliente> clientes;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<Conta>();
        this.clientes = new HashSet<Cliente>();
    }

    private Cliente procuraCliente(int numeroDocumento) {
        return clientes.stream()
                .filter(x -> x.getNumeroDocumento() == numeroDocumento)
                .findFirst()
                .orElse(null);
    }
    
    public void novoCliente(String nome, int numeroDocumento){
        this.clientes.add(new Cliente(nome, numeroDocumento));
    }

    public void novaContaPoupanca(int numeroDocumentoCliente){
        Cliente cliente = procuraCliente(numeroDocumentoCliente);
        if (cliente == null) System.out.println("model.Cliente não cadastrado no sistema");
        else this.contas.add(new ContaPoupanca(cliente));
    }

    public void novaContaCorrente(int numeroDocumentoCliente){
        Cliente cliente = procuraCliente(numeroDocumentoCliente);
        if (cliente == null) System.out.println("model.Cliente não cadastrado no sistema");
        else this.contas.add(new ContaCorrente(cliente));
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

   private List<Conta> getContas() {
        return this.contas;
    }

    public HashSet<Cliente> getClientes() {
        return this.clientes;
    }

    public List<Conta> getContaPorCliente( int numeroDocumentoCliente) {
        List<Conta> contasCliente = null;
        return getContas().stream()
                .filter(x-> x.getCliente().equals(procuraCliente(numeroDocumentoCliente)))
                .toList();
    }

}
