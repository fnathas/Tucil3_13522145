package fnathas.stima.tucil3.Controller;

import fnathas.stima.tucil3.UCS.UCS;
import fnathas.stima.tucil3.AStar.AStar;
import fnathas.stima.tucil3.GreedyBestFirst.GreedyBestFirst;
import fnathas.stima.tucil3.Result.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class Controller {

    private final UCS ucs;
    private final AStar aStar;
    private final GreedyBestFirst greedyBestFirst;

    public Controller(UCS ucs, AStar aStar, GreedyBestFirst greedyBestFirst) {
        this.ucs = ucs;
        this.aStar = aStar;
        this.greedyBestFirst = greedyBestFirst;
    }

    @GetMapping(value = "/run", produces = "application/json")
    public Map<String, Object> runAlgorithm(@RequestParam String start, @RequestParam String goal, @RequestParam String algorithm) {
        Object result = switch (algorithm.toLowerCase()) {
            case "ucs" -> ucs.ucs(start, goal);
            case "astar" -> aStar.aStar(start, goal);
            case "gbfs" -> greedyBestFirst.greedyBestFirst(start, goal);
            default -> throw new IllegalArgumentException("Invalid algorithm: " + algorithm);
        };

        if (result instanceof Result) {
            Result res = (Result) result;
            Map<String, Object> map = new HashMap<>();
            map.put("path", res.getPath());
            map.put("executionTime", res.getExecutionTime());
            map.put("totalNodesVisited", res.getTotalNodesVisited());
            return map;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("path", null);
        return map;
    }
}