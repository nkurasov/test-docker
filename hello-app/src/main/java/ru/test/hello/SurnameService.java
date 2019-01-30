package ru.test.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

/**
 * @author Nikita Kurasov
 */
@Service
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
        return template.getForObject(uri, String.class);
    }

    @Value("${ru.test.surname-service.protocol}")
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Value("${ru.test.surname-service.host}")
    public void setHost(String host) {
        this.host = host;
    }

    @Value("${ru.test.surname-service.port}")
    public void setPort(int port) {
        this.port = port;
    }
}
