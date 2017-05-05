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
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

/**
 *
 * @author Louis Jenkins
 */
public class StatementASTNode implements ASTNode {
    public List<ASTNode> body = new ArrayList<>();
    
    public StatementASTNode(ASTNode ...nodes) {
        Collections.addAll(body, nodes);
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
        return "BLOCK";
    }

    @Override
    public CFG createCFG() {
        CFG cfg = new CFG(null);
        BasicBlock start = new BasicBlock("STATEMENT-START", false);
        BasicBlock end = new BasicBlock("STATEMENT-END", false);
        
        cfg.start = start;
        cfg.end = end;
        
        cfg.addVertex(start);
        cfg.addVertex(end);
        
        if (body.isEmpty()) {
            cfg.addEdge(start, end);
            return cfg;
        }
        
        
        BasicBlock prev = start;
        for (ASTNode node : body) {
            CFG stmtCFG = node.createCFG();
            if (stmtCFG == null) continue;
            cfg.addVertex(stmtCFG.start);
            cfg.addVertex(stmtCFG.end);
            cfg.addEdge(prev, stmtCFG.start);
            
            System.out.println(stmtCFG);
            
            stmtCFG.vertexSet()
                    .stream()
                        .forEachOrdered(block ->
                            stmtCFG
                                    .edgesOf(block)
                                    .stream()
                                    .forEachOrdered(edge -> {
                                        BasicBlock src = stmtCFG.getEdgeSource(edge);
                                        BasicBlock tgt = stmtCFG.getEdgeTarget(edge);
                                        cfg.addVertex(src);
                                        cfg.addVertex(tgt);
                                        cfg.addEdge(src, tgt);
                                    })
                        );
            prev = stmtCFG.end;
        }
        
        cfg.addEdge(prev, end);
                
        return cfg;                
    }

    @Override
    public String stringify() {
        return null;
    }
    
    
}
