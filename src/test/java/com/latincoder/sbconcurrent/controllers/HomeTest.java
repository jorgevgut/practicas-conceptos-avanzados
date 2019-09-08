package com.latincoder.sbconcurrent.controllers;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class HomeTest {

    @MockBean
    public  ExecutorService executor;

  //  @InjectMocks
//    Home homeController;

    @Test
    public void testHelloReturnsStringHappyCase() {
        //String string = homeController.hello();
        //Assert.assertNotNull(string);
    }

    @Ignore
    @Test(expected = NullPointerException.class)
    public void testHelloFailsBecauseOfExecutorNullPointerException() {
        Mockito.doThrow(NullPointerException.class).when(executor).execute(any());
       // homeController.hello("Jorge", 23);
    }

    @Ignore
    @Test(expected = NumberFormatException.class)
    public void testLePasePalabrasEnVezNUmeros() {
     //   homeController.hello();
    }

}
