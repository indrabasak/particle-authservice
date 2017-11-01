package com.divstar.particle.authservice.rest.jwtservice;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divstar.particle.authservice.rest.tos.PersistableAccount;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {
	private static final String ISSUER = "com.divstar.particle";

	@Autowired
	private SecretKeyProvider secretKeyProvider;

	public String tokenFor(final PersistableAccount account) throws IOException, URISyntaxException {
		final byte[] secretKey = secretKeyProvider.getKey();
		final Date expiration = Date.from(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC));
		return Jwts.builder()
				   .setSubject(account.getUsername())
				   .setExpiration(expiration)
				   .setIssuer(JwtServiceImpl.ISSUER)
				   .signWith(SignatureAlgorithm.HS512, secretKey)
				   .compact();
	}
}
