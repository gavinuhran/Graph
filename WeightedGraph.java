
/**
 * Gavin Uhran
 * Weighted Graph
 * March 7, 2019
 */

public class WeightedGraph<T> implements WeightedGraphInterface<T>
{
    public static final int nullEdge = 0;
    private static int defCapacity = 50;
    private int numVertices;
    private int maxVertices;
    private T[] vertices;
    private int[][] edges;
    private boolean[] marks;
    
    public WeightedGraph(int maxV)
    {
        numVertices = 0;
        maxVertices = maxV;
        vertices = (T[]) new Object[maxV];
        marks = new boolean[maxV];
        edges = new int[maxV][maxV];
    }
    
    public WeightedGraph()
    {
        this(defCapacity);
    }
    
    // tests if graph is empty
    public boolean empty()
    {
        System.out.println("EMPTY");
        //System.out.println("CHECK IF EMPTY: " + (numVertices == 0));
        return numVertices == 0;
    }
    
    // tests if graph is full
    public boolean full()
    {
        System.out.println("FULL");
        //System.out.println("CHECK IF FULL: " + (numVertices == maxVertices));
        return numVertices == maxVertices;
    }
    
    // Precondition: Vertex is not already in graph
    // Precondition: Vertex is not null
    // adds vertex to graph
    public void addVertex(T vertex)
    {
        System.out.println("ADD VERTEX");
        //System.out.println("ADD: " + vertex);
        vertices[numVertices] = vertex;
        for (int i = 0; i < numVertices; i++)
        {
            edges[numVertices][i] = nullEdge;
            edges[i][numVertices] = nullEdge;
        }
        numVertices++;
    }
        
    // returns true if graph contains vertex
    public boolean hasVertex(T vertex)
    {
        System.out.println("HAS VERTEX");
        //System.out.println("HAS VERTEX");
        for (T v : vertices) 
            if (v.equals(vertex)) return true;
        return false;
    }
    
    // adds an edge with the specified weight from fromVertex to toVertex
    public void setEdge(T fromVertex, T toVertex,int weight)
    {
        System.out.println("SET EDGE");
        //System.out.println("SET EDGE");
        int row = verticeIndex(fromVertex);
        int col = verticeIndex(toVertex);
        edges[row][col] = weight;
    }
    
    public int verticeIndex(T vertex)
    {
        System.out.println("VERTICE INDEX");
        //System.out.println("VERTICE INDEX");
        int i = 0;
        for (i = 0; i < numVertices; i++)
            if (vertices[i].equals(vertex))
                break;
        return i;
    }
    
    // if edge from fromVertex to toVertex exists, return the weight of
    // the edge; otherwise, returns a special "null-edge" value.
    public int weightIs(T fromVertex, T toVertex)
    {
        System.out.println("WEIGHT IS");
        //System.out.println("WEIGHT IS");
        int row = verticeIndex(fromVertex);
        int col = verticeIndex(toVertex);
        return edges[row][col];
    }
    
    // returns a queue of the vertices that are adjacent to the vertex
    public QueueInterface<T> getAdjacentVertices(T vertex)
    {
        System.out.println("GET ADJACENT VERTICES");
        //System.out.println("GET ADJACENT VERTICES");
        int fromIndex = verticeIndex(vertex);
        ArrayQueue<T> queue = new ArrayQueue<T>();
        for (int i = 0; i < numVertices; i++)
        {
            if (edges[fromIndex][i] != 0) queue.add(vertices[i]);
        }
        return queue;
    }
    
    // sets marks for all vertices to false
    public void clearMarks()
    {
        System.out.println("CLEAR MARKS");
        //System.out.println("CLEAR MARKS");
        for (int i = 0; i < marks.length; i++)
            marks[i] = false;
    }
    
    // sets mark for vertex to true
    public void markVertex(T vertex)
    {
        System.out.println("MARK VERTEX");
        //System.out.println("MARK VERTEX: " + vertex);
        marks[verticeIndex(vertex)] = true;
    }
    
    // returns true if vertex is marked
    public boolean isMarked(T vertex)
    {
        System.out.println("IS MARKED");
        //System.out.println("IS MARKED: " + vertex + " | " + (marks[verticeIndex(vertex)] == true));
        return marks[verticeIndex(vertex)] == true;
    }
    
    // returns an unmarked vertex if any exist, otherwise returns null
    public T getUnmarked()
    {
        System.out.println("GET UNMARKED");
        //System.out.print("UNMARKED: ");
        for (int i = 0; i < numVertices; i++)
            if (!marks[i]) 
            {
                //System.out.print(vertices[i] + "\n");
                return vertices[i];
            }
        //System.out.print("NONE\n");
        return null;
    }
    
    // return a String representation of this graph
    public String toString()
    {
        System.out.println("TO STRING");
        String result = "\nGraph representation:\n\t";
        for(int i = 0; i < vertices.length; i++)
        {
            if(vertices[i] != null)
            {
                result += ((String)vertices[i]).substring(0, 3) + "\t";
                result += "\n";
            }
        }
        for(int r = 0; r < vertices.length; r++)
        {
            if(vertices[r] != null)
            {
                result += ((String)vertices[r]).substring(0, 3) + "\t";
                for(int c = 0; c < vertices.length; c++)
                {
                    if(vertices[c] != null)
                    {
                        result += edges[r][c] + "\t";
                    }
                }
                result += "\n";
            }
        }
        return result;
    }
}
