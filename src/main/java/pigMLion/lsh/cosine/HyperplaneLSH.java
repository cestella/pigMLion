package pigMLion.lsh.cosine;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.random.*;
import pigMLion.lsh.interfaces.IDistanceMetric;
import pigMLion.lsh.interfaces.ILSH;
import pigMLion.lsh.interfaces.ILSHCreator;
import pigMLion.lsh.math.metrics.CosineDistanceMetric;

/**
 * Created with IntelliJ IDEA.
 * User: cstella
 * Date: 9/3/13
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class HyperplaneLSH implements ILSH
{
    public static class Creator implements ILSHCreator
    {
        private int dim = 0;
        public Creator(int dim)
        {
            this.dim = dim;
        }
        public ILSH construct(long seed){
            return new HyperplaneLSH(dim, seed);
        }
    }
    private int dim;
    private RealVector r;
    private HyperplaneLSH(int dim, long seed)
    {
        this.dim = dim;
        RandomGenerator rg = new MersenneTwister();
        rg.setSeed(seed);
        UnitSphereRandomVectorGenerator generator = new UnitSphereRandomVectorGenerator(dim, rg);
        double[] normalVector = generator.nextVector();
        r = new ArrayRealVector(normalVector);
    }
    public IDistanceMetric getMetric() {
        return new CosineDistanceMetric();
    }

    public long apply(RealVector vector)
    {
        return r.dotProduct(r) >= 0?1:0;
    }
}
