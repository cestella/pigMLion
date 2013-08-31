package pigMLion.lsh.math;

import org.apache.commons.math.linear.RealVector;

import pigMLion.lsh.interfaces.IDistanceMetric;

public class L1DistanceMetric implements IDistanceMetric
{
	public double apply(RealVector v1, RealVector v2) {
		return v1.getL1Distance(v2);
	}
	
	public static IDistanceMetric INSTANCE = new L1DistanceMetric();
}
