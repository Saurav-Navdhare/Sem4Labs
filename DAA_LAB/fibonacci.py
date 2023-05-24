from time import time
def fibona(n):
    a, b = 0, 1
    if(n==1):
        print(a)
    else:
        print(a, b)
    for i in range(2, n):
        print(a+b)
        a,b = b, a+b
start = time()
fibona(10)
print("Time taken: ", (time()-start)*1e6, "microseconds")