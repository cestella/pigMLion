package pigMLion.lsh;

import pigMLion.lsh.composite.RepeatingLSH;
import pigMLion.lsh.interfaces.ILSHCreator;
import pigMLion.lsh.p_stable.l1.L1LSH;

/**
 * Created with IntelliJ IDEA.
 * User: cstella
 * Date: 9/3/13
 * Time: 8:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class L1DistributionTest  extends LSHDistributionTestUtil
{
    public L1DistributionTest() throws Exception {
        super(.1, .1);
    }

    @Override
    protected ILSHCreator createLSH() throws Exception
    {
        ILSHCreator inner = new L1LSH.Creator(2, (float) .8);
        return new RepeatingLSH.Creator(10, inner);
    }

    @org.junit.Test
    public void testLSH() throws Exception {
        exec();
    }
}
