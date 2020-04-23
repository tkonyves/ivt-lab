package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class TorpedoStoreTest {
    @BeforeEach
    public void init(){
    }
    
    @Test
    public void fireTorpedo() {
        TorpedoStore torpedoStore = new TorpedoStore(1);
        boolean resultFire = torpedoStore.fire(1);
        boolean isStoreEmpty = torpedoStore.isEmpty();

        assertEquals(true, resultFire);
        assertEquals(true, isStoreEmpty);
    }
}