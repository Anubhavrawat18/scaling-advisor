package com.advisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;
import java.util.*;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

@RestController
@RequestMapping("/api")
class MetricsController {
    
    private final RecommendationService recommendationService;
    
    public MetricsController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }
    
    @PostMapping("/metrics")
    public Map<String, String> submitMetrics(@RequestBody MetricsData metrics) {
        String recommendation = recommendationService.analyze(metrics);
        return Map.of("recommendation", recommendation, "status", "success");
    }
    
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "healthy", "service", "scaling-advisor");
    }
}

@Service
class RecommendationService {
    
    public String analyze(MetricsData metrics) {
        double cpu = metrics.getCpuUsage();
        double memory = metrics.getMemoryUsage();
        
        if (cpu < 20 && memory < 30) {
            return "t3.micro - Downsize to save costs";
        } else if (cpu > 80 || memory > 80) {
            return "t3.large - Upgrade for better performance";
        } else {
            return "t3.small - Current size is optimal";
        }
    }
}

class MetricsData {
    private double cpuUsage;
    private double memoryUsage;
    private String instanceId;
    
    public double getCpuUsage() { return cpuUsage; }
    public void setCpuUsage(double cpuUsage) { this.cpuUsage = cpuUsage; }
    
    public double getMemoryUsage() { return memoryUsage; }
    public void setMemoryUsage(double memoryUsage) { this.memoryUsage = memoryUsage; }
    
    public String getInstanceId() { return instanceId; }
    public void setInstanceId(String instanceId) { this.instanceId = instanceId; }
}
