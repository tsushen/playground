package com.aifunc.leetcode.test.leet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 1/7/17.
 */
public class leet133 {
  public class UndirectedGraphNode{
    Integer label;
    List<UndirectedGraphNode> neighbors;
    public UndirectedGraphNode (Integer l){
      label = l;
    }
  }


  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if(node == null) return null;

    Map<Integer, UndirectedGraphNode> map = new HashMap<>();
    return clone(node, map);
  }

  public UndirectedGraphNode clone(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> map) {
    if(map.containsKey(node.label)) return map.get(node.label);

    UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
    map.put(node.label, newNode);

    for(UndirectedGraphNode n : node.neighbors) {
      UndirectedGraphNode neighbor = clone(n, map);
      newNode.neighbors.add(neighbor);
    }

    return newNode;
  }
}
