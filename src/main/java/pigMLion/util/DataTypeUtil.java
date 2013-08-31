package pigMLion.util;

import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.RealVector;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.Tuple;
import org.apache.pig.impl.logicalLayer.schema.Schema;

/**
 * Created with IntelliJ IDEA.
 * User: cstella
 * Date: 8/30/13
 * Time: 7:08 PM
 * To change this template use File | Settings | File Templates.
 */
public enum DataTypeUtil
{
    INSTANCE;
    public RealVector convert(Tuple tuple) throws ExecException
    {
        RealVector ret = new ArrayRealVector(tuple.size());

        for(int i = 0;i < tuple.size();++i)
        {
            Number n = (Number)tuple.get(i);
            ret.setEntry(i, n.doubleValue());
        }
        return ret;
    }
}
