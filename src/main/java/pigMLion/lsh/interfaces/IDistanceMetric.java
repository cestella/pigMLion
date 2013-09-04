package pigMLion.lsh.interfaces;

import org.apache.commons.math3.linear.RealVector;

public interface IDistanceMetric
   {
	   public double apply(RealVector v1, RealVector v2);
   }