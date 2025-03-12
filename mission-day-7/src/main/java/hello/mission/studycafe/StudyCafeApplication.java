package hello.mission.studycafe;

import hello.mission.studycafe.io.provider.LockerPassFileReader;
import hello.mission.studycafe.io.provider.SeatPassFileReader;
import hello.mission.studycafe.provider.LockerPassProvider;
import hello.mission.studycafe.provider.SeatPassProvider;


public class StudyCafeApplication {

    public static void main(String[] args) {
        SeatPassProvider seatPassProvider = new SeatPassFileReader();
        LockerPassProvider lockerPassProvider = new LockerPassFileReader();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
                seatPassProvider, lockerPassProvider
        );
        studyCafePassMachine.run();
    }

}