package me.gleeming.galaxy.server.request;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class Request {

    /**
     * The type of request.
     */
    private RequestType type;

    /**
     * The url path.
     */
    private String path;

    /**
     * The version of http.
     */
    private String version;

    /**
     * The query of the request.
     */
    private final Map<String, String> query = new HashMap<>();

    /**
     * The headers specified in the request.
     */
    private final Map<String, String> headers = new HashMap<>();

    /**
     * Parses the http request by splitting
     * at the line breaks.
     * @param lines Request Lines
     * @return Request
     */
    public static Request parse(List<String> lines) {
        try {
            Request request = new Request();
            String[] baseRequest = lines.get(0).split(" ");

            request.setType(RequestType.valueOf(baseRequest[0]));
            request.setVersion(baseRequest[2]);

            URL url = new URL("https://url.net" + baseRequest[1]);
            request.setPath(url.getPath());

            if (url.getQuery() != null) Arrays.stream(url.getQuery().split("&")).forEach(param -> {
                int equalsIndex = param.indexOf("=");

                String key = equalsIndex > 0 ? param.substring(0, equalsIndex) : param;
                String value = equalsIndex > 0 && param.length() > equalsIndex + 1 ?
                        param.substring(equalsIndex + 1) : null;

                request.getQuery().put(
                        URLDecoder.decode(key, StandardCharsets.UTF_8),
                        value == null ? null : URLDecoder.decode(value, StandardCharsets.UTF_8)
                );
            });

            lines.subList(1, lines.size()).forEach(header -> {
                int splitIndex = header.indexOf(": ");
                request.getHeaders().put(
                        header.substring(0, splitIndex),
                        header.substring(splitIndex + 2)
                );
            });

            return request;
        } catch (Exception ignored) {  }

        return null;
    }

}
