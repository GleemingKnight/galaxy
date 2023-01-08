package me.gleeming.galaxy.server.thread;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.gleeming.galaxy.server.GalaxyServer;
import me.gleeming.galaxy.server.request.Request;
import me.gleeming.galaxy.server.response.Response;
import me.gleeming.galaxy.server.response.content.ContentType;
import me.gleeming.galaxy.server.response.status.StatusCode;
import me.gleeming.galaxy.thread.GalaxyThread;
import me.gleeming.galaxy.thread.runnable.ErrorRunnable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor(staticName = "of")
public class ClientAcceptThread extends GalaxyThread {

    /**
     * An instance of the galaxy server.
     */
    private final GalaxyServer server;

    /**
     * The execution service which is meant to handle
     * the client requests and should be scaled
     * dependent on how many requests you handle.
     */
    private ExecutorService clientService;

    @Override @SneakyThrows
    public void tick() {
        if (clientService == null) {
            clientService = Executors.newFixedThreadPool(
                    server.getGalaxy().getConfig().clientThreads()
            );
        }

        Socket socket = server.getServerSocket().accept();

        clientService.execute(new ErrorRunnable() {
            @Override
            public void runSafe() throws Exception {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                List<String> lines = new ArrayList<>();
                String line = br.readLine();

                while (line != null && !line.isBlank()) {
                    lines.add(line);
                    line = br.readLine();
                }

                Request request = Request.parse(lines);

                // A parsed request being null
                // means that the client has
                // sent a bad request.
                if (request == null) {
                    ErrorRunnable.of(() -> new Response(
                            StatusCode.BAD_REQUEST,
                            ContentType.JSON,
                            "Bad request.".getBytes()
                    ).send(socket)).run();
                    return;
                }

                Response response = new Response(StatusCode.OK, ContentType.JSON, "{banana: \"hi\"}".getBytes());
                response.send(socket);
                socket.close();
            }
        });
    }
}
