class Solution:
    """
    P:
        - Given: a List of List of strings -> board
            - guaranteed a 9 by 9 board as its Sudoku
        - Want: 
            - to return a boolean
                - if a given board, represents a valid Sudoku board
        - Constraints:  
            - board.length == 9
            - board[i].length == 9
            - board[i][j] is '1-9' or '.' 
    E:
        - the examples make sense
    D:
        - perhaps the usage of Sets/Maps where we can relate a cell to
        other cells that they must not conflict (thinking arc-constraints)
    A:
        IDEA 1:
        - Brute Force Solution:
            - arc = {} # a map that maps every cell to a set of the other cells that it must
            not conflict with
            - define 
                - get_column_cells, get_row_cells, get_box_cells
            # populate arc map
            - for i in row_indices:
                - for j in col_indices:
                    - arc[(i, j)] = {}.union(get_column_cells)
                    - arc[(i, j)].union(get_row_cells)
                    - arc[(i, j)].union(get_box_cells)
                    - can probably ignore any cell that has value '.'
            - for key in arc:
                - constraints = arc[key]
                - for contraint in constraints:
                    - if board[key[0], key[1]] == board[constraint[0], constraint[1]]:
                        - return False
            - return True
        - column cells, every cell except self (same row index)
        - row cells, every cell except self (same col index)
        - box cells, more complicated
            - 0 <= i <= 2 (top box) -> left box for j
            - 3 <= i <= 5: (mid box) -> middle box for j
            - 6 <= i <= 8: (bot box) -> right box for j
        
        IDEA 2:
        - A more optimal approach?
            - we can reduce the memory overhead introduced in the previous one, by creating a 
            list of sets for each row, for each column, and for each box
            - there are 9 of each
    C:
    """
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        # arc_constraints = {} 
        # # a mapping from a given cell (tuple) -> a set of cells that it must not conflict with 
        
        # def get_cells_in_row(i, j):
        #     # those that share the same row (i index)
        #     cells = set()
        #     for column in range(len(board[0])):
        #         if column != j and board[i][column] != '.': # only add cells that's not the same
        #             cells.add((i , column))
        #     return cells

        # def get_cells_in_column(i, j):
        #     # those that share the same col (j index)
        #     cells = set()
        #     for row in range(len(board)):
        #         if row != i and board[row][j] != '.': # only add the cells that's not the same cell
        #             cells.add((row, j))
        #     return cells
        
        # def get_cells_in_box(i, j):
        #     # those that share the same box
        #     cells = set()

        #     row_indices = []
        #     if 0 <= i <= 2: # top box
        #         row_indices = [0, 1, 2]
        #     elif 3 <= i <= 5: # mid box
        #         row_indices = [3, 4, 5]
        #     elif 6 <= i <= 8: # bot box
        #         row_indices = [6, 7, 8]

        #     col_indices = []
        #     if 0 <= j <= 2: # left box
        #         col_indices = [0, 1, 2]
        #     elif 3 <= j <= 5: # mid box
        #         col_indices = [3, 4, 5]
        #     elif 6 <= j <= 8: # right box
        #         col_indices = [6, 7, 8]

        #     for row in row_indices:
        #         for col in col_indices:
        #             if (row, col) != (i, j) and board[row][col] != '.':
        #                 cells.add((row, col))
        #     return cells

        # for i in range(len(board)):
        #     for j in range(len(board[0])):
        #         if board[i][j] != '.': # ignore empty cells
        #             arc_constraints[(i, j)] = set().union(get_cells_in_row(i, j))
        #             arc_constraints[(i, j)].update(get_cells_in_column(i, j))
        #             arc_constraints[(i, j)].update(get_cells_in_box(i, j))

        # for i in range(len(board)):
        #     for j in range(len(board[0])):
        #         current = (i , j)
        #         if board[i][j] != '.':
        #             constraints = arc_constraints[current]
        #             for constraint in constraints:
        #                 if board[constraint[0]][constraint[1]] == board[i][j]:
        #                     return False

        row_sets = [set() for _ in range(9)] # keep track of nums that appeared in row i
        col_sets = [set() for _ in range(9)] # keep track of nums that appeared in col j
        box_sets = [set() for _ in range(9)] # keep track of nums that appeared in box k

        for row in range(len(board)):
            for col in range(len(board[0])):
                val = board[row][col]
                if val == '.': # skip any cell thats empty
                    continue
                box_idx = (row//3) * 3 + (col//3) # box index formula

                if val in row_sets[row] or val in col_sets[col] or val in box_sets[box_idx]:
                    return False
                row_sets[row].add(val)
                col_sets[col].add(val)
                box_sets[box_idx].add(val)
                    
        return True