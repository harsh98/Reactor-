package cp213;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Horizontal bar view of current reactor rod height.
 * 
 * @author Harsh Joshi
 * @version 2017-12-08
 */
@SuppressWarnings("serial")
public class RodsView extends BarView {

    private Reactor model = null;

    /**
     * Constructor.
     * 
     * @param model
     *            The reactor.
     */
    public RodsView(Reactor model) {
	super(Color.BLACK, Reactor.ROD_LENGTH);
	this.model = model;
    }

    @Override
    public void paintComponent(Graphics g) {
	this.modelValue = this.model.getRodsHeight();
	super.paintComponent(g);
    }

}
