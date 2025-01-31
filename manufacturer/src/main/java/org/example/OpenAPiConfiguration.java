package org.example;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPiConfiguration {

    @Bean OpenAPI OpenApi(){
        Server server = new Server();
        server.setUrl("http://localhost:8085");
        server.setDescription("Manufacturer");

        Contact contact = new Contact();
        contact.setName("Noy Nyström, Samuel Österberg");
        contact.setEmail("noynys-2@student.ltu.se, setsam-1@student.ltu.se");

        Info Information = new Info();
        Information.title("Manufacturer API");
        Information.description("This is a description of manufacturer functionality regarding API.");
        Information.version("1.0");
        Information.contact(contact);

        return new OpenAPI().servers(List.of(server)).info(Information);
    }
}
