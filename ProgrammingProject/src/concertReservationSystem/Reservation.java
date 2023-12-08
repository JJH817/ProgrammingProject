package concertReservationSystem;

public class Reservation {
	private static int reservationCounter = 1;

    private String name;
    private String phoneNumber;
    private Seat reservedSeat;  
    private int reservationNumber;
    private int numberOfSeats; //한 사람이 예약한 좌석수>

    // 새로운 예매을 위한 컨스트럭터
    public Reservation(String name, String phoneNumber, Seat reservedSeat)throws InvalidSeatException, ExceededMaxSeatsException {
    	if (reservedSeat == null) {
            throw new InvalidSeatException("예약 불가능한 좌석입니다.");
        }
    	
    	this.numberOfSeats = 1;
        this.reservationNumber = reservationCounter++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.reservedSeat = reservedSeat;
    }

    // 예매번호로 기존의 예약을 불러오기 위한 컨스트럭터
    public Reservation(int reservationNumber, String name, String phoneNumber, Seat reservedSeat) {
        this.reservationNumber = reservationNumber;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.reservedSeat = reservedSeat;

       reservationCounter = Math.max(reservationCounter, reservationNumber + 1);
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Seat getReservedSeat() {
        return reservedSeat;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void updateNumberOfSeats(int additionalSeats) {
        this.numberOfSeats += additionalSeats;
    }

    @Override
    public String toString() {
        return "예약번호: " + reservationNumber + ", 예약자: " + name +
                ", 전화번호: " + phoneNumber + ", 예약된 좌석: " + reservedSeat;
    }
}
