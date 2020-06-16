package com.learningproject.microservices.limitsservice.model;

public class LimitConfig {
	private int minimum;
	private int maximum;

	public LimitConfig() {
	}

	public LimitConfig(int minimum, int maximum) {
		super();
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	@Override
	public String toString() {
		return "LimitConfig [minimum=" + minimum + ", maximum=" + maximum + "]";
	}

}
