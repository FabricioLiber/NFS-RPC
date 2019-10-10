package br.edu.ifpb.filesystem.sockets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Server extends UnicastRemoteObject implements ServerInterface {

    private static String dir = System.getProperty("user.dir") + "/files/";

    Server() throws RemoteException { }

    @Override
    public String readdir(String path) throws IOException {
        Path file = Paths.get(dir + path);
        List<String> lista = new ArrayList<>();
        Files.list(file).forEach(item -> {
            lista.add(item.getFileName().toString());
        });
        StringBuilder resultado = new StringBuilder();

        for (String s : lista)
            resultado.append(s).append("\n");
        return resultado.toString();
    }

    @Override
    public void rename(String path, String newName) throws IOException {

        Path file1 = Paths.get(dir + path);
        Path file2 = Paths.get(dir + newName);
        Files.move(file1, file2);
    }

    @Override
    public boolean remove(String path) throws IOException {
        Path p = Paths.get(dir + path);
        if (Files.exists(p)) {
            Files.delete(p);
            return true;
        }
        return false;
    }

    @Override
    public void create(String path) throws IOException {
        Path p = Paths.get(dir + path);
        Files.createFile(p);
    }
}
