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
package dsllanguage;

import dsllanguage.ASTNodes.NameASTNode;
import dsllanguage.ASTNodes.ASTNode;
import dsllanguage.ASTNodes.ConstantASTNode;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Louis Jenkins
 */
public class DSLLexer implements DSLTokens {

    Queue<String> tokens;
    public DSLLexer(File file) throws IOException {
        // Read all into buffer...
        tokens = new BufferedReader(new FileReader(file))
                .lines()
                .flatMap(line -> Stream.of(line.replace(";", " ;").replace("(", "( ").replace(")", " )").split("[ \r\t\n]")))
                .filter(str -> !"".equals(str.trim()))
                .collect(Collectors.toCollection(() -> new ArrayDeque()));
    }
    
    public DSLLexer(String contents) {
        // Read all into buffer...
        tokens = Stream.of(contents.split("\n"))
                .flatMap(line -> Stream.of(line.replace(";", " ;").replace("(", "( ").replace(")", " )").split("[ \r\t\n]")))
                .filter(str -> !"".equals(str.trim()))
                .collect(Collectors.toCollection(() -> new ArrayDeque()));
    }
    

    public static void yyerror(String msg) {
        throw new IllegalArgumentException(msg);
    }

    private int c;

    public void nextChar() {
        if (c >= 0) {
            try {
                c = System.in.read();
            } catch (Exception e) {
                c = (-1);
            }
        }
    }

    int token;
    ASTNode yylval;

    int nextToken() {
        for (;;) {
            String tok = tokens.poll();
            if (tok == null) {
                return token = ENDINPUT;
            }
            
            // Look for operator symbols
            if (tok.length() == 1) {
                char ch;
                switch (ch = tok.charAt(0)) {
                    case '+':
                    case '*':
                    case '/':
                    case '-':
                    case '<':
                    case '>':
                    case ';':
                    case '(':
                    case ')':
                    case '{':
                    case '}':
                    case '=':
                        return token = ch;
                }
            }
            
            if (tok.equals("==")) {
                return token = EQ;
            }
            
            if (tok.equals("!=")) {
                return token = NEQ;
            }
            
            if (tok.equals("<=")) {
                return token = LE;
            }
            
            if (tok.equals(">=")) {
                return token = GE;
            }
            
            // String literal
            if (tok.startsWith("\"")) {
                // In case String was chunked, need to collect next lines up to ending '"'
                // Also drop the excess '"'
                String str = tok.substring(1);
                while (!str.endsWith("\"")) {
                    String nextStr = tokens.poll();
                    if (nextStr == null) {
                        yyerror("Unterminated String!");
                    }
                    
                    str += " " + nextStr;
                }
                yylval = new ConstantASTNode(str.substring(0, str.length() - 1));
                return token = STRING;
            }
            
            // Integer literal
            if (tok.matches("0") || tok.matches("[1-9][0-9]*")) {
                yylval = new ConstantASTNode(Integer.parseInt(tok));
                return token = INTEGER;
            }
            
            // 'var' declaration
            if (tok.equals("var")) {
                // Check next token for name...
                String t = tokens.element();
                if (t == null) {
                    yyerror("Need NAME after 'var'");
                }
                return token = VAR;
            }
            
            if (tok.equals("print")) {
                String t = tokens.element();
                if (t == null) {
                    yyerror("Need EXPR after 'print'");
                }
                return token = PRINT;
            }
            
            if (tok.equals("while")) {
                String t = tokens.element();
                if (t == null) {
                    yyerror("Need EXPR after 'while'");
                }
                return token = WHILE;
            }
            
            if (tok.equals("if")) {
                String t = tokens.element();
                if (t == null) {
                    yyerror("Need EXPR after 'if'");
                }
                return token = IF;
            }
            
            if (tok.equals("else")) {
                String t = tokens.element();
                if (t == null) {
                    yyerror("Need EXPR after 'else'");
                }
                return token = ELSE;
            }
            
            // name declaration
            if (tok.matches("[A-z][A-z0-9_]*")) {
                yylval = new NameASTNode(tok);
                return token = NAME;
            }
            
            
            // At this point, does not match the above...
            yyerror("Bad Input: " + tok);
        }
    }
    
    int getToken() {
        return token;
    }
    
    ASTNode getSemantic() {
        return yylval;
    }
}
