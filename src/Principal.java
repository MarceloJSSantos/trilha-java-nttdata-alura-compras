import java.util.Collections;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner entrada =  new Scanner(System.in);
        System.out.println("Com qual limite você deseja criar o cartão?");
        double limite = entrada.nextDouble();
        CartaoCredito cartaoCredito = new CartaoCredito(limite);

        int opcao = 1;
        while(opcao != 0){
            System.out.print("Descrição da compra: ");
            String descricao = entrada.next();

            System.out.print("Valor da compra: ");
            double valor = entrada.nextDouble();

            Compra compra = new Compra(descricao, valor);

            boolean compraFeita = cartaoCredito.registraCompra(compra);

            if (compraFeita){
                System.out.println("Compra feita!");
                opcao = opcaoMenu(entrada);
            } else {
                System.out.println("Compra não efetivada!");
                System.out.println("Seu saldo é insuficiente: Saldo %.2f".formatted(cartaoCredito.getSaldo()));
                opcao = opcaoMenu(entrada);
            }
        }
        System.out.println("=".repeat(100));
        System.out.println("Resumo de Compras");
        System.out.println("=".repeat(100));
        Collections.sort(cartaoCredito.getCompras());
        for (Compra compra : cartaoCredito.getCompras()){
            System.out.println(compra.getDescricao() + "\t\t\t\t\t\tR$ " + compra.getValor());
        }
        System.out.println("Saldo do cartão:\t\t\tR$ " + cartaoCredito.getSaldo());
        System.out.println("=".repeat(100));
    }

    private static int opcaoMenu(Scanner entrada) {
        int opcao;
        System.out.println("=".repeat(100));
        System.out.print("""
                        O que você deseja fazer?
                        [1] para continuar comprando
                        [0] para sair
                        """);
        System.out.println("=".repeat(100));
        System.out.print("Escolha: ");
        opcao = entrada.nextInt();
        return opcao;
    }
}
