public class GraphPaths
{
    public static boolean isPathDF(WeightedGraphInterface<String> graph, 
                                   String startVertex, String endVertex)
    // Returns true if a path exists on graph, from startVertex to endVertex; 
    // otherwise returns false. Uses depth-first search algorithm.
    {
        System.out.println("IS PATH DF");
        boolean found = false;
        ArrayStack<String> stack = new ArrayStack<String>();
        graph.clearMarks();
        stack.push(startVertex);
        while (!stack.empty() && !found)
        {
            String vertex = stack.pop();
            if (vertex.equals(endVertex)) found = true;
            else
            {
                if (!graph.isMarked(vertex))
                {
                    graph.markVertex(vertex);
                    QueueInterface<String> adjacentVertices = graph.getAdjacentVertices(vertex);
                    while (!adjacentVertices.empty()) stack.push(adjacentVertices.remove());
                }
            }
        }
        return found;
    }

    public static boolean isPathBF(WeightedGraphInterface<String> graph, 
                                   String startVertex, String endVertex)
    // Returns true if a path exists on graph, from startVertex to endVertex; 
    // otherwise returns false. Uses breadth-first search algorithm.
    {
        System.out.println("IS PATH BF");
        boolean found = false;
        ArrayQueue<String> queue = new ArrayQueue<String>();
        graph.clearMarks();
        queue.add(startVertex);
        while (!queue.empty() && found == false)
        {
            String vertex = queue.remove();
            if (vertex.equals(endVertex)) found = true;
            else 
            {
                if (!graph.isMarked(vertex))
                {
                    graph.markVertex(vertex);
                    QueueInterface<String> adjacentVertices = graph.getAdjacentVertices(vertex);
                    while (!adjacentVertices.empty()) queue.add(adjacentVertices.remove());
                }
            }
        }
        return found;
    }

    public static QueueInterface<Flight> shortestPaths(WeightedGraphInterface<String> graph, 
                                                       String startVertex)
    // Determines the shortest distance from startVertex to every other reachable vertex in graph.
    {
        System.out.println("SHORTEST PATHS");
        graph.clearMarks();
        QueueInterface<Flight> resultQueue = new ArrayQueue<Flight>();
        QueueInterface<String> vertexQueue = new ArrayQueue<String>();
        QueueInterface<Flight> priorityQueue = new ArrayQueue<Flight>();
        Flight flight = new Flight(startVertex, startVertex, 0);
        priorityQueue.add(flight);
        while (!priorityQueue.empty())
        {
            System.out.println("About to remove from priorityQueue");
            flight = priorityQueue.remove();
            System.out.println("Removed from priorityQueue");
            if (!graph.isMarked(flight.getToVertex()))
            {
                graph.markVertex(flight.getToVertex()); //Problem here
                resultQueue.add(flight);
                flight.setFromVertex(flight.getToVertex());
                int minDistance = flight.getDistance();
                vertexQueue = graph.getAdjacentVertices(flight.getFromVertex());
                while (!vertexQueue.empty())
                {
                    System.out.println("About to remove from vertexQueue");
                    String vertex = vertexQueue.remove();
                    System.out.println("Removed from vertexQueue");
                    if (!graph.isMarked(vertex))
                    {
                        int newDistance = minDistance + graph.weightIs(flight.getFromVertex(), vertex);
                        Flight newFlight = new Flight(flight.getFromVertex(), vertex, newDistance);
                        priorityQueue.add(flight);
                    }
                }
            }
        }
        System.out.println("The unreachable vertices are:");
        String vertex = graph.getUnmarked();
        while (vertex != null)
        {
            System.out.println(vertex);
            graph.markVertex(vertex);
            vertex = graph.getUnmarked();
        }
        System.out.println("CODE REACHES HERE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return resultQueue;
    }
}