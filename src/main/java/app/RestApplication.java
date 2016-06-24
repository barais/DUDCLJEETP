package app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import io.swagger.jaxrs.config.BeanConfig;
import io.undertow.Undertow;
import web.rest.DepartementResource;
import web.rest.EmployeeResource;
import web.rest.StaticResource;
import web.rest.SwaggerResource;

public class RestApplication extends Application {

    public static void main(String[] args) {
        UndertowJaxrsServer server = new UndertowJaxrsServer();
        server.deploy(new RestApplication());
        //ResourceHandler handler= io.undertow.Handlers.resource(new FileResourceManager(new File("src/main/webapp"), 1024));
        
        
        
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("0.0.0-alpha");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/");
        beanConfig.setResourcePackage("web.rest");
        beanConfig.setScan(true);
        beanConfig.setPrettyPrint(true);
        

        server.start(Undertow.builder().addHttpListener(8080, "0.0.0.0"));
    }

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<>();

        // REST endpoints
        resources.add(DepartementResource.class);
        resources.add(EmployeeResource.class);

        // SWAGGER endpoints
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        resources.add(SwaggerResource.class);
        resources.add(StaticResource.class);

        return resources;
    }
}
