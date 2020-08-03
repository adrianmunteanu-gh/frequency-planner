package com.frequency.frequencyplanner.domain;

import java.util.List;

public class FrequencyBand {
	
	private List<Integer> frequency;
	private int MAIO;

    public FrequencyBand(List<Integer> frequency, int MAIO) {
    	this.frequency=frequency;
    	this.MAIO=MAIO;
    }

	public List<Integer> getFrequency() {
        return frequency;
    }

    public void setFrequency(List<Integer> frequency) {
        this.frequency = frequency;
    }
    
    public int getMAIO() {
    	return MAIO;
    }
    
    public void setMAIO(int MAIO) {
    	this.MAIO=MAIO;
    }
    
    public String toString() {
    	String str="{ ";
    	for(int i=0; i<frequency.size();i++)
    	{
    		str+=frequency.get(i)+" ";
    	}
    	str+="}  ("+MAIO+")";
    	return str;
    }
    
    public boolean compare(FrequencyBand fr,int diff) {
    	int a = frequency.get(MAIO);
    	int b = fr.getFrequency().get(fr.getMAIO());
    	int i=1;
    	do {
    		if(Math.abs(frequency.get((i+MAIO)%frequency.size())
    				-fr.getFrequency().get((i+fr.getMAIO())%fr.getFrequency().size()))<diff)
    			return true;
    		i++;
    	}while(a==frequency.get(i) && fr.getFrequency().get(i)==b);
    	return false;
    }

}