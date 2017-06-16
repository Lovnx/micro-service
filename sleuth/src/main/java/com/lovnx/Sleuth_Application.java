package com.lovnx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class Sleuth_Application {

    public static void main(String[] args) {
        SpringApplication.run(Sleuth_Application.class, args);
    }
}