package com.aifunc.leetcode.test.leet;

import java.util.LinkedList;

/**
 * Created by alex on 1/10/17.
 */
public class leet37 {
  public void solveSudoku(char[][] board) {
    int len = board.length;

    boolean[][] cols = new boolean[len][9];
    boolean[][] rows = new boolean[len][9];
    boolean[][] subs = new boolean[len * len / 3][9];

    LinkedList<int[]> toFill = new LinkedList<>();
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < len; j++) {
        if (board[i][j] == '.') {
          toFill.add(new int[]{i, j});
        } else {
          int num = board[i][j] - '1';
          cols[j][num] = true;
          rows[i][num] = true;
          subs[i / 3 + j / 3 * 3][num] = true;
        }
      }
    }
    solve(board, toFill, cols, rows, subs);

  }

  public boolean solve(char[][] board, LinkedList<int[]> toFill, boolean[][] cols, boolean[][] rows, boolean[][] subs) {
    int[] e = toFill.poll();
    int x = e[0];
    int y = e[1];

    if (board[x][y] == '.') {
      for (int i = 0; i < 9; i++) {
        if (!cols[y][i] && !rows[x][i] && !subs[x / 3 + y / 3 * 3][i]) {
          char fill = (char) ('1' + i);
          board[x][y] = fill;
          cols[y][i] = true;
          rows[x][i] = true;
          subs[x / 3 + y / 3 * 3][i] = true;

          if (toFill.size() == 0) return true;

          if (solve(board, toFill, cols, rows, subs)) {
            return true;
          }

          cols[y][i] = false;
          rows[x][i] = false;
          subs[x / 3 + y / 3 * 3][i] = false;
          board[x][y] = '.';
        }
      }
    }
    board[x][y] = '.';
    toFill.addFirst(e);
    return false;
  }

  public static void main(String[] args) {

    String[] strs = {"..9748...", "7........", ".2.1.9...", "..7...24.", ".64.1.59.", ".98...3..", "...8.3.2.", "........6", "...2759.."};
    char[][] board = new char[9][];

    for (int i = 0; i < 9; i++) {
      board[i] = strs[i].toCharArray();
    }

    leet37 l = new leet37();
    l.solveSudoku(board);



  }

}
