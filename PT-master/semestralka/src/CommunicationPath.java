import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*******
 * Třída {@code CommunicationPath} se stará o výpočet nejoptimílnější cesty packetu
 * @author cagy
 *
 */
public class CommunicationPath
{
	
	/**
	 * Metoda pro výpočet vzdáleností mezi body
	 * @param source výchozí router
	 */
    public static void computePaths(Router source)
    {
        source.setMinDistance(0.0); 
        PriorityQueue<Router> routerQueue = new PriorityQueue<Router>();
        routerQueue.add(source);

    while (!routerQueue.isEmpty()) {
        Router u = routerQueue.poll();

            
            for (Link e : u.getRouterEdges())
            {
                Router v = e.getToRouter();
                double weight = e.getWeight(e.getBandwidth());
                double distanceThroughU = u.getMinDistance() + weight;
		        if (distanceThroughU < v.getMinDistance()) {
		            routerQueue.remove(v);
		            
		            v.setMinDistance(distanceThroughU) ;
		            v.setPrevious(u);
		            
		            routerQueue.add(v);
		        	}
            }
        }
    }

    
    /**
     * Metoda pro vytvoření  nejkratší cesty do targetu
     * @param target cílový bod
     * @return nejlepší cesta
     */
    public List<Router> getShortestPathTo(Router target)
    {
        List<Router> path = new ArrayList<Router>();
        path.add(new Router("targetUser"));
        for (Router router = target; router != null; router = router.getPrevious()) {
            path.add(router);
        }
        path.add(new Router("sourceUser"));
        Collections.reverse(path);
        
        return path;
    }
    
    /**
     * Metoda pro získání maximální propustnosti aby nedošlo na žádné lince ke ztrátě
     * @param routerList seznam routerů
     * @return maximální velikost
     */
    public double getMaxSize(List<Router> routerList){
	    	double tmp;
	    	double min = Double.MAX_VALUE;
	    	for (int i = 1; i < routerList.size()-2; i++) {
				tmp = routerList.get(i).getBandwithToRouter(routerList.get(i+1));
				if(tmp<min) { 	
					min = tmp;
				}
						
		}
			return min;
    }
 	    
}