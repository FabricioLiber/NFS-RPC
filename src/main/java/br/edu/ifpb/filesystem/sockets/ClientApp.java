package br.edu.ifpb.filesystem.sockets;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClientApp {

    private static String dir = System.getProperty("user.dir") + "/files/";

    public static void main(String[] args) {
        try {

            Registry registry = LocateRegistry.getRegistry();
            ServerInterface server = (ServerInterface) registry.lookup("Server");
            Scanner entrada = new Scanner(System.in);
            while (true) {
                String mensagem = entrada.nextLine();
                List<String> data = Arrays.asList(mensagem.split(" "));
                if (data.get(1) == null || data.get(1).equals("")) {
                    System.out.println("Informe o caminho do arquivo correto!");
                } else {
                    if (data.get(0).equals("readdir")) {
                        System.out.println("Arquivos no diretório " + data.get(1));
                        System.out.println(server.readdir(data.get(1)));
                    } else if (data.get(0).equals("rename")) {
                        server.rename(data.get(1), data.get(2));
                        System.out.println("Arquivo renomeado!");
                    } else if (data.get(0).equals("create")) {
                        server.create(data.get(1));
                        System.out.println("Arquivo criado!");
                    } else if (data.get(0).equals("remove")) {
                        boolean resultado = server.remove(data.get(1));
                        System.out.println(resultado ? "Arquivo removido!" : "Arquivo não existe!");
                    } else {
                        System.out.println("Ação inválida!");
                    }
                }
            }
        } catch (IOException | NotBoundException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

}
