/* BSD 3-Clause License
 *
 * Copyright (c) 2017, Louis Jenkins <LouisJenkinsCS@hotmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     - Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *
 *     - Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 *     - Neither the name of Louis Jenkins, Bloomsburg University nor the names of its 
 *       contributors may be used to endorse or promote products derived
 *       from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package dsllanguage.ASTNodes;

import dsllanguage.BasicBlock;
import dsllanguage.CFG;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

/**
 *
 * @author Louis Jenkins
 */
public class ProgramASTNode implements ASTNode {
    protected List<ASTNode> body = new ArrayList<>();
    
    public ProgramASTNode(ASTNode ...nodes) {
        Collections.addAll(body, nodes);
    }
    
    public Graph<ASTNode, DefaultEdge> getAST() {
        Graph<ASTNode, DefaultEdge> ast = new ListenableDirectedGraph<>(DefaultEdge.class);
        ast.addVertex(this);
        body.forEach(node -> {
            node.addDependency(ast);
            ast.addEdge(this, node);
        });
        
        return ast;
    }
    
    @Override
    public Object execute() {
        body.forEach(ASTNode::execute);
        return null;
    }

    @Override
    public void addDependency(Graph<ASTNode, DefaultEdge> graph) {
        graph.addVertex(this);
        
        body.forEach(node -> {
            node.addDependency(graph);
            graph.addEdge(this, node);
        });
    }
    
    @Override
    public String toString() {
        return "PROGRAM";
    }

    @Override
    public CFG createCFG() {
        CFG cfg = new CFG(null);
        BasicBlock start = new BasicBlock("PROGRAM-START", BasicBlock.NOT_REDUCIBLE);
        BasicBlock end = new BasicBlock("PROGRAM-END", BasicBlock.REDUCIBLE_SINGLETON);
        
        cfg.start = start;
        cfg.end = end;
        
        cfg.addVertex(start);
        cfg.addVertex(end);
        
        CFG firstCFG = body.get(0).createCFG();
        cfg.addVertex(firstCFG.start);
        cfg.addVertex(firstCFG.end);
        cfg.addEdge(start, firstCFG.start);
        
        BasicBlock prev = firstCFG.end;
        
        // If there is only one node, then we would fall through the for loop, so
        // we just copy over all nodes in the CFG here. TODO: Don't Copy Paste
        if (body.size() == 1) {
            firstCFG.vertexSet()
                    .stream()
                        .forEachOrdered(block ->
                            firstCFG
                                    .edgesOf(block)
                                    .stream()
                                    .forEachOrdered(edge -> {
                                        BasicBlock src = firstCFG.getEdgeSource(edge);
                                        BasicBlock tgt = firstCFG.getEdgeTarget(edge);

                                        cfg.addVertex(src);
                                        cfg.addVertex(tgt);
                                        cfg.addEdge(src, tgt);
                                    })
                        );
        }
        for (ASTNode node : body.subList(1, body.size())) {
            if (node instanceof IfConditionalASTNode) {
                Logger.getAnonymousLogger().info(node.getClass().getName());
            }
            CFG stmtCFG = node.createCFG();
            if (stmtCFG == null) { 
                continue;
            }
            cfg.addVertex(stmtCFG.start);
            cfg.addVertex(stmtCFG.end);
            cfg.addEdge(prev, stmtCFG.start);
            
            Logger.getAnonymousLogger().info(stmtCFG.toString());
            
            stmtCFG.vertexSet()
                    .stream()
                        .forEachOrdered(block ->
                            stmtCFG
                                    .edgesOf(block)
                                    .stream()
                                    .forEachOrdered(edge -> {
                                        BasicBlock src = stmtCFG.getEdgeSource(edge);
                                        BasicBlock tgt = stmtCFG.getEdgeTarget(edge);
                                        
                                        if (node instanceof IfConditionalASTNode) {
                                            Logger.getAnonymousLogger().info("Bridging Gap for: " + src + " and " + tgt);
                                        }
                                        cfg.addVertex(src);
                                        cfg.addVertex(tgt);
                                        cfg.addEdge(src, tgt);
                                    })
                        );
            prev = stmtCFG.end;
        }
        
        cfg.addEdge(prev, end);
        
        removeTrivialNodes(cfg);
        
        Logger.getAnonymousLogger().info("Reducing...");
        Set<BasicBlock> processed = new TreeSet<>();
        reduceCFG(cfg, processed);
//        processed.clear();
//        reduceCFG(cfg, processed);
        
        
        
        return cfg;                
    }
    
    private static void removeTrivialNodes(CFG cfg) {
        Logger.getAnonymousLogger().info(cfg.toString());
        Logger.getAnonymousLogger().info("Removing trivial nodes...");
        // Remove any nodes without an intended description
        for (BasicBlock bb : cfg.vertexSet()) {
            Logger.getAnonymousLogger().info("BasicBlock: " + bb.description + ", Has Description: " + bb.hasDescription);
            if (!bb.hasDescription) {
                Logger.getAnonymousLogger().info("Removing: " + bb);
                if (cfg.getIngoing(bb).isEmpty()) {
                    Logger.getAnonymousLogger().info(cfg.getGraph().edgeSet().toString());
                }
                // Bridge gap... We know for a fact we only have ONE target, 
                // because only trivial nodes are to be removed this way.
                if (cfg.getOutgoing(bb).isEmpty()) {
                    continue;
                }
                BasicBlock tgt = cfg.getOutgoing(bb).iterator().next();
                cfg.getIngoing(bb).stream()
                        .forEachOrdered(src -> {                            
                            cfg.addEdge(src, tgt);
                        });
                cfg.getGraph().removeVertex(bb);
                
                removeTrivialNodes(cfg);
                return;
            }
        }
    }
    
    
    private static void reduceCFG(CFG cfg, Set<BasicBlock> processed) {
//        BasicBlock root = cfg.getOutgoing(cfg.start).iterator().next();
        for (BasicBlock bb : cfg.vertexSet()) {
            if (processed.contains(bb)) {
                continue;
            }
            // (b, bb)
            for (BasicBlock b : cfg.getIngoing(bb)) {
                
                if ((bb.reducibility == BasicBlock.REDUCIBLE || bb.reducibility == BasicBlock.NOT_REDUCIBLE) && b.reducibility == BasicBlock.REDUCIBLE) {
                    BasicBlock block = new BasicBlock("", bb.reducibility);
                    block.addChild(b);
                    block.addChild(bb);
                    
                    Logger.getAnonymousLogger().info("Reducing to: \n" + block.description);
                    cfg.addVertex(block);
                    
                    // Add all edges going in
                    cfg.getIngoing(b)
                            .stream()
//                            .filter(incomingBlock -> !bb.equals(incomingBlock))
                            .forEach(incomingBlock -> cfg.addEdge(incomingBlock, block));
                    
                    // Add all edges going out
                    cfg.getOutgoing(bb)
                            .stream()
//                            .filter(outgoingBlock -> !b.equals(outgoingBlock))
                            .forEach(outgoingBlock -> cfg.addEdge(block, outgoingBlock));
                    
                    cfg.getGraph().removeVertex(b);
                    cfg.getGraph().removeVertex(bb);
//                    processed.add(bb);
//                    processed.add(b);
                    processed.add(block);
                    reduceCFG(cfg, processed);
                    return;
                    
                } 
            }
            
            // (bb, b)
            for (BasicBlock b : cfg.getOutgoing(bb)) {
                
                if (bb.reducibility == BasicBlock.REDUCIBLE && b.reducibility == BasicBlock.REDUCIBLE) {
                    BasicBlock block = new BasicBlock("");
                    block.addChild(bb);
                    block.addChild(b);
                    
                    Logger.getAnonymousLogger().info("Reducing to: \n" + block.description);
                    cfg.addVertex(block);
                    
                    // Add all edges going in
                    cfg.getIngoing(bb)
                            .stream()
//                            .filter(incomingBlock -> !bb.equals(incomingBlock))
                            .forEach(incomingBlock -> cfg.addEdge(incomingBlock, block));
                    
                    // Add all edges going out
                    cfg.getOutgoing(b)
                            .stream()
//                            .filter(outgoingBlock -> !b.equals(outgoingBlock))
                            .forEach(outgoingBlock -> cfg.addEdge(block, outgoingBlock));
                    
                    cfg.getGraph().removeVertex(b);
                    cfg.getGraph().removeVertex(bb);
//                    processed.add(bb);
//                    processed.add(b);
                    processed.add(block);
                    reduceCFG(cfg, processed);
                    return;
                    
                } 
            }
        }
    }
    
    @Override
    public String stringify() {
        return null;
    }
    
    
}
