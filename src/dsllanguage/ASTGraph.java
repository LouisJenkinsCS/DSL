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

import dsllanguage.ASTNodes.ProgramASTNode;
import dsllanguage.ASTNodes.ASTNode;
import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;
import com.jgraph.layout.tree.JGraphTreeLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraphView;
import com.mxgraph.view.mxStylesheet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

/**
 *
 * @author Louis Jenkins
 */
public class ASTGraph extends JApplet {
    
    public static List<ASTNode> roots = new ArrayList<>();
    
    public static int x = 0, y = 0;
    public static final ASTGraph INSTANCE = new ASTGraph();
    private static final Dimension DEFAULT_SIZE = new Dimension( 530, 320 );
    private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
    Graph<ASTNode, DefaultEdge> graph;
    JGraph jgraph;
    JGraphModelAdapter adapter;
    
    public static void main(String[] args)
    {
        INSTANCE.init();

        JFrame frame = new JFrame();
        frame.getContentPane().add(INSTANCE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setTitle("JGraphT Adapter to JGraph Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    @Override
    public void init() {
        graph = new ListenableDirectedGraph<>(DefaultEdge.class);
        adapter = new JGraphModelAdapter(graph);
        jgraph = new JGraph(adapter);
        adjustDisplaySettings(jgraph);
        getContentPane(  ).add(jgraph );
        resize( DEFAULT_SIZE );
        
        
        Main.main(null);
        ProgramASTNode progNode = new ProgramASTNode(roots.toArray(new ASTNode[0]));
        CFG cfg = progNode.createCFG();
//        System.out.println(cfg.reduce());
//        JGraph cfgGraph = new JGraph(new JGraphXAdapter(cfg.getGraph()));
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
        
        
        
//        Collection<Object> cells =  graphModel.getCells().values(); 
        
//        mxUtils.setCellStyles(graphComponent.getGraph().getModel(), 
//        cells.toArray(), mxConstants.STYLE_ENDARROW, mxConstants.NONE);
        getContentPane().add(graphComponent);
        mxHierarchicalLayout layout = new mxHierarchicalLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());
        
        this.addMouseWheelListener(e -> {
            mxGraphView view = graphComponent.getGraph().getView();
            int notches = e.getWheelRotation();
            double scale = view.getScale();
            double newScale = view.getScale() - ((double) notches / 61.8033988272);
            view.setScale(newScale);
            
           
        });
//        adjustDisplaySettings(cfgGraph);
//        getContentPane().add(cfgGraph);
//        final  JGraphTreeLayout hir = new JGraphTreeLayout();
//        final JGraphFacade graphFacade = new JGraphFacade(cfgGraph, new Object[] {cfg.start});  
//        graphFacade.setOrdered(true);
//        hir.setPositionMultipleTrees(true);
//        hir.setTreeDistance(50);
//        hir.setLevelDistance(50);
//        hir.run(graphFacade);
//        final Map nestedMap = graphFacade.createNestedMap(true, true);
//        cfgGraph.getGraphLayoutCache().edit(nestedMap);
    
//        SwingUtilities.invokeLater(() -> {
//            mxGraphView view = graphComponent.getGraph().getView();
//            int compLen = graphComponent.getWidth();
//            int viewLen = (int)view.getGraphBounds().getWidth();
//            System.out.println("Complen: " + compLen + ", viewlen: " + viewLen);
//            System.out.println("Scale: " + (double)compLen/viewLen * view.getScale());
//            view.setScale(2);
//        });
    }
    
    private void adjustDisplaySettings( JGraph jg ) {
        jg.setPreferredSize( DEFAULT_SIZE );

        Color  c        = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter( "bgcolor" );
        }
         catch( Exception e ) {}

        if( colorStr != null ) {
            c = Color.decode( colorStr );
        }

        jg.setBackground( c );
    }
    
    private void positionVertexAt( Object vertex, int x, int y ) {
        DefaultGraphCell cell = adapter.getVertexCell( vertex );
        Map              attr = cell.getAttributes(  );
        Rectangle        b    = GraphConstants.getBounds( attr ).getBounds();

        GraphConstants.setBounds( attr, new Rectangle( x, y, b.width, b.height ) );

        Map cellAttr = new HashMap(  );
        cellAttr.put( cell, attr );
        adapter.edit( cellAttr, null, null, null );
    }
    
    public static void graph(ASTNode node) {
        node.addDependency(INSTANCE.graph); 
        roots.add(node);
        
        final  JGraphTreeLayout hir = new JGraphTreeLayout();
        final JGraphFacade graphFacade = new JGraphFacade(INSTANCE.jgraph, roots.toArray());  
        graphFacade.setOrdered(true);
        hir.setPositionMultipleTrees(true);
        hir.setTreeDistance(25);
        hir.run(graphFacade);
        final Map nestedMap = graphFacade.createNestedMap(true, true);
        INSTANCE.jgraph.getGraphLayoutCache().edit(nestedMap);
    }
}
