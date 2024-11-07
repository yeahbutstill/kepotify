package com.yeahbutstill.kepotify;

import org.springframework.boot.SpringApplication;

public class TestKepotifyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(KepotifyServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
