import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceImplTest {

    @Mock
    private GeolocationClient geolocationClient;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUserWhenGeolocationIsCanada() {
        // given
        String username = "Ram";
        String password = "secret@123!";
        String ipAddress = "192.168.0.1";
        Geolocation geolocation = new Geolocation("Canada", "Toronto");
        when(geolocationClient.geolocation(ipAddress)).thenReturn(geolocation);

        // when
        User user = userService.createUser(username, password, ipAddress);

        // then
        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertEquals(geolocation.getCity(), user.getCity());
    }

    @Test
    public void testCreateUserWhenGeolocationIsNotCanada() {
        // given
        String username = "Hari";
        String password = "Secret123!@";
        String ipAddress = "192.168.0.2";
        Geolocation geolocation = new Geolocation("India", "Delhi");
        when(geolocationClient.geolocation(ipAddress)).thenReturn(geolocation);

        // when
        User user = userService.createUser(username, password, ipAddress);

        // then
        assertNull(user);
    }

    @Test
    public void testCreateUserWhenGeolocationIsNull() {
        // given
        String username = "Mark";
        String password = "secret@1!";
        String ipAddress = "192.168.0.3";
        when(geolocationClient.geolocation(ipAddress)).thenReturn(null);

        // when
        User user = userService.createUser(username, password, ipAddress);

        // then
        assertNull(user);
    }
}
