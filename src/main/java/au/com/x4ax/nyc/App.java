package au.com.x4ax.nyc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Spring boot app
 */
@SpringBootApplication
@EnableCaching
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) {
        final App app = new App();
        app.run(app.configure(new SpringApplicationBuilder(App.class)).build());
    }
}