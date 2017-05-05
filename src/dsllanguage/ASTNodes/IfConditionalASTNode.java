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
import dsllanguage.DSLLexer;
import java.util.logging.Logger;

/**
 *
 * @author Louis Jenkins
 */
public class IfConditionalASTNode extends ConditionalASTNode {

    public IfConditionalASTNode(ASTNode conditional, ASTNode primaryBlock, ASTNode secondaryBlock) {
        super(conditional, primaryBlock, secondaryBlock);
    }

    @Override
    public Object execute() {
        Object conditionalValue = conditional.execute();
        boolean cond;

        if (conditionalValue == null) {
            cond = false;
        } else if (conditionalValue instanceof Boolean) {
            cond = (Boolean) conditionalValue;
        } else if (conditionalValue instanceof Integer) {
            cond = ((Integer) conditionalValue) != 0;
        } else {
            DSLLexer.yyerror("Conditional evaluated to be invalid: " + conditionalValue);
            cond = false;
        }

        if (!cond) {
            if (secondaryBlock != null) {
                secondaryBlock.execute();
            }
            return null;
        }

        primaryBlock.execute();
        
        return null;
    }
    
    
    @Override
    public String toString() {
        return "IF";
    }

    @Override
    public CFG createCFG() { 
        CFG cfg = new CFG(null);
        BasicBlock start = new BasicBlock("if (" + conditional.stringify() + ")", BasicBlock.NOT_REDUCIBLE);
        BasicBlock primaryEnter = new BasicBlock("IF-STATEMENT-IF-ENTER", false);
        BasicBlock primaryEnd = new BasicBlock("IF-STATEMENT-IF-END", false);
        BasicBlock secondaryEnter = new BasicBlock("IF-STATEMENT-ELSE-ENTER", false);
        BasicBlock secondaryEnd = new BasicBlock("IF-STATEMENT-ELSE-END", false);
        BasicBlock end = new BasicBlock("IF-STATEMENT-END", false);
        cfg.start = start;
        cfg.end = end;
        
        cfg.addVertex(start);
        cfg.addVertex(end);
        
        // The PrimaryBlock (evaluated if conditional is true...)
        CFG primaryCFG = primaryBlock.createCFG();
        cfg.addVertex(primaryCFG.start);
        cfg.addVertex(primaryCFG.end);
        cfg.addVertex(primaryEnter);
        cfg.addVertex(primaryEnd);
        primaryCFG.vertexSet().forEach(System.out::println);
        primaryCFG.vertexSet().forEach(cfg::addVertex);
        // Start -> PrimaryBlock.Start
        cfg.addEdge(start, primaryEnter);
        cfg.addEdge(primaryEnter, primaryCFG.start);
        primaryCFG.vertexSet()
                .stream()
                .forEach(block ->
                    primaryCFG
                            .edgesOf(block)
                            .stream()
                            .forEach(edge -> {
                                Logger.getAnonymousLogger().info("Added edge to graph: " + edge);
                                BasicBlock src = primaryCFG.getEdgeSource(edge);
                                BasicBlock tgt = primaryCFG.getEdgeTarget(edge);
                                cfg.addVertex(src);
                                cfg.addVertex(tgt);
                                cfg.addEdge(src, tgt);
                            })
                );
        // PrimaryBlock.End -> End
        cfg.addEdge(primaryCFG.end, primaryEnd);
        cfg.addEdge(primaryEnd, end);
        
        if (secondaryBlock != null) {
            // The SecondaryBlock (evaluated if conditional is false AND if SecondaryBlock exists)
            CFG secondaryCFG = secondaryBlock.createCFG();
            cfg.addVertex(secondaryCFG.start);
            cfg.addVertex(secondaryCFG.end);
            secondaryCFG.vertexSet().forEach(cfg::addVertex);
            cfg.addVertex(secondaryEnter);
            cfg.addVertex(secondaryEnd);
            // Start -> SecondaryBlock.Start
            cfg.addEdge(start, secondaryEnter);
            cfg.addEdge(secondaryEnter, secondaryCFG.start);
            secondaryCFG.vertexSet()
                .stream()
                .forEach(block ->
                    secondaryCFG
                            .edgesOf(block)
                            .stream()
                            .forEach(edge -> {
                                BasicBlock src = secondaryCFG.getEdgeSource(edge);
                                BasicBlock tgt = secondaryCFG.getEdgeTarget(edge);
                                cfg.addVertex(src);
                                cfg.addVertex(tgt);
                                cfg.addEdge(src, tgt);
                            })
                );
            cfg.addEdge(secondaryCFG.end, secondaryEnd);
            cfg.addEdge(secondaryEnd, end);
        } else {
            // No else statement, but conditional evaluates to false...
            cfg.addEdge(start, end);
        }
        
        
        Logger.getAnonymousLogger().info(cfg.toString());
        return cfg;
    }

    @Override
    public String stringify() {
        return null;
    }
    
    
}
