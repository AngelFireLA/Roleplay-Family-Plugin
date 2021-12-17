package me.angelfire.rpfamily;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;

public class Profile {
	
	
	private String playernameString;
	private Set<String> mother;
	private Set<String> father;
	private Set<String> son;
	private Set<String> daughter;
	private Set<String> brother;
	private Set<String> sister;
	
	
	public Profile(String playernameString, Set<String> mother, Set<String> father, Set<String> son,
			Set<String> daughter, Set<String> brother, Set<String> sister) {
		super();
		this.playernameString = playernameString;
		this.mother = mother;
		this.father = father;
		this.son = son;
		this.daughter = daughter;
		this.brother = brother;
		this.sister = sister;
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
	
	public static Profile createProfile(String empty, String playername, String mother1, String father1, String son1, String daughter1, String brother1, String sister1) {
		
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
		
		

		return new Profile(playername, mother, father, son, daughter, brother, sister);
		
		
	}

}
