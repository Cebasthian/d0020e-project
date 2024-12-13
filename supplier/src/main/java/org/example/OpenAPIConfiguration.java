package org.example;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean OpenAPI openAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:8083");
        server.setDescription("Supplier");

        Contact contact = new Contact();
        contact.setName("cebasthian");
        contact.setEmail("cebasthian@mail.com");

        Info information = new Info();
        information
                .title("Supplier API documentation")
                .version("1.0")
                .description("A collection of the supplier modules API functionality.")
                .contact(contact);

        return new OpenAPI().info(information).servers(List.of(server));
    }
}
