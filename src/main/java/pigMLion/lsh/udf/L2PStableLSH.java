package pigMLion.lsh.udf;

import org.apache.commons.math.MathException;
import pigMLion.lsh.interfaces.ILSH;
import pigMLion.lsh.p_stable.l2.L2LSH;

/**
 * Created with IntelliJ IDEA.
 * User: cstella
 * Date: 8/30/13
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class L2PStableLSH extends AbstractPStableLSH{
    public L2PStableLSH(String sDim, String sW, String sSeed)
    {
        super(sDim, sW, sSeed);
    }
    @Override
    protected ILSH getHash(int dim, float w, long seed) throws MathException
    {
        return new L2LSH.Creator(dim, w).construct(seed);
    }
}
