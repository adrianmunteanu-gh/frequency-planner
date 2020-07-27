package com.frequency.frequencyplanner;

import java.util.ArrayList;
import java.util.List;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import com.frequency.frequencyplanner.domain.FrequencyBand;
import com.frequency.frequencyplanner.domain.FrequencyPlan;
import com.frequency.frequencyplanner.domain.Transmitter;

public class FrequencyPlanner {

	public static void main(String[] args) {
		SolverFactory<FrequencyPlan> solverFactory = SolverFactory
				.createFromXmlResource("FrequencyPlannerSolverConfig.xml");
		Solver<FrequencyPlan> solver = solverFactory.buildSolver();

		FrequencyPlan unsolvedFrequencyPlan = generateProblem();

		FrequencyPlan solvedFrequencyPlan = solver.solve(unsolvedFrequencyPlan);

		System.out.println("\nSolved frequency plan:\n" + solvedFrequencyPlan.getSolution());
	}

	private static FrequencyPlan generateProblem() {
		List<FrequencyBand> frequencyBandList = new ArrayList<>();
		frequencyBandList.add(new FrequencyBand(1));
		frequencyBandList.add(new FrequencyBand(3));
		frequencyBandList.add(new FrequencyBand(6));

		List<Transmitter> transmitterList = new ArrayList<>();
		transmitterList.add(new Transmitter(1, "Tx1", 2));
		transmitterList.add(new Transmitter(2, "Tx2", 5));
		transmitterList.add(new Transmitter(3, "Tx3", 2));
		transmitterList.add(new Transmitter(4, "Tx4", 1));
		transmitterList.add(new Transmitter(5, "Tx5", 6));
		transmitterList.add(new Transmitter(6, "Tx6", 2));
		transmitterList.add(new Transmitter(7, "Tx7", 6));

		return new FrequencyPlan(frequencyBandList, transmitterList);
	}
}
