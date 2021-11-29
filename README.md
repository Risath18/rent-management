# ENSF 480 Property Rental Management System
Course: ENSF 480: Principles of Software Design
Section: L02/B02 - Dr. Moshirpour
Group: 9
Semester: Fall 2021

|Name|UCID|
|---|---|
|Liana Goodman | 30089196 |
|Jay Gurjar | 30096042|
|Alexis Hamrak | 30065648|
|Risat Haque | 30094174|

## Requirements:
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

## Development
The Property Rental Management System design project is divided into 2 processes: diagrams, and code implementation.

### Diagarams
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

### Code implementation
The code implementation is found in this git repository.
