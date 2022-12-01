package com.example.songarmeria.config;

import com.example.songarmeria.armeria.blog.BlogService;
import com.example.songarmeria.grpc.GrpcBlogService;
import com.linecorp.armeria.common.HttpHeaderNames;
import com.linecorp.armeria.common.grpc.GrpcSerializationFormats;
import com.linecorp.armeria.common.logging.LogLevel;
import com.linecorp.armeria.server.ClientAddressSource;
import com.linecorp.armeria.server.HttpServiceWithRoutes;
import com.linecorp.armeria.server.docs.DocService;
import com.linecorp.armeria.server.grpc.GrpcService;
import com.linecorp.armeria.server.healthcheck.HealthCheckService;
import com.linecorp.armeria.server.logging.AccessLogWriter;
import com.linecorp.armeria.server.logging.LoggingService;
import com.linecorp.armeria.spring.ArmeriaAutoConfiguration;
import com.linecorp.armeria.spring.ArmeriaServerConfigurator;
import io.grpc.protobuf.services.ProtoReflectionService;
import io.netty.channel.ChannelOption;
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
            // Add HealthCheck service
            builder.service("/health", HealthCheckService.of());
            // Log every message which the server receives and responds.
            //builder.decorator(LoggingService.newDecorator());

            // Write access log after completing a request.
            builder.accessLogWriter(AccessLogWriter.common(), true);

            // LoggingService
            builder.decorator(
                    LoggingService.builder()
                            .requestLogLevel(LogLevel.INFO)
                            .successfulResponseLogLevel(LogLevel.INFO)
                            .failureResponseLogLevel(LogLevel.WARN)
                            .newDecorator()
            );

            // Add an Armeria annotated HTTP service.
            builder.annotatedService(service);

            // Configure a list of sources which are used to determine where to look for
            // the client address, in the order of preference. If unspecified, 'Forwarded',
            // 'X-Forwarded-For' and the source address of a PROXY protocol header would be used.
            builder.clientAddressSources(ClientAddressSource.ofHeader(HttpHeaderNames.FORWARDED));

            // You can also bind asynchronous RPC services such as Thrift and gRPC:
            // builder.service(THttpService.of(...));
            // builder.service(GrpcService.builder()...build());

            builder.channelOption(ChannelOption.SO_REUSEADDR, true);
        };
    }
}
