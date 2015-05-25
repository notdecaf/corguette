import java.util.*;

public class WhatShouldIEat {
static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("Please input your zip code: ");
		
		String location = input.nextLine();
		String answer = whereEat();
	}
	
	public static String whereEat() { 
		System.out.println("How many people are you with?: ");
		int people = input.nextInt();

		String [] foods = {"Organic","Gourmet","Spicy","Fastfood","International", "Halal","Kosher","Alcohol","Vegan","Vegetarian","Healthy"};
		
		if (people >= 0) {
			System.out.println("Do you feel like spending a lot of money?(Yes or no) :");
			String g = input.next();
			if (decision(g) == false) 
			{
				foods = remove (foods, "Gourmet");
			}

		}
		
		
		System.out.println("Do you have any dietary restrictions?(Yes or No): ");
		String dr = input.next();
		if (decision(dr) == true) 
		{

			System.out.println("Are you vegeterian/vegan?(Yes or No): ");
			
			String v = input.next();
			if (decision(v) == true) 
				{
				System.out.println("Are you vegan?(Yes or No) :");
				String ve = input.next();
				if (decision (ve) == true)
				{
					foods = remove (foods, "Vegetarian");
				}
				}
			else {
				System.out.println("Would you like to eat non vegetarian?(Yes or No) :");
				String nv = input.next();
				if (decision (nv)) 
				{
					foods = remove(foods,"Vegetarian");
					foods = remove(foods,"Vegan");
				}
			}
			System.out.println("Do you only eat Halal food?(Yes or No): ");
			String ha = input.next();
			if (decision(ha)==true)
			{
				return "Halal";
			}
			else if (decision(ha)==false)
			
			{
				foods = remove(foods,"Halal");
				System.out.println("Do you only eat Kosher food?(Yes or No): ");
				String ko = input.next();
				if (decision(ko)==true)
				{
					return "Kosher";
				}
				else {
					foods = remove(foods,"Kosher");
				}
			}
		}
		else {
			foods = remove(foods,"Kosher");
			foods = remove(foods,"Halal");
			foods = remove(foods,"Vegetarian");
			foods = remove(foods,"Vegan");
		}
		
		System.out.println("Do you prefer Organic?(Yes or No); ");
		String or = input.next();
		if (decision(or)==false)
		{
			foods = remove (foods, "Organic");
		}
		System.out.println("Do you want to eat healthy?(Yes or No) :");
		String he = input.next();
		if (decision (he)==false)
		{
			foods = remove (foods, "Organic");
			foods = remove(foods,"Healthy");
		}
		else if (decision(he)==true)
		{
			foods = remove (foods, "Fastfood");
			foods = remove (foods, "Alcohol");
		}
		System.out.println("Are you over 21 and planning to drink?(Yes or no): ");
		String aa = input.next();
		if (decision(aa) == true)
		{
			foods = remove (foods, "Halal");
					foods = remove (foods, "Kosher");
						foods = remove (foods, "Healthy");
							foods = remove (foods, "Fastfood");
		}
		else if (decision (aa)==false)
		{
			foods = remove (foods, "Alcohol");
			
		}
		
		
		System.out.println("Would you like to try something International?(Yes or No): ");
		String in = input.next();
		if (decision (in)==true)
		{
			foods = remove (foods, "Fastfood");
		}
		
		System.out.println("Are you in the mood for Spicy?(Yes or No): ");
		String sp = input.next();
		if (decision (sp) == false)
		{
			foods = remove (foods, "Spicy");
		}
		else
		{
		System.out.println("Would you like to eat Indian food?(Yes or No) :");
		String ind = input.next();
		if (decision (ind))
			return "Indian";
		else {
			System.out.println("What about Thai?(Yes or No) :");
			String tha = input.next ();
		    if (decision(tha))
		    	return "Thai"; 
		    	
		}
		}
		
		System.out.println("You should try: ");
		for(int i=0;i<foods.length;i++)
			System.out.println(foods[i]);
		String result = "";
		for(int i=0;i<foods.length;i++)
			result+=foods[i] + " ";
		return result;
	}
		
	// if they feel spendy then call on this method, string g;if theyre
	// vegeterian string v
	// dietary restrictions string dr; string de for deals; string ha for halal
	//string kosher=ko; string he for healthy; string sp for spicy
	//string aa for alcohol; string in for international; string ve for vegan ; string or for organic
	public static boolean decision(String i) {
		if (i.length() > 2) {
			return true;

		} 
		else 
		{
			return false;
		}

	}
	   public static String [] remove(String [] food, String cat )
				{
			    int ctr=0;
				for( int i=0;i<food.length;i++)
				{
					if (cat.equals(food[i]))
						ctr++;
					
				}
				String[] ret=new String [food.length-ctr]; 
				for (int i = 0,j=0; i <food.length; i++)
				{
					if (cat.equals(food[i]));
					else
					{
					ret [j] = food [i];
					j++;
					}
				}
				return ret;
				}
		
}