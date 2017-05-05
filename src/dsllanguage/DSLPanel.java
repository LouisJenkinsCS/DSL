package dsllanguage;


import dsllanguage.ASTNodes.ProgramASTNode;
import dsllanguage.ASTNodes.ASTNode;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraphView;
import com.mxgraph.view.mxStylesheet;
import dsllanguage.DSL;
import dsllanguage.DSLLexer;
import java.awt.Component;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import org.jgraph.JGraph;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

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

/**
 *
 * @author Louis Jenkins
 */
public class DSLPanel extends javax.swing.JFrame {
    
    private static List<ASTNode> roots = new ArrayList<>();
    private static Graph<ASTNode, DefaultEdge> graph;

    /**
     * Creates new form DSLPanel
     */
    public DSLPanel() {
        initComponents();
        Document doc = textInput.getDocument();
        doc.putProperty(PlainDocument.tabSizeAttribute, 2);
        GlobalOutputStream.PRINTER = msg -> {
            textOutput.append(msg + "\n");
        };
        runButton.addActionListener(e -> {
            tearDown();
            DSLLexer lexer = new DSLLexer(textInput.getText());
            lexer.nextToken();
            DSL dsl = new DSL(lexer);
            try {
            dsl.parse();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            createAST();
            createCFG();
            
            
            SymbolTable.clear();
        });
        graph = new ListenableDirectedGraph<>(DefaultEdge.class);
    }
    
    public static void addNode(ASTNode node) {
        node.addDependency(graph); 
        roots.add(node);
    }
    
    public void tearDown() {
        roots.clear();
        SymbolTable.clear();
        graph = new ListenableDirectedGraph<>(DefaultEdge.class);
        destroyCFG();
        destroyAST();
    }
    
    public void destroyCFG() {
        cfgPane.removeAll();
    }
    
    public void createCFG() {
        ProgramASTNode progNode = new ProgramASTNode(roots.toArray(new ASTNode[0]));
        CFG cfg = progNode.createCFG();
        JGraphXAdapter jgxAdapter = new JGraphXAdapter(cfg.getGraph());
        jgxAdapter.setAutoSizeCells(true);
        jgxAdapter.setCellsResizable(true);
//        jgxAdapter.alignCells(mxConstants.ALIGN_CENTER);
        mxStylesheet stylesheet = jgxAdapter.getStylesheet();
        Hashtable<String, Object> style = new Hashtable<String, Object>();
        style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
        style.put(mxConstants.STYLE_OPACITY, 50);
        style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
        stylesheet.putCellStyle("ROUNDED", style);
        jgxAdapter.setStylesheet(stylesheet);
        jgxAdapter.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_NOLABEL, "1");
        mxGraphComponent graphComponent = new mxGraphComponent(jgxAdapter);
        graphComponent.setWheelScrollingEnabled(false);
        mxGraphModel graphModel  =       (mxGraphModel)graphComponent.getGraph().getModel();
        
        cfgPane.add(graphComponent);
        mxHierarchicalLayout layout = new mxHierarchicalLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());
        
        this.addMouseWheelListener(e -> {
            mxGraphView view = graphComponent.getGraph().getView();
            int notches = e.getWheelRotation();
            double scale = view.getScale();
            double newScale = view.getScale() - ((double) notches / 61.8033988272);
            view.setScale(newScale);
            
           
        });
        
        cfgPane.revalidate();
        cfgPane.repaint();
    }
    
    public void destroyAST() {
        astPane.removeAll();
    }
    
    public void createAST() {
        Logger.getAnonymousLogger().info(" Graph: " + graph);
        ProgramASTNode progNode = new ProgramASTNode(roots.toArray(new ASTNode[0]));
        JGraphXAdapter jgxAdapter = new JGraphXAdapter(progNode.getAST());
        jgxAdapter.setAutoSizeCells(true);
        jgxAdapter.setCellsResizable(true);
//        jgxAdapter.alignCells(mxConstants.ALIGN_CENTER);
        mxStylesheet stylesheet = jgxAdapter.getStylesheet();
        Hashtable<String, Object> style = new Hashtable<String, Object>();
        style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
        style.put(mxConstants.STYLE_OPACITY, 50);
        style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
        stylesheet.putCellStyle("ROUNDED", style);
        jgxAdapter.setStylesheet(stylesheet);
        jgxAdapter.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_NOLABEL, "1");
        mxGraphComponent graphComponent = new mxGraphComponent(jgxAdapter);
        graphComponent.setWheelScrollingEnabled(false);
        mxGraphModel graphModel  =       (mxGraphModel)graphComponent.getGraph().getModel();
        
        astPane.add(graphComponent);
        mxGraphLayout layout = new mxCompactTreeLayout(jgxAdapter, false);
        layout.execute(jgxAdapter.getDefaultParent());
        
        this.addMouseWheelListener(e -> {
            mxGraphView view = graphComponent.getGraph().getView();
            int notches = e.getWheelRotation();
            double scale = view.getScale();
            double newScale = view.getScale() - ((double) notches / 61.8033988272);
            view.setScale(newScale);
            System.out.println(scale + " -> " + newScale);
            
           
        });
        
        astPane.revalidate();
        astPane.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        runButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textInput = new javax.swing.JEditorPane();
        tabbedPane = new javax.swing.JTabbedPane();
        outputPane = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textOutput = new javax.swing.JTextArea();
        astPane = new javax.swing.JPanel();
        cfgPane = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        runButton.setText("Run");
        runButton.setFocusable(false);
        runButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        runButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(runButton);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        textInput.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(textInput);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );

        tabbedPane.setBackground(new java.awt.Color(255, 255, 255));

        textOutput.setEditable(false);
        textOutput.setBackground(new java.awt.Color(204, 204, 204));
        textOutput.setColumns(20);
        textOutput.setRows(5);
        jScrollPane2.setViewportView(textOutput);

        javax.swing.GroupLayout outputPaneLayout = new javax.swing.GroupLayout(outputPane);
        outputPane.setLayout(outputPaneLayout);
        outputPaneLayout.setHorizontalGroup(
            outputPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
        );
        outputPaneLayout.setVerticalGroup(
            outputPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Output", outputPane);

        astPane.setLayout(new java.awt.BorderLayout());
        tabbedPane.addTab("AST", astPane);

        cfgPane.setLayout(new java.awt.BorderLayout());
        tabbedPane.addTab("CFG", cfgPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabbedPane))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DSLPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DSLPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DSLPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DSLPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DSLPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel astPane;
    private javax.swing.JPanel cfgPane;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel outputPane;
    private javax.swing.JButton runButton;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JEditorPane textInput;
    private javax.swing.JTextArea textOutput;
    // End of variables declaration//GEN-END:variables
}
