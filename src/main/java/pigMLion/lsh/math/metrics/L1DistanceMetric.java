package pigMLion.lsh.math.metrics;

import org.apache.commons.math3.linear.RealVector;
import pigMLion.lsh.interfaces.IDistanceMetric;

public class L1DistanceMetric implements IDistanceMetric
{
	public double apply(RealVector v1, RealVector v2) {
		return v1.getL1Distance(v2);
	}
	
	public static IDistanceMetric INSTANCE = new L1DistanceMetric();
}
