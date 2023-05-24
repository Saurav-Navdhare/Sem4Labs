#include <iostream>
#include <string>
#include <vector>
#include <cmath>

struct City
{
    std::string name;
    int x;
    int y;
};

std::vector<City> cities;

void insertCity(const std::string &name, int x, int y)
{
    City city;
    city.name = name;
    city.x = x;
    city.y = y;
    cities.push_back(city);
}

void deleteCity(const std::string &name)
{
    for (int i = 0; i < cities.size(); i++)
    {
        if (cities[i].name == name)
        {
            cities.erase(cities.begin() + i);
            i--;
        }
    }
}

void deleteCity(int x, int y)
{
    for (int i = 0; i < cities.size(); i++)
    {
        if (cities[i].x == x && cities[i].y == y)
        {
            cities.erase(cities.begin() + i);
            i--;
        }
    }
}

std::vector<City> searchCity(const std::string &name)
{
    std::vector<City> results;
    for (int i = 0; i < cities.size(); i++)
    {
        if (cities[i].name == name)
        {
            results.push_back(cities[i]);
        }
    }
    return results;
}

std::vector<City> searchCity(int x, int y)
{
    std::vector<City> results;
    for (int i = 0; i < cities.size(); i++)
    {
        if (cities[i].x == x && cities[i].y == y)
        {
            results.push_back(cities[i]);
        }
    }
    return results;
}

void printCitiesWithinDistance(int x, int y, int distance)
{
    for (int i = 0; i < cities.size(); i++)
    {
        int dx = cities[i].x - x;
        int dy = cities[i].y - y;
        if (std::sqrt(dx * dx + dy * dy) <= distance)
        {
            std::cout << cities[i].name << " " << cities[i].x << " " << cities[i].y << std::endl;
        }
    }
}

int main()
{
    int choice;
    std::string name;
    int x, y, distance;
    std::vector<City> results;
    while (true){
        std::cout << "1. Insert a city" << std::endl;
        std::cout << "2. Delete a city" << std::endl;
        std::cout << "3. Search for a city" << std::endl;
        std::cout << "4. Print cities within a distance" << std::endl;
        std::cout << "5. Exit" << std::endl;
        std::cout << "Enter your choice: ";
        std::cin >> choice;
        if (choice == 1){
            std::cout << "Enter the name of the city: ";
            std::cin >> name;
            std::cout << "Enter the x coordinate of the city: ";
            std::cin >> x;
            std::cout << "Enter the y coordinate of the city: ";
            std::cin >> y;
            insertCity(name, x, y);
        }
        else if (choice == 2){
            std::cout << "Enter the name of the city: ";
            std::cin >> name;
            deleteCity(name);
        }
        else if (choice == 3){
            std::cout << "Enter the name of the city: ";
            std::cin >> name;
            results = searchCity(name);
            for (int i = 0; i < results.size(); i++){
                std::cout << results[i].name << " " << results[i].x << " " << results[i].y << std::endl;
            }
        }
        // print cities within a distance of a given city and if theres no city with that name print no city found
        else if (choice == 4){
            std::cout << "Enter the name of the city: ";
            std::cin >> name;
            std::cout << "Enter the distance: ";
            std::cin >> distance;
            results = searchCity(name);
            if (results.size() == 0){
                std::cout << "No city found" << std::endl;
            }
            else{
                for (int i = 0; i < results.size(); i++){
                    printCitiesWithinDistance(results[i].x, results[i].y, distance);
                }
            }
        }
        else if (choice == 5){
            break;
        }
        else{
            std::cout << "Invalid choice" << std::endl;
        }
    }
    return 0;
}