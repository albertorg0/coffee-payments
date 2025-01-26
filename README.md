# Coffee Payments

## Overview

Coffee Payments is a demo Spring Boot application that manages coffee orders and payments. This project includes RESTful endpoints for retrieving payment information and managing orders.

## Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- Docker (optional, for running the database)

## Configuration

1. **Clone the repository:**

    ```sh
    git clone https://github.com/yourusername/coffee-payments.git
    cd coffee-payments
    ```

2. **Database Configuration:**

   The application uses an in-memory H2 database. The database configuration is already configured to load the data from the JSON files located in `src/main/resources`.

## Running the Application

1. **Build the project:**

    ```sh
    mvn clean install
    ```

2. **Run the application:**

    ```sh
    mvn spring-boot:run
    ```

   The application will start on `http://localhost:8080`.

## API Endpoints

### Get Amount Paid Per User

- **URL:** `/payments/amount-paid`
- **Method:** `GET`
- **Response:**
    ```json
    [
        {
            "user": "user1",
            "amountPaid": 70.0
        },
        {
            "user": "user2",
            "amountPaid": 30.0
        }
    ]
    ```

### Get Amount Owed Per User

- **URL:** `/payments/amount-owed`
- **Method:** `GET`
- **Response:**
    ```json
    [
        {
            "user": "user1",
            "amountOwed": 0.0
        },
        {
            "user": "user2",
            "amountOwed": -10.0
        }
    ]
    ```


## Testing the Application

### Unit Tests

To run the unit tests, use the following command:

```sh
mvn test
```

### Integration Tests

To run the integration tests, use the following command:

```sh
mvn verify
```

### Manual Testing with curl

You can manually test the endpoints using `curl`:

- Get the amount paid per user:
```sh
curl -X GET "http://localhost:8080/payments/amount-paid" -H "accept: application/json"
```
- Get the amount owed per user:
```sh
curl -X GET "http://localhost:8080/payments/amount-owed" -H "accept: application/json"
```

## License

This project is licensed under the [MIT License](https://opensource.org/license/mit).
