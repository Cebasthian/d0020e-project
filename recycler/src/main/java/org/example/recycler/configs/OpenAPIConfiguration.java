package org.example.recycler.configs;

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
        server.setUrl("http://localhost:8081/");
        server.setDescription("Recycler");

        Contact contact = new Contact();
        contact.setName("Samuel M");
        contact.setEmail("sammel-2@student.ltu.se");

        Info information = new Info();
        information
                .title("Recycler API")
                .version("1.0")
                .description("""
                        All the endpoints from the Recycler module.
                        
                        The instructions endpoints are for an employee creating/updating their own database.
                        
                        DPP endpoint(s) are for an employee getting the product passport via the IDS data space.
                        """)
                .contact(contact);

        return new OpenAPI().info(information).servers(List.of(server));
    }

}
