package com.latincoder.sbconcurrent.controllers;

import com.google.common.collect.ImmutableList;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/")
public class Home {

    private ExecutorService executor;

    @Autowired
    public Home(ExecutorService executor) {
        this.executor = executor;
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String index() {
        StringBuffer stringBuffer = new StringBuffer();
        List<String> words = ImmutableList.of("1", "2", "3", "4");
        CompletableFuture<String> futureString = produceFutureString("asdas");
        words.stream()
                .parallel()
                .filter(this::isValidNumber)
                .map(Integer::valueOf)
                .forEach(stringBuffer::append);

        return stringBuffer.toString();
    }

    private CompletableFuture<String> produceFutureString(String value) {
        return CompletableFuture.supplyAsync(
                () -> String.format("%s - %s\n", value,"hello"),
                executor);
    }


    public boolean isValidNumber(String posibleNumero) {
        return Try.of(() -> Integer.valueOf(posibleNumero)).isSuccess();
    }

    private void printCurrentTime() {
        System.out.println(LocalDateTime.now());
    }

    private void printNumber(int n) {
        System.out.println(n);
    }

}
