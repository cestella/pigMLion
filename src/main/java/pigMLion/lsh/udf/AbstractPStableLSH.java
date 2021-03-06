package pigMLion.lsh.udf;

import org.apache.commons.math3.linear.RealVector;
import org.apache.pig.EvalFunc;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.Tuple;
import pigMLion.lsh.interfaces.ILSH;
import pigMLion.lsh.interfaces.ILSHCreator;
import pigMLion.lsh.composite.RepeatingLSH;
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
    public AbstractPStableLSH(String sDim, String sW, String sSeed, String sRepeat)
    {
        int dim = Integer.parseInt(sDim);
        float w = Float.parseFloat(sW);
        long seed = Long.parseLong(sSeed);
        int repeat = Integer.parseInt(sRepeat);
        hasher = new RepeatingLSH.Creator(repeat, getHash(dim, w)).construct(seed);
    }
    protected abstract ILSHCreator getHash(int dim, float w) ;

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
