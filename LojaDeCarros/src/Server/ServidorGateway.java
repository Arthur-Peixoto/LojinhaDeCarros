package Server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Utils.Carro;
import Utils.User;

public class ServidorGateway implements Gateway{

    private int currentIndex = 0;
    Loja[] replicLojas;
    boolean isServer;

    private List<Carro> carros = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static String caminho = "LojaDeCarros/src/Utils/concessionaria.txt";


    public ServidorGateway(Loja[] replicaLojas, boolean isServer){
        this.replicLojas = replicaLojas;
        this.isServer = isServer;
        this.currentIndex = 0;

        users.add(new User("vasco", "qqqq", 1));
        users.add(new User("flamengo", "qqqq", 2));

        try {
            readFile(caminho);
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        
        

    }

    @Override
    public Carro adicionarCarro(String renavan, String nome, int categoria, int ano, double preco, int quant) {
        Carro carroNovo = new Carro(renavan, nome, categoria, ano, preco, quant);
        carros.add(carroNovo);
        return carroNovo;
    }

    @Override
    public Carro buscarCarro(String chave) {
        boolean encontrou = false;
        for (Carro carro : this.carros) {
                if (carro.getNome().equalsIgnoreCase(chave) || carro.getRenavam().equalsIgnoreCase(chave)) {
                    carro.toString();
                    System.out.println("-------------------------");
                    encontrou = true;
                    return carro;
                }
            }
            if(!encontrou){
                System.out.println("Carro não encontrado!");
                System.out.println("-------------------------");               
            }
            return null;
    }

    @Override
    public Carro excluirCarro(String nome) {
        Iterator<Carro> iter = carros.iterator();
        while (iter.hasNext()) {
            Carro carro = iter.next();
            if (carro.getNome().equalsIgnoreCase(nome)) {
                iter.remove();
                System.out.println("Carro removido: " + nome);
                getQuantCarros();
                return carro;
            }
        }
        System.out.println("\nNão foi encontrado nenhum carro com o nome " + nome);
        System.out.println("-------------------------");
        return null;
    }

    @Override
    public Carro alterar(String chave, String renavam, String nome, int categoria, int ano, double preco,int quant) {
                for (Carro carro : this.carros) {
                        if (carro.getNome().equalsIgnoreCase(chave) || carro.getRenavam().equalsIgnoreCase(chave)) {
                            carro.setNome(nome);
                            carro.setRenavam(renavam);
                            carro.setAno(ano);
                            carro.setCategoria(categoria);
                            carro.setPreco(preco);
                            carro.setQuant(quant);
                            System.out.println("Carro alterado: " + nome);
                            getQuantCarros();
                            return carro;
                        }
                    }
                    return null;
    }

    @Override
    public int getQuantCarros() {
        int quantidadeTotal = 0;

        for (Carro carro : carros){
            quantidadeTotal += carro.getQuant();
        }
        System.out.println("Quantidade de carros disponívies = " + quantidadeTotal);
        System.out.println("-------------------------");
        return quantidadeTotal;
    }

    @Override
    public String comprarCarro(String nome) {
        Carro carro = null;
        for (Carro carrinho : carros) {
            if (carrinho.getNome().equals(nome)) {
                carro = carrinho;
                break;
            }
        }
        
        if (carro == null) {
            System.out.println("Carro não encontrado na loja");
            return "não encontrou"; 
        }else {
            carro.setQuant(carro.getQuant() - 1);
            if(carro.getQuant() == 0) { 
                carros.remove(carro);
            }
        } 
        return carro.getNome()+" vendido com sucesso!\t agora restam apenas"+carro.getQuant()+" unidades";
    }

    @Override
    public List<Carro> listarCarros() {
        List<Carro> carrosRetorno = new ArrayList<>();
        for (Carro carro : carros) {
            carrosRetorno.add(carro);
            carro.toString();
            System.out.println("-------------------------");
        }
        return carrosRetorno;
    }

    @Override
    public User autenticar(String login, String senha) {
         for (User user : users) {
            if (user.getLogin().equals(login) && user.getSenha().equals(senha)) {
                if(user.getRole() == 1){
                    System.out.println("cliente");
                }
                else if (user.getRole() == 2) {
                    System.out.println("funcionário");
                }
                return user;
            }
        }
        return null;
    }

    @Override
    public void writeFile(String caminho) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for (Carro carro : carros) {
                writer.write("vasco");
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void readFile(String caminho) throws RemoteException{
        try {
            File arquivoCarros = new File(caminho);
            Scanner scanner = new Scanner(arquivoCarros);
            while (scanner.hasNextLine()) {
                String texto = scanner.nextLine();
                String[] dados = texto.split(",");
                String nome = dados[0];
                String renavan = dados[1];
                int categoria = Integer.parseInt(dados[2]);
                int ano = Integer.parseInt(dados[3]);
                double preco = Double.parseDouble(dados[4]);
                int quantidade = Integer.parseInt(dados[5]);
                Carro carro = new Carro(nome, renavan, categoria, ano, preco, quantidade);
                carros.add(carro);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo");
        }
    }

    @Override
    public String getNextAddress() {
        currentIndex = (currentIndex + 1) % replicLojas.length;
        return currentIndex + " :index atual";
    }

    @Override
    public void setNextAddress(String Address) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNextAddress'");
    }

}