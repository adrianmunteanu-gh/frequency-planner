package com.frequency.frequencyplanner.score;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.frequencyplanner.domain.FrequencyPlan;

@RestController
@RequestMapping("/frequency")
public class FrequencyPlannerController {

    @Autowired
    private SolverManager<FrequencyPlan, UUID> solverManager;

    @PostMapping("/solve")
    public FrequencyPlan solve(@RequestBody FrequencyPlan problem) {
        UUID problemId = UUID.randomUUID();
        // Submit the problem to start solving
        SolverJob<FrequencyPlan, UUID> solverJob = solverManager.solve(problemId, problem);
        FrequencyPlan solution;
        try {
            // Wait until the solving ends
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Solving failed.", e);
        }
        return solution;
    }

}
