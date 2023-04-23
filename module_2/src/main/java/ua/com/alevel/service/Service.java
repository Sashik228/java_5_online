package ua.com.alevel.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static ua.com.alevel.controller.Controller.departurePoint;
import static ua.com.alevel.controller.Controller.destination;
import static ua.com.alevel.util.Color.Colors.CYAN;
import static ua.com.alevel.util.Color.Colors.WHITE;
import static ua.com.alevel.util.Enums.Enum.*;

public class Service {

    private static final Set<Integer> path = new LinkedHashSet<>();
    private static final Set<Integer> allDist = new TreeSet<>();

    public void matrix() throws IOException {

        int[][] adjacencyMatrix = new int[][] {                    //               (0) Ukraine
                { 0, 1000, 0, 0, 1500      },                      //          1000/   \1500
                { 1000, 0, 2500, 0, 900    },                      //             /     \
                { 0, 2500, 0, 1800, 1600   },                      //   Poland (1)- 900 -(4) Slovakia
                { 0, 0, 1800, 0, 1500      },                      //      2500 |1600 /   | 1500
                { 1500, 900, 1600, 1500, 0 }                       //           |   /     |
        };                                                         //  Germany (2)--1800--(3) Czech

        int start = departurePoint;
        int dest = destination;
        ShortestPath.shortestPath(adjacencyMatrix,start,dest);
        ShortestPath.find2ndShortest(adjacencyMatrix,start,dest);
        ShortestPath.outputFile(FILE_OUTPUT);
    }

    static class ShortestPath {

        public static void outputFile(String fileName) throws IOException {
            File file = new File(fileName);
            file.getAbsolutePath();
            System.out.println(CYAN + "Calculation of your trip have been sent to your e-mail, " +
                    "check your box, please >> ");
            System.out.println(WHITE + ">>  " + file.getAbsolutePath());
            System.out.println(" ");
            file.createNewFile();

            List<Integer> list = new ArrayList<>(allDist);
            try(FileWriter writer = new FileWriter(FILE_OUTPUT, false))
            {
                String a = String.valueOf(list.get(0));
                String b = String.valueOf(list.get(1));
                writer.write("The Cheapest route is: " + "$ " + a + '\n' +
                        "   also, if you want, you can choose alternative route"
                        + '\n' + "The 2nd position route is: " + "$ " + b);
                writer.flush();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }

        static void shortestPath(int[][] matrix, int start, int dest) {
            int n = matrix[0].length;
            int[] shortest = new int[n];
            boolean[] visited = new boolean[n];
            int[] parents = new int[n];
            for (int v = 0; v < n; v++) {
                shortest[v] = Integer.MAX_VALUE;
                visited[v] = false;
            }
            shortest[start] = 0;
            parents[start] = NO_PARENT;
            for (int i = 1; i < n; i++) {
                int pre = -1;
                int min = Integer.MAX_VALUE;
                for (int v = 0; v < n; v++) {
                    if (!visited[v] && shortest[v] < min) {
                        pre = v;
                        min = shortest[v];
                    }
                }
                if (pre == -1)
                    return;
                visited[pre] = true;
                for (int v = 0; v < n; v++) {
                    int dist = matrix[pre][v];
                    if (dist > 0 && ((min + dist) < shortest[v])) {
                        parents[v] = pre;
                        shortest[v] = min + dist;
                    }
                }
            }
            allDist.add(shortest[dest]);
            addPath(dest, parents);
        }

        static void addPath(int i, int[] parents) {
            if (i == NO_PARENT)
                return;
            addPath(parents[i], parents);
            path.add(i);
        }

        static void find2ndShortest(int[][] adjacencyMatrix, int src, int dest) {
            int preV = -1, preS = -1, preD = -1;
            List<Integer> list = new ArrayList<>(path);
            for (int i = 0; i < list.size() - 1; i++) {
                int s = list.get(i);
                int d = list.get(i + 1);
                if (preV != -1) {
                    adjacencyMatrix[preS][preD] = preV;
                    adjacencyMatrix[preD][preS] = preV;
                }
                preV = adjacencyMatrix[s][d];
                preS = s;
                preD = d;
                adjacencyMatrix[s][d] = 0;
                adjacencyMatrix[d][s] = 0;
                shortestPath(adjacencyMatrix, src, dest);
            }
        }
    }

    public void deleteOutputFile() {
        File file = new File(FILE_OUTPUT);
        file.delete();
        System.out.println("\u001B[31mInformation was deleted from your device:\u001B[37m");
        file.getAbsolutePath();
        System.out.println(file.getAbsolutePath());
        file = new File(FILE_INPUT);
        file.delete();
        file.getAbsolutePath();
        System.out.println(file.getAbsolutePath());
    }
}