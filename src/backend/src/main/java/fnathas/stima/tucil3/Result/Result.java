package fnathas.stima.tucil3.Result;

import java.util.List;

public class Result {
    private List<String> path;
    private long executionTime;
    private int totalNodesVisited;

    public Result(List<String> path, long executionTime, int totalNodesVisited) {
        this.path = path;
        this.executionTime = executionTime;
        this.totalNodesVisited = totalNodesVisited;
    }

    // getters and setters
    public List<String> getPath() {
        return path;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public int getTotalNodesVisited() {
        return totalNodesVisited;
    }
}
