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

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8081");
        server.setDescription("Recycler");

        Contact contact = new Contact();
        contact.setName("melander");
        contact.setEmail("melander@mail.se");

        Info information = new Info();
        information
                .title("title")
                .version("1.0")
                .description("description")
                .contact(contact);

        return new OpenAPI().info(information).servers(List.of(server));
    }

}
