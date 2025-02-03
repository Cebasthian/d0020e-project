package org.example.edc.extension;

import org.eclipse.edc.spi.EdcException;
import org.eclipse.edc.spi.monitor.Monitor;
import org.eclipse.edc.spi.system.configuration.Config;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/")
public class MetadataController {
    private final Monitor monitor;

    private final MetadataResponse response;

    public MetadataController(Monitor monitor, Config config) {
        this.monitor = monitor;
        this.response = new MetadataResponse(monitor, config);
    }

    @GET
    @Path("metadata")
    public MetadataResponse getMetadata() {
        monitor.info("Received a metadata request!");
        return this.response;
    }

    public static class MetadataResponse {

        public String participantId = "edc.participant.id";
        public String name = "edc.participant.name";
        public String description = "edc.participant.description";
        public String hostname = "edc.hostname";
        public String protocolAddress = "/protocol";
        
        private Monitor monitor;
        private Config config;


        public MetadataResponse(Monitor monitor, Config config) {
            this.monitor = monitor;
            this.config = config;

            this.participantId = tryConfig(this.participantId);
            this.name = tryConfig(this.name, this.participantId);
            this.description = tryConfig(this.description, "");
            this.hostname = tryConfig(this.hostname, "localhost");

            Integer protocolPort = tryConfig("web.http.protocol.port", 29194);
            String protocolPath = tryConfig("web.http.protocol.path", "/protocol");
            this.protocolAddress = "http://" + this.hostname + ":" + protocolPort.toString() + protocolPath;
        }

        private String tryConfig(String property, String fallback) {
            try {
                return this.config.getString(property);
            } catch (EdcException exception) {
                monitor.warning("Config value: no setting found for '" + property + "', falling back to default value '" + fallback + "'");
                return fallback;
            }
        }

        private String tryConfig(String property) {
            return tryConfig(property, property);
        }        

        private Integer tryConfig(String property, Integer fallback) {
            try {
                return this.config.getInteger(property);
            } catch (EdcException exception) {
                monitor.warning("Config value: no setting found for '" + property + "', falling back to default value '" + fallback + "'");
                return fallback;
            }
        }
    }
}
