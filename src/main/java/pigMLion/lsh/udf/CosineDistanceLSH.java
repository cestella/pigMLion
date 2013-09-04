package pigMLion.lsh.udf;

import org.apache.commons.math3.linear.RealVector;
import org.apache.pig.EvalFunc;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.Tuple;
import pigMLion.lsh.composite.RepeatingLSH;
import pigMLion.lsh.cosine.HyperplaneLSH;
import pigMLion.lsh.interfaces.ILSH;
import pigMLion.util.DataTypeUtil;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: cstella
 * Date: 9/3/13
 * Time: 7:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class CosineDistanceLSH extends EvalFunc<Long>
{
    private ILSH hasher;
    public CosineDistanceLSH(String sDim, String sSeed, String sRepeat)
    {
        int dim = Integer.parseInt(sDim);
        long seed = Long.parseLong(sSeed);
        int repeat = Integer.parseInt(sRepeat);
        HyperplaneLSH.Creator creator = new HyperplaneLSH.Creator(dim);
        hasher = new RepeatingLSH.Creator(repeat, creator).construct(seed);

    }

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
