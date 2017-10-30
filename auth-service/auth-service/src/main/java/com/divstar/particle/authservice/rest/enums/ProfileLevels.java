package com.divstar.particle.authservice.rest.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.ToString;

/**
 * This enum defines account-levels, that are expected to be present at any given time.
 * <p>
 * The administrators / coders may include more levels, e.g. more USER-levels.
 * 
 * @author divstar
 *
 */
@Getter
@ToString
public enum ProfileLevels {
	/**
	 * Account is (possibly forcefully) suspended.
	 * <p>
	 * {@code level = -1000;}
	 */
	BANNED(-1000),

	/**
	 * Account is (possibly forcefully) suspended for a certain time.
	 * <p>
	 * {@code level = -100;}
	 */
	BANNED_TEMPORARILY(-100),

	/**
	 * Account has not yet been registered, yet is in use.
	 * <p>
	 * {@code level = 0;}
	 * <p>
	 * <i>Note:</i> this level might be used if a response has to be sent to anonymous (<i>not yet logged in</i>) users.
	 */
	ANONYMOUS(0),

	/**
	 * Account is deactivated (usually, because it has not been used for too long).
	 * <p>
	 * {@code level = 10;}
	 */
	DEACTIVATED(10),

	/**
	 * Account activation / confirmation is pending.
	 * <p>
	 * {@code level = 50;}
	 */
	PENDING(50),

	/**
	 * Account belongs to a regular user.
	 * <p>
	 * {@code level = 100;}
	 */
	USER(100),

	/**
	 * Account belongs to a moderator.
	 * <p>
	 * {@code level = 1000;}
	 */
	MODERATOR(1000),

	/**
	 * Account belongs to an administrator.
	 * <p>
	 * {@code level = 10000;}
	 */
	ADMINISTRATOR(10000),

	/**
	 * Account belongs to a SysOp (usually the owner).
	 * <p>
	 * {@code level = 50000;}
	 */
	SYSOP(50000),

	/**
	 * Account belongs to a coder.
	 * <p>
	 * {@code level = 100000;}
	 */
	CODER(100000);

	private static final List<ProfileLevels> PROFILE_LEVELS = Stream.of(ProfileLevels.values())
																	.sorted((v1, v2) -> Integer.compare(v1.getLevel(), v2.getLevel()))
																	.collect(Collectors.toList());

	private final int level;

	/**
	 * Default private constructor.
	 * 
	 * @param level
	 *            (int) level associated with a given constant
	 */
	private ProfileLevels(final int level) {
		this.level = level;
	}

	/**
	 * This method determines the actual {@link ProfileLevels}-object by a level (int).
	 * <p>
	 * <i>Note:</i> since custom levels are allowed, this method simply maps the given level to the closest value,
	 * that is smaller than the given level value.
	 * <p>
	 * <table style="border: 1px dotted #000;" border="1" cellpadding="2">
	 * <tr><th>Given this input level...</th><th>You would get this enum value</th></tr>
	 * <tr><td>level = 9000</td><td>{@link ProfileLevels#MODERATOR}</td></tr>
	 * <tr><td>level = -1500</td><td>{@link ProfileLevels#BANNED}</td></tr>
	 * <tr><td>level = 111111</td><td>{@link ProfileLevels#CODER}</td></tr>
	 * </table>
	 * 
	 * @param level
	 *            (int) any integer specifying an account level;
	 *            it does not have to correspond to one of the enum values defined in this enum
	 * @return ({@link ProfileLevels}) the closest enum value
	 */
	public static ProfileLevels fromLevel(final int level) {
		ProfileLevels previousLevel = ProfileLevels.PROFILE_LEVELS.get(0);

		for (int i = 1; i < ProfileLevels.PROFILE_LEVELS.size(); i++) {
			if (level >= ProfileLevels.PROFILE_LEVELS.get(i).level) {
				previousLevel = ProfileLevels.PROFILE_LEVELS.get(i);
			} else if (level < ProfileLevels.PROFILE_LEVELS.get(i).level) {
				break;
			}
		}
		return previousLevel;
	}
}
