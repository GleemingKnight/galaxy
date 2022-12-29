package me.gleeming.galaxy.server.response;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import me.gleeming.galaxy.server.response.content.ContentType;
import me.gleeming.galaxy.server.response.status.StatusCode;

import java.net.Socket;

@AllArgsConstructor
public class Response {

    private static final String HTTP_VERSION = "HTTP/1.1";

    private StatusCode status;
    private ContentType contentType;
    private String content;

    @SneakyThrows
    public void send(Socket socket) {
        if (socket.isClosed()) {
            return;
        }

        socket.getOutputStream().write((HTTP_VERSION + " " + status.getStatus() + "\r\n").getBytes());
        socket.getOutputStream().write(("Content-Type: " + contentType.getUsage() + "\r\n").getBytes());
        socket.getOutputStream().write("\r\n".getBytes());
        socket.getOutputStream().write((content).getBytes());
    }

}
