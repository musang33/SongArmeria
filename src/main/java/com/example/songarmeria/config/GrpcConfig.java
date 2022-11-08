package com.example.songarmeria.config;

import com.example.songarmeria.grpc.GrpcBlogService;
import com.linecorp.armeria.common.grpc.GrpcSerializationFormats;
import com.linecorp.armeria.server.HttpServiceWithRoutes;
import com.linecorp.armeria.server.grpc.GrpcService;
import com.linecorp.armeria.server.grpc.GrpcServiceBuilder;
import io.grpc.BindableService;
import io.grpc.Grpc;
import io.grpc.protobuf.services.ProtoReflectionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GrpcConfig {
    @Bean
    GrpcService grpc(final List<BindableService> services) {
        final var grpcServiceBuilder = GrpcService.builder();

        services.stream().forEach(grpcServiceBuilder::addService);

        return grpcServiceBuilder.build();
    }
}
