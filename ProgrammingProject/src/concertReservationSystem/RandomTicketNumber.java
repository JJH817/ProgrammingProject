package concertReservationSystem;

import java.util.*;


//랜덤한 번호 부여 class
public class RandomTicketNumber {
	
	private int ticketNum;
	private Set<Integer> usedReservationNumbers = new HashSet<>();		//중복 방지용 set

    public int getReservationNumber() {
        return ticketNum;
    }

    public void setReservationNumber(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public void createNewReservation() {
        this.ticketNum = generateUniqueReservationNumber();
    }

    private int generateUniqueReservationNumber() {
        Random random = new Random();
        int randomReservationNumber;
        do {
            randomReservationNumber = random.nextInt(9000) + 1000;		//4자리의 랜덤한 번호 부여
        } while (usedReservationNumbers.contains(randomReservationNumber));
        usedReservationNumbers.add(randomReservationNumber);
        return randomReservationNumber;
    }

}
