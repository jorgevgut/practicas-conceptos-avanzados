package com.latincoder.sbconcurrent.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

/**
 * This way of defining an object by making it an interface plus using Immutable library
 * will save up time and increase productivity for the developer.
 * Look up for more info at {https://immutables.github.io/}
 */
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@Value.Immutable
public interface Student {
    @JsonProperty("name")
    public String name();
    @JsonProperty("age")
    public Integer age();
    @JsonProperty("interests")
    public List<String> interests();
}
