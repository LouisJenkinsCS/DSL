/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsllanguage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lpj11535
 */
public class BasicBlock implements Serializable, Comparable<BasicBlock> {
    public String description = "";
    public static final int NOT_REDUCIBLE = 0;
    public static final int REDUCIBLE = 1;
    public static final int REDUCIBLE_SINGLETON = 2;
    public static final int NOT_REDUCIBLE_NOP = 3;
    public int reducibility = REDUCIBLE;
    public boolean hasDescription = true;

    public List<BasicBlock> children = new ArrayList<>();
    
    public BasicBlock(String description) {
        this.description = description;
    }
    
    public BasicBlock(String description, boolean hasDescription) {
        this.description = description;
        this.hasDescription = hasDescription;
    }
    
    public BasicBlock(String description, int reducibility) {
        this.description = description;
        this.reducibility = reducibility;
    }
    
    public BasicBlock(String description, int reducibility, boolean hasDescription) {
        this.description = description;
        this.reducibility = reducibility;
        this.hasDescription = hasDescription;
    }
    
    public void addChild(BasicBlock child) {
        children.add(child);
        if (child.hasDescription) {
            description = description == "" ? child.description : description + "\n" + child.description;
        }
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public int compareTo(BasicBlock o) {
        return Integer.compare(this.hashCode(), o.hashCode());
    }
    
    
}
