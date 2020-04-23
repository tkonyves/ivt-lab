package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;


public class GT4500Test {

  private TorpedoStore mockTS;
  private GT4500 ship;

  @BeforeEach
  public void init(){
    mockTS = mock(TorpedoStore.class);

    this.ship = new GT4500(mockTS, mockTS);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    // Set the behavior of the mock
    when(mockTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTS, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    // Set the behavior of the mock
    when(mockTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockTS, times(2)).fire(1);
  }

  public void multipleTorpedoFiring(int nr, FiringMode fm) {
      List<Boolean> results = new ArrayList<Boolean>();

      for (int i = 0; i < nr; i++) {
        results.add(ship.fireTorpedo(fm));
      }

      for (int i = 0; i < nr; i++) {
        assertEquals(true, results.get(i));
      }

      int invocationTimes = fm == FiringMode.SINGLE ? nr : nr * 2;
      verify(mockTS, times(invocationTimes)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_10_times() {
      // Arrange
      when(mockTS.fire(1)).thenReturn(true);

      // Act, Assert
      multipleTorpedoFiring(10, FiringMode.SINGLE);
  }

  @Test
  public void fireTorpedo_Single_100_times() {
      // Arrange
      when(mockTS.fire(1)).thenReturn(true);

      // Act, Assert
      multipleTorpedoFiring(100, FiringMode.SINGLE);
  }

  @Test
  public void fireTorpedo_All_10_times() {
      // Arrange
      when(mockTS.fire(1)).thenReturn(true);

      // Act, Assert
      multipleTorpedoFiring(10, FiringMode.ALL);
  }

  @Test
  public void fireTorpedo_All_100_times() {
      // Arrange
      when(mockTS.fire(1)).thenReturn(true);

      // Act, Assert
      multipleTorpedoFiring(100, FiringMode.ALL);
  }

  @Test
  public void fireTorpedo_AllAndSingle_alternate_100_times() {
    // Arrange
    when(mockTS.fire(1)).thenReturn(true);

    // Act
    int firingRounds = 100;
    int firesPerRound = 3;
    List<Boolean> results = new ArrayList<Boolean>();
    for (int i = 0; i < firingRounds; i++) {
      results.add(ship.fireTorpedo(FiringMode.ALL));
      results.add(ship.fireTorpedo(FiringMode.SINGLE));
    }

    // Assert
    for (int i = 0; i < results.size(); i++) {
      assertEquals(true, results.get(i));
    }
    verify(mockTS, times(firingRounds * firesPerRound)).fire(1);
  }
}
