package speech.recognition;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Window extends JFrame {

	private JPanel contentPane;
	private JPanel graphPane;
	private Voice voice;
	private short[] data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		voice = new Voice();

		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					data = voice.load(Window.this);
					if (data != null) {
						repaintChart();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnLoad.setBounds(10, 428, 89, 23);
		contentPane.add(btnLoad);

		graphPane = new JPanel();
		graphPane.setBounds(10, 11, 964, 406);
		graphPane.setLayout(new BoxLayout(graphPane, BoxLayout.X_AXIS));
		ChartPanel chartPanel = new ChartPanel(ChartDrawer.drawChart(
				new XYSeriesCollection(), "Number", "Value"));
		graphPane.add(chartPanel);
		contentPane.add(graphPane);
	}

	private void repaintChart() {
		XYSeries xySeries = new XYSeries("Voice");
		for (int i = 0; i < data.length; i++) {
			xySeries.add(i, data[i]);

		}

		XYSeriesCollection seriesCollection = new XYSeriesCollection(xySeries);

		Container contentPane = getContentPane();

		Container c = (Container) contentPane.getComponent(1);
		c.remove(0);

		c.add(new ChartPanel(ChartDrawer.drawChart(seriesCollection, "Number",
				"Value")));
		validate();
		repaint();
	}
}
