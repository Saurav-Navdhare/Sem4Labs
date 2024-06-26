#include <iostream>
using namespace std;

// create linked list
struct Node{
    int data;
    Node *next;
};

// create a new node

Node *createNode(int data){
    Node *newNode = new Node();
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
}

Node *createLinkedList(string str){
    Node *head = NULL;
    for (int i = 0; i < str.length(); i++){
        Node *newNode = createNode(int(str[i]) - 48);
        if (head == NULL)
            head = newNode;
        else{
            newNode->next = head;
            head = newNode;
        }
    }
    return head;
}

void printReverseLinkedList(Node *head){
    Node *temp = head;
    if (temp == NULL)
        return;
    printReverseLinkedList(temp->next);
    cout << temp->data << " ";
}

void makeLinkedListLengthEqual(Node *head1, Node *head2){
    int length1 = 0, length2 = 0;
    Node *temp1 = head1;
    Node *temp2 = head2;
    while (temp1->next != NULL){
        length1++;
        temp1 = temp1->next;
    }
    length1++;
    while (temp2->next != NULL){
        length2++;
        temp2 = temp2->next;
    }
    length2++;
    if (length1 > length2){
        for (int i = 0; i < length1 - length2; i++){
            Node *newNode = createNode(0);
            temp2->next = newNode;
            temp2 = temp2->next;
        }
    }
    else if (length1 < length2){
        for (int i = 0; i < length2 - length1; i++){
            Node *newNode = createNode(0);
            temp1->next = newNode;
            temp1 = temp1->next;
        }
    }
}

Node *addLinkedLists(Node *head, Node *head1, Node *head2, int carry){
    if (head1 == NULL && head2 == NULL)
        if (carry == 0)
            return NULL;
        else
            return createNode(carry);
    int result = head1->data + head2->data + carry;
    head = createNode(result % 10);
    head->next = addLinkedLists(head->next, head1->next, head2->next, result / 10);
    return head;
}

int findGreater(Node *head1, Node *head2){
    if (head1 == NULL && head2 == NULL)
        return 0;
    int result = 0;
    result = findGreater(head1->next, head2->next);
    if (result != 0)
        return result;
    if (head1->data > head2->data)
        return -1;
    else if (head1->data < head2->data)
        return 1;
    else
        return 0;
}

Node *subtractLinkedLists(Node *head, Node *head1, Node *head2, int borrow){
    if (head1 == NULL && head2 == NULL)
        return NULL;
    Node *temp1 = head1, *temp2 = head2;
    int greater = findGreater(head1, head2);
    if (greater == -1){
        int result = head1->data - head2->data - borrow;
        if (result < 0){
            result += 10;
            borrow = 1;
        }
        else
            borrow = 0;
        head = createNode(result);
        head->next = subtractLinkedLists(head->next, head1->next, head2->next, borrow);
        return head;
    }
    else{
        int result = head2->data - head1->data - borrow;
        if (result < 0){
            result += 10;
            borrow = 1;
        }
        else
            borrow = 0;
        head = createNode(result);
        head->next = subtractLinkedLists(head->next, head2->next, head1->next, borrow);
        return head;
    }
}

int main(){
    string s1, s2;
    cout << "Enter first number: ";
    cin >> s1;
    cout << "Enter second number: ";
    cin >> s2;
    Node *head1 = createLinkedList(s1);
    Node *head2 = createLinkedList(s2);
    makeLinkedListLengthEqual(head1, head2);
    cout << "First linked list is: ";
    printReverseLinkedList(head1);
    cout << endl << "Second linked list is: ";
    printReverseLinkedList(head2);
    cout << endl
         << "Addition is: ";
    Node *head = addLinkedLists(head, head1, head2, 0);
    printReverseLinkedList(head);
    cout << endl
         << "Subtraction is: ";
    head = subtractLinkedLists(head, head1, head2, 0);
    printReverseLinkedList(head);
    cout << endl;
    return 0;
}