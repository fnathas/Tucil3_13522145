package fnathas.stima.tucil3.Result;

import java.util.List;

public class Result {
    private List<String> path;
    private int totalNodesVisited;

    public Result(List<String> path, int totalNodesVisited) {
        this.path = path;
        this.totalNodesVisited = totalNodesVisited;
    }

    public List<String> getPath() {
        return path;
    }

    public int getTotalNodesVisited() {
        return totalNodesVisited;
    }
}
