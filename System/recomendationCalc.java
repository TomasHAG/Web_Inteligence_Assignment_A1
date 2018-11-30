package com.System;

public class recomendationCalc {
	
	public double euclideanDist(User x, User y) {
		if(!x.hasMovies() || !y.hasMovies())
			return 0;
		
		double sim = 0;
		
		for(Movie m : x.allMovies()) {
			for(Movie n : y.allMovies()) {
				if(n.getName().equals(m.getName())) {
					sim += Math.pow(m.getScore() - n.getScore(),2);
				}
			}
		}
		
		if(sim == 0)
			return 0;
		
		return 1 / (1 + sim);
	}
	
	public double pearsonDist(User x, User y) {
		double sum1 = 0, sum2 = 0, sum1sq = 0, sum2sq = 0, pSum = 0;
		int e = 0;
		
		for(Movie m : x.allMovies()) {
			for(Movie n : y.allMovies()) {
				if(n.getName().equals(m.getName())) {
					sum1 += m.getScore();
					sum2 += n.getScore();
					sum1sq += Math.pow(m.getScore(), 2);
					sum2sq += Math.pow(n.getScore(), 2);
					pSum += m.getScore() * n.getScore();
					e++;
				}
			}
		}
		
		if(e == 0)
			return 0;
		double num = pSum - ((sum1 * sum2)/e);
		double den = Math.sqrt( (sum1sq - (Math.pow(sum1, 2)/e)) * (sum2sq - (Math.pow(sum2, 2)/e)) );
		
		return num / den;
	}

}
