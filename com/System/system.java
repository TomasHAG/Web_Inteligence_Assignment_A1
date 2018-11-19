package com.System;

import java.io.BufferedReader;
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList; 
import java.util.List;
import java.util.Scanner;

public class system {
	
	private List<User> users;
	private List<String> moviesExist;
	
	public system() {
		users = new ArrayList<User>();
		moviesExist = new ArrayList<String>();
		readUsersFromFile();
	}
	
	public void readUsersFromFile() {
		
		if(!users.isEmpty())
			return;
		
		
		String content = null;
		try {
			Scanner scanner = new Scanner(Paths.get("users.csv"), StandardCharsets.UTF_8.name());
			content = scanner.useDelimiter("\\A").next();
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] conten = content.split("\n");
		String[] con = null;
		
		for(int nr = 1; nr < conten.length;nr++) {
			con = conten[nr].split(";");
			users.add(new User(con[0],Integer.parseInt(con[1])));
		}
		
		
			
			
		
		
		content = null;
		try {
			Scanner scanner = new Scanner(Paths.get("ratings.csv"), StandardCharsets.UTF_8.name());
			content = scanner.useDelimiter("\\A").next();
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int ui = 0;
		conten = content.split("\n");
		for(int nr = 1; nr < conten.length;nr++) {
			con = conten[nr].split(";");
			ui = Integer.parseInt(con[0]);
			for(User u:users) {
				if(u.getId() == ui) {
					con[1].replaceAll("\"", "");
					u.addMovie(con[1], Double.parseDouble(con[2])); //
					if(!moviesExist.contains(con[1]))
						moviesExist.add(con[1]);
					break;
				}
					
			}
		}
			
			
			
		
	}
	
	public User getUser(int index) {
		return users.get(index);
	}
	
	public int numOfUsers() {
		return users.size();
	}
	
	public int getIndexWithName(String name) {
		int index = 0;
		for(User u : users) {
			if(u.getName().equals(name))
				return index;
			index ++;
		}
		return -1;
	}
	
	public int getIndexWithId(int id) {
		int index = 0;
		for(User u : users) {
			if(u.getId() == id)
				return index;
			index ++;
		}
		return -1;
	}
	
	public List<extraType> createSortedListOfRecomendation(int id, int type) {
		List<extraType> list = new ArrayList<extraType>();
		List<extraType> listToSend = new ArrayList<extraType>();
		
		recomendationCalc calc = new recomendationCalc();
		
		for(User u : users) {
			if(u.getId() != id) {
				if(type == 1)
					list.add(new extraType(u.getId(), calc.euclideanDist(getUser(getIndexWithId(id)), u)));
				if(type == 2) {
					list.add(new extraType(u.getId(), calc.pearsonDist(getUser(getIndexWithId(id)), u)));
				}
			}
		}
		
		int element = 0;
		
		while(!list.isEmpty()) {
			for(int nr = 0; nr < list.size();nr++) {
				if(list.get(element).getvalue() < list.get(nr).getvalue()) {
					element = nr;
				}
			}
			listToSend.add(list.get(element));
			list.remove(element);
			element = 0;
		}
		
		
		return listToSend;
	}
	
	public List<extraType> listOfWheightedUsercompared(User u, int type){
		recomendationCalc calc = new recomendationCalc();
		
		List<extraType> listToSend = new ArrayList<extraType>();
		
		for(User p : users) {
			if(u.equals(p))
				continue;
			if(type == 1) {
				listToSend.add(new extraType(p.getId(), calc.euclideanDist(u, p)));
			}else if(type == 2) {
				listToSend.add(new extraType(p.getId(), calc.pearsonDist(u, p)));
			}else {
				return null;
			}
		}
		
		return listToSend;
	}
	
	public List<String> getListOfMovies(){
		return moviesExist;
	}
	
}
