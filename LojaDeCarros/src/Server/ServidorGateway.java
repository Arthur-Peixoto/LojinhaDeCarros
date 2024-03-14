package Server;

import java.util.List;

import Utils.Carro;
import Utils.User;

public class ServidorGateway implements Loja{

    Loja[] replicLojas;
    boolean isServer;
    

    public ServidorGateway(Loja[] replicaLojas, boolean isServer){
        this.replicLojas = replicaLojas;
        this.isServer = isServer;

        
    }


    @Override
    public Carro adicionarCarro(String renavan, String nome, String categoria, int ano, double preco) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionarCarro'");
    }

    @Override
    public Carro buscarCarro(String chave) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarCarro'");
    }

    @Override
    public Carro excluirCarro(String nome) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluirCarro'");
    }

    @Override
    public Carro alterar(String chave, String renavam, String nome, String categoria, int ano, double preco,
            int quant) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alterar'");
    }

    @Override
    public int getQuantCarros() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getQuantCarros'");
    }

    @Override
    public String comprarCarro(String nome) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'comprarCarro'");
    }

    @Override
    public List<Carro> listarCarros() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarCarros'");
    }

    @Override
    public User autenticar(String login, String senha) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'autenticar'");
    }

    @Override
    public void writeFile(String caminho) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeFile'");
    }
}