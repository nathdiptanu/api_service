package org.diptanu.india.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.diptanu.india.messenger.database.DatabaseClass;

import org.diptanu.india.messenger.model.Profile;

public class ProfileService {
	
	private Map<String,Profile> profiles=DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("Diptanu", new Profile(1L, "Diptanu", "Bunty", "NAth"));
	}
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	
	public List<Profile> getAllProfilesOfYear(int year){
		ArrayList<Profile> profilesofyear=new ArrayList<Profile>();
		Calendar calc=Calendar.getInstance();
		for(Profile profile:profiles.values()){
			calc.setTime(profile.getCreated());
			if(calc.get(Calendar.YEAR)==year){
				profilesofyear.add(profile);
			}
					}
		return profilesofyear;
		
	}
	public List<Profile> getAllProfilesPaginated(int start,int size){
		ArrayList<Profile> list=new ArrayList<Profile>(profiles.values());
		if(start+size >list.size())return new ArrayList<Profile>();
		return list.subList(start, size);
	}
		
	
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
		
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size()+1);
		return profiles.put(profile.getProfileName(),profile);
		
	}
	
	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}
