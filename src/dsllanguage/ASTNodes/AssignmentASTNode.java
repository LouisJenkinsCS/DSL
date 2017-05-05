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
import dsllanguage.SymbolTable;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

/**
 *
 * @author Louis Jenkins
 */
public class AssignmentASTNode implements ASTNode {
    
    public ASTNode name;
    public ASTNode expr;
    
    public AssignmentASTNode(ASTNode name, ASTNode expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    public Object execute() {
        
       if (!(name instanceof NameASTNode)) {
           DSLLexer.yyerror("Cannot assign to a non-Symbol...");
       }
       
       String nname = ((NameASTNode) name).name;
       if (!SymbolTable.exists(nname)) {
            DSLLexer.yyerror("Undeclared name: " + nname);
        }
       
        // Create the value equal to the expression...
        SymbolTable.get(nname).get().setValue(expr.execute());
        
        return null;
    }

    @Override
    public void addDependency(Graph<ASTNode, DefaultEdge> graph) {
        graph.addVertex(this);
        
        name.addDependency(graph);
        graph.addEdge(this, name);
        
        expr.addDependency(graph);
        graph.addEdge(this, expr);
    }
    
    @Override
    public String toString() {
        return "=";
    }

    @Override
    public CFG createCFG() {
        CFG cfg = new CFG(null);
        BasicBlock bb = new BasicBlock(this.stringify());
        cfg.start = cfg.end = bb;
        
        return cfg;
    }

    @Override
    public String stringify() {
        return name.stringify() + " = " + expr.stringify();
    }
    
}
