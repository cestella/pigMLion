package pigMLion.lsh.p_stable.stabledistribution;

import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomDataImpl;
import org.apache.commons.math3.random.RandomGenerator;

import pigMLion.lsh.interfaces.ILSH;

public abstract class AbstractStableDistributionFunction implements ILSH
{
	public static interface ISampler
	{
		public double apply(RandomDataImpl randomData) ;
	}
   private double[] a;
   private double b;
   private float w;
   private int dim;

   /**
    * Constructs a new instance.
    */
   public AbstractStableDistributionFunction(int dim, float w, RandomGenerator rand)
   {
      reset(dim, w, rand); 
   }

   public AbstractStableDistributionFunction(int dim, float w, long seed)
   {
      RandomGenerator generator = new JDKRandomGenerator();
      generator.setSeed(seed);
      reset(dim
           ,w
           ,generator
           );
   }

   public void reset(int dim, float w, RandomGenerator rand)
   {
      RandomDataImpl dataSampler = new RandomDataImpl(rand);
      ISampler sampler = getSampler(dataSampler);      
      this.a = new double[dim];
      this.dim = dim;
      this.w = w;
      for(int i = 0;i < dim;++i)
      {
         a[i] = sampler.apply(dataSampler);
      }
      b = dataSampler.nextUniform(0, w);
   }


   protected abstract ISampler 
   getSampler(RandomDataImpl dataSampler);
   
   public long apply(RealVector vector)
   {
      double ret = b;
      //inner product
      for(int i = 0;i < dim;++i)
      {
         ret += vector.getEntry(i)*a[i];
      }
      return (long)Math.floor(ret/w);
   } 
}
