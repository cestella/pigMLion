package pigMLion.lsh.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.linear.RealVector;
import pigMLion.lsh.interfaces.IDistanceMetric;
import pigMLion.lsh.interfaces.ILSH;
import pigMLion.lsh.interfaces.ILSHCreator;

public class RepeatingLSH implements ILSH
{
	public static class Creator implements ILSHCreator
	{
		int numRepetitions;
		ILSHCreator underlyingCreator;
		
		
		public Creator(int numRepetitions, ILSHCreator underlyingCreator)
		{
			this.numRepetitions = numRepetitions;
			this.underlyingCreator = underlyingCreator;
			
		}
		public ILSH construct(long seed){
			return new RepeatingLSH(numRepetitions, underlyingCreator, seed);
		}
		
	}
	private List<ILSH> lshList;
	private int[] coefficients;
	public RepeatingLSH(int numRepetitions, ILSHCreator pHashCreator, long seed)
	{
		lshList = new ArrayList<ILSH>();
		coefficients = new int[numRepetitions];
		Random r = new Random(seed);
		for(int i = 0;i < numRepetitions;++i)
		{
			lshList.add(pHashCreator.construct(r.nextLong()));
			coefficients[i] = Math.abs(r.nextInt());
		}
		
		
	}
	
	public IDistanceMetric getMetric() {
		return lshList.get(0).getMetric();
	}

	public long apply(RealVector vector) {
		long ret = 0;
		for(int i = 0;i < lshList.size();++i)
		{
			ret += coefficients[i] * lshList.get(i).apply(vector);
		}
		return ret;
	}

}
