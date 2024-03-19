import info.gridworld.actor.Flower;
import java.awt.Color;
public class Blossom extends Flower{
    private static final Color DEFAULT_COLOR = Color.GREEN;
    private static final double DARKENING_FACTOR = 0.05;
    private static int steps;
    private static int stepLimit; 

    // lose 5% of color value in each step

    /**
     * Constructs a pink flower.
     */
    public Blossom()
    {
        steps = 0; 
        stepLimit = 0; 
        setColor(DEFAULT_COLOR);
    }

    /**
     * Constructs a blossom of a given lifetime.
     * @param initialColor the initial lifetime of this blossom
     */
    public Blossom(int limit)
    {
        stepLimit = limit;
        steps = 0; 
        setColor(DEFAULT_COLOR);
    }

    /**
     * Causes the color of this flower to darken.
     */
    public void act()
    {
        if (steps == 10)
            removeSelfFromGrid();
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

        setColor(new Color(red, green, blue));
        steps++; 
    }
}
