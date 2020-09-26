//ARRIVE > SERVE > DONE
// Customer just arrives, ArriveEvent
new ArriveEvent(new Customer(1, 0.5), Arrays.asList(new Server(1, true, false, 0))) // server is available

// As the server is available, it will return a ServeEvent
// Finds an available server, if there is the server will serve the customer
new ArriveEvent(new Customer(1, 0.5), Arrays.asList(new Server(1, true, false, 0))).execute()

// Server is done serving the customer, returns a DoneEvent
new ArriveEvent(new Customer(1, 0.5), Arrays.asList(new Server(1, true, false, 0))).execute().execute()

// ARRIVE > WAIT > SERVE > DONE
// Customer just arrives, ArriveEvent
new ArriveEvent(new Customer(2, 0.6), Arrays.asList(new Server(1, false, false, 1.0))) // server is busy, but no waiting customer

// Server is unavaible and has no waiting customers
// Customer is waits for his turn (WaitEvent), Server's hasWaitingCustomer becomes true
new ArriveEvent(new Customer(2, 0.6), Arrays.asList(new Server(1, false, false, 1.0))).execute()

// Server is done serving first customer, Customer is being served ServeEvent
new ArriveEvent(new Customer(2, 0.6), Arrays.asList(new Server(1, false, false, 1.0))).execute().execute()

// Server is done serving
new ArriveEvent(new Customer(2, 0.6), Arrays.asList(new Server(1, false, false, 1.0))).execute().execute().execute()

// Customer just arrives, ArriveEvent
new ArriveEvent(new Customer(3, 0.6), Arrays.asList(new Server(1, false, true, 1.0))) // server is busy with waiting customer

// Server is unavailable and has waiting customer
// Customer leaves
new ArriveEvent(new Customer(3, 0.6), Arrays.asList(new Server(1, false, true, 1.0))).execute()

// Sever 1 is unavailable but Server 2 is available
// Server 2 serves Customer (Same as first case)
new ArriveEvent(new Customer(1, 0.5), Arrays.asList(new Server(1, false, false, 0), new Server(2, true, false, 0))).execute() // two servers: (1) busy; (2) free

new ArriveEvent(new Customer(1, 0.5), Arrays.asList(new Server(1, false, false, 0), new Server(2, true, false, 0))).execute().execute()

// Both Servers are unavailable but Server 2 has no waiting customer
// Customer waits under Server 2 (WaitEvent)
new ArriveEvent(new Customer(1, 0.5), Arrays.asList(new Server(1, false, true, 5.0), new Server(2, false, false, 10.0))).execute() // both servers busy, (1) has waiting customer

// When Server 2 is done, Server 2 serves Customer (Same as 2nd use case)
new ArriveEvent(new Customer(1, 0.5), Arrays.asList(new Server(1, false, true, 5.0), new Server(2, false, false, 10.0))).execute().execute()

new ArriveEvent(new Customer(1, 0.5), Arrays.asList(new Server(1, false, true, 5.0), new Server(2, false, false, 10.0))).execute().execute().execute()

new ArriveEvent(new Customer(1, 0.5), Arrays.asList(new Server(1, false, true, 5.0), new Server(2, false, true, 10.0))).execute() // both busy with waiting customer

/exit
