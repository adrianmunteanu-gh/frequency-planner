package com.frequency.frequencyplanner;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import com.frequency.frequencyplanner.domain.FrequencyPlan;
import com.frequency.frequencyplanner.generator.FrequencyPlanGenerator;

public class FrequencyPlanner {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		SolverFactory<FrequencyPlan> solverFactory = SolverFactory
				.createFromXmlResource("FrequencyPlannerSolverConfig.xml");
		Solver<FrequencyPlan> solver = solverFactory.buildSolver();

		FrequencyPlanGenerator generator = new FrequencyPlanGenerator();
		
		FrequencyPlan unsolvedFrequencyPlan = generator.generator(20, 100, 2, 4);
	
		FrequencyPlan solvedFrequencyPlan = solver.solve(unsolvedFrequencyPlan);

		System.out.println("\nSolved frequency plan:\n" + solvedFrequencyPlan.getSolution());
		
		System.out.println(solver.getBestScore());
	}

}
