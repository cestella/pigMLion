package pigMLion.lsh.p_stable.l2;

import org.apache.commons.math3.random.RandomDataImpl;
import org.apache.commons.math3.random.RandomGenerator;

import pigMLion.lsh.interfaces.IDistanceMetric;
import pigMLion.lsh.interfaces.ILSHCreator;
import pigMLion.lsh.interfaces.ILSH;
import pigMLion.lsh.math.metrics.L2DistanceMetric;
import pigMLion.lsh.p_stable.stabledistribution.AbstractStableDistributionFunction;

public class L2LSH extends AbstractStableDistributionFunction {

	protected static class L2Sampler implements AbstractStableDistributionFunction.ISampler
	   {


	      public double apply(RandomDataImpl randomData)
	      {
	            return randomData.nextGaussian(0,1); 
	      }
	         
	   }
	public static class Creator implements ILSHCreator
	{
		int dim;
		float w;
		
		public Creator(int dim, float w)
		{
			this.dim = dim;
			this.w = w;
			
		}
		public ILSH construct(long seed) {
			return new L2LSH(dim, w, seed);
		}
		
	}
	private static ISampler sampler = new L2Sampler();
	public static IDistanceMetric metric = new L2DistanceMetric();
	/**
	 * Constructs a new instance.
	 */
	public L2LSH(int dim, float w, RandomGenerator rand) {
		super(dim, w, rand);
	}

	public L2LSH(int dim, float w, long seed) {
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
