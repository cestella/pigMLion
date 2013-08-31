package pigMLion.lsh.math;

import org.apache.commons.math.linear.RealVector;

import pigMLion.lsh.interfaces.IDistanceMetric;

public class L2DistanceMetric implements IDistanceMetric {

	public double apply(RealVector v1, RealVector v2) {
		return v1.getDistance(v2);
	}
	public static IDistanceMetric INSTANCE = new L2DistanceMetric();
}
