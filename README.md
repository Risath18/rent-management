<img alt="Java" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white"/> 
<img alt="Maven" src="https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white"/> 
<img alt="Postgres" src="https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white"/> 
<img alt="Heroku" src="https://img.shields.io/badge/heroku-%23430098.svg?style=for-the-badge&logo=heroku&logoColor=white"/> 
<img alt="SQLite" src="https://img.shields.io/badge/sqlite-%2307405e.svg?style=for-the-badge&logo=sqlite&logoColor=white"/>

---

# ENSF 480-Property Rental Management System

**Course:** ENSF 480: Principles of Software Design

**Section:** L02/B02 - Dr. Moshirpour

**Group:** 9

**Semester:** Fall 2021

---

| **Name**      | **UCID** |
| ------------- | -------- |
| Liana Goodman | 30089196 |
| Jay Gurjar    | 30096042 |
| Alexis Hamrak | 30065648 |
| Risat Haque   | 30094174 |

---

### **Prerequisites (Development)**

---

For development purposes, you must have the latest version of Maven and Java installed.

- [Java](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/)

---

### **Installation (Standard)**

---

To run the program, make sure you have unzipped the provided file with **.jar** and **.env**. Then, double click on the **.jar** file and it should run normally.

---

### **Installation (Development)**

---

To develop or run this repository locally: (You may require a **.env** file to connect to the database and Twilio API. Contact the admin for information regarding this.)

```bash
git clone https://github.com/Risath18/rent-management.git
cd rent-management
mvn clean
mvn package
mvn exec:java
```

---

### **Program Specific Notes**

---

- You can register as a Landlord and Renter in this program. Not as a manager. Contact the admin if you would like access to Manager accounts.
- Emails will be sent to actual accounts through our program.
- Spamming buttons on the GUI may break or send mass emails to connected accounts. Avoid doing this.
- Emails may be sent to spam folder.

---

## **Requirements:**

---

You have been hired by a company to analyse, design, and develop an online Property Rental Management System (PRMS) that its minimal requirements include:

1. Landlords should be able to register their properties using this online application. Once their property is registered they should be able to pay certain fee and make their property posted online and be available to renters to view it. The fee is certain amount in dollars for a fixed period of time (example 60 days).
2. Regular renters don't need to login, and should be able to enter their search criteria such as:
   - Apartment, attached/detached house, townhouse, etc.
   - Number of bedrooms,
   - Number of bathrooms
   - Furnished/unfurnished
   - City quadrant: SW. NW, NE, SE
3. Registered renters, must first login. This group of renters, in addition previous functionalities for regular renters, will have the privilege of being notified, when new listings is posted that matches their search criteria. Also, they should be to unsubscribe at any time.
4. Renters who need further details should be able to send email to the landlord without seeing the landlord's name, or landlord's email address.
5. Renters who are interested in property should be able to send email to landlord to arrange a meeting to view the property, and possibly sign a contract. Note: Your system will only provide email communication between renters and landlord, and doesn't do anything with signing the contract and rest of the details.
6. Managers should be able to login in, set or change the amount and period of fees, and should have a full access to the renters, landlords, and properties information via company's database system, which can be some sort of light database. Please notice your application need to access this database engine to save or
   retrieve properties, and registered customers, and landlord.
7. Mangers should be able to ask for a periodical summary report that shows:
   - Total number of houses listed in the period. Notice that some houses that are listed may not be active anymore. It means some houses their posting period can be expired or landlords have cancelled their posting, therefore the renters cannot view them anymore.
   - Total number of houses rented in the period
   - Total number of active listing.
   - List of houses rented in the period. Which displays, the landlord's name, the house's id number, followed by its address.
8. Managers and Landlords should be able to change the state of a listing, from active, to rented, cancelled, or suspended.
9. The system needs to have flexibility for future changes
10. Other requirements, as might be discovered during development process.

---

## **Development**

---

## The Property Rental Management System design project is divided into 2 processes: diagrams, and code implementation.

### **Diagrams**

---

The system must be modelled with

- a system use case diagram,
- system interaction diagrams (1 per group member for a total of 4),
- a class diagram,
- a detail class specification diagram,
- state transition diagrams (1 per group member for a total of 4),
- systems activity diagarams,
- a package diagram, and
- a deployment diagram.

These diagrams can be found https://drive.google.com/drive/u/0/folders/1X-Zi68zVS4sIzCSZG89MpbMnslWlfQhp

---

### **Code implementation**

---

The code implementation is found in this git repository.

---

### **Contributing**

---

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change. Please base all pull requests off of the main branch as they will be rebase merged.

---

### **License**

---

[MIT](https://choosealicense.com/licenses/mit/)
