package pigMLion.lsh.p_stable.l1;

import org.apache.commons.math.MathException;
import org.apache.commons.math.random.RandomDataImpl;
import org.apache.commons.math.random.RandomGenerator;


import pigMLion.lsh.interfaces.IDistanceMetric;
import pigMLion.lsh.interfaces.IHashCreator;
import pigMLion.lsh.interfaces.ILSH;
import pigMLion.lsh.math.L1DistanceMetric;
import pigMLion.lsh.p_stable.stabledistribution.AbstractStableDistributionFunction;

public class L1LSH extends AbstractStableDistributionFunction 
{
	protected static class L1Sampler implements AbstractStableDistributionFunction.ISampler
	{

		public double apply(RandomDataImpl randomData) throws MathException {
			
			return randomData.nextCauchy(0, 1);
			
		}

	}
	private static ISampler sampler = new L1Sampler();
	public static IDistanceMetric metric = new L1DistanceMetric();
	
	public static class Creator implements IHashCreator
	{
		int dim;
		float w;
		
		public Creator(int dim, float w)
		{
			this.dim = dim;
			this.w = w;
			
		}
		public ILSH construct(long seed) throws MathException {
			return new L1LSH(dim, w, seed);
		}
		
	}
	
	/**
	 * Constructs a new instance.
	 * @throws MathException 
	 */
	public L1LSH(int dim, float w, RandomGenerator rand) throws MathException {
		super(dim, w, rand);
	}

	public L1LSH(int dim, float w, long seed) throws MathException {
		
		super(dim, w, seed);
	}
	
	public IDistanceMetric getMetric() {
		return metric;
	}

	@Override
	protected ISampler getSampler(
			RandomDataImpl dataSampler) {
		return sampler;
	}

}
