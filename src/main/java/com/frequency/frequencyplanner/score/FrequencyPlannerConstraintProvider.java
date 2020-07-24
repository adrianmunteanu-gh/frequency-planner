package com.frequency.frequencyplanner.score;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

import com.frequency.frequencyplanner.domain.Transmitter;

public class FrequencyPlannerConstraintProvider implements ConstraintProvider {

	@Override
	public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
		return new Constraint[]{
                firstNeighbour(constraintFactory)
        };
    }

	private Constraint firstNeighbour(ConstraintFactory constraintFactory) {
		return constraintFactory
				.from(Transmitter.class)
				.penalize("ceva",
						HardSoftScore.ONE_HARD);
	}
}