package cp213;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Horizontal bar view of current reactor temperature.
 * 
 * @author Harsh Joshi
 * @version 2017-12-08
 */
@SuppressWarnings("serial")
public class TemperatureView extends BarView {

    private Reactor model = null;

    /**
     * Constructor.
     * 
     * @param model
     *            The reactor.
     */
    public TemperatureView(Reactor model) {
	super(Color.GREEN, Reactor.MAX_TEMP - Reactor.MIN_TEMP);
	this.model = model;
    }

    @Override
    public void paintComponent(Graphics g) {
	this.modelValue = this.model.getTemperature() - Reactor.MIN_TEMP;
	super.paintComponent(g);
    }
}
