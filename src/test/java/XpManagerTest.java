import moe.hoshinetsu.burkani.util.XpManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class XpManagerTest {

    private static DummyPlayer dp;
    private static HashMap<Integer, Integer> testMappings;

    private static void addMapping(int level, int xp){
        testMappings.put(level, xp);
    }

    static {
        testMappings = new HashMap<>();
        dp = new DummyPlayer(0, 0);
        // Mappings: player level to total XP at that level
        // Obtained by running `/xp give @p 1l` and so on.. command in game
        addMapping(1, 7);
        addMapping(5, 55);
        addMapping(10, 160);
        addMapping(15, 315);
        addMapping(16, 352);
        addMapping(25, 910);
        addMapping(30, 1395);
        addMapping(31, 1507);
        addMapping(50, 5345);
        addMapping(100, 30970);
    }

    /**
     * Test for Level up cost calculation method
     */
    @Test
    public void getXpToNextLevelTest() {
        // TODO
        Assert.assertEquals(true, true);
    }

    /**
     * Test for Level to XP conversion
     */
    @Test
    public void getTotalXpTest() {
        for(Map.Entry<Integer, Integer> entry : testMappings.entrySet()){
            int level = entry.getKey(); // Expected Level
            int xp = entry.getValue(); // Total player XP at that level

            // Set dummy player to that level with 0 levelup progress (additional XP)
            dp.setLevel(level);
            dp.setExp(0f);

            // Run assertions
            Assert.assertEquals(xp, XpManager.getPlayerTotalXp(dp));
        }
    }

    /**
     * Test for Total XP to Levels conversion
     */
    @Test
    public void setPlayerTotalXpTest() {
        for(Map.Entry<Integer, Integer> entry : testMappings.entrySet()){
            int expectedLevel = entry.getKey(); // Expected Level
            int totalXP = entry.getValue(); // Total player XP at that level

            // Convert
            XpManager.setPlayerTotalXp(dp, totalXP);

            // Obtain conversion result
            int level = dp.getLevel();
            float xp = dp.getExp();

            // Run assertions
            Assert.assertEquals(expectedLevel, level);
            Assert.assertEquals(0, xp, 0.001); // allowed 0.1% floating point accuracy
        }
    }
}
