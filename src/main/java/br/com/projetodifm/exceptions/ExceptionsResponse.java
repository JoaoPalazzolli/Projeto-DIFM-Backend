package br.com.projetodifm.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ExceptionsResponse implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private Set<String> messages;
}
