package pigMLion.lsh.udf;

import org.apache.commons.math.MathException;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import pigMLion.lsh.interfaces.ILSH;
import pigMLion.lsh.p_stable.l1.L1LSH;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: cstella
 * Date: 8/30/13
 * Time: 7:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class L1PStableLSH extends AbstractPStableLSH
{
    public L1PStableLSH(String sDim, String sW, String sSeed)
    {
        super(sDim, sW, sSeed);
    }

    @Override
    protected ILSH getHash(int dim, float w, long seed) throws MathException
    {
        return new L1LSH.Creator(dim, w).construct(seed);
    }

}
