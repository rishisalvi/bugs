import info.gridworld.actor.Flower;
import java.awt.Color;
public class Blossom extends Flower{
    private static final Color DEFAULT_COLOR = Color.GREEN;
    private static double factor; 
    private static int steps;
    private static int stepLimit; 

    /**
     * Constructs a pink flower.
     */
    public Blossom()
    {
        steps = 0; 
        stepLimit = 10; 
        factor = 1.00 / stepLimit; 
        setColor(DEFAULT_COLOR);
    }

    /**
     * Constructs a blossom of a given lifetime.
     * @param initialColor the initial lifetime of this blossom
     */
    public Blossom(int limit)
    {
        steps = 0; 
        stepLimit = limit;
        factor = 1.00 / stepLimit; 
        setColor(DEFAULT_COLOR);
    }

    /**
     * Causes the color of this flower to darken.
     */
    public void act()
    {
        if (steps >= stepLimit)
            removeSelfFromGrid();
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - factor));
        int green = (int) (c.getGreen() * (1 - factor));
        int blue = (int) (c.getBlue() * (1 - factor));

        setColor(new Color(red, green, blue));
        steps++; 
    }
}
