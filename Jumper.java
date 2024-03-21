import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import java.awt.Color;
public class Jumper extends Bug{
    private int maxSteps; 
    private int steps;
    private int turnCount; 
    public Jumper()
    {
		maxSteps = 10; 
		steps = 0; 
		turnCount = 0; 
        setColor(Color.BLUE);
    }
    
    public Jumper(int max)
    {
		maxSteps = max; 
		steps = 0; 
		turnCount = 0; 
        setColor(Color.BLUE);
    }

    /**
     * Moves if it can move, turns otherwise.
     */
    public void act()
    {
        if (canMove() && steps < maxSteps){
            move();
            steps++; 
            turnCount = 0; 
        }
        else if (steps == maxSteps){
			setDirection(getDirection() + Location.HALF_RIGHT * (int)(Math.random() * 7 + 1));
			steps = 0; 
			turnCount = 1; 
		}
        else{
			steps = 0; 
			turn();
			turnCount++; 
			if (turnCount >= 8){
				Grid<Actor> gr = getGrid();
				Location next = getLocation().getAdjacentLocation(getDirection());
				while (!gr.isValid(next)){
					turn(); 
					next = getLocation().getAdjacentLocation(getDirection());
				}
				moveTo(next);
				turnCount = 0; 
				steps = 0; 
			}
        }
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
        Location next2 = next1.getAdjacentLocation(getDirection());
        if (gr.isValid(next2))
            moveTo(next2);
        else
            removeSelfFromGrid();
        Blossom flower = new Blossom((int)(Math.random() * 25 + 1));
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
        // ok to move into empty location
        // not ok to move onto any other actor
    }
}
