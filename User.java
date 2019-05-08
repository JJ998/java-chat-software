package v2;

import java.io.Serializable;

public class User implements Serializable {
	public String username;
	public String gender;
	public int age;
	public User(String name, int sex, int year) {
		username = name;
		String g[] = {"ÄĞ", "Å®", "Î´Öª"};
		gender = g[sex];
		age = year;
	}
	public String toString() {
		return username + ", " + gender + ", " + age + "Ëê";
	}
}