Author: Bhavisha Anil Gaikwad

Date: 27/05/2026

**Intro:**

This system implements a dispatch solution that uses 4 modules

1. DSAGraph.java - The main Graph algorithms and creation of te city. It uses DSAGraphEdge and DSAGraphNode also
2. DSAGraphNode.java - implements DSALinkedList and DSAListNode
3. DSAHashTable.java - handles passenger and driver information. It is supported by DSAHashEntry
4. DSAHeap.java - prioritizes the requests and assigns drivers. Supported by DSAHashEntry
5. Sorts.java - uses mergeSort and quickSort to sort the time taken to reach the location

<u>**Supporting classes:**</u>

Passenger.java - handles the passenger details and validation

Driver.java - handle Driver details ad validation

PickupRequest.java - handles the details of request.

FileParser.java - helps in parsing the files (passenger.csv and drivers.csv)

---

<u>**Additional files:**</u>

passenger.csv - the sample passenger details (20 records)

driver.csv -- the sample driver details (20 records)

---

**How to run:**

**Complile the files:**

    -> javac *.java

**Run individual Modules**

<u>Module 1:</u>

	-> java DSAGraph

<u>Module 2:</u>

	-> java DSAHashTable

<u>Module 3:</u>

	-> java DSAHeap

<u>Module 4:</u>

	-> java Sorts

