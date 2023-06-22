import java.util.Scanner;

public class lanche {
    private static final int MAX_PEDIDOS = 10;
    private static Pedido[] pedidos = new Pedido[MAX_PEDIDOS];
    private static int numPedidos = 0;
    private static double faturamentoTotal = 0;
    private static int pedidosAtendidos = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    incluirPedido();
                    break;
                case 2:
                    atenderPedido();
                    break;
                case 3:
                    listarPedidos();
                    break;
                case 4:
                    pesquisarPedido();
                    break;
                case 5:
                    encerrar();
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
            }
        } while (opcao != 5 || numPedidos > 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("###### LANCHONETE ######");
        System.out.println("# 1 - INCLUIR PEDIDO     #");
        System.out.println("# 2 - ATENDER PEDIDO     #");
        System.out.println("# 3 - LISTAR PEDIDOS     #");
        System.out.println("# 4 - PESQUISAR PEDIDO   #");
        System.out.println("# 5 - ENCERRAR           #");
        System.out.println("#########################");
        System.out.print("Digite a opção desejada: ");
    }

    private static void incluirPedido() {
        if (numPedidos == MAX_PEDIDOS) {
            System.out.println("Fila Cheia – Não Pode Mais Incluir Pedidos");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Pedido pedido = new Pedido();

        System.out.print("Número do pedido: ");
        pedido.setNumeroPedido(scanner.nextInt());
        System.out.print("Tipo de atendimento (1 - Loja / 2 - Delivery): ");
        pedido.setTipoAtendimento(scanner.nextInt());
        System.out.print("Nome do cliente: ");
        scanner.nextLine(); // Consumir a quebra de linha pendente
        pedido.setNomeCliente(scanner.nextLine());
        System.out.print("Valor total: ");
        pedido.setValorTotal(scanner.nextDouble());

        pedidos[numPedidos] = pedido;
        numPedidos++;
        faturamentoTotal += pedido.getValorTotal();

        System.out.println("Pedido registrado com sucesso!");
        System.out.println();
    }

    private static void atenderPedido() {
        if (numPedidos == 0) {
            System.out.println("Lista Vazia – Não Existem Pedidos");
            return;
        }

        System.out.println("Pedido atendido: " + pedidos[0]);
        for (int i = 1; i < numPedidos; i++) {
            pedidos[i - 1] = pedidos[i];
        }

        numPedidos--;
        pedidosAtendidos++;
        System.out.println();
    }

    private static void listarPedidos() {
        if (numPedidos == 0) {
            System.out.println("Lista Vazia – Não Existem Pedidos");
            return;
        }

        System.out.println("Pedidos em aberto:");
        for (int i = 0; i < numPedidos; i++) {
            System.out.println(pedidos[i]);
        }

        System.out.println();
    }

    private static void pesquisarPedido() {
        if (numPedidos == 0) {
            System.out.println("Não Existem Pedidos para ser atendido.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o número do pedido: ");
        int numeroPedido = scanner.nextInt();

        int posicao = -1;
        for (int i = 0; i < numPedidos; i++) {
            if (pedidos[i].getNumeroPedido() == numeroPedido) {
                posicao = i;
                break;
            }
        }

        if (posicao != -1) {
            System.out.println("Pedido encontrado na posição: " + posicao);
        } else {
            System.out.println("Pedido não encontrado.");
        }

        System.out.println();
    }

    private static void encerrar() {
        if (numPedidos > 0) {
            System.out.println("Ainda existem pedidos para serem atendidos. Não é possível encerrar.");
            System.out.println();
        } else {
            System.out.println("Faturamento total: R$" + faturamentoTotal);
            System.out.println("Pedidos atendidos: " + pedidosAtendidos);
            System.out.println("Programa encerrado.");
        }
    }
}

class Pedido {
    private int numeroPedido;
    private int tipoAtendimento;
    private String nomeCliente;
    private double valorTotal;

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public int getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(int tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numeroPedido=" + numeroPedido +
                ", tipoAtendimento=" + tipoAtendimento +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
