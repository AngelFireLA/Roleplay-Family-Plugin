package me.angelfire.rpfamily;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;

public class Profile {
	
	private String girlOrBoy;
	private String playernameString;
	private Set<String> mother;
	private Set<String> father;
	private Set<String> son;
	private Set<String> daughter;
	private Set<String> brother;
	private Set<String> sister;
	private Set<String> husband;
	private int age;









	public void setDeadoralive(String deadoralive) {
		this.deadoralive = deadoralive;
	}

	private Set<String> wife;
	private String deadoralive;
	
	
	public Profile(String playernameString, String girlOrBoy, Set<String> mother, Set<String> father, Set<String> son,
			Set<String> daughter, Set<String> brother, Set<String> sister, Set<String> husband, Set<String> wife, String deadoralive, Integer age) {
		super();
		this.girlOrBoy = girlOrBoy;
		this.playernameString = playernameString;
		this.mother = mother;
		this.father = father;
		this.son = son;
		this.daughter = daughter;
		this.brother = brother;
		this.sister = sister;
		this.husband = husband;
		this.wife = wife;
		this.deadoralive = deadoralive;
		this.age = age;

	}


	

	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getGirlOrBoy() {
		return girlOrBoy;
	}
	
	public String getPlayernameString() {
		return playernameString;
	}


	public Set<String> getMother() {
		return mother;
	}


	public Set<String> getFather() {
		return father;
	}


	public Set<String> getSon() {
		return son;
	}


	public Set<String> getDaughter() {
		return daughter;
	}


	public Set<String> getBrother() {
		return brother;
	}


	public Set<String> getSister() {
		return sister;
	}
	
	public void setHusband(Set<String> husband) {
		this.husband = husband;
	}




	public Set<String> getWife() {
		return wife;
	}




	public void setWife(Set<String> wife) {
		this.wife = wife;
	}


	public String getDeadoralive() {
		return deadoralive;
	}
	
	public Set<String> getHusband() {
		return husband;
	}
	
	public static Profile createProfile(String playername, String girlOrBoy, String mother1, String father1, String son1, String daughter1, String brother1, String sister1, String husband1, String wife1, String deadoralive1, Integer age) {
		
		final Set<String> mother = new HashSet<>();
		mother.add(mother1);
		
		final Set<String> father = new HashSet<>();
		father.add(father1);
		
		final Set<String> son = new HashSet<>();
		son.add(son1);
		
		final Set<String> daughter = new HashSet<>();
		daughter.add(daughter1);
		
		final Set<String> brother = new HashSet<>();
		brother.add(brother1);
		
		final Set<String> sister = new HashSet<>();
		sister.add(sister1);
		
		final Set<String> husband = new HashSet<>();
		husband.add(husband1);
		
		final Set<String> wife = new HashSet<>();
		wife.add(wife1);
		
		
		

		return new Profile(playername, girlOrBoy, mother, father, son, daughter, brother, sister, husband, wife, deadoralive1, age);
		
		
	}

}
