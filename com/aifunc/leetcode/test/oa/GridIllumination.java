package com.aifunc.leetcode.test.oa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridIllumination {
  public static class Entry {
    int X;
    int Y;

    public Entry(int x, int y) {
      X = x;
      Y = y;
    }
  }

  public List<String> checkIllumination(int N, Entry[] lamps, Entry[] queries) {
    HashMap<Integer, List<Integer>> xMap = new HashMap<>(); // x -> y
    HashMap<Integer,List<Integer>> yMap = new HashMap<>(); // y -> x
    HashMap<Long, List<Integer>> downMap = new HashMap<>();// x + y -> x
    HashMap<Integer, List<Integer>> upMap = new HashMap<>(); // y - x -> x


    for (Entry lamp : lamps) {
      if(lamp.X <= N || lamp.Y <= N) {
        addToMap(xMap, lamp.X, lamp.Y);
        addToMap(yMap, lamp.Y, lamp.X);
        addToMap(upMap, lamp.Y - lamp.X, lamp.X);
        Long key = (long) (lamp.X + lamp.Y);
        addToLongMap(downMap, key, lamp.X);
      }
    }

    List res = new ArrayList<>();
    for (int i = 0; i < queries.length; i++) {
      Entry e = queries[i];

      boolean flag = false;

      if(xMap.containsKey(e.X)) {
        List<Integer> list = xMap.get(e.X);
        for (Integer y : list) {
          if( y <= e.Y + 1 && y >= e.Y - 1) continue;
          flag = true;
          break;
        }
      }

      if (!flag && yMap.containsKey(e.Y)) {
        List<Integer> list = yMap.get(e.Y);
        for (Integer x : list) {
          if( x <= e.X + 1 && x >= e.X - 1) continue;
          flag = true;
          break;
        }
      }

      if (!flag && upMap.containsKey(e.Y - e.X)) {
        List<Integer> list = upMap.get(e.Y - e.X);
        for (Integer x : list) {
          if( x <= e.X + 1 && x >= e.X - 1) continue;
          flag = true;
          break;
        }
      }

      Long lKey = (long)e.X + e.Y;
      if (!flag && downMap.containsKey(lKey)) {
        List<Integer> list = downMap.get(lKey);
        for (Integer x : list) {
          if( x <= e.X + 1 && x >= e.X - 1) continue;
          flag = true;
          break;
        }
      }

      if(flag) {
        res.add("LIGHT");
      } else {
        res.add("DARK");
      }
    }

    return res;
  }

  public void addToMap(Map<Integer, List<Integer>> map, Integer key, Integer value) {
    if(!map.containsKey(key)) {
      List<Integer> list = new ArrayList<>();
      list.add(value);
      map.put(key, list);
    } else {
      List<Integer> list = map.get(key);
      list.add(value);
    }
  }

  public void addToLongMap(Map<Long, List<Integer>> map, Long key, Integer value) {
    if(!map.containsKey(key)) {
      List<Integer> list = new ArrayList<>();
      list.add(value);
      map.put(key, list);
    } else {
      List<Integer> list = map.get(key);
      list.add(value);
    }
  }

  public static void main(String[] args) {
    Entry[] lamps = new Entry[]{new Entry(1, 6), new Entry(5, 6), new Entry(7, 3), new Entry(3, 2)};
    Entry[] query = new Entry[]{new Entry(4, 4), new Entry(6, 6), new Entry(8, 1), new Entry(3, 2), new Entry(2, 3)};

    GridIllumination grid = new GridIllumination();
    List<String> res = grid.checkIllumination(100000, lamps, query);

    for (String s : res) {
      System.out.println(s);
    }

  }

}
