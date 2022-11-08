package com.example.songarmeria.config;

import com.example.songarmeria.armeria.blog.BlogService;
import com.example.songarmeria.grpc.GrpcBlogService;
import com.linecorp.armeria.common.grpc.GrpcSerializationFormats;
import com.linecorp.armeria.server.HttpServiceWithRoutes;
import com.linecorp.armeria.server.docs.DocService;
import com.linecorp.armeria.server.grpc.GrpcService;
import com.linecorp.armeria.spring.ArmeriaAutoConfiguration;
import com.linecorp.armeria.spring.ArmeriaServerConfigurator;
import io.grpc.protobuf.services.ProtoReflectionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArmeriaConfig {
    @Bean
    public ArmeriaServerConfigurator armeriaServerConfigurator(final BlogService service,
                                                               final GrpcService grpcService) {

        // Customize the server using the given ServerBuilder. For example:
        return builder -> {
            // Add DocService that enables you to send Thrift and gRPC requests from web browser.
            builder.serviceUnder("/docs", new DocService());
            builder.service(grpcService);
            // Log every message which the server receives and responds.
            //builder.decorator(LoggingService.newDecorator());

            // Write access log after completing a request.
            //builder.accessLogWriter(AccessLogWriter.combined(), false);

            // Add an Armeria annotated HTTP service.
            builder.annotatedService(service);

            // You can also bind asynchronous RPC services such as Thrift and gRPC:
            // builder.service(THttpService.of(...));
            // builder.service(GrpcService.builder()...build());
        };
    }
}
