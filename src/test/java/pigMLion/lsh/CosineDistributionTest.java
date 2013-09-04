package pigMLion.lsh;

import pigMLion.lsh.composite.RepeatingLSH;
import pigMLion.lsh.cosine.HyperplaneLSH;
import pigMLion.lsh.interfaces.ILSHCreator;

/**
 * Created with IntelliJ IDEA.
 * User: cstella
 * Date: 9/3/13
 * Time: 8:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class CosineDistributionTest extends LSHDistributionTestUtil
{

    public CosineDistributionTest() throws Exception {
        super(.1,.1);
    }

    @Override
    protected ILSHCreator createLSH() throws Exception
    {
        ILSHCreator inner = new HyperplaneLSH.Creator(2);
        return new RepeatingLSH.Creator(10, inner);
    }
    @org.junit.Test
    public void testLSH() throws Exception {
        exec();
    }
}
