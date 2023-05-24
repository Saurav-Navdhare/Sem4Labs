#include <stdio.h>
#include <pthread.h>

#define NUM_PLANES 2 // Number of processes

int entering[NUM_PLANES];  // Indicates the intention of each process to access the shared resource
int turn;  // Indicates whose turn it is to access the shared resource

void *airplane0(void *arg)
{
    // Set flag[0] to indicate intention to access shared resource
    entering[0] = 1;
    // Set turn to 1, indicating that it is process 1's turn to access the shared resource
    turn = 1;
    while (entering[1] == 1 && turn == 1); // Do nothing (busy waiting)
    // enter the shared airspace
    printf("Airplane 0 entering the shared airspace\n");
    // release the shared airspace
    entering[0] = 0;
    printf("Airplane 0 is exited\n");
}

void *airplane1(void *arg)
{
    // Set flag[1] to indicate intention to access shared resource
    entering[1] = 1;
    // Set turn to 0, indicating that it is process 0's turn to access the shared resource
    turn = 0;
    // Wait until it is process 1's turn to access the shared resource
    while (entering[0] == 1 && turn == 0); // Do nothing (busy waiting)
    // enter the shared airspace
    printf("Airplane 1 entering the shared airspace\n");
    entering[1] = 0;
    printf("Airplane 1 is exited\n");
}

int main()
{
    pthread_t plane0, plane1;

    // Create the two threads to represent the two processes
    pthread_create(&plane0, NULL, airplane0, NULL);
    pthread_create(&plane1, NULL, airplane1, NULL);

    // Wait for both threads to complete
    pthread_join(plane0, NULL);
    pthread_join(plane1, NULL);

    return 0;
}