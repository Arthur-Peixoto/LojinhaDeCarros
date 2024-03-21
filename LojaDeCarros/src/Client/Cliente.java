package Client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Server.Loja;
import Server.ServidorLoja;
import Utils.Carro;
import Utils.User;

public class Cliente {
    public static void main(String[] args) {

        int porta = 50005;

        boolean isConected = false;

        Scanner scanner = new Scanner(System.in);

        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 50005);

            // Procura a referência do servidor pelo nome "Vasco"
            Loja lojinha = (Loja) registry.lookup("vasco");

            System.out.println("Bem-vindo à loja de carros!");

            User userzinho = new User(null, null, 1);
            String login = "";

            while (!isConected) {
                System.out.println("Digite seu Login: ");
                login = scanner.nextLine();
                System.out.println("Digite sua senha: ");
                String senha = scanner.nextLine();

                if (lojinha.autenticar(login, senha) != null) {
                    userzinho = lojinha.autenticar(login, senha);
                    isConected = true;
                }
            }

            System.out.println("Bem vindo, " + login);
            int opcao = 0;

            // Clientes
            if (userzinho.getRole() == 1) {

                do {
                    System.out.println("\nEscolha uma opção:");
                    System.out.println("1 - Listar carros disponiveis");
                    System.out.println("2 - Pesquisar carro por nome ou renavan");
                    System.out.println("3 - Exibir quantidade de carros disponíveis");
                    System.out.println("4 - Comprar carro");
                    System.out.println("0 - Sair");

                    opcao = Integer.parseInt(System.console().readLine());

                    switch (opcao) {
                        case 1:
                            List<Carro> listaRetornadaCliente = new ArrayList<>();
                            listaRetornadaCliente = lojinha.listarCarros();
                            System.out.println("");

                            for (Carro carro : listaRetornadaCliente) { // todos os atributos
                                System.out.println("Nome = " + carro.getNome() + ", Renavan = " + carro.getRenavam() +
                                        ", Categoria = " + carro.getCategoria() + ", Ano = " + carro.getAno() +
                                        ", Preço = " + carro.getPreco() + ", Quantidade disponível = "
                                        + carro.getQuant());
                            }
                            break;
                        case 2:
                            System.out.println("\nDigite o nome ou o renavan do carro que deseja pesquisar:");
                            String chave = scanner.nextLine();
                            Carro carroPesquisadoCliente = lojinha.buscarCarro(chave);

                            // se não foi retorna null
                            if (carroPesquisadoCliente != null) {
                                System.out.println("\nCarro achado = " + carroPesquisadoCliente.toString()); // imprimindo
                                                                                                             // pro
                                                                                                             // cliente
                            } else {
                                System.out.println("\nCarro não encontrado");
                            }
                            break;
                        case 3:
                            int quant = lojinha.getQuantCarros();
                            System.out.println("\nQuantidade de carros disponívies = " + quant);
                            break;
                        case 4:
                            System.out.println("\nDigite o nome do carro que deseja comprar:");
                            String nomeCarro = scanner.nextLine();
                            lojinha.comprarCarro(nomeCarro);
                            System.out.println("Carro " + nomeCarro + " comprado!");
                        case 0:
                            lojinha.writeFile("src/Utils/concessionaria.txt");
                            System.out.println("\nObrigado por utilizar a loja de carros!");
                            break;
                        default:
                            System.out.println("\nOpção inválida. Tente novamente.");
                            break;
                    }

                } while (opcao != 0);
            }

            // Funcionários
            if (userzinho.getRole() == 2) {
                do {
                    System.out.println("\nEscolha uma opção:");
                    System.out.println("1 - Adiconar carro");
                    System.out.println("2 - Apagar carro");
                    System.out.println("3 - Listar carros disponiveis");
                    System.out.println("4 - Pesquisar carro por nome ou renavan");
                    System.out.println("5 - Alterar atributos de um carro");
                    System.out.println("6 - Exibir quantidade de carros disponíveis");
                    System.out.println("7 - Comprar carro");
                    System.out.println("0 - Sair");

                    opcao = scanner.nextInt();

                    switch (opcao) {
                        case 1:
                            System.out.println("\nDigite o renavan do carro que deseja adicionar:");
                            String renavan = scanner.nextLine();

                            System.out.println("\nDigite o nome do carro que deseja adicionar:");
                            String nome = scanner.nextLine();

                            System.out.println("\nDigite a categoria do carro que deseja adicionar:");
                            int categoria = scanner.nextInt();

                            System.out.println("\nDigite o ano do carro que deseja adicionar:");
                            int ano = scanner.nextInt();

                            System.out.println("\nDigite o preço do carro que deseja adicionar:");
                            double preco = scanner.nextDouble();

                            Carro carroAdicionado = lojinha.adicionarCarro(renavan, nome, categoria, ano, preco, 1);
                            System.out.println("\nCarro adicionado = " + carroAdicionado.toString());
                            break;
                        case 2:
                            System.out.println("\nDigite o nome do carro que deseja apagar:");
                            String apagado = System.console().readLine();
                            Carro carroRemovido = lojinha.excluirCarro(apagado);

                            if (carroRemovido instanceof Carro) {
                                System.out.println("\nCarro removido = " + carroRemovido.toString());
                            } else {
                                System.out.println("\nCarro não encontrado/removido");
                            }
                            break;
                        case 3:
                            List<Carro> listaRetornada = new ArrayList<>();
                            listaRetornada = lojinha.listarCarros();
                            System.out.println("");

                            for (Carro carro : listaRetornada) {
                                carro.toString();
                            }
                            break;
                        case 4:
                            System.out.println("\nDigite o nome ou o renavan do carro que deseja pesquisar:");
                            String chave = System.console().readLine();
                            Carro carroBuscado = lojinha.buscarCarro(chave);

                            if (carroBuscado instanceof Carro) {
                                System.out.println("\nCarro achado = " + carroBuscado.toString());
                            } else {
                                System.out.println("\nCarro não encontrado");
                            }
                            break;
                        case 5:
                            System.out.println("\nDigite o nome ou o renavan do carro que deseja pesquisar:");
                            String chaveAlterar = System.console().readLine();
                            Carro carroAlterado = lojinha.buscarCarro(chaveAlterar);

                            if (carroAlterado instanceof Carro) {
                                System.out.println("\nAltere os atributos do carro pesquisado: ");

                                System.out.println("\nDigite o novo renavan do carro:");
                                String renavanAlte = scanner.nextLine();

                                System.out.println("\nDigite o novo nome do carro:");
                                String nomeAlte = scanner.nextLine();

                                System.out.println("\nDigite a nova categoria do carro:");
                                int categoriaAlte = scanner.nextInt();

                                System.out.println("\nDigite o novo ano do carro:");
                                int anoAlte = scanner.nextInt();

                                System.out.println("\nDigite o novo preço do carro:");
                                double precoAlte = scanner.nextDouble();

                                System.out.println("\nDigite a nova quantidade disponivel:");
                                int qauntAlte = scanner.nextInt();

                                Carro carroAlterReturn = lojinha.alterar(chaveAlterar, renavanAlte, nomeAlte,
                                        categoriaAlte, anoAlte, precoAlte, qauntAlte);
                                if (carroAlterReturn instanceof Carro) {
                                    System.out.println("\nCarro alterado = " + carroAlterReturn.toString()); // imprimindo
                                                                                                             // pro
                                                                                                             // cliente
                                } else {
                                    System.out.println("Carro pesquisado não encontrado");
                                }
                            }
                            break;
                        case 6:
                            int quant = lojinha.getQuantCarros();
                            System.out.println("\nQuantidade de carros disponívies = " + quant);
                            break;
                        case 7:
                            System.out.println("\nDigite o nome do carro que deseja comprar:");
                            String nomeCarro = System.console().readLine();
                            lojinha.comprarCarro(nomeCarro);
                            System.out.println("Carro " + nomeCarro + " comprado com sucesso, Parabéns!");
                            break;
                        case 0:
                            lojinha.writeFile("src/Utils/concessionaria.txt");
                            System.out.println("\nObrigado por utilizar a loja de carros!");
                            break;
                        default:
                            System.out.println("\nOpção inválida. Tente novamente.");
                            break;
                    }
                } while (opcao != 0);
            }

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
