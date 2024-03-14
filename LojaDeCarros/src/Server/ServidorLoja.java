package Server;

import java.util.List;

import Utils.Carro;
import Utils.User;

public interface ServidorLoja {
    public Carro adicionarCarro(String renavan, String nome, String categoria, int ano, double preco);
    public Carro buscarCarro(String chave);
    public Carro excluirCarro(String nome);  
    public Carro alterar(String chave, String renavam, String nome, String categoria, int ano, double preco, int quant);
    public int getQuantCarros();
    public String comprarCarro(String nome);

    public List<Carro> listarCarros();

    public User autenticar(String login, String senha);
    public void writeFile(String caminho);
}
