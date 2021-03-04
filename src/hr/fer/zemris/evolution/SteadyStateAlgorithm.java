package hr.fer.zemris.evolution;

import java.util.ArrayList;
import java.util.Collections;

public class SteadyStateAlgorithm extends GeneticAlgorithm {

	public SteadyStateAlgorithm(Function function, int maxPopulation, int maxIterations, double mutationProbability) {
		super(function, maxPopulation, maxIterations, mutationProbability);
	}

	@Override
	protected void createGeneration() {
		for(Individual individual : this.population) {
			individual.setCostFunction(this.function.evaluateIndividual(individual));
		}
		
		while (true) {
			Individual individualA = null;
			Individual individualB = null;
			Individual individualC = null;
			
			individualA = Selection.kTournament(this.population, 3);
			while (!individualA.equals(individualB))
				individualB = Selection.kTournament(this.population, 3);
			while (!individualA.equals(individualC) && !individualB.equals(individualC))
				individualC = Selection.kTournament(this.population, 3);
			
			ArrayList<Individual> selectedIndividuals = new ArrayList<>();
			selectedIndividuals.add(individualA);
			selectedIndividuals.add(individualB);
			selectedIndividuals.add(individualC);
			
			Collections.sort(selectedIndividuals);
			
			Individual newMember = Individual.crossover(selectedIndividuals.get(0), selectedIndividuals.get(1));
			newMember.mutate(this.mutationProbability);
			
			newMember.setCostFunction(this.function.evaluateIndividual(newMember));
			
			if (newMember.getCostFunction() <= selectedIndividuals.get(2).getCostFunction()) {
				this.population.set(this.population.indexOf(selectedIndividuals.get(2)), newMember);
				break;
			}
		}
		
	}

}
