package pigMLion.lsh.udf;

import org.apache.commons.math.MathException;
import org.apache.commons.math.linear.RealVector;
import org.apache.pig.EvalFunc;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.Tuple;
import pigMLion.lsh.interfaces.ILSH;
import pigMLion.util.DataTypeUtil;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: cstella
 * Date: 8/30/13
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractPStableLSH extends EvalFunc<Long>
{
    private ILSH hasher;
    public AbstractPStableLSH(String sDim, String sW, String sSeed)
    {
                int dim = Integer.parseInt(sDim);
        float w = Float.parseFloat(sW);
        long seed = Long.parseLong(sSeed);
        try {
            hasher = getHash(dim, w, seed);
        } catch (MathException e)
        {
            throw new IllegalStateException("Unable to create LSH instance", e);
        }
    }
    protected abstract ILSH getHash(int dim, float w, long seed) throws MathException;

    @Override
    public Long exec(Tuple t) throws IOException
    {
        RealVector r = null;
        try {
            r = DataTypeUtil.INSTANCE.convert(t);
        } catch (ExecException e) {
            throw new IllegalStateException("Unable to convert tuple: " + t.toString() + " to RealVector");
        }
        return hasher.apply(r);
    }
}
