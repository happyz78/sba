package com.ibm.fsd.sba.jwt.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@Getter
@ToString
public class JwtAuthenticationConfig {

    @Value("${sba.security.jwt.url:/login}")
    private String url;

    @Value("${sba.security.jwt.header:Authorization}")
    private String header;

    @Value("${sba.security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${sba.security.jwt.expiration:#{24*60*60}}")
    private int expiration; // default 24 hours

    @Value("${sba.security.jwt.secret}")
    private String secret;
}
