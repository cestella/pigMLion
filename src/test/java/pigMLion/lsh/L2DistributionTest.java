package pigMLion.lsh;

import pigMLion.lsh.composite.RepeatingLSH;
import pigMLion.lsh.interfaces.ILSHCreator;
import pigMLion.lsh.p_stable.l2.L2LSH;

/**
 * Created with IntelliJ IDEA.
 * User: cstella
 * Date: 9/3/13
 * Time: 8:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class L2DistributionTest extends LSHDistributionTestUtil
{
     public L2DistributionTest() throws Exception {
        super(.1, .1);
    }

    @Override
    protected ILSHCreator createLSH() throws Exception
    {
        ILSHCreator inner = new L2LSH.Creator(2, (float) .4);
        return new RepeatingLSH.Creator(15, inner);
    }

    @org.junit.Test
    public void testLSH() throws Exception {
        exec();
    }
}
