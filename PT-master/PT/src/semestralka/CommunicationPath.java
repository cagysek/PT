package semestralka;

import java.util.*;
import java.util.stream.Collectors;

public class CommunicationPath {
	private final List<Router> router;
    private final List<Link> link;
    private Set<Router> settledNodes;
    private Set<Router> unSettledNodes;
    private Map<Router, Router> predecessors;
    private Map<Router, Integer> distance;

    public CommunicationPath(Topology topology) {
        // create a copy of the array so that we can operate on this array
        this.router = new ArrayList<Router>(topology.getRouterList());
        this.link = new ArrayList<Link>(topology.getLinkList());
    }

    public void execute(Router source) {
        settledNodes = new HashSet<Router>();
        unSettledNodes = new HashSet<Router>();
        distance = new HashMap<Router, Integer>();
        predecessors = new HashMap<Router, Router>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
        	Router node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }
/*
    private void findMinimalDistances(Router node) {
        List<Router> adjacentNodes = getNeighbors(node);
    
            for (Router target : adjacentNodes) {
           	    int temp = getDistance(node, target);
                if (getShortestDistance(target) > getShortestDistance(node)
                        + temp) {
                    distance.put(target, getShortestDistance(node)
                            + temp);
                    predecessors.put(target, node);
                    unSettledNodes.add(target);
                }
            }
        
       

    }
    
  */  
    private void findMinimalDistances(Router node) {
        List<Router> adjacentNodes = getNeighbors(node);
        for (Router target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }
    
    

    private int getDistance(Router node, Router target) {
   /*     for (Link edge : link) {
        
            if (edge.getFromRouter().equals(node) && edge.getToRouter().equals(target))
            		 {
                return edge.maxBandwidthWithoutFail();
            }
        }
       */ 
        
        return node.getNeighbours().get(target);
        //throw new RuntimeException("Should not happen");
    }

    private List<Router> getNeighbors(Router node) {
    		Map<Router, Integer> temp= node.getNeighbours();
    		
    		return temp
    		        .entrySet()
    		        .stream()
    		        .map(e -> e.getKey())
    		        .collect(Collectors.toList());
    }

    private Router getMinimum(Set<Router> vertexes) {
    	Router minimum = null;
        for (Router vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Router vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(Router destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Router> getPath(Router target) {
        LinkedList<Router> path = new LinkedList<Router>();
        Router step = target;
        // check if a path exists
       
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }
}
