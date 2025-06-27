A DevOps CI/CD pipeline demo that provides intelligent EC2 instance scaling recommendations.

## Features
- REST API for metrics submission
- Smart scaling recommendations
- Complete CI/CD pipeline
- Docker containerization
- Monitoring with Grafana

## Architecture 
Git → Jenkins → Maven → Docker → Deploy → Monitor

## Quick Start
```bash
mvn package
java -jar target/scaling-advisor-1.0.0.jar
```