import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class CommunicationPath
{
    public static void computePaths(Router source)
    {
        source.setMinDistance(0.0);
        PriorityQueue<Router> routerQueue = new PriorityQueue<Router>();
        routerQueue.add(source);

    while (!routerQueue.isEmpty()) {
        Router u = routerQueue.poll();

            // Visit each edge exiting u
            for (Link e : u.getRouterEdges())
            {
                Router v = e.getToRouter();
                double weight = e.getWeight();
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

    public List<Router> getShortestPathTo(Router target)
    {
        List<Router> path = new ArrayList<Router>();
        path.add(new Router("targetUser"));
        for (Router router = target; router != null; router = router.getPrevious())
            path.add(router);
        
        path.add(new Router("sourceUser"));
        Collections.reverse(path);
        
        return path;
    }
    
    public double getMaxSize(List<Router> routerList){
    	double tmp;
    	double min = Double.MAX_VALUE;
    	for (int i = 1; i < routerList.size()-2; i++) {
			tmp = routerList.get(i).getBandwithToRouter(routerList.get(i+1));
			if(tmp<min) min = tmp;
					
		}
    	return min;
    	
    	
		
    	
    }
}