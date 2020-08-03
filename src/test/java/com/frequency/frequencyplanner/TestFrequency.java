package com.frequency.frequencyplanner;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.frequency.frequencyplanner.domain.FrequencyBand;
import com.frequency.frequencyplanner.generator.FrequencyPlanGenerator;

class TestFrequency {
	FrequencyBand frequency;
	FrequencyPlanGenerator generator = new FrequencyPlanGenerator();
	List<FrequencyBand> frequencyList = new ArrayList<FrequencyBand>();


	@Test
	public void canCreateOneGroup() {
		frequencyList=generator.generateFrequencyList(6, 1);
		for(int i=0;i<frequencyList.size();i++)
			System.out.println(frequencyList.get(i).toString());
		System.out.print("\n");
	}
	
	@Test
	public void canCreateTwoGroups() {
		frequencyList=generator.generateFrequencyList(6, 2);
		for(int i=0;i<frequencyList.size();i++)
			System.out.println(frequencyList.get(i).toString());
		System.out.print("\n");
	}
	
	@Test
	public void canCreateTreeGroups() {
		frequencyList=generator.generateFrequencyList(12, 3);
		for(int i=0;i<frequencyList.size();i++)
			System.out.println(frequencyList.get(i).toString());
		System.out.print("\n");
		assertEquals(frequencyList.get(0).compare(frequencyList.get(1), 2), false);
	}

}
