package br.edu.ifpb.filesystem.sockets;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerApp {

    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
        // Cria uma instância do NFS
        ServerInterface server = new Server();

        // instanciando o registro
        Registry registry = LocateRegistry.createRegistry(1099);

        // liga (bind) o serviço ao RMI Registry
        registry.rebind("Server", server);

        System.out.println("Server registrado ....");
    }

}
