package com.divstar.particle.authservice.rest.jwtservice;

import java.io.IOException;
import java.net.URISyntaxException;

import com.divstar.particle.authservice.rest.tos.PersistableProfile;

public interface JwtService {
	String tokenFor(final PersistableProfile account) throws IOException, URISyntaxException;
}
