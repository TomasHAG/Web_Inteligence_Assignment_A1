package com.System; 

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement(name = "user") 

public class User implements Serializable {  
	
	private static final long serialVersionUID = 1L; 
	private String name;
	private int id;
	private HashSet<Movie> movies;
	
	public User(String name, int id) {
		this.name = name;
		this.id = id;
		movies = new HashSet();
	}
	
	public void addMovie(String name, double score) {
		movies.add(new Movie(name, score));
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean hasMovies() {
		return !movies.isEmpty();
	}
	
	public boolean hasMovie(String name) {
		for(Movie m:movies) {
			if(name.equals(m.getName()))
				return true;
		}
		return false;
	}
	
	public double getMovieScore(String name) {
		for(Movie m:movies) {
			if(name == m.getName())
				return m.getScore();
		}
		return 0;
	}
	
	public List<Movie> allMovies(){
		return new ArrayList<Movie>(movies);
	}
	/*
   private static final long serialVersionUID = 1L; 
   private int id; 
   private String name; 
   private String profession;  
   public User(){}  
   
   public User(int id, String name, String profession){ 
      this.id = id; 
      this.name = name; 
      this.profession = profession; 
   }  
    
   public int getId() {
      return id; 
   } 
   @XmlElement 
   public void setId(int id) { 
      this.id = id; 
   } 
   public String getName() { 
      return name; 
   } 
   @XmlElement 
      public void setName(String name) { 
      this.name = name; 
   } 
   public String getProfession() { 
      return profession; 
   } 
   @XmlElement 
   public void setProfession(String profession) { 
      this.profession = profession; 
   }   
   @Override 
   public boolean equals(Object object){ 
      if(object == null){ 
         return false; 
      }else if(!(object instanceof User)){ 
         return false; 
      }else { 
         User user = (User)object; 
         if(id == user.getId() 
            && name.equals(user.getName()) 
            && profession.equals(user.getProfession())){ 
               return true; 
         }
      } 
      return false; 
   }
   */
	
	
} 