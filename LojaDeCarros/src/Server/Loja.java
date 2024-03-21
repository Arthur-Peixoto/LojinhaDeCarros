package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import Utils.Carro;
import Utils.User;

public interface Loja extends Remote{
    public Carro adicionarCarro(String nome, String renavam, int categoria, int ano, double preco, int quant) throws RemoteException;
    public Carro buscarCarro(String chave) throws RemoteException;
    public Carro excluirCarro(String nome) throws RemoteException;  
    public Carro alterar(String chave, String renavam, String nome, int categoria, int ano, double preco, int quant) throws RemoteException;
    public int getQuantCarros() throws RemoteException;
    public String comprarCarro(String nome) throws RemoteException;

    public List<Carro> listarCarros() throws RemoteException;

    public User autenticar(String login, String senha) throws RemoteException;
    public void writeFile(String caminho) throws RemoteException;
    public void readFile(String caminho) throws RemoteException;
}
