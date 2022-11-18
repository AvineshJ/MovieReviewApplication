package movieReviewApplication;

public class User {
	String username;
	String email;
	String password;
	String status;
	int totalReviews;
	public User(String name) {
		this.username = name;
		this.status = "viewer";
		this.totalReviews = 0;
		this.email=name;
		this.password=name;
	}
}
