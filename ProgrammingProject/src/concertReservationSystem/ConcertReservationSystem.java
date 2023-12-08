package concertReservationSystem;


class InvalidSeatException extends Exception {
	// 이미 선점된 좌석을 지정했을 경우 발생하는 exception
    public InvalidSeatException(String message) {
        super(message);
    }
}

class ExceededMaxSeatsException extends Exception {
	// 2자리를 초과하여 예매하려고 할 때 일어나는 exception
    public ExceededMaxSeatsException(String message) {
        super(message);
    }
}

public class ConcertReservationSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
