package pigMLion.lsh.interfaces;

import org.apache.commons.math.MathException;


public interface IHashCreator
{
	public ILSH construct(long seed) throws MathException;
}