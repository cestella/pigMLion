package pigMLion.lsh;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.random.CorrelatedRandomVectorGenerator;
import org.apache.commons.math3.random.GaussianRandomGenerator;
import org.apache.commons.math3.random.MersenneTwister;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pigMLion.lsh.interfaces.IDistanceMetric;
import pigMLion.lsh.interfaces.ILSH;
import pigMLion.lsh.interfaces.ILSHCreator;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: cstella
 * Date: 9/3/13
 * Time: 7:58 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class LSHDistributionTestUtil
{
    public static final int NUM_VECTORS = 10000;
    private List<RealVector> vectors = new ArrayList<RealVector>();
    private ILSH lsh = null;
    private double minProbability;
    private double minDistance;

    public LSHDistributionTestUtil(double minProbability, double minDistance) throws Exception
    {
        this.minProbability = minProbability;
        this.minDistance = minDistance;
        createGenerator();
    }

    public void createGenerator() throws Exception
    {
        long seed = 17399225432l;
        // Create and seed a RandomGenerator (could use any of the generators in the random package here)
        MersenneTwister rg = new MersenneTwister();
		rg.setSeed(seed);  // Fixed seed means same results every time

		// Create a GaussianRandomGenerator using rg as its source of randomness
		GaussianRandomGenerator rawGenerator = new GaussianRandomGenerator(rg);
		double c = 3 * 4 * .5;
		double[] mean = {1, 2};
		double[][] cov = {{9, c}, {c, 16}};
		RealMatrix covariance = MatrixUtils.createRealMatrix(cov);
		// Create a CorrelatedRandomVectorGenerator using rawGenerator for the components
        CorrelatedRandomVectorGenerator generator;
		generator = new CorrelatedRandomVectorGenerator(mean, covariance, 1.0e-12 * covariance.getNorm(), rawGenerator);
        for(int i = 0;i < NUM_VECTORS;++i)
        {
            RealVector v = new ArrayRealVector(generator.nextVector());
            vectors.add(v);
        }
        lsh = createLSH().construct(seed);
    }

    protected abstract ILSHCreator createLSH() throws Exception;


    public void exec() throws Exception
    {
        IDistanceMetric metric = lsh.getMetric();
        Map<Long, List<RealVector>> groupings = new HashMap<Long, List<RealVector>>();
        for(RealVector v : vectors)
        {
            long l = lsh.apply(v);
            List<RealVector> vecs = null;
            if(groupings.containsKey(l))
            {
               vecs = groupings.get(l);
            }
            else
            {
                vecs = new ArrayList<RealVector>();
                groupings.put(l, vecs);
            }
            vecs.add(v);
        }
        int total = 0;
        int error = 0;
        for(Map.Entry<Long, List<RealVector>> group : groupings.entrySet())
        {
            List<RealVector> groupValues = group.getValue();
            for(int i = 0;i < groupValues.size();++i)
            {
                RealVector v_i = groupValues.get(i);
                for(int j = i + 1;j < groupValues.size();++j)
                {
                    total++;
                    RealVector v_j = groupValues.get(j);
                    double dist = metric.apply(v_i, v_j);
                    if(dist >= minDistance)
                    {
                        error++;
                    }
                }
            }
        }
        double errorRate = (1.0*error) / total;
        Assert.assertTrue(errorRate < minDistance);
    }
}
