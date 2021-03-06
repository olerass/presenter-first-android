package com.olerass.pfexample.android.geoquiz.domain.state;

import android.os.Bundle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AndroidStateManagerTest {
    @Test
    public void saveInt_savesTheGivenIntWithTheGivenKeyInBackingBundle_whenCalled() {
        Bundle bundle = mock(Bundle.class);
        AndroidStateManager manager = new AndroidStateManager(bundle);

        manager.saveInt("key", 1);

        verify(bundle).putInt("key", 1);
    }

    @Test
    public void loadInt_returnsTheValueForTheGivenKey_whenKeyExists() {
        Bundle bundle = mock(Bundle.class);
        when(bundle.getInt(eq("key"), anyInt())).thenReturn(1);
        AndroidStateManager manager = new AndroidStateManager(bundle);

        int res = manager.loadInt("key", 0);

        assertEquals(1, res);
    }

    @Test
    public void loadInt_returnsTheProvidedDefault_whenBundleIsNull() {
        AndroidStateManager manager = new AndroidStateManager(null);

        int res = manager.loadInt("key", 0);

        assertEquals(0, res);
    }

    @Test
    public void loadInt_returnsTheProvidedDefault_whenKeyDoesNotExist() {
        Bundle bundle = mock(Bundle.class);
        when(bundle.getInt("key", 0)).thenReturn(0);
        AndroidStateManager manager = new AndroidStateManager(bundle);

        int res = manager.loadInt("key", 0);

        assertEquals(0, res);
    }

    @Test
    public void saveBool_savesTheGivenBoolWithTheGivenKeyInBackingBundle_whenCalled() {
        Bundle bundle = mock(Bundle.class);
        AndroidStateManager manager = new AndroidStateManager(bundle);

        manager.saveBool("key", true);

        verify(bundle).putBoolean("key", true);
    }

    @Test
    public void loadBool_returnsTheValueForTheGivenKey_whenKeyExists() {
        Bundle bundle = mock(Bundle.class);
        when(bundle.getBoolean(eq("key"), anyBoolean())).thenReturn(true);
        AndroidStateManager manager = new AndroidStateManager(bundle);

        boolean res = manager.loadBool("key", false);

        assertEquals(true, res);
    }

    @Test
    public void loadBool_returnsTheProvidedDefault_whenBundleIsNull() {
        AndroidStateManager manager = new AndroidStateManager(null);

        boolean res = manager.loadBool("key", true);

        assertEquals(true, res);
    }

    @Test
    public void loadBool_returnsTheProvidedDefault_whenKeyDoesNotExist() {
        Bundle bundle = mock(Bundle.class);
        when(bundle.getBoolean("key", false)).thenReturn(false);
        AndroidStateManager manager = new AndroidStateManager(bundle);

        boolean res = manager.loadBool("key", false);

        assertEquals(false, res);
    }
}
