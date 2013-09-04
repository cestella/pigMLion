package pigMLion.lsh.p_stable.stabledistribution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.random.CorrelatedRandomVectorGenerator;
import org.apache.commons.math3.random.GaussianRandomGenerator;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;

public class GenerateRandomVectorsCLI {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		// Create and seed a RandomGenerator (could use any of the generators in the random package here)
		RandomGenerator rg = new JDKRandomGenerator();
		rg.setSeed(17399225432l);  // Fixed seed means same results every time

		// Create a GassianRandomGenerator using rg as its source of randomness
		GaussianRandomGenerator rawGenerator = new GaussianRandomGenerator(rg);
		double c = 3 * 4 * .5;
		double[] mean = {1, 2};
		double[][] cov = {{9, c}, {c, 16}};
		RealMatrix covariance = MatrixUtils.createRealMatrix(cov); 
		// Create a CorrelatedRandomVectorGenerator using rawGenerator for the components
		CorrelatedRandomVectorGenerator generator = 
		    new CorrelatedRandomVectorGenerator(mean, covariance, 1.0e-12 * covariance.getNorm(), rawGenerator);
		int num = Integer.parseInt(args[0]);
		int dim = Integer.parseInt(args[1]);
		PrintStream writer = args.length > 2?new PrintStream(new File(args[2])):System.out;
		for(int i = 0;i < num;++i)
		{
			for(int j = 0;j < dim/2;++j)
			{
				// Use the generator to generate correlated vectors
				double[] randomVector = generator.nextVector();
				for(double d : randomVector)
				{
					writer.print(d + " ");
				}
			}
			writer.println("");
		}
		if(writer != System.out)
		{
			writer.flush();
			writer.close();
		}
		
	}

}
