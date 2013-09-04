package pigMLion.lsh.udf;

import pigMLion.lsh.interfaces.ILSH;
import pigMLion.lsh.interfaces.ILSHCreator;
import pigMLion.lsh.p_stable.l2.L2LSH;

/**
 * Created with IntelliJ IDEA.
 * User: cstella
 * Date: 8/30/13
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class L2PStableLSH extends AbstractPStableLSH{
    public L2PStableLSH(String sDim, String sW, String sSeed, String sRepeat)
    {
        super(sDim, sW, sSeed, sRepeat);
    }
    @Override
    protected ILSHCreator getHash(int dim, float w)
    {
        return new L2LSH.Creator(dim, w);
    }
}
