package cli;

import java.util.List;
import java.util.Scanner;

import model.Banco;
import model.conta.*;

public class Terminal {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Banco banco = new model.Banco("DIO Bank");

    public static void main() {
        boolean executando = true;

        while (executando) {
            System.out.println(String.format("\n=== %s TERMINAL ===", banco.getNome().toUpperCase()));
            System.out.println("1. Criar novo cliente");
            System.out.println("2. Criar nova conta");
            System.out.println("3. Listar contas de um cliente");
            System.out.println("4. Depositar");
            System.out.println("5. Sacar");
            System.out.println("6. Transferir");
            System.out.println("7. Imprimir extratos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> criarCliente();
                case 2 -> criarConta();
                case 3 -> listarContas();
                case 4 -> depositar();
                case 5 -> sacar();
                case 6 -> transferir();
                case 7 -> imprimirExtratos();
                case 0 -> {
                    System.out.println("Encerrando terminal...");
                    executando = false;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void criarCliente() {
        System.out.print("Nome do cliente: ");
        scanner.nextLine(); // limpar buffer
        String nome = scanner.nextLine();
        System.out.print("Número do documento: ");
        int doc = scanner.nextInt();
        banco.novoCliente(nome, doc);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void criarConta() {
        System.out.print("Número do documento do cliente: ");
        int doc = scanner.nextInt();
        System.out.print("Tipo de conta (1 - Corrente | 2 - Poupança): ");
        int tipo = scanner.nextInt();

        if (tipo == 1) banco.novaContaCorrente(doc);
        else if (tipo == 2) banco.novaContaPoupanca(doc);
        else System.out.println("Tipo inválido.");

        System.out.println("Conta criada com sucesso!");
    }

    private static void listarContas() {
        System.out.print("Número do documento do cliente: ");
        int doc = scanner.nextInt();
        List<Conta> contas = banco.getContaPorCliente(doc);

        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta encontrada.");
            return;
        }

        for (Conta conta : contas) {
            System.out.println("Conta: " + conta.getNumero() + " | Tipo: " + (conta instanceof ContaCorrente ? "Corrente" : "Poupança"));
        }
    }

    private static Conta buscarConta(String acao) {
        System.out.print("Número do documento do cliente: ");
        int doc = scanner.nextInt();
        List<Conta> contas = banco.getContaPorCliente(doc);

        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta encontrada.");
            return null;
        }

        System.out.println("Selecione a conta para " + acao + ":");
        for (int i = 0; i < contas.size(); i++) {
            Conta c = contas.get(i);
            System.out.printf("[%d] Conta %d (%s)%n", i + 1, c.getNumero(), c instanceof ContaCorrente ? "Corrente" : "Poupança");
        }

        System.out.print("Escolha: ");
        int escolha = scanner.nextInt();

        if (escolha < 1 || escolha > contas.size()) {
            System.out.println("Opção inválida.");
            return null;
        }

        return contas.get(escolha - 1);
    }

    private static void depositar() {
        Conta conta = buscarConta("depósito");
        if (conta == null) return;
        System.out.print("Valor do depósito: ");
        double valor = scanner.nextDouble();
        conta.depositar(valor);
        System.out.println("Depósito realizado!");
    }

    private static void sacar() {
        Conta conta = buscarConta("saque");
        if (conta == null) return;
        System.out.print("Valor do saque: ");
        double valor = scanner.nextDouble();
        conta.sacar(valor);
        System.out.println("Saque realizado!");
    }

    private static void transferir() {
        System.out.println("Conta origem:");
        Conta origem = buscarConta("transferência");
        if (origem == null) return;

        System.out.println("Conta destino:");
        Conta destino = buscarConta("transferência");
        if (destino == null) return;

        System.out.print("Valor da transferência: ");
        double valor = scanner.nextDouble();
        origem.transferir(valor, destino);
        System.out.println("Transferência realizada!");
    }

    private static void imprimirExtratos() {
        Conta conta = buscarConta("visualizar extrato");
        if (conta != null) conta.imprimirExtrato();
    }
}
