package com.frequency.frequencyplanner.domain;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@SuppressWarnings("deprecation")
@PlanningSolution
public class FrequencyPlan {

    @PlanningEntityCollectionProperty
    private List<Transmitter> transmitters;
    
	@ProblemFactCollectionProperty
	 @ValueRangeProvider(id = "frequency")
   private List<FrequencyBand> frequencies;
	
    @PlanningScore
    private HardSoftScore score;

    public FrequencyPlan() {
    }

	public FrequencyPlan(List<FrequencyBand> frequencies, List<Transmitter> transmitters) {
        this.frequencies = frequencies;
        this.transmitters = transmitters;
    }

    public List<FrequencyBand> getFrequencies() {
        return frequencies;
    }

    public void setFrequenceis(List<FrequencyBand> frequencies) {
        this.frequencies = frequencies;
    }

    public List<Transmitter> getTransmitters() {
        return transmitters;
    }

    public void setTransmitters(List<Transmitter> transmitters) {
        this.transmitters = transmitters;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }
    
    public String getSolution() {
    	String solution ="";
    	for(int i=0;i<transmitters.size();i++)
    	{
    		solution+=(transmitters.get(i).getName()
    				+"(n:"+transmitters.get(i).getFirstNeighbour()+")"
    				+" ---> "
    				+transmitters.get(i).getFrequency().getFrequency()
    				+"\n");
    	}
    	return solution;
    }

}
