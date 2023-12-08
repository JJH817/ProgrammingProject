package concertReservationSystem;

public class Seat {
	private String seatGrade; //좌석등급
    private int seatNumber;  //좌석번호

    public Seat(String seatGrade, int seatNumber) {
        this.seatGrade = seatGrade;
        this.seatNumber = seatNumber;
    }

    public String getSeatGrade() {
        return seatGrade;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    @Override
    public String toString() {
        return seatGrade + "석 " + seatNumber + "번";
    }
}
