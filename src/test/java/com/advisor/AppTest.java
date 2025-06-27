package com.advisor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    
    private RecommendationService service;
    
    @BeforeEach
    void setUp() {
        service = new RecommendationService();
    }
    
    @Test
    void testLowUsageRecommendation() {
        MetricsData metrics = new MetricsData();
        metrics.setCpuUsage(15.0);
        metrics.setMemoryUsage(25.0);
        
        String result = service.analyze(metrics);
        assertTrue(result.contains("t3.micro"));
        assertTrue(result.contains("Downsize"));
    }
    
    @Test
    void testHighUsageRecommendation() {
        MetricsData metrics = new MetricsData();
        metrics.setCpuUsage(85.0);
        metrics.setMemoryUsage(90.0);
        
        String result = service.analyze(metrics);
        assertTrue(result.contains("t3.large"));
        assertTrue(result.contains("Upgrade"));
    }
    
    @Test
    void testOptimalUsageRecommendation() {
        MetricsData metrics = new MetricsData();
        metrics.setCpuUsage(50.0);
        metrics.setMemoryUsage(60.0);
        
        String result = service.analyze(metrics);
        assertTrue(result.contains("optimal"));
    }
}
