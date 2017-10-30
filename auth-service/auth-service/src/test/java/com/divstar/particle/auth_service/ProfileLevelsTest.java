package com.divstar.particle.auth_service;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.divstar.particle.authservice.rest.enums.ProfileLevels;

public class ProfileLevelsTest {
	@Test
	public void testFromLevel() {
		MatcherAssert.assertThat("", ProfileLevels.fromLevel(110000), Matchers.equalTo(ProfileLevels.CODER));
		MatcherAssert.assertThat("", ProfileLevels.fromLevel(11000), Matchers.equalTo(ProfileLevels.ADMINISTRATOR));
		MatcherAssert.assertThat("", ProfileLevels.fromLevel(10000), Matchers.equalTo(ProfileLevels.ADMINISTRATOR));
		MatcherAssert.assertThat("", ProfileLevels.fromLevel(9000), Matchers.equalTo(ProfileLevels.MODERATOR));
		MatcherAssert.assertThat("", ProfileLevels.fromLevel(-500), Matchers.equalTo(ProfileLevels.BANNED));
		MatcherAssert.assertThat("", ProfileLevels.fromLevel(-15000), Matchers.equalTo(ProfileLevels.BANNED));
	}
}
