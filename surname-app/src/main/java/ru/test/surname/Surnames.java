package ru.test.surname;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author nkurasov
 */
public class Surnames {

    private static final Logger logger = LoggerFactory.getLogger(Surnames.class);

    private final ObjectMapper mapper;

    private Resource source;

    private final Map<String, String> surnames = new HashMap<>();

    public Surnames(ObjectMapper mapper) {
        this.mapper = Objects.requireNonNull(mapper, "mapper");
    }

    public void setSource(Resource source) {
        this.source = source;
    }

    private static class Surname {
        private final String name;
        private final String surname;

        @JsonCreator
        Surname(@JsonProperty("name") String name, @JsonProperty("surname") String surname) {
            this.name = name;
            this.surname = surname;
        }
    }

    @PostConstruct
    public void loadSurnames() throws IOException {
        try (InputStream in = source.getInputStream()) {
            List<Surname> surnames = mapper.readValue(in, mapper.getTypeFactory().constructCollectionType(List.class, Surname.class));
            for (Surname s : surnames) {
                logger.info("Loaded {} {}", s.name, s.surname);
                this.surnames.put(s.name, s.surname);
            }
        }
    }

    public Optional<String> getSurname(String name) {
        return Optional.ofNullable(surnames.get(name));
    }
}
