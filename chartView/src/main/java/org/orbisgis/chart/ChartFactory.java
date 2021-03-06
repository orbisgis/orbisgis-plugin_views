/**
 * OrbisGIS is a GIS application dedicated to scientific spatial analysis.
 * This cross-platform GIS is developed at the Lab-STICC laboratory by the DECIDE 
 * team located in University of South Brittany, Vannes.
 * 
 * OrbisGIS is distributed under GPL 3 license.
 *
 * Copyright (C) 2007-2014 IRSTV (FR CNRS 2488)
 * Copyright (C) 2015-2016 CNRS (UMR CNRS 6285)
 *
 * This file is part of OrbisGIS.
 *
 * OrbisGIS is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * OrbisGIS is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * OrbisGIS. If not, see <http://www.gnu.org/licenses/>.
 *
 * For more information, please consult: <http://www.orbisgis.org/>
 * or contact directly:
 * info_at_ orbisgis.org
 */
package org.orbisgis.chart;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.data.jdbc.JDBCPieDataset;
import org.jfree.data.jdbc.JDBCXYDataset;
import org.orbisgis.commons.progress.SwingWorkerPM;
import org.orbisgis.corejdbc.DataManager;
import org.orbisgis.sif.edition.EditorManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class is used to build charts
 * Data is collected from a SQL query
 * 
 * @author Erwan Bocher
 */
public class ChartFactory {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChartFactory.class);
    
    
    /**
     * Create a bar chart
     * @param title title
     * @param categoryAxisLabel YAxis label
     * @param valueAxisLabel XAxis label
     * @param sqlQuery SQL Query
     */
    public static void createBarChart(String title, String categoryAxisLabel, String valueAxisLabel, String sqlQuery) {
        ChartElement chart = new ChartElement(ChartElement.CHART.BARCHART, sqlQuery, title);
        JFreeChart jfreechart = org.jfree.chart.ChartFactory.createBarChart(title,
                categoryAxisLabel, valueAxisLabel, null);
        chart.setJFreeChart(jfreechart);
        buildChartElement(chart);
    }
    
    /**
     * Creates a bar chart with a 3D effect. 
     * @param title title
     * @param categoryAxisLabel YAxis label
     * @param valueAxisLabel XAxis label
     * @param sqlQuery SQL Query
     */
    public static void createBarChart3D(String title, String categoryAxisLabel, String valueAxisLabel, String sqlQuery) {
        ChartElement chart = new ChartElement(ChartElement.CHART.BARCHART3D, sqlQuery, title);        
        JFreeChart jfreechart = org.jfree.chart.ChartFactory.createBarChart3D(title,
                categoryAxisLabel, valueAxisLabel, null);
        chart.setJFreeChart(jfreechart);
        buildChartElement(chart);
    }
    
    /**
     * Creates an area chart with default settings.
     * @param title title
     * @param categoryAxisLabel YAxis label
     * @param valueAxisLabel XAxis label
     * @param sqlQuery SQL Query
     */
    public static void createAreaChart(String title, String categoryAxisLabel, String valueAxisLabel, String sqlQuery) {
        ChartElement chart = new ChartElement(ChartElement.CHART.AREACHART, sqlQuery, title);        
        JFreeChart jfreechart = org.jfree.chart.ChartFactory.createAreaChart(title,
                categoryAxisLabel, valueAxisLabel, null);
        chart.setJFreeChart(jfreechart);
        buildChartElement(chart);
    }
    
     /**
     * Creates a line chart with default settings.
     *
     * @param title - the chart title (null permitted).
     * @param categoryAxisLabel - the label for the category axis (null
     * permitted).
     * @param valueAxisLabel - the label for the value axis (null permitted).
     * @param sqlQuery SQL Query
     */
    public static void  createLineChart(String title, String categoryAxisLabel, String valueAxisLabel, String sqlQuery) {
        ChartElement chart = new ChartElement(ChartElement.CHART.LINECHART, sqlQuery, title);        
        JFreeChart jfreechart = org.jfree.chart.ChartFactory.createLineChart(title,
                categoryAxisLabel
                , valueAxisLabel, null);
        chart.setJFreeChart(jfreechart);
        buildChartElement(chart);
    }
    
    /**
     * Creates a line chart with default settings.
     *
     * @param title - the chart title (null permitted).
     * @param categoryAxisLabel - the label for the category axis (null
     * permitted).
     * @param valueAxisLabel - the label for the value axis (null permitted).
     * @param sqlQuery SQL Query
     */
    public static void  createLineChart3D(String title, String categoryAxisLabel, String valueAxisLabel, String sqlQuery) {
        ChartElement chart = new ChartElement(ChartElement.CHART.LINECHART3D, sqlQuery, title);        
        JFreeChart jfreechart = org.jfree.chart.ChartFactory.createLineChart3D(title,
                categoryAxisLabel
                , valueAxisLabel, null);
        chart.setJFreeChart(jfreechart);
        buildChartElement(chart);
    }
    
    
     /**
     * Creates a scatter plot with default settings.
     *
     * @param title - the chart title (null permitted).
     * @param xAxisLabel - a label for the X-axis (null permitted).
     * @param yAxisLabel - a label for the Y-axis (null permitted).
     * @param sqlQuery SQL Query
     */
    public static void  createScatterPlot(String title, String xAxisLabel, String yAxisLabel, String sqlQuery) {
        ChartElement chart = new ChartElement(ChartElement.CHART.SCATTERPLOT, sqlQuery, title);        
        JFreeChart jfreechart = org.jfree.chart.ChartFactory.createScatterPlot(title,
                xAxisLabel
                , yAxisLabel, null);
        chart.setJFreeChart(jfreechart);
        buildChartElement(chart);
    }
    
    
    /**
     * Creates a pie chart with default settings.
     *
     * @param title - the chart title (null permitted).
     * @param sqlQuery SQL Query
     */
    public static void  createPieChart(String title, String sqlQuery) {
        ChartElement chart = new ChartElement(ChartElement.CHART.PIECHART, sqlQuery, title);        
        JFreeChart jfreechart = org.jfree.chart.ChartFactory.createPieChart(title, null);
        chart.setJFreeChart(jfreechart);
        buildChartElement(chart);
    }
    
    /**
     * Creates a 3D pie chart with default settings.
     *
     * @param title - the chart title (null permitted).
     * @param sqlQuery SQL Query
     */
    public static void  createPieChart3D(String title, String sqlQuery) {
        ChartElement chart = new ChartElement(ChartElement.CHART.PIECHART3D, sqlQuery, title);        
        JFreeChart jfreechart = org.jfree.chart.ChartFactory.createPieChart3D(title, null);
        chart.setJFreeChart(jfreechart);
    }
       
    
    /**
     * Build a chart element
     * 
     * @param chart 
     */
    public static void buildChartElement(ChartElement chart) {
        BundleContext thisBundle = FrameworkUtil.getBundle(ChartFactory.class).getBundleContext();
        
        ExecutorService executorService = getExecutorService(thisBundle);
        CreateJFreeChart loadCategoryDataset = new CreateJFreeChart(chart, thisBundle);
        
        if (executorService != null) {
            executorService.execute(loadCategoryDataset);
        } else {
            loadCategoryDataset.execute();
        }  
    }   
    
    /**
     * Get the dataManager 
     * @param thisBundle
     * @return 
     */
    public static DataManager getDataManager(BundleContext thisBundle) {
        ServiceReference<?> serviceDataManager = thisBundle.getServiceReference(DataManager.class.getName());
        if (serviceDataManager != null) {
            return (DataManager) thisBundle.getService(serviceDataManager);
        } else {
            throw new IllegalArgumentException("Cannot access to the data");
        }
    }
    
    /**
     * Get the ExecutorService 
     * @param thisBundle
     * @return 
     */
    public static ExecutorService getExecutorService(BundleContext thisBundle) {
        ServiceReference<?> serviceExecutor = thisBundle.getServiceReference(ExecutorService.class.getName());
        if (serviceExecutor != null) {
            return (ExecutorService) thisBundle.getService(serviceExecutor);
        } else {
            return null;
        }
    }
    
    /**
     * Open the {@link org.orbisgis.char.ChartElement}
     * 
     * @param thisBundle
     * @param chart 
     */
    public static void openChartElement(BundleContext thisBundle, ChartElement chart){
        ServiceReference<?> serviceReference = thisBundle.getServiceReference(EditorManager.class.getName());
        // serviceReference  may be null if not available
        if(serviceReference != null) {
            EditorManager editorManager= (EditorManager ) thisBundle .
                    getService(serviceReference );
            if (!editorManager.getEditableElements().contains(chart)) {
                editorManager.openEditable(chart);
            }
        }
    }
    
    /**
     * Create a JFreeChart object from a chartElement
     *
     *
     * @author Erwan Bocher
     */
    public static class CreateJFreeChart extends SwingWorkerPM {

        private final BundleContext thisBundle;
        private final ChartElement chart;

        public CreateJFreeChart(ChartElement chart, BundleContext thisBundle) {
            this.thisBundle = thisBundle;
            this.chart = chart;
            setTaskName("Preparing chart...");
        }

        @Override
        protected Object doInBackground() throws Exception {
            try (Connection connection = getDataManager(thisBundle).getDataSource().getConnection()) {
                switch (chart.getChartType()) {
                    case BARCHART:
                    case BARCHART3D:
                    case AREACHART:
                    case LINECHART:
                    case LINECHART3D:
                        JDBCCategoryDataset dataset = new JDBCCategoryDataset(connection, chart.getSqlQuery());
                        chart.getJfreeChart().getCategoryPlot().setDataset(dataset);
                        break;    
                    case SCATTERPLOT:
                        JDBCXYDataset datasetXY = new JDBCXYDataset(connection, chart.getSqlQuery());
                        chart.getJfreeChart().getXYPlot().setDataset(datasetXY);
                        break;
                    case PIECHART:
                    case PIECHART3D:
                        JDBCPieDataset pieDataset = new JDBCPieDataset(connection, chart.getSqlQuery());
                        PiePlot piePlot = (PiePlot) chart.getJfreeChart().getPlot();
                        piePlot.setDataset(pieDataset);                        
                        break;
                    case BUBBLECHART:
                    case HISTOGRAM:                                        
                    case TIMESERIESCHART:
                    case XYAREACHART:
                    case XYBARCHART:
                    case XYLINECHART:
                        break;
                    default:
                        break;
                }
                openChartElement(thisBundle, chart);
            } catch (SQLException ex) {
                LOGGER.error(ex.getLocalizedMessage(), ex);
            }
            return null;
        }
    }
    
    
}
