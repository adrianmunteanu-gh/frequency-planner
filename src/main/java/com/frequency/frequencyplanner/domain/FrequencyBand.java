package com.frequency.frequencyplanner.domain;

public class FrequencyBand {
	
	private int frequency;

    public FrequencyBand(int frequency) {
    	this.frequency=frequency;
    }

	public long getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

}