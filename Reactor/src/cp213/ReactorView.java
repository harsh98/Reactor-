package cp213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Manual Reactor GUI. Same layout as ReactorControllerView with extra controls.
 *
 * @author Harsh Joshi
 * @version 2017-12-08
 */
@SuppressWarnings("serial")
public class ReactorView extends JPanel {

    // ---------------------------------------------------------------
    /**
     * Continue button listener.
     */
    private class ContinueButtonListener implements ActionListener {
	@Override
	public void actionPerformed(final ActionEvent e) {
	    // Comment out this line when using Reactor run() method.
	    ReactorView.this.model.tick();
	}
    }

    // ---------------------------------------------------------------
    /**
     * DropRods button listener.
     */
    private class DropRodsButtonListener implements ActionListener {
	@Override
	public void actionPerformed(final ActionEvent e) {
	    ReactorView.this.model.dropRods();
	    ReactorView.this.updateView();
	}
    }

    // ---------------------------------------------------------------
    /**
     * Quit button listener.
     */
    private class QuitButtonListener implements ActionListener {
	@Override
	public void actionPerformed(final ActionEvent e) {
	    ReactorView.this.model.quit();
	    ReactorView.this.updateView();
	}
    }

    // ---------------------------------------------------------------
    /**
     * Reactor properties listener.
     */
    private class ReactorListener implements PropertyChangeListener {

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
	    ReactorView.this.updateView();
	}
    }

    // ---------------------------------------------------------------
    /**
     * Raise and Lower Rods buttons listener.
     */
    private class RodsButtonListener implements ActionListener {
	@Override
	public void actionPerformed(final ActionEvent e) {
	    final String direction = ((JButton) e.getSource()).getText();

	    if (direction.equals("+")) {
		ReactorView.this.model.raiseRods();
	    } else if (direction.equals("-")) {
		ReactorView.this.model.lowerRods();
	    }
	    // Comment out this line when using Reactor run() method.
	    ReactorView.this.model.tick();
	}
    }

    // ---------------------------------------------------------------
    private static final DecimalFormat decimalFormat = new DecimalFormat(
	    "#####.#");
    private final JLabel averagePowerLabel = new JLabel("",
	    SwingConstants.TRAILING);
    private final JLabel averageTemperatureLabel = new JLabel("",
	    SwingConstants.TRAILING);
    private final JButton continueButton = new JButton("Continue");
    private final JButton dropRodsButton = new JButton("Drop");
    private final JButton lowerButton = new JButton("-");
    private final JButton quitButton = new JButton("Quit");
    private Reactor model = null;
    private final JLabel powerLabel = new JLabel("", SwingConstants.TRAILING);
    private final JButton raiseButton = new JButton("+");
    private final JLabel rodsLabel = new JLabel("", SwingConstants.TRAILING);
    private final JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
    private final JLabel temperatureLabel = new JLabel("",
	    SwingConstants.TRAILING);
    private final JLabel timeLabel = new JLabel("", SwingConstants.CENTER);
    private TemperatureView temperatureView = null;
    private PowerView powerView = null;
    private AverageTemperatureView averageTemperatureView = null;
    private AveragePowerView averagePowerView = null;
    private RodsView rodsView = null;

    /**
     * The view constructor.
     *
     * @param model
     *            The reactor.
     */
    public ReactorView(final Reactor model) {
	this.model = model;
	this.layoutView();
	this.registerListeners();
	this.updateView();
    }

    /**
     * Uses the GridLayout to place the labels and buttons.
     */
    private void layoutView() {
	this.setLayout(new GridLayout(12, 3));

	this.temperatureView = new TemperatureView(this.model);
	this.powerView = new PowerView(this.model);
	this.averageTemperatureView = new AverageTemperatureView(this.model);
	this.averagePowerView = new AveragePowerView(this.model);
	this.rodsView = new RodsView(this.model);

	// Display Status
	this.add(new JLabel("Status: "));
	this.add(this.statusLabel);
	this.add(new JLabel(""));
	// Display Time
	this.add(new JLabel("Time: "));
	this.add(this.timeLabel);
	this.add(new JLabel());
	// Display Temperature
	this.add(new JLabel("Temperature: "));
	this.add(this.temperatureView);
	this.add(this.temperatureLabel);
	// Display Average Temperature
	this.add(new JLabel("Avg Temperature: "));
	this.add(this.averageTemperatureView);
	this.add(this.averageTemperatureLabel);
	// Display Power
	this.add(new JLabel("Power: "));
	this.add(this.powerView);
	this.add(this.powerLabel);
	// Display Average Power
	this.add(new JLabel("Avg Power: "));
	this.add(this.averagePowerView);
	this.add(this.averagePowerLabel);
	// Display Rods Height
	this.add(new JLabel("Rods Height: "));
	this.add(this.rodsView);
	this.add(this.rodsLabel);
	// Add Rods buttons
	this.add(new JLabel("Raise Rods: "));
	this.add(this.raiseButton);
	this.add(new JLabel());
	this.add(new JLabel("Lower Rods: "));
	this.add(this.lowerButton);
	this.add(new JLabel());
	this.add(new JLabel("Continue: "));
	this.add(this.continueButton);
	this.add(new JLabel());
	this.add(new JLabel("Drop Rods: "));
	this.add(this.dropRodsButton);
	this.add(new JLabel());
	this.add(new JLabel());
	this.add(this.quitButton);
    }

    /**
     * Assigns listeners to the buttons and labels.
     */
    private void registerListeners() {
	this.raiseButton.addActionListener(new RodsButtonListener());
	this.lowerButton.addActionListener(new RodsButtonListener());
	this.continueButton.addActionListener(new ContinueButtonListener());
	this.dropRodsButton.addActionListener(new DropRodsButtonListener());
	this.quitButton.addActionListener(new QuitButtonListener());
	this.model.addPropertyChangeListener(new ReactorListener());
    }

    /**
     * Updates the GUI with the current state of the reactor.
     */
    private void updateView() {
	this.temperatureLabel
		.setText(decimalFormat.format(this.model.getTemperature()));
	this.averageTemperatureLabel.setText(
		decimalFormat.format(this.model.getAverageTemperature()));
	this.powerLabel.setText(decimalFormat.format(this.model.getPower()));
	this.averagePowerLabel
		.setText(decimalFormat.format(this.model.getAveragePower()));
	this.statusLabel.setText(this.model.getStatus().toString());
	this.timeLabel.setText(Integer.toString(this.model.getTicks()));
	this.rodsLabel.setText(Integer.toString(this.model.getRodsHeight()));
	this.temperatureView.repaint();
	this.powerView.repaint();
	this.averageTemperatureView.repaint();
	this.averagePowerView.repaint();
	this.rodsView.repaint();
    }

}
