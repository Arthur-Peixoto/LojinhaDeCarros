package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final String[] enderecos = {"cliente1", "cliente2", "cliente3"};
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(50005);
    
            List<Loja> lojinhas = new ArrayList<>();
    
            for (String cliente : enderecos) {
                Loja lojinha = new ServidorLoja("src/Utils/concessionaria.txt");
                lojinhas.add(lojinha);
            }
    
            Loja[] lojas = lojinhas.toArray(new Loja[0]);
    
            ServidorGateway redirector = new ServidorGateway(lojas, true);

            Loja stub = (Loja) UnicastRemoteObject.exportObject(redirector, 0);

            registry.rebind("vasco", stub);

            System.out.println("Servidor pronto para receber conexões dos clientes");
    
        } catch (RemoteException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }
}
