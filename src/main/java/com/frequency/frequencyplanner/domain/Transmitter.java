package com.frequency.frequencyplanner.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Transmitter {
	
	private int id;
	private String name;
	private int firstNeighbour;
	
	 @PlanningVariable(valueRangeProviderRefs = "frequency")
	 private FrequencyBand frequency;

	
	public Transmitter() {
	}
	
	public Transmitter(int id, String name, int firstNeighbour) {
		this.id=id; 
		this.name=name;
		this.firstNeighbour=firstNeighbour;
	}
	
	public int getId() {
		return id;
	}
	
	public void setID(int id) {
		this.id=id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public int getFirstNeighbour() {
		return firstNeighbour;
	}
	
	public void setFirstNeighbour(int firstNeighbour) {
		this.firstNeighbour=firstNeighbour;
	}
	
	public FrequencyBand getFrequency() {
		return frequency;
	}
	
	public void setFrequency(FrequencyBand frequency) {
		this.frequency=frequency;
	}
}
