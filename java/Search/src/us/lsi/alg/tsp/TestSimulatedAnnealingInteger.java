package us.lsi.alg.tsp;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;

import us.lsi.graphs.Graphs2;
import us.lsi.graphs.SimpleEdge;
import us.lsi.graphs.alg.GraphAlg;
import us.lsi.graphs.alg.SimulatedAnnealingSearch;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.path.GraphPaths;

public class TestSimulatedAnnealingInteger {
	
	
	
	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));
		
		SimulatedAnnealingSearch.numIntentos = 1;
		SimulatedAnnealingSearch.numPorIntento = 400;
		SimulatedAnnealingSearch.numMismaTemperatura = 5;
		SimulatedAnnealingSearch.temperaturaInicial = 100000;
		SimulatedAnnealingSearch.alfa = 0.97;
		SimulatedAnnealingSearch.stop = v->false;
		
		Graph<Integer,SimpleEdge<Integer>> graph = AuxiliaryTsp.generate(50);
		System.out.println(graph);
		System.out.println(graph.edgeSet());
		List<Integer> camino = graph.vertexSet().stream().collect(Collectors.toList());
		camino.add(0);
//		System.out.println(graph.getEdgeWeight(graph.getEdge(0,1)));
		GraphPath<Integer,SimpleEdge<Integer>> path = GraphPaths.of(graph,camino);
		System.out.println(path.getWeight());
		TravelVertexInteger e1 = TravelVertexInteger.of(graph, camino);
		System.out.println(e1);
		
		EGraph<TravelVertexInteger,TravelEdgeInteger> graph2 = Graphs2.simpleVirtualGraphLast(e1,v->v.getWeight());	
		SimulatedAnnealingSearch<TravelVertexInteger, TravelEdgeInteger> m = GraphAlg.simulatedAnnealing(graph2,e1,e->e.weight);
		m.search();
		System.out.println(m.bestWeight);
		System.out.println(m.bestVertex);
	}

}
