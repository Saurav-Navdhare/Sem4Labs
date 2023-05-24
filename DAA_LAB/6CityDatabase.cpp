/*
Implement a city database using unordered lists. Each database record contains the name of the city(a string of arbitrary length) and the coordinates of the city expressed as integer x and y coordinates.Your program should allow following functionalities:
a)Insert a record, 
b)Delete a record by name or coordinate,
c)Search a record by name or coordinate. 
d)Print all records within a given distance of a specified point. 

Implement  the  database  using an  array-based  list  implementation,  and  then  a  linked  list implementation. Perform following analysis:
a)Collect running time statistics for each operation in both implementations. 
b)What are your conclusions about the relative advantages and disadvantages of the two implementations? 
c)Would storing records on the list in alphabetical order by city name speed any of the operations? 
d)Would keeping the list in alphabetical order slow any of the operations?
*/
#include <iostream>
using namespace std;

class City {
public:
    string name;
    int x;
    int y;
    City* next;

    City(string name, int x, int y) : name(name), x(x), y(y), next(nullptr) {}
};

class CityDatabase {
private:
    City* head;
    City* tail;

public:
    CityDatabase() : head(nullptr), tail(nullptr) {}

    void insert(City city) {
        City* newCity = new City(city.name, city.x, city.y);
        if (head == nullptr) {
            head = newCity;
            tail = newCity;
        } else {
            tail->next = newCity;
            tail = newCity;
        }
    }

    City* searchByName(string name) {
        City* current = head;
        while (current != nullptr) {
            if (current->name == name) {
                return current;
            }
            current = current->next;
        }
        return nullptr;
    }

    City* searchByCoordinate(int x, int y) {
        City* current = head;
        while (current != nullptr) {
            if (current->x == x && current->y == y) {
                return current;
            }
            current = current->next;
        }
        return nullptr;
    }

    void removeByName(string name) {
        City* current = head;
        City* prev = nullptr;
        while (current != nullptr) {
            if (current->name == name) {
                if (prev == nullptr) {
                    head = current->next;
                } else {
                    prev->next = current->next;
                }
                delete current;
                return;
            }
            prev = current;
            current = current->next;
        }
    }

    void removeByCoordinate(int x, int y) {
        City* current = head;
        City* prev = nullptr;
        while (current != nullptr) {
            if (current->x == x && current->y == y) {
                if (prev == nullptr) {
                    head = current->next;
                } else {
                    prev->next = current->next;
                }
                delete current;
                return;
            }
            prev = current;
            current = current->next;
        }
    }

    void printWithinDistance(int x, int y, int distance) {
        City* current = head;
        while (current != nullptr) {
            int d = (current->x - x) * (current->x - x) + (current->y - y) * (current->y - y);
            if (d <= distance * distance) {
                cout << current->name << " " << current->x << " " << current->y << endl;
            }
            current = current->next;
        }
    }
};

int main() {
    CityDatabase db;

    // Insert cities
    db.insert(City("Ahmedabad", 0, 0));
    db.insert(City("Bhuj", 1, 1));
    db.insert(City("Mumbai", 2, 2));
    db.insert(City("Bengaluru", 3, 3));

    // Search cities by name
    City* ahmedabad = db.searchByName("Ahmedabad");
    if (ahmedabad != nullptr) {
        cout << ahmedabad->name << " " << ahmedabad->x << " " << ahmedabad->y << endl;
    } else {
        cout << "City not found" << endl;
    }

    // Search cities by coordinate
    City* bhuj = db.searchByCoordinate(1, 1);
    if (bhuj != nullptr) {
        cout << bhuj->name << " " << bhuj->x << " " << bhuj->y << endl;
    } else {
        cout << "City not found" << endl;
    }

    // Remove cities by name
    db.removeByName("Mumbai");

    // Remove cities by coordinate
    db.removeByCoordinate(3, 3);

    // Print cities within distance
    db.printWithinDistance(0, 0, 2);

    return 0;
}
