package com.revature.interfaces;

import java.io.Serializable;
import java.util.Objects;

public class Athlete implements Runner, Serializable {
	
	private int height;
	private double weight;
	private double speed;
	private Gender gender;
	
	public Athlete() {
		super();
	}

	public Athlete(int height, double weight, double speed, Gender gender) {
		this();
		this.height = height;
		this.weight = weight;
		this.speed = speed;
		this.gender = gender;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(gender, height, speed, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Athlete)) {
			return false;
		}
		Athlete other = (Athlete) obj;
		return gender == other.gender && height == other.height
				&& Double.doubleToLongBits(speed) == Double.doubleToLongBits(other.speed)
				&& Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
	}
	
	@Override
	public String toString() {
		return "Athlete [height=" + height + ", weight=" + weight + ", speed=" + speed + ", gender=" + gender + "]";
	}

	@Override
	public void run() {
		System.out.println("A " + gender.toString().toLowerCase() + " athlete that is " + height + " cm tall and weighs " + weight + " kg ran "
				+ "at a speed of " + speed + " kmph");
	}
	
	@Override
	public void run2() {
		System.out.println("A " + gender.toString().toLowerCase() + " athlete that is " + height + " cm tall and weighs " + weight + " kg ran again "
				+ "at a speed of " + speed + " kmph");
	}

}
