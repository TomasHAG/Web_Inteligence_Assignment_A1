package com.System;

public class movscr {
	private String movie;
	private double sum;
	private double sum_sim;
	
	public movscr(String movie) {
		this.movie = movie;
		sum = 0;
		sum_sim = 0;
	}
	
	public movscr(String movie, double sum) {
		this.movie = movie;
		this.sum = sum;
		sum_sim = 0;
	}
	
	public movscr(String movie, double sum, double sum_sim) {
		this.movie = movie;
		this.sum = sum;
		this.sum_sim = sum_sim;
	}
	
	public void setSum(double sum) {
		this.sum = sum;
	}
	
	public void setSum_sim(double sum_sim) {
		this.sum_sim = sum_sim;
	}
	
	public String getName() {
		return movie;
	}
	
	public double getSum() {
		return sum;
	}
	
	public double getSum_sim() {
		return sum_sim;
	}
}
