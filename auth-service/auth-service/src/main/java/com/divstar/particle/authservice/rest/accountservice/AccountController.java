package com.divstar.particle.authservice.rest.accountservice;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.divstar.particle.authservice.rest.accountservice.exceptions.AccountLoginFailedException;
import com.divstar.particle.authservice.rest.jwtservice.JwtService;
import com.divstar.particle.authservice.rest.tos.Credentials;
import com.divstar.particle.authservice.rest.tos.GenericResponse;
import com.divstar.particle.authservice.rest.tos.PersistableAccount;
import com.divstar.particle.authservice.rest.tos.RegisterableAccount;

/**
 * This class represents the controller, which acts upon requests arriving at mapped endpoints.
 * <p>
 * This class is expected to contain little logic since most of it is performed in the {@link AccountService} or rather its implementation.
 * 
 * @author divstar
 *
 */
@RestController
public class AccountController {
	//private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private JwtService jwtService;

	/**
	 * This method attempts to login a user and issue a token if the login was successful.
	 * <p>
	 * If login fails due a login attempt with a non-existent username or an invalid password, an exception is thrown.
	 * 
	 * @param credentials
	 *            ({@link Credentials}) credentials in a JSON-form, that can be unserialized into an object of this type
	 * @param response
	 *            ({@link HttpServletResponse}) response, which will be sent to the client;
	 *            if the credentials were valid the response receives a JWT as an additional header
	 * @return ({@link PersistableAccount}) JSON (automatically serialized from the given TO);
	 *         if the request was successful, an additional header containing the freshly issued JWT is added into the response
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersistableAccount login(@RequestBody final Credentials credentials, final HttpServletResponse response)
			throws IOException, URISyntaxException {
		final Optional<PersistableAccount> account = accountService.login(credentials);
		if (!account.isPresent()) {
			throw new AccountLoginFailedException(credentials);
		}
		response.setHeader("Token", jwtService.tokenFor(account.get()));
		return account.get();
	}

	/**
	 * This method attempts to retrieve all accounts ({@link PersistableAccount}-objects) and return it as a response.
	 * 
	 * @return (List&lt;{@link PersistableAccount}&gt;) list of accounts ({@link PersistableAccount}-objects)
	 */
	@RequestMapping(value = "/accounts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersistableAccount> getAccounts() {
		return accountService.getAccounts();
	}

	/**
	 * This method attempts to retrieve a single account ({@link PersistableAccount}-object) and return it as a response.
	 * 
	 * @param accountId
	 *            (int) account to retrieve
	 * @return ({@link PersistableAccount}) account in its serialized (JSON) format
	 */
	@RequestMapping(value = "/account/{id:[\\d]+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersistableAccount getAccount(@PathVariable("id") final int accountId) {
		return accountService.getAccount(accountId);
	}

	/**
	 * This method attempts to register an account using a {@link Credentials}, containing credentials.
	 * 
	 * @param registrationAccount
	 *            ({@link Credentials}) minimal account containing credentials and other important data
	 * @return ({@link PersistableAccount}) persisted account awaiting confirmation and activation
	 */
	@RequestMapping(value = "/registerAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersistableAccount registerAccount(@RequestBody final RegisterableAccount registrationAccount) {
		return accountService.registerAccount(registrationAccount);
	}

	/**
	 * This method attempts to confirm a given account.
	 * 
	 * @param accountId
	 *            (int) account to confirm
	 * @return ({@link GenericResponse}&lt;Boolean&gt;) generic response containing a return value
	 */
	@RequestMapping(value = "/confirmAccount/{id:[\\d]+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse<Boolean> confirmAccount(@PathVariable("id") final int accountId) {
		final boolean accountConfirmed = accountService.confirmAccount(accountId);

		return new GenericResponse<>("confirmAccount", HttpStatus.ACCEPTED, accountConfirmed);
	}
}
