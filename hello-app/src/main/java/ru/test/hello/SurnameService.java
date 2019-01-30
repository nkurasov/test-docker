package ru.test.hello;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

/**
 * @author Nikita Kurasov
 */
public class SurnameService {

    private final RestTemplate template;

    private String protocol;

    private String host;

    private int port;

    public SurnameService(RestTemplate template) {
        this.template = template;
    }

    public String getSurname(String name) {
        Objects.requireNonNull(name, "name");
        URI uri = UriComponentsBuilder.newInstance()
                .scheme(protocol)
                .host(host)
                .port(port)
                .path("/surname")
                .queryParam("name", name)
                .build()
                .toUri();
        try {
            return template.getForObject(uri, String.class);
        } catch (RestClientException e) {
            return null;
        }
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
