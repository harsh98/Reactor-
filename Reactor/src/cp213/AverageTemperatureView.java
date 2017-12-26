package cp213;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Horizontal bar view of the reactor average temperature.
 * 
 * @author Harsh Joshi
 * @version 2017-12-08
 */
@SuppressWarnings("serial")
public class AverageTemperatureView extends BarView {

    private Reactor model = null;

    /**
     * Constructor.
     * 
     * @param model
     *            The reactor.
     */
    public AverageTemperatureView(Reactor model) {
	super(Color.GREEN, Reactor.MAX_TEMP);
	this.model = model;
    }

    @Override
    public void paintComponent(Graphics g) {
	this.modelValue = this.model.getAverageTemperature();
	super.paintComponent(g);
    }

}
