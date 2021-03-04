package hr.fer.zemris.evolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Function {
	ArrayList<Double> x;
	ArrayList<Double> y;
	ArrayList<Double> f;
	
	public Function(String source) throws IOException {
		this.x = new ArrayList<>();
		this.y = new ArrayList<>();
		this.f = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new FileReader(source));
		String line;
		
		while ((line = br.readLine()) != null) {
			String[] parts = line.split("\\s+");
			
			this.x.add(Double.parseDouble(parts[0]));
			this.y.add(Double.parseDouble(parts[1]));
			this.f.add(Double.parseDouble(parts[2]));
		}
		
		br.close();
		
	}
	
	// returns x, y, f
	public ArrayList<Double> getMeasuredValue(int index) {
		ArrayList<Double> result = new ArrayList<>();
		
		result.add(x.get(index));
		result.add(y.get(index));
		result.add(f.get(index));
		
		return result;
	}
	
	// sin(gene0 + gene1*x) + gene2*cos(x*(gene3+y))*(1/(1+exp(pow(x-gene4, 2))))
	public double evaluateIndividual(Individual individual) {
		double sum = 0;
		
		double[] genes = individual.getGenes();
		
		for(int i=0; i<x.size(); i++) {
			double termA = Math.sin(genes[0] + genes[1]*x.get(i));
			double termB = genes[2]*Math.cos(x.get(i)*(genes[3]+y.get(i)));
			double termC = 1 + Math.exp(Math.pow(x.get(i)-genes[4], 2));
			
			double calculatedValue = termA + termB/termC;
			double error = f.get(i) - calculatedValue;
			
			sum += Math.pow(error, 2);
		}
		
		return sum/x.size();
	}
}
