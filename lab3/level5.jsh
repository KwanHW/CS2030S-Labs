public Booking findBestBooking(Request request, Driver[] drivers){
    Booking selectedBooking = new Booking(drivers[0],request) ;

    for (Driver d : drivers){
        Booking b = new Booking(d,request);

        // If the current booking is cheaper OR
        // waiting time is lesser than selectedBooking
        if (b.compareTo(selectedBooking) < 0) {
            selectedBooking = b;
        }
    }

    return selectedBooking;
}
