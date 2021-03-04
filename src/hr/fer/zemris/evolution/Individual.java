package hr.fer.zemris.evolution;

import java.util.Arrays;
import java.util.Random;

public class Individual implements Comparable<Individual> {
	
	private double[] genes;
	private double costFunction;
	
	// creates individual with genes passed as arguments
	public Individual(double[] genes) {
		this.genes = new double[genes.length];
		
		for(int i=0; i<genes.length; i++) {
			this.genes[i] = genes[i];
		}
	}
	
	// creates individual with random genes
	public Individual(Random r) {
		this.genes = new double[5];
		
		for(int i=0; i<genes.length; i++) {
			this.genes[i] = -4 + 8*r.nextDouble();
		}
	}
	
	public double[] getGenes() {
		return this.genes;
	}
	
	public void setCostFunction(double value) {
		this.costFunction = value;
	}
	
	public double getCostFunction() {
		return this.costFunction;
	}
	
	public static Individual crossover(Individual parentA, Individual parentB) {
		double[] childGenes = new double[parentA.genes.length];
		
		for(int i=0; i<parentA.genes.length; i++) {
			childGenes[i] = (parentA.genes[i] + parentB.genes[i])/2;
		}
		
		return new Individual(childGenes);
	}
	
	public void mutate(double mutationProbability) {
		Random r = new Random();
		
		for(int i=0; i<this.genes.length; i++) {
			if(r.nextDouble() < mutationProbability) this.genes[i] = -4 + 8*r.nextDouble();
		}
	}

	@Override
	public int compareTo(Individual o) {
		Double cfA = this.costFunction;
		Double cfB = o.costFunction;
		
		return cfA.compareTo(cfB);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(genes);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Individual other = (Individual) obj;
		if (!Arrays.equals(genes, other.genes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Individual: genes=" + Arrays.toString(genes);
	}

}
