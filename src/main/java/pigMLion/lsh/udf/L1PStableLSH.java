package pigMLion.lsh.udf;

import pigMLion.lsh.interfaces.ILSHCreator;
import pigMLion.lsh.p_stable.l1.L1LSH;

/**
 * Created with IntelliJ IDEA.
 * User: cstella
 * Date: 8/30/13
 * Time: 7:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class L1PStableLSH extends AbstractPStableLSH
{
    public L1PStableLSH(String sDim, String sW, String sSeed, String sRepeat)
    {

        super(sDim, sW, sSeed, sRepeat);
    }

    @Override
    protected ILSHCreator getHash(int dim, float w)
    {
        return new L1LSH.Creator(dim, w);
    }

}
