import moe.hoshinetsu.burkani.util.XpManager;
import org.junit.Assert;
import org.junit.Test;

public class XpManagerTest {

    @Test
    public void getTotalXpTest(){
        int totalXp = XpManager.getTotalXp(10, 0);
        Assert.assertEquals(160, totalXp);
    }
}
