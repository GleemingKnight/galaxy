package me.gleeming.galaxy.server;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.gleeming.galaxy.Galaxy;
import me.gleeming.galaxy.server.thread.ClientAcceptThread;

import java.net.ServerSocket;

@RequiredArgsConstructor
@Getter
public class GalaxyServer {

    /**
     * An instance of the main galaxy class.
     */
    private final Galaxy galaxy;

    /**
     * The main instance of the server socket.
     */
    private ServerSocket serverSocket;

    @SneakyThrows
    public void run() {
        serverSocket = new ServerSocket(galaxy.getConfig().port());
        ClientAcceptThread.of(this);
    }

    @SneakyThrows
    public void close() {
        serverSocket.close();
    }
}
