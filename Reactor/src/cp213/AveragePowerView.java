package cp213;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Horizontal bar view of the reactor average power.
 *
 * @author Harsh Joshi
 * @version 2017-12-08
 */
@SuppressWarnings("serial")
public class AveragePowerView extends BarView {

    private Reactor model = null;

    /**
     * Constructor.
     *
     * @param model
     *            The reactor.
     */
    public AveragePowerView(Reactor model) {
	super(Color.BLUE, Reactor.MAX_POWER);
	this.model = model;
    }

    @Override
    public void paintComponent(Graphics g) {
	this.modelValue = this.model.getAveragePower();
	super.paintComponent(g);
    }

}
