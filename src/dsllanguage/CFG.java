/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsllanguage;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

/**
 *
 * @author lpj11535
 */
public class CFG {
    private Graph<BasicBlock, DefaultEdge> graph;
    public boolean reducible = false;
    public BasicBlock start, end;

    public DefaultEdge addEdge(BasicBlock v, BasicBlock v1) {
        Logger.getAnonymousLogger().info("Added Edge = (" + v + "," + v1 + ")");
        return graph.addEdge(v, v1);
    }

    public boolean addVertex(BasicBlock v) {
        if (graph.containsVertex(v)) {
            Logger.getAnonymousLogger().info("Dropped Duplicate...");
            return false;
        }
        Logger.getAnonymousLogger().info("Added Vertex = " + v);
        return graph.addVertex(v);
    }
    

    public Set<DefaultEdge> edgesOf(BasicBlock v) {
        return graph.edgesOf(v);
    }

    public Set<BasicBlock> vertexSet() {
        return graph.vertexSet();
    }

    public BasicBlock getEdgeSource(DefaultEdge e) {
        return graph.getEdgeSource(e);
    }

    public BasicBlock getEdgeTarget(DefaultEdge e) {
        return graph.getEdgeTarget(e);
    }
    
    
    
    
    public CFG(Graph<BasicBlock, DefaultEdge> graph) {
        this.graph = graph == null ? new ListenableDirectedGraph<>(DefaultEdge.class) : graph;
    }

    public BasicBlock getStart() {
        return start;
    }

    public void setStart(BasicBlock start) {
        this.start = start;
    }

    public BasicBlock getEnd() {
        return end;
    }

    public void setEnd(BasicBlock end) {
        this.end = end;
    }

    public Graph<BasicBlock, DefaultEdge> getGraph() {
        return graph;
    }

    @Override
    public String toString() {
        return "CFG{" + "graph=" + graph + ", start=" + start + ", end=" + end + '}';
    }
    
    public Set<BasicBlock> getIngoing(BasicBlock bb) {
        return graph.edgesOf(bb)
                .stream()
                .filter(e -> graph.getEdgeTarget(e) == bb)
                .map(graph::getEdgeSource)
                .collect(Collectors.toSet());
    }
    
    public Set<BasicBlock> getOutgoing(BasicBlock bb) {
        return graph.edgesOf(bb)
                .stream()
                .filter(e -> graph.getEdgeSource(e) == bb)
                .map(graph::getEdgeTarget)
                .collect(Collectors.toSet());
    }
    
    public CFG reduce() {
        CFG cfg = new CFG(null);
        cfg.start = start;
        cfg.end = end;
        cfg.addVertex(start);
        cfg.addVertex(end);
        
        
        BasicBlock block = start;
//        BasicBlock current;
        BasicBlock prev = null;
        
//        System.out.println(toString());
        Deque<BasicBlock> stack = new ArrayDeque<>();
        stack.push(start);
        Set<BasicBlock> processed = new TreeSet<>();
        processed.add(end);
//        BasicBlock block = new BasicBlock(start.toString());
//        cfg.addVertex(block);
//        reduce(cfg, processed, null, start, block);
        while (!stack.isEmpty()) {
            final BasicBlock current = stack.pop();
//            System.out.println("Popped " + current + " from Stack: " + stack);
            switch (current.reducibility) {
                case BasicBlock.NOT_REDUCIBLE:
                    // Add basic block to the new graph...
                    cfg.addVertex(block);
                    if (prev != null) {
                        cfg.addEdge(prev, block);
                    }

                    // Create a new basic block
                    prev = block;
                    block = new BasicBlock("");
                    prev.addChild(current);
                    cfg.addVertex(block);
                    cfg.addEdge(prev, block);
                    break;
                case BasicBlock.REDUCIBLE:
                    // Add to block...
                    System.out.println("Reduced: " + current.toString());
                    block.addChild(current);
                    break;
                case BasicBlock.REDUCIBLE_SINGLETON:
                    // Add basic block to the new graph...
                    cfg.addVertex(block);
                    if (prev != null) {
                        cfg.addEdge(prev, block);
                    }

                    // Create a new basic block
                    prev = block;
                    block = new BasicBlock("");
                    block.addChild(current);
                    cfg.addVertex(block);
                    cfg.addEdge(prev, block);
                    
                    prev = block;
                    block = new BasicBlock("");
                    cfg.addVertex(block);
                    cfg.addEdge(prev, block);
                    break;
            }
            
            processed.add(current);
            
            
            graph.edgesOf(current)
                    .stream()
                    .map(graph::getEdgeTarget)
                    .filter(b -> !processed.contains(b))
                    .peek(edge -> System.out.println("Edge for vertex: " + current + " is " + edge))
                    .forEachOrdered(stack::push);
            
            final BasicBlock currentBlock = block;
            
//            graph.edgesOf(current)
//                    .stream()
//                    .map(graph::getEdgeTarget)
//                    .filter(b -> cfg.graph.ver)
//                    .forEachOrdered(b -> {
//                        if (cfg.graph.containsVertex(b) && currentBlock.children.stream().anyMatch(_b -> graph.containsVertex(_b))) {
//                            cfg.addEdge(currentBlock, b);
//                        }
//                        
//                    });
            
//            System.out.println("New Stack: " + stack);
            
            
            
        }
        
        cfg.addVertex(block);
        cfg.addEdge(block, end);
        return cfg;
    }

}
