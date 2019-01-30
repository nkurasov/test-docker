package ru.test.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @author Nikita Kurasov
 */
@Service
public class SurnameService {

    private final RestTemplate template;

    private String uri;

    public SurnameService(RestTemplate template) {
        this.template = template;
    }

    public String getSurname(String name) {
        Objects.requireNonNull(name, "name");
        return template.getForObject(uri + "/surname?name=" + name, String.class);
    }

    @Value("${ru.test.surname-service.uri}")
    public void setUri(String uri) {
        this.uri = uri;
    }
}
