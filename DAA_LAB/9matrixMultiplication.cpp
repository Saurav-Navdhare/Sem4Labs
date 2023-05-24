#include <bits/stdc++.h>
using namespace std;

// Code not working correctly (Wrong Output, Logical error), needed to be fixed

#define Row1 4
#define Col1 5
#define Row2 5
#define Col2 4

void print(string str, vector<vector<int>> matrix, int start_row, int start_column, int end_row, int end_column)
{
    cout << endl << str << endl;
    for (int i = start_row; i <= end_row; i++)
    {
        for (int j = start_column; j <= end_column; j++)
        {
            cout << matrix[i][j]<<"  ";
        }
        cout << endl;
    }
    cout << endl;
    return;
}

void addMatrix(vector<vector<int>> matrix_A, vector<vector<int>> matrix_B, vector<vector<int>> &matrix_C, int split_index)
{
    for (int i = 0; i < split_index; i++)
    {
        for (int j = 0; j < split_index; j++)
        {
            matrix_C[i][j] = matrix_A[i][j] + matrix_B[i][j];
        }
    }
}

vector<vector<int>> matrixMultiplication(vector<vector<int>> matrix_A, vector<vector<int>> matrix_B)
{
    int col_1 = matrix_A[0].size();
    int row_1 = matrix_A.size();
    int col_2 = matrix_B[0].size();
    int row_2 = matrix_B.size();

    if (col_1 != row_2)
    {
        cout << "\nThe number of columns in Matrix A must be equal to the number of rows in Matrix B!!\n";
        return {};
    }

    vector<int> result_matrix_row(col_2, 0);    // col_2 elements, all initialized to 0
    vector<vector<int>> result_matrix(row_1, result_matrix_row); // creates row_1 copies of result_matrix_row, each of which becomes a row in result_matrix.

    if (col_1 == 1)
    {
        result_matrix[0][0] = matrix_A[0][0] * matrix_B[0][0];
    }
    else
    {
        int split_index = col_1 / 2;
        vector<int> rows(split_index, 0);
        vector<vector<int>> result_matrix_00(split_index, rows);
        vector<vector<int>> result_matrix_01(split_index, rows);
        vector<vector<int>> result_matrix_10(split_index, rows);
        vector<vector<int>> result_matrix_11(split_index, rows);

        vector<vector<int>> a00(split_index, rows);
        vector<vector<int>> a01(split_index, rows);
        vector<vector<int>> a10(split_index, rows);
        vector<vector<int>> a11(split_index, rows);
        vector<vector<int>> b00(split_index, rows);
        vector<vector<int>> b01(split_index, rows);
        vector<vector<int>> b10(split_index, rows);
        vector<vector<int>> b11(split_index, rows);

        for (int i = 0; i < split_index; i++)
        {
            for (int j = 0; j < split_index; j++)
            {
                a00[i][j] = matrix_A[i][j];
                a01[i][j] = matrix_A[i][j + split_index];
                a10[i][j] = matrix_A[split_index + i][j];
                a11[i][j] = matrix_A[i + split_index][j + split_index];
                b00[i][j] = matrix_B[i][j];
                b01[i][j] = matrix_B[i][j + split_index];
                b10[i][j] = matrix_B[split_index + i][j];
                b11[i][j] = matrix_B[i + split_index][j + split_index];
            }
        }
            

        addMatrix(matrixMultiplication(a00, b00), matrixMultiplication(a01, b10), result_matrix_00, split_index);
        addMatrix(matrixMultiplication(a00, b01), matrixMultiplication(a01, b11), result_matrix_01, split_index);
        addMatrix(matrixMultiplication(a10, b00), matrixMultiplication(a11, b10), result_matrix_10, split_index);
        addMatrix(matrixMultiplication(a10, b01), matrixMultiplication(a11, b11), result_matrix_11, split_index);

        for (int i = 0; i < split_index; i++)
        {
            for (int j = 0; j < split_index; j++)
            {
                result_matrix[i][j] = result_matrix_00[i][j];
                result_matrix[i][j + split_index] = result_matrix_01[i][j];
                result_matrix[split_index + i][j] = result_matrix_10[i][j];
                result_matrix[i + split_index][j + split_index] = result_matrix_11[i][j];
            }
        }

        result_matrix_00.clear();
        result_matrix_01.clear();
        result_matrix_10.clear();
        result_matrix_11.clear();
        a00.clear();
        a01.clear();
        a10.clear();
        a11.clear();
        b00.clear();
        b01.clear();
        b10.clear();
        b11.clear();
    }
    return result_matrix;
}

int main()
{
    vector<vector<int>> matrix_A = {{2, 2, 2, 2, 2},
                                    {1, 1, 1, 1, 2},
                                    {1, 1, 1, 1, 2},
                                    {2, 2, 2, 2, 2}};

    print("Matrix A:", matrix_A, 0, 0, 3, 4);

    vector<vector<int>> matrix_B = {{1, 1, 1, 1},
                                    {1, 1, 1, 1},
                                    {1, 1, 1, 1},
                                    {1, 1, 1, 1},
                                    {1, 1, 1, 1}};

    print("Matrix B:", matrix_B, 0, 0, 4, 3);

    vector<vector<int>> result_matrix(matrixMultiplication(matrix_A, matrix_B));

    print("Resultant Matrix:", result_matrix, 0, 0, 3, 3);
}
