package hello.mission.studycafe.io;

import hello.mission.studycafe.model.pass.locker.StudyCafeLockerPasses;

public interface PassReader {

    StudyCafeLockerPasses readStudyCafePasses();

    StudyCafeLockerPasses readLockerPasses();
}