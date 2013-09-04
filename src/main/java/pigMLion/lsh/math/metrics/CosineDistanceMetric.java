package pigMLion.lsh.math.metrics;

import org.apache.commons.math3.linear.RealVector;
import pigMLion.lsh.interfaces.IDistanceMetric;

/**
 * Created with IntelliJ IDEA.
 * User: cstella
 * Date: 9/3/13
 * Time: 7:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class CosineDistanceMetric implements IDistanceMetric
{
    public double apply(RealVector v1, RealVector v2)
    {
        double dotProduct = v1.dotProduct(v2);
        return dotProduct / (v1.getNorm()*v2.getNorm());
    }
}
