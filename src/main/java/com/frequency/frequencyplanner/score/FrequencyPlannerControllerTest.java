package com.frequency.frequencyplanner.score;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.frequency.frequencyplanner.domain.FrequencyBand;
import com.frequency.frequencyplanner.domain.FrequencyPlan;
import com.frequency.frequencyplanner.domain.Transmitter;

@SpringBootTest(properties = { "optaplanner.solver.termination.spent-limit=5s" })
public class FrequencyPlannerControllerTest {
	@Autowired
	private FrequencyPlannerController frequencyplannercontroller;

	@Test
	@Timeout(600_000)
	public void solve() {
		FrequencyPlan problem = generateProblem();
		FrequencyPlan solution = frequencyplannercontroller.solve(problem);
		assertFalse(solution.getTransmitters().isEmpty());
		for (Transmitter transmitter : solution.getTransmitters()) {
			assertNotNull(transmitter.getFrequency());
		}
		assertTrue(solution.getScore().isFeasible());
		
		System.out.print(solution.getSolution());
	}

	private FrequencyPlan generateProblem() {
		List<FrequencyBand> frequencyBandList = new ArrayList<>();
		frequencyBandList.add(new FrequencyBand(1));
		frequencyBandList.add(new FrequencyBand(3));
		frequencyBandList.add(new FrequencyBand(6));

		List<Transmitter> transmitterList = new ArrayList<>();
		transmitterList.add(new Transmitter(1, "Tx1", 2));
		transmitterList.add(new Transmitter(2, "Tx2", 1));
		transmitterList.add(new Transmitter(3, "Tx3", 1));
		transmitterList.add(new Transmitter(4, "Tx4", 5));
		transmitterList.add(new Transmitter(5, "Tx5", 2));
		transmitterList.add(new Transmitter(6, "Tx6", 5));
		transmitterList.add(new Transmitter(7, "Tx7", 3));

		return new FrequencyPlan(frequencyBandList, transmitterList);
	}

}
