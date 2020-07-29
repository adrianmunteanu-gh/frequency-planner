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

	@ProblemFactCollectionProperty
	private List<Site> sites;

	public FrequencyPlan() {
	}

	public FrequencyPlan(List<FrequencyBand> frequencies, List<Transmitter> transmitters, List<Site> sites) {
		this.frequencies = frequencies;
		this.transmitters = transmitters;
		this.sites = sites;
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

	public List<Site> getSite() {
		return sites;
	}

	public String getSolution() {
		String solution = "";
		for (int i = 0; i < sites.size(); i++) {
			solution+="\nSite: "+sites.get(i).getId()+"\n";
			for(int j=0;j<sites.get(i).getTransmittersId().size();j++)
			{
				solution+="Tx"
						+transmitters.get(sites.get(i).getTransmittersId().get(j)).getId()
						+" --->"
						+transmitters.get(sites.get(i).getTransmittersId().get(j)).getFrequency().getFrequency()
						+"\n";
				if(j==sites.get(i).getTransmittersId().size()-1) {
					solution+="	Neighbours:\n";
					solution+=transmitters.get(sites.get(i).getTransmittersId().get(0)).toStringNeighbours();
				}
			}
		}
		return solution;
	}

}
