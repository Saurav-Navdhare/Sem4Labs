from math import log2, ceil
def add_matrices(A, B):
    n = len(A)
    C = [[0 for j in range(n)] for i in range(n)]

    for i in range(n):
        for j in range(n):
            C[i][j] = A[i][j] + B[i][j]

    return C

def subtract_matrices(A, B):
    n = len(A)
    C = [[0 for j in range(n)] for i in range(n)]

    for i in range(n):
        for j in range(n):
            C[i][j] = A[i][j] - B[i][j]

    return C

def partition_matrix(A):
    n = len(A)
    mid = n // 2

    A11 = [[A[i][j] for j in range(mid)] for i in range(mid)]
    A12 = [[A[i][j] for j in range(mid, n)] for i in range(mid)]
    A21 = [[A[i][j] for j in range(mid)] for i in range(mid, n)]
    A22 = [[A[i][j] for j in range(mid, n)] for i in range(mid, n)]

    return A11, A12, A21, A22

def matrix_multiply(A, B):
    n = len(A[0])
    if (n!=len(A[0]) or n!=len(B) or n!=len(B)):
        print("Matrices are not compatible for multiplication")
        return
        
    if n == 1:
        return [[A[0][0] * B[0][0]]]

    A11, A12, A21, A22 = partition_matrix(A)
    B11, B12, B21, B22 = partition_matrix(B)

    M1 = matrix_multiply(add_matrices(A11, A22), add_matrices(B11, B22))
    M2 = matrix_multiply(add_matrices(A21, A22), B11)
    M3 = matrix_multiply(A11, subtract_matrices(B12, B22))
    M4 = matrix_multiply(A22, subtract_matrices(B21, B11))
    M5 = matrix_multiply(add_matrices(A11, A12), B22)
    M6 = matrix_multiply(subtract_matrices(A21, A11), add_matrices(B11, B12))
    M7 = matrix_multiply(subtract_matrices(A12, A22), add_matrices(B21, B22))

    C11 = add_matrices(subtract_matrices(add_matrices(M1, M4), M5), M7)
    C12 = add_matrices(M3, M5)
    C21 = add_matrices(M2, M4)
    C22 = add_matrices(subtract_matrices(add_matrices(M1, M3), M2), M6)

    C = [[0 for j in range(n)] for i in range(n)]

    for i in range(n // 2):
        for j in range(n // 2):
            C[i][j] = C11[i][j]
            C[i][j + n // 2] = C12[i][j]
            C[i + n // 2][j] = C21[i][j]
            C[i + n // 2][j + n // 2] = C22[i][j]

    return C

A = [
    [1, 2, 3, 4],
    [5, 6, 7, 8],
    [9, 10, 11, 12],
    [13, 14, 15, 16]
]

B = [
    [17, 18, 19, 20],
    [21, 22, 23, 24],
    [25, 26, 27, 28],
    [29, 30, 31, 32]
]
# matrix = matrix_multiply(A, B)
# [print(i) for i in matrix]
C = matrix_multiply(A, B)

for i in C:
    print(i)