package com.latincoder.sbconcurrent.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Optional;

@JsonSerialize
@Value.Immutable
public interface StudentResponse {
    public String status();
    public Optional<String> error();
}
