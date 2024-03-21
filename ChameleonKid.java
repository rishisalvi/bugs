import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;
import java.awt.Color;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ChameleonKid extends ChameleonCritter{
	public ArrayList<Actor> getActors()
    {
		Grid<Actor> gr = getGrid();
		ArrayList<Actor> actors = new ArrayList<>(); 
		Location loc = getLocation();
        Location forward = loc.getAdjacentLocation(getDirection());
        Location backward = loc.getAdjacentLocation(getDirection() + 4 * Location.HALF_RIGHT);
        if (gr.isValid(forward) && gr.get(forward) != null)
			actors.add(gr.get(forward)); 
        if (gr.isValid(backward) && gr.get(backward) != null)
			actors.add(gr.get(backward)); 
        return actors; 
    }
}

