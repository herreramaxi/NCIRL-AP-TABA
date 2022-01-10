# Bank loan calculator
Distributed Cloud application composed by two nodes, one for the UI and one for the backend. The frontend node is a test client and is built as a single page application (SPA) with Angular. The backend node provides the RESTful web services for the bank loan calculator, and it is built with JAX-RS/Jersey framework.
This repository is the implementation of the backend node. The frontend can be found on following repository: https://github.com/herreramaxi/NCIRL-AP-TABA-UI

## Architecture
<img src="https://github.com/herreramaxi/NCIRL-AP-TABA/blob/main/repoResources/Architecture.png" width="900" height="300">

# Java RESTful webservices (JAX-RS/Jersey)
It is the backend of the distributed application "bank loan calculator", and it provides the following services:
* Computation of the monthly payment based on the amount of money borrowed from the bank, the annual interest rate, and the period of the loan (specified as months) provided as input data.
* Computation of the total repayment amount based on the monthly payment and duration of the loan (specified as months) provided as input data.

## URLS
### Backend: Java RESTful Web Services
#### Health check status 
https://ncirl-ap-mhtaba.herokuapp.com/

#### Monthly payment
* Path: /api/monthlypayment/{anualIR}/{months}/{amount}
* https://ncirl-ap-mhtaba.herokuapp.com/api/monthlypayment/6/360/100000

#### Total repayment
* Path: /api/totalrepayment/{monthlyPayment}/{months}
* https://ncirl-ap-mhtaba.herokuapp.com/api/totalrepayment/599.550/360

### Frontend: Angular Web App
https://ncirl-ap-mhtaba-ui.herokuapp.com/

## Github repositories
### Backend: Java REST Web Services
https://github.com/herreramaxi/NCIRL-AP-TABA/
### Frontend: Angular Web App
https://github.com/herreramaxi/NCIRL-AP-TABA-UI
