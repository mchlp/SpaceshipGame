package tests;

import backend.Vector;

public class VectorTestData {

	private double mMagnitude;
	private double mDirection;
	private double mActualDirection;
	private double mXMagnitude;
	private double mYMagnitude;

	public VectorTestData(double magnitude, double direction) {
		mMagnitude = magnitude;
		mDirection = direction;
		mActualDirection = direction % Vector.MAX_DEGREES;
		mXMagnitude = magnitude * Math.cos(Math.toRadians(mDirection));
		mYMagnitude = magnitude * Math.sin(Math.toRadians(mDirection));
	}

	public double getmMagnitude() {
		return mMagnitude;
	}

	public double getmDirection() {
		return mDirection;
	}

	public double getmActualDirection() {
		return mActualDirection;
	}

	public double getmXMagnitude() {
		return mXMagnitude;
	}

	public double getmYMagnitude() {
		return mYMagnitude;
	}
}
