package com.nit.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class DashBoardUtil {
	
	public void generatePieChart(List<Object[]> list,String path) {
		DefaultPieDataset dataset=new DefaultPieDataset();
		for(Object[] ob:list) {
			dataset.setValue(ob[0].toString(), Double.valueOf(ob[1].toString()));
		}
		
		JFreeChart chart=ChartFactory.createPieChart("Slot Requests", dataset);
		
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/piechart.jpg"), chart, 300, 300);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void generateBarChart(List<Object[]> list,String path) {
		DefaultCategoryDataset dataset=new DefaultCategoryDataset();
		for(Object[] obj:list) {
			dataset.setValue(Double.valueOf(obj[1].toString()), obj[0].toString(), "");
		}
		
		JFreeChart chart=ChartFactory.createBarChart("Slot Requests", "Types Of Status", "Status", dataset);
		
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/barchart.jpg"), chart, 300, 300);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
