package com.System;  

import java.io.IOException; 
import java.util.List;  
import javax.servlet.http.HttpServletResponse; 
import javax.ws.rs.Consumes; 
import javax.ws.rs.DELETE; 
import javax.ws.rs.FormParam; 
import javax.ws.rs.GET; 
import javax.ws.rs.OPTIONS; 
import javax.ws.rs.POST; 
import javax.ws.rs.PUT; 
import javax.ws.rs.Path; 
import javax.ws.rs.PathParam; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.Context; 
import javax.ws.rs.core.MediaType;  
@Path("/UserService") 

public class UserService { 
  
   system sys = new system(); 
   recomendationCalc calc = new recomendationCalc();
   private static final String SUCCESS_RESULT = "<result>success</result>"; 
   private static final String FAILURE_RESULT = "<result>failure</result>";  
   
   @GET
   @Path("recommend/test")
   @Produces(MediaType.APPLICATION_ATOM_XML)
   public String test() {
	   return "this is working atleast." + calc.euclideanDist(sys.getUser(sys.getIndexWithName("Mick")), sys.getUser(sys.getIndexWithName("Toby")));
   }
   
   
   @GET
   @Path("/recommend/{pr1}/{pr2}/{type}")
   @Produces(MediaType.APPLICATION_ATOM_XML)
   public String getcalculation(@PathParam("pr1") String user1, @PathParam("pr2") String user2, @PathParam("type") String type) {
	   int indexOfUser1, indexOfUser2;
	   
	   try {
		   indexOfUser1 = sys.getIndexWithId(Integer.parseInt(user1)) + 1;
	   }catch(NumberFormatException nfe) {
		   indexOfUser1 = sys.getIndexWithName(user1) + 1;
	   }
	   
	   try {
		   indexOfUser2 = sys.getIndexWithId(Integer.parseInt(user2)) + 1;
	   }catch(NumberFormatException nfe) {
		   indexOfUser2 = sys.getIndexWithName(user2) + 1;
	   }
	   
	   
	   if(indexOfUser1 == -1 || indexOfUser2 == -1)
		   return "Error: one of the names do not exsist.";
	   
	   if(type.equals("Euclidean") || type.equals("euclidean"))
		   return "The euclidean value between " + user1 + " and " + user2 + " is: " +calc.euclideanDist(sys.getUser(indexOfUser1), sys.getUser(indexOfUser2));
	   
	   if(type.equals("Pearson") || type.equals("pearson") || type.equals("Pearson Correlation") || type.equals("pearson correlation"))
		   return "The Pearson Correlation value between " + user1 + " and " + user2 + " is: " +calc.pearsonDist(sys.getUser(indexOfUser1), sys.getUser(indexOfUser2));
	   
	   return "Error: That algoritm type do not exsist";
	   
   }
   
   @GET
   @Path("/recommend/best/{pr}")
   @Produces(MediaType.APPLICATION_ATOM_XML)
   public String recomendate(@PathParam("pr") String user) {
	   int idOfuser = -1;
	   try {
		   idOfuser = sys.getIndexWithId(Integer.parseInt(user)) + 1;
	   }catch(NumberFormatException nfe) {
		   idOfuser = sys.getIndexWithName(user) + 1;
	   }
	   
	   String print = "Top three matches for " + sys.getUser(sys.getIndexWithId(idOfuser)).getName() + " (Euclidean): \n\r";
	   
	   List<extraType> list = sys.createSortedListOfRecomendationEuclidean(idOfuser,1);
	   
	   print += "[(" + sys.getUser(sys.getIndexWithId(list.get(0).getId())).getName() + " : " + list.get(0).getvalue() + "), ";
	   print += "(" + sys.getUser(sys.getIndexWithId(list.get(1).getId())).getName() + " : " + list.get(1).getvalue() + "), ";
	   print += "(" + sys.getUser(sys.getIndexWithId(list.get(2).getId())).getName() + " : " + list.get(2).getvalue() + ")]\n\r";
	   print += "\n\r";
	   
	   List<extraType> list2 = sys.createSortedListOfRecomendationEuclidean(idOfuser,2);
	   
	   print += "Top three matches for " + sys.getUser(sys.getIndexWithId(idOfuser)).getName() + " (Pearson): \n\r";
	   print += "[(" + sys.getUser(sys.getIndexWithId(list2.get(0).getId())).getName() + " : " + list2.get(0).getvalue() + "), ";
	   print += "(" + sys.getUser(sys.getIndexWithId(list2.get(1).getId())).getName() + " : " + list2.get(1).getvalue() + "), ";
	   print += "(" + sys.getUser(sys.getIndexWithId(list2.get(2).getId())).getName() + " : " + list2.get(2).getvalue() + ")]\n\r";
	   
	   return print;
   }
   
   
}