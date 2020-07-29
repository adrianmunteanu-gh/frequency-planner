package com.frequency.frequencyplanner.domain;

import java.util.ArrayList;
import java.util.List;

public class Site {

	private int id;
	private List<Transmitter> transmitters;
	private List<Site> neighbours;
	private List<Integer> transmittersId;
	
	public Site(int id)
	{
		this.id=id;
		transmitters=new ArrayList<Transmitter>();
		neighbours=new ArrayList<Site>();
		this.transmittersId=new ArrayList<Integer>();
	}
	
	public void update(Site newSite)
	{
		this.id=newSite.getId();
		this.transmitters=newSite.getTransmitters();
		this.neighbours=newSite.getNeighbours();
	}
	
	public int getId()
	{
		return id;
	}
	
	public List<Transmitter> getTransmitters(){
		return transmitters;
	}
	
	public void addTransmitter(Transmitter t) {
		transmitters.add(t);
	}
	
	public List<Site> getNeighbours(){
		return neighbours;
	}
	
	public void addNeighbour(Site n) {
		neighbours.add(n);
	}
	
	public List<Integer> getTransmittersId(){
		return transmittersId;
	}
	
	public void addTransmitterId(int id) {
		transmittersId.add(id);
	}
	

}
