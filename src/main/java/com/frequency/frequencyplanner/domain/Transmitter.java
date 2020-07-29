package com.frequency.frequencyplanner.domain;

import java.util.ArrayList;
import java.util.List;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Transmitter {
	
	private int id;
	private List<Transmitter> neighbours;
	private Site site;
	
	 @PlanningVariable(valueRangeProviderRefs = "frequency")
	 private FrequencyBand frequency;

	
	public Transmitter() {
	}
	
	public Transmitter(int id){
		this.id=id; 
		neighbours=new ArrayList<Transmitter>();
	}
	
	public int getId() {
		return id;
	}
	
	public void setID(int id) {
		this.id=id;
	}
	
	public List<Transmitter> getNeighbours() {
		return neighbours;
	}
	
	public void setNeighbours(List<Transmitter> neighbours) {
		this.neighbours=neighbours;
	}
	
	public void addNeighbour(Transmitter neighbour) {
		neighbour.addNeighbour(neighbour);
	}
	
	public FrequencyBand getFrequency() {
		return frequency;
	}
	
	public void setFrequency(FrequencyBand frequency) {
		this.frequency=frequency;
	}
	
	public Site getSite() {
		return site;
	}
	
	public int getSiteId() {
		return site.getId();
	}
	
	public void setSite(Site site) {
		this.site=site;
	}
	
	public String toStringNeighbours() {
		String display="";
		for(int i=0;i<neighbours.size();i++)
		{
			display+="	Tx"+ neighbours.get(i).getId()+" ---> "
					+neighbours.get(i).getFrequency().getFrequency()
					+"\n";
		}
		return display;
	}
}
