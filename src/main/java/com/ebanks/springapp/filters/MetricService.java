package com.ebanks.springapp.filters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
//import org.springframework.boot.actuate.metrics.repository.MetricRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * The Class MetricService.
 */
@Service
public class MetricService {

	// @Autowired
	// private MetricRepository repo1;
	// @Autowired
	// private BufferMetricReader repo;

	/** The counter. */
	@Autowired
	private CounterService counter;

	@Autowired
	private GaugeService gaugeService;

	/** The status metric. */
	private ConcurrentMap<Integer, Integer> statusMetric;

	/** The status metric actuator. */
	private List<ArrayList<Integer>> statusMetricActuator;

	/** The status list. */
	private List<String> statusList;

	/** The metric map. */
	private ConcurrentMap<String, ConcurrentHashMap<Integer, Integer>> metricMap;

	/** The date format. */
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	/** The time map. */
	private ConcurrentMap<String, ConcurrentHashMap<Integer, Integer>> timeMap;

	/**
	 * Increase actuator count.
	 *
	 * @param status the status
	 */
	public void increaseActuatorCount(final int status) {
		counter.increment("status." + status);
		if (!statusList.contains("counter.status." + status)) {
			statusList.add("counter.status." + status);
		}
	}

	/**
	 * Increases the count.
	 *
	 * @param request the request
	 * @param status  the status
	 */
	public void increaseCount(final String request, final int status) {
		String time = dateFormat.format(new Date());
		ConcurrentHashMap<Integer, Integer> statusMap = timeMap.get(time);
		if (statusMap == null) {
			statusMap = new ConcurrentHashMap<>();
		}

		Integer count = statusMap.get(status);
		if (count == null) {
			count = 1;
		} else {
			count++;
		}
		statusMap.put(status, count);
		timeMap.put(time, statusMap);
	}

	/**
	 * Gets the graph data.
	 *
	 * @return the graph data
	 */
	public Object[][] getGraphData() {
		int colCount = statusMetric.keySet().size() + 1;
		Set<Integer> allStatus = statusMetric.keySet();
		int rowCount = timeMap.keySet().size() + 1;
		Object[][] result = new Object[rowCount][colCount];
		result[0][0] = "Time";

		int j = 1;
		for (int status : allStatus) {
			result[0][j] = status;
			j++;
		}
		int i = 1;
		ConcurrentMap<Integer, Integer> tempMap;
		for (Entry<String, ConcurrentHashMap<Integer, Integer>> entry : timeMap.entrySet()) {
			result[i][0] = entry.getKey();
			tempMap = entry.getValue();
			for (j = 1; j < colCount; j++) {
				result[i][j] = tempMap.get(result[0][j]);
				if (result[i][j] == null) {
					result[i][j] = 0;
				}
			}
			i++;
		}

		return result;
	}

	/**
	 * Export metrics.
	 */
	@Scheduled(fixedDelay = 60000)
	private void exportMetrics() {
		/*
		 * Metric<?> metric = null; ArrayList<Integer> statusCount = new
		 * ArrayList<Integer>(); for (String status : statusList) { metric =
		 * repo.findOne(status);
		 * 
		 * if (metric != null) { statusCount.add(metric.getValue().intValue());
		 * counter.reset(metric.getName()); gaugeService.submit(metric.getName(), 10d);
		 * } else { statusCount.add(0); } } statusMetricActuator.add(statusCount);
		 */
	}

	/**
	 * Gets the full metric.
	 *
	 * @return the full metric
	 */
	public Map<String, ConcurrentHashMap<Integer, Integer>> getFullMetric() {
		return metricMap;
	}
}
