package movieReviewApplication;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws Exception {
		ManageAll manager = new ManageAll();
		Scanner sc = new Scanner(System.in);
		int nu = 1;
		do {
			System.out.println("Press 1 to add new user");
			System.out.println("Press 2 to add new movie");
			System.out.println("Press 3 to add new review");
			System.out.println("Press 4 to print movies");
			System.out.println("Press 5 to print users");
			System.out.println("Press 6 to print reviews");
			System.out.println("Press 7 to print total review score for each movie by genre");
			int choice = sc.nextInt();
			switch(choice) {
			case 1 : System.out.println("Enter UserName : "); 
					String username = sc.next();
					System.out.println("Enter Email : "); 
					String email=sc.next();
					System.out.println("Enter Password : "); 
					String password=sc.next();
					if(manager.checkUniqueUser(username) == false) {
						manager.addUser(username);
						manager.addEmail(email);
						manager.addPassword(password);
					}else {
						throw new Exception("User with this username already exist! Please try with different username");
					}
			    	
			    	break;
			case 2 : System.out.println("Enter Movie Name : ");
					String moviename = sc.next();
					System.out.println("Enter Year of Release : ");
					int yearOfRelease = sc.nextInt();
					List<String> genre = new ArrayList<>();
					System.out.println("Enter Total number of Genres : ");
					int noOfgenres = sc.nextInt();
					System.out.println("Enter Genres : ");
					while(noOfgenres > 0) {
						String gen = sc.next();
						genre.add(gen);
						noOfgenres--;
					}
					manager.addMovie(moviename,yearOfRelease,genre);
					break;
			case 3 : System.out.println("Enter User Name : "); 
					String username1 = sc.next();
					System.out.println("Enter Movie Name : ");
					String moviename1 = sc.next();
					System.out.println("Enter Rating : ");
					int rating = sc.nextInt();
					
					if(manager.findMovieByName(moviename1) == true) {
						if(manager.reviewsLength() == 0) {
							manager.updateUser(username1);
							manager.addReviews(username1,moviename1,rating);
						}
						else {
							if(manager.findSameReview(username1, moviename1, rating) == false) {
								manager.updateUser(username1);
								manager.addReviews(username1,moviename1,rating);
							}
							else {
								throw new Exception("Exception Multiple reviews not allowed");
							}
						}
					}else {
						throw new Exception("Exception Movie yet to be released");
					}
					break;
			case 4 : manager.printMovies();break;
			case 5 : manager.printUsers(); break;
			case 6 : manager.printReviews(); break;
			case 7 : System.out.println("Enter value of n : ");
			 		int va = sc.nextInt();
			 		System.out.println("Enter Genre : ");
			 		String gen = sc.next();
			 		LinkedHashMap<String,Integer> mapp = manager.printTopNMovies(gen);
			 		List<String> keys = mapp.entrySet().stream()
			 				  .map(Map.Entry::getKey)
			 				  .limit(va)
			 				  .collect(Collectors.toList());
			 		for(String key : keys){
			             System.out.println(key + " -> "+mapp.get(key));
			        }
			 		break;
			default  : System.out.println("Invalid Entry");
			}
			System.out.println("Press 1 to continue the menu otherwise press 0");
			nu = sc.nextInt();
		}while(nu == 1);
		sc.close();
	}
}
