import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import java.awt.Color;
public class Jumper extends Bug{
    /**
     * Constructs a red bug.
     */
    public Jumper()
    {
        setColor(Color.BLUE);
    }

    /**
     * Moves if it can move, turns otherwise.
     */
    public void act()
    {
        if (canMove())
            move();
        else
            turn();
    }

    /**
     * Turns the bug 45 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next1 = loc.getAdjacentLocation(getDirection());
        Location next2 = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next2))
            moveTo(next2);
        else
            removeSelfFromGrid();
        Blossom flower = new Blossom((int)(Math.random() * 20 + 1));
        flower.putSelfInGrid(gr, loc);
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * @return true if this bug can move.
     */
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next1 = loc.getAdjacentLocation(getDirection());
        Location next2 = next1.getAdjacentLocation(getDirection());
        if (!gr.isValid(next2))
            return false;
        Actor neighbor = gr.get(next2);
        return (neighbor == null);
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }
}
