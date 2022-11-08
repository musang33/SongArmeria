package com.example.songarmeria;

import com.example.songarmeria.armeria.blog.BlogService;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.docs.DocService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication( exclude = {SecurityAutoConfiguration.class} )
public class Application {
    //private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(final String ... args) {
        SpringApplication.run(Application.class, args);
    }
}
