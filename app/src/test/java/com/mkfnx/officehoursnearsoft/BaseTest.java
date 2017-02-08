package com.mkfnx.officehoursnearsoft;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowApplication;

/**
 * Created by mkfnx on 07/02/17.
 */

public class BaseTest {
    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        if (RuntimeEnvironment.application != null) {
            ShadowApplication shadowApplication = Shadows.shadowOf(RuntimeEnvironment.application);
        }
    }
}
