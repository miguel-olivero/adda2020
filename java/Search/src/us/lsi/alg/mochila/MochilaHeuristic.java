package us.lsi.alg.mochila;

import java.util.function.Predicate;

import org.jgrapht.GraphPath;

import us.lsi.graphs.Graphs2;
import us.lsi.graphs.alg.GraphAlg;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.mochila.datos.DatosMochila;

public class MochilaHeuristic {
	
	public static Double heuristic(MochilaVertex v1, Predicate<MochilaVertex> goal, MochilaVertex v2) {
		EGraph<MochilaVertex,MochilaEdge> graph = Graphs2.simpleVirtualGraph(v1);
		return GraphAlg.greedy(
				graph,
				MochilaVertex::greedyEdgeHeuristic,goal).weightToEnd().get();
	}
	
	public static Double voraz(MochilaVertex v1, MochilaVertex v2) {
		return voraz(v1.index, v1.capacidadRestante, v->v.equals(v2));
	}

	public static Double voraz(int index, Double capacidadRestante, Predicate<MochilaVertex> goal) {
		Double r = 0.;
		//Integer ind = index;
		MochilaVertex v = MochilaVertex.of(index, capacidadRestante);
		while (!goal.test(v)) {
			Double a = Math.min(capacidadRestante / DatosMochila.getPeso(index),
					DatosMochila.getNumMaxDeUnidades(index));
			r = r + a * DatosMochila.getValor(index);
			capacidadRestante = capacidadRestante - a * DatosMochila.getPeso(index);
			index = index + 1;
			v = MochilaVertex.of(index, capacidadRestante);
		}
		//System.out.println(String.format("Index %d, Heurística %.2f",ind,r));
		return r;
	}
	
	public static Double voraz(MochilaVertex v1, Predicate<MochilaVertex> goal, MochilaVertex v2) {
		return -voraz(v1,v2);
	}
	
	public static GraphPath<MochilaVertex, MochilaEdge> greadyPath(MochilaVertex v1, Predicate<MochilaVertex> goal) {
		EGraph<MochilaVertex,MochilaEdge> graph = Graphs2.simpleVirtualGraph(v1);
		return GraphAlg.greedy(
				graph,
				MochilaVertex::greedyEdge,goal).search();
	}
	
	

}
