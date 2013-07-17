package com.blogspot.nurkiewicz.asyncretry.backoff;

import com.blogspot.nurkiewicz.asyncretry.RetryContext;

/**
 * @author Tomasz Nurkiewicz
 * @since 7/17/13, 11:15 PM
 */
public interface Backoff {

	public static final Backoff DEFAULT = new FixedIntervalBackoff();

	long delayMillis(RetryContext context);

	default Backoff withUniformJitter() {
		return new UniformRandomBackoff(this);
	}

	default Backoff withUniformJitter(long range) {
		return new UniformRandomBackoff(this, range);
	}

	default Backoff withProportionalJitter() {
		return new ProportionalRandomBackoff(this);
	}

	default Backoff withProportionalJitter(double multiplier) {
		return new ProportionalRandomBackoff(this, multiplier);
	}

	default Backoff withMinDelay(long minDelayMillis) {
		return new BoundedMinBackoff(this, minDelayMillis);
	}

	default Backoff withMaxDelay(long maxDelayMillis) {
		return new BoundedMaxDelayPolicy(this, maxDelayMillis);
	}



}