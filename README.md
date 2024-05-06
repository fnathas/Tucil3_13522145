# Tucil3_13522145

<!-- 
minimal berisi:
i. Penjelasan singkat algoritma IDS dan BFS yang diimplementasikan
ii. Requirement program dan instalasi tertentu bila ada
iii. Command atau langkah-langkah dalam meng-compile atau build program
iv. Author (identitas pembuat) 
-->

# 🪜 Word Ladder

Word ladder (also known as Doublets, word-links, change-the-word puzzles, paragrams, laddergrams, or word golf) is one of the famous word games enjoyed by people of all ages. Word ladder was invented by Lewis Carroll, a writer and mathematician, in 1877. In this game, players are given two words known as the start word and end word. To win the game, players must find a chain of words that can connect the start word and end word. The number of letters in the start word and end word is always the same. Each word adjacent in the word chain can only differ by one letter. In this game, an optimal solution is expected, meaning a solution that minimizes the number of words included in the word chain. Here is an illustration and the rules of the game. For more information:

- [Project Specification](https://docs.google.com/document/d/1TUvKn-vPXhLsxga8K7mjSUbYnInHp2TSRtGFWlngwYk/edit)

## Implementation
In this project, there are three algorithm used to solve a word ladder game.

#### UCS Algorithm
The UCS (Uniform Cost Search) algorithm systematically explores the word ladder game, aiming to find the shortest path between the start and end words. It initializes by validating the presence and equality of lengths of both words in the dictionary. Employing a priority queue, UCS meticulously explores feasible word transitions, ensuring each step minimizes the overall path cost. This process continues until the end word is reached, at which point UCS returns the shortest path discovered, encapsulating it as a list of words. Through its methodical approach, UCS efficiently navigates the word ladder, striving for an optimal solution while minimizing the number of words in the word chain.

#### GBFS Algorithm
The Greedy Best-First algorithm is employed to navigate the word ladder game efficiently, prioritizing paths based on heuristic cost. Initializing with available words from the dictionary, it explores potential transitions from the start word towards the end word, selecting nodes with the lowest heuristic cost via a priority queue. This process continues until the end word is reached, returning the shortest path discovered as a list of words, or null if no path is found, along with the total number of explored words. Through this method, Greedy Best-First aims to swiftly find a solution by favoring paths that seem closest to the end word based on heuristic evaluation.

#### A* Algorithm
The A* algorithm efficiently navigates the word ladder game by combining actual costs from the start word to the current word with heuristic estimations of costs from the current word to the end word. Initializing with a loaded dictionary, it verifies the presence of start and end words and their equal lengths. Utilizing a priority queue to prioritize nodes based on combined costs, it explores potential transitions from the start to the end word, iteratively computing combined costs for each word. By continually selecting nodes with the lowest combined costs for further exploration until the end word is reached, A* aims to find the shortest path while minimizing computational overhead. Upon discovering the solution, it returns the shortest path as a list of words, or null if no path is found, along with the total number of explored words. Through this method, A* strives for optimal efficiency in solving the word ladder game.

## Getting Started

### Prerequisites
1. Install npm
2. Install maven

### Installing Dependencies 🔨

1. Clone this repository and move to the root of this project's directory

   ```
   git clone https://github.com/fnathas/Tucil3_13522145.git
   ```

2. Install dependencies

- frontend

   ```
   cd src/frontend
   ```
   ```
   npm i
   ```

- backend
   ```
   cd src/backend
   ```
   ```
   mvn clean install
   ```

## How to Run 💻

1. open the web

   ```
   cd src/frontend
   ```
   ```
   npm run dev
   ```

2. open the backend in another terminal

   ```
   cd src/backend
   ```
   ```
   mvn spring-boot::run
   ```

#### Note:

- if there's a permission pop-up, pick allow, it will not affect your device 

## Author

- [Farrel Natha Saskoro](https://github.com/fnathas) - 13522145
