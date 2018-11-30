package com.System;

public class Movie {
	private String name;
	private Double score;
	
	public Movie(String name, Double score) {
		this.name = name;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}
	
	public Double getScore() {
		return score;
	}
	
	public void editScore(double score) {
		this.score = score;
	}
}
