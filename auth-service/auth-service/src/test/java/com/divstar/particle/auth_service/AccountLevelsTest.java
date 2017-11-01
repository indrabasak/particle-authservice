package com.divstar.particle.auth_service;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.divstar.particle.authservice.rest.accountservice.enums.AccountLevels;

public class AccountLevelsTest {
	@Test
	public void testFromLevel() {
		MatcherAssert.assertThat("", AccountLevels.fromLevel(110000), Matchers.equalTo(AccountLevels.CODER));
		MatcherAssert.assertThat("", AccountLevels.fromLevel(11000), Matchers.equalTo(AccountLevels.ADMINISTRATOR));
		MatcherAssert.assertThat("", AccountLevels.fromLevel(10000), Matchers.equalTo(AccountLevels.ADMINISTRATOR));
		MatcherAssert.assertThat("", AccountLevels.fromLevel(9000), Matchers.equalTo(AccountLevels.MODERATOR));
		MatcherAssert.assertThat("", AccountLevels.fromLevel(-500), Matchers.equalTo(AccountLevels.BANNED));
		MatcherAssert.assertThat("", AccountLevels.fromLevel(-15000), Matchers.equalTo(AccountLevels.BANNED));
	}
}
