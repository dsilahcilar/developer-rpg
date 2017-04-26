package org.deniz.rpg.io;

import org.deniz.rpg.engine.game.Listener;
import org.deniz.rpg.engine.storage.StorageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InputProxyTest {
    final String text = "Read something...";

    @Mock
    private InputService inputService;

    @Mock
    private StorageService storageService;

    private InputProxy inputProxy;

    @Before
    public void init() {
        inputProxy = new InputProxy(inputService, storageService);
    }

    @Test
    public void readIntShouldCallInputServiceWhenStorageServiceCouldntFindInput() throws Exception {
        doReturn(null).when(storageService).getInput(0);
        inputProxy.readInt(text);
        verify(inputService).readInt(text);
    }

    @Test
    public void readIntShouldNotCallInputServiceWhenStorageServiceFindInput() throws Exception {
        doReturn(1).when(storageService).getInput(0);
        inputProxy.readInt(text);
        verifyZeroInteractions(inputService);
    }

    @Test
    public void readStrShouldCallInputServiceWhenStorageServiceCouldntFindInput() throws Exception {
        doReturn(null).when(storageService).getInputStr(0);
        inputProxy.readStr(text);
        verify(inputService).readStr(text);
    }

    @Test
    public void readStrShouldNotCallInputServiceWhenStorageServiceFindInput() throws Exception {
        doReturn("readed").when(storageService).getInputStr(0);
        inputProxy.readStr(text);
        verifyZeroInteractions(inputService);
    }

    @Test
    public void shouldNotifyListenersWhenEveryInputOperation() throws Exception {
        Listener listener = Mockito.mock(Listener.class);
        inputProxy.addListener(listener);

        when(storageService.getInput(0)).thenReturn(null);
        when(inputService.readInt(any())).thenReturn(8);

        when(storageService.getInput(1)).thenReturn(3);
        when(storageService.getInputStr(0)).thenReturn("readed");

        when(storageService.getInputStr(1)).thenReturn(null);
        when(inputService.readStr(any())).thenReturn("deniz");


        inputProxy.readInt(text);
        inputProxy.readStr(text);


        Map<Integer, Integer> intMap = new HashMap<>();
        intMap.put(0, 8);
        Map<Integer, String> strMap = new HashMap<>();
        strMap.put(0, "readed");
        verify(listener, atLeastOnce()).listen(intMap, strMap);

        inputProxy.readInt(text);
        inputProxy.readStr(text);
        strMap.put(1, "deniz");
        intMap.put(1, 3);
        verify(listener, atLeastOnce()).listen(intMap, strMap);
    }


}