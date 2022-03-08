package il.ac.telhai.ds.graph;

import java.util.*;

public class Graph<V extends Comparable<V>, E> implements IGraph<V, E>{

    private Map<V, List<Edge>> graph;
    public Graph(){

        graph = new TreeMap<V, List<Edge>>();
    }
    /**
     * Add a new vertex if none with equal item exists, and return null. Otherwise
     * retain the existing vertex incidents.
     *
     * @param v
     */


    @Override
    public void add(V v) {
        if(containsVertex(v)){
            return;
        }
        graph.put(v,new LinkedList<Edge>());
    }

    /**
     * @param u
     * @param v
     * @return the label of the edge (u,v)
     */
    @Override
    public E getEdge(V u, V v) {
        List<Edge> edgesV = graph.get(v);
        for(Edge e: edgesV){
            if(e.getV1().equals(v) && e.getV2().equals(u)){
                return e.getEdgeLabel();
            }if(e.getV1().equals(u)){
                return e.getEdgeLabel();
            }
        }
        return null;
    }

    /**
     * Add a new edge if none exists between the two vertices Otherwise retain the
     * existing edge and replace the item with the given one. If the vertices u or v
     * do not exist, add them to the graph.
     *
     * @param u
     * @param v
     * @param edgeLabel
     */
    @Override
    public E putEdge(V u, V v, E edgeLabel) {
        Edge e = new Edge(u,v,edgeLabel);
        if(!containsVertex(v) && containsVertex(u)){
            graph.put(v,new LinkedList<Edge>());
            graph.get(v).add(e);
            graph.get(u).add(e);
        }else if(!containsVertex(u) && containsVertex(v)){
            graph.put(u,new LinkedList<Edge>());
            graph.get(u).add(e);
            graph.get(v).add(e);
        }else if(!containsVertex(u) && !containsVertex(v)){
            graph.put(v,new LinkedList<Edge>());
            graph.put(u,new LinkedList<Edge>());
            graph.get(u).add(e);
            graph.get(v).add(e);
        }else{
            removeEdge(v,u);
            graph.get(u).add(e);
            graph.get(v).add(e);
        }
        return edgeLabel;
    }

    /**
     * @param v
     * @return If the graph contains the vertex v.
     */
    @Override
    public boolean containsVertex(V v) {
        return graph.containsKey(v);
    }

    /**
     * Remove the vertex and its edges from the graph, and return its incidents. If
     * the vertex dosn't exit return null.
     *
     * @param v
     */
    @Override
    public void removeVertex(V v) {
        if(!containsVertex(v)){
            return;
        }
        List<Edge> edges = graph.get(v);
        for(Edge e: edges){
            V u = (e.getV1().equals(v))? e.getV2():e.getV1();
            graph.get(u).remove(e);
        }
        graph.remove(v);
    }

    /**
     * @param u
     * @param v
     * @return The label of the edge between the two vertices. Null if the edge does
     * not exist Throws an exception if one of the vertices does not exist.
     */
    @Override
    public E removeEdge(V u, V v) {
        if(!containsVertex(v) || !containsVertex(u)){
            throw new RuntimeException();
        }
        List<Edge> edgeV = graph.get(v);
        List<Edge> edgeU = graph.get(u);
        for(Edge e: edgeV){
            if(e.getV1().equals(u) || e.getV2().equals(u)){
                edgeU.remove(e);
                E edge = e.getEdgeLabel();
                edgeV.remove(e);
                return edge;
            }
        }
        return null;
    }
    @Override
    /**
     * @return The concatenation of the vertices separated by commas.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if(graph.isEmpty()){
            return "";
        }
        for(V v: graph.keySet()){
            sb.append( v.toString() + ",");
        }
        sb.deleteCharAt(sb.toString().length() - 1);
        return sb.toString();
    }
    /**
     * @param u
     * @param v
     * @return The weight of edge (u,v), if it exists. Otherwise return 0.
     */
    @Override
    public double getWeight(V u, V v) {
        List<Edge> edgesV = graph.get(v);
        for(Edge e: edgesV){
            if(e.getV1().equals(u) || e.getV2().equals(u)){
                return e.getWeight();
            }
        }
        return 0;
    }

    /**
     * @returns The concatenation of the vertices separated by newlines Every vertex
     * is printed with a comma separated list of its incident edges. The
     * list is separated from the vertex with a colon.
     */
    @Override
    public String toStringExtended() {
        StringBuilder sb = new StringBuilder();
        if(graph.isEmpty()){
            return "";
        }
        for(V v: graph.keySet()){
            sb.append(v.toString()+":");
            for( Edge e: graph.get(v)){
                sb.append(e.toString()+",");
            }
            if(sb.charAt(sb.length() - 1) == ','){
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.toString().length() - 1);
        return sb.toString();
    }

    /**
     * @param u
     * @param v
     * @return If the the edge (u,v) exists.
     */
    @Override
    public boolean areAdjacent(V u, V v) {
        if(!containsVertex(u) || !containsVertex(v)){
            return false;
        }
        List<Edge> edgeV = graph.get(v);
        for(Edge e: edgeV){
            if(e.getV1() == u || e.getV2() == u){
                return true;
            }
        }
        return false;
    }
    private class Edge{
        private E edgeLabel;
        private V v1;
        private V v2;

        public Edge(V v1, V v2,E edgeLabel) {
            this.v1 = v1;
            this.v2 = v2;
            this.edgeLabel = edgeLabel;
        }

        public V getV1() {
            return v1;
        }

        public void setV1(V v1) {
            this.v1 = v1;
        }

        public V getV2() {
            return v2;
        }

        public void setV2(V v2) {
            this.v2 = v2;
        }
        public double getWeight(){
            if(edgeLabel instanceof Number){
                return ((Number) edgeLabel).doubleValue();
            }else if(edgeLabel instanceof Weighted){
                return ((Weighted) edgeLabel).getWeight();
            }else{
                throw new RuntimeException();
            }
        }

        public E getEdgeLabel() {
            return edgeLabel;
        }

        public void setEdgeLabel(E edgeLabel) {
            this.edgeLabel = edgeLabel;
        }

        public boolean equals(Edge e){
            if(e.getV1() == getV1() && e.getV2() == getV2() && e.getWeight() == getWeight() && e.getEdgeLabel() == getEdgeLabel()){
                return true;
            }if(e.getV1() == getV2() && e.getV2() == getV1() && e.getWeight() == getWeight() && e.getEdgeLabel() == getEdgeLabel()){
                return true;
            }
            return false;
        }

        public boolean sameVertices(Edge e){
            if(e.getV1() == getV1() && e.getV2() == getV2()){
                return true;
            }if(e.getV1() == getV2() && e.getV2() == getV1()){
                return true;
            }
            return false;
        }
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder( "{"+v1.toString()+","+v2.toString()+"}");
            if(edgeLabel instanceof Number){
                sb.append("(" +((Number) edgeLabel).doubleValue() + ")");
            }else if(edgeLabel instanceof Weighted){
                sb.append("(" + ((Weighted) edgeLabel).getWeight() + ")");
            }
            return sb.toString();
        }
    }
}
