package br.edu.ifpb.filesystem.sockets;

import java.io.IOException;
import java.rmi.Remote;

public interface ServerInterface  extends Remote {

    String readdir(String path) throws IOException;
    void rename(String path, String newName) throws IOException;
    boolean remove(String path) throws IOException;
    void create(String path) throws IOException;

}
