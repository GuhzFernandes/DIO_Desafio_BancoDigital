package model;

import java.util.Objects;

public class Cliente {

    private String nome;
    private int numeroDocumento;
    private int numeroContato;

    public Cliente(String nome, int numeroDocumento) {
        this.nome = nome;
        this.numeroDocumento = numeroDocumento;
    }

    public String getNome() {
        return nome;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public int getNumeroContato() {
        return numeroContato;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumeroContato(int numeroContato) {
        this.numeroContato = numeroContato;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return numeroDocumento == cliente.numeroDocumento;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroDocumento);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "model.Cliente{" +
                "nome='" + nome + '\'' +
                ", numeroDocumento=" + numeroDocumento +
                ", numeroContato=" + numeroContato +
                '}';
    }
}
