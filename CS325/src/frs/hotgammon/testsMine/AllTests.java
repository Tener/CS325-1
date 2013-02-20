package frs.hotgammon.testsMine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({CoreTests.class, BetaMonTests.class, AlphaMonTests.class, BoardTests.class, LocationTests.class, DeltaMonTests.class, GammaMonTests.class} )
public class AllTests {
}