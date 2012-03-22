package speech.recognition;
import java.awt.Color;
import java.awt.Shape;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ShapeUtilities;

/**
 * Created by IntelliJ IDEA. User: Loke Date: 07.11.11 Time: 12:10 To change
 * this template use File | Settings | File Templates.
 */
public class ChartDrawer {

	public static JFreeChart drawChart(XYSeriesCollection dataset,
			String xaxis, String yaxis) {

		JFreeChart chart = ChartFactory.createXYLineChart("Данные", // Title
				xaxis, // x-axis Label
				yaxis, // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);

		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.black);
		plot.setDomainGridlinePaint(Color.white);

		plot.setRangeGridlinePaint(Color.white);

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

		Shape shape = ShapeUtilities.createDiamond(0);

		renderer.setSeriesShape(0, shape);
		renderer.setSeriesPaint(0, Color.green);

		final Marker target = new ValueMarker(0.0);
		target.setPaint(Color.black);

		target.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
		target.setLabelTextAnchor(TextAnchor.BOTTOM_RIGHT);
		plot.addRangeMarker(target);
		plot.setRenderer(renderer);

		final Marker currentEnd = new ValueMarker(0.0);
		currentEnd.setPaint(Color.red);
		currentEnd.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
		currentEnd.setLabelTextAnchor(TextAnchor.TOP_LEFT);
		plot.addDomainMarker(currentEnd);

		return chart;

	}
}
