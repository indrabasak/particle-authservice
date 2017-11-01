package com.divstar.particle.authservice.rest.jwtservice;

import java.io.IOException;
import java.net.URISyntaxException;

import com.divstar.particle.authservice.rest.tos.PersistableAccount;

public interface JwtService {
	String tokenFor(final PersistableAccount account) throws IOException, URISyntaxException;
}
