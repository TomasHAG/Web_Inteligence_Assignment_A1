package com.System;

public class test {

	public static void main(String[] args) {
		system sys = new system();
		recomendationCalc calc = new recomendationCalc();
		
		System.out.println(sys.numOfUsers());
		
		User user = null;
		
		for(int nr = 0; nr < sys.numOfUsers();nr++) {
			user = sys.getUser(nr);
			System.out.print("id :" + user.getId() + " name: " + user.getName() + " ");
			for(Movie m : user.allMovies()) {
				System.out.print(m.getName() + " score: " + m.getScore() + " | ");
			}
			System.out.println("");
		}
		
		UserService ser = new UserService();
		System.out.println(ser.recomendate("Toby"));
		
		System.out.println(ser.toptreeRecomended("Euclidean", "Toby"));
	}

}
