package fnathas.stima.tucil3.AStar;

import fnathas.stima.tucil3.Result.Result;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


class Node {
    String word;
    int cost;
    Node parent;

    Node(String word, int cost, Node parent) {
        this.word = word;
        this.cost = cost;
        this.parent = parent;
    }
}

@Service
public class AStar {
    public Result aStar(String start, String goal) {

        Set<String> dictionary = new HashSet<>();
        try {
            dictionary = Files.lines(Paths.get("C:/Informatika/sem4/stima/Tucil3_13522145/src/backend/dictionary.txt")).collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!dictionary.contains(start) || !dictionary.contains(goal)) {
            throw new IllegalArgumentException("Start or end word is not in the dictionary");
        }

        if (start.length() != goal.length()) {
            throw new IllegalArgumentException("Start and end word must have the same length");
        }

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        Set<String> visited = new HashSet<>();
        queue.add(new Node(start, heuristic(start, goal), null));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            visited.add(current.word);

            if (current.word.equals(goal)) {
                return new Result(nodeToList(current), visited.size());
            }

            for (int i = 0; i < current.word.length(); i++) {
                char[] chars = current.word.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    String newWord = new String(chars);
                    if (dictionary.contains(newWord) && !visited.contains(newWord)) {
                        queue.add(new Node(newWord, current.cost + 1 + heuristic(newWord, goal), current));
                    }
                }
            }
        }

        return new Result(null, visited.size());
    }

    private List<String> nodeToList(Node node) {
        List<String> list = new ArrayList<>();
        while (node != null) {
            list.add(0, node.word);
            node = node.parent;
        }
        return list;
    }

    private int heuristic(String word, String goal) {
        int cost = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != goal.charAt(i)) {
                cost++;
            }
        }
        return cost;
    }
}


