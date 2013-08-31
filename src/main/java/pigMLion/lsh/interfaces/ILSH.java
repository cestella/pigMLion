package pigMLion.lsh.interfaces;

import org.apache.commons.math.linear.RealVector;

import pigMLion.lsh.interfaces.IDistanceMetric;

public interface ILSH 
{
	public IDistanceMetric getMetric();
	public long apply(RealVector vector);
}
